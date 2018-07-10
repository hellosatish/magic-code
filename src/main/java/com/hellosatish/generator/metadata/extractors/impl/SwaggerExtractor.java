package com.hellosatish.generator.metadata.extractors.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellosatish.generator.metadata.Entity;
import com.hellosatish.generator.metadata.ExposedPath;
import com.hellosatish.generator.metadata.Field;
import com.hellosatish.generator.metadata.Parameter;
import com.hellosatish.generator.metadata.ParameterPositions;
import com.hellosatish.generator.metadata.ProjectGenerationMetadata;
import com.hellosatish.generator.metadata.SupportedDataFormats;
import com.hellosatish.generator.metadata.SupportedDataTypes;
import com.hellosatish.generator.metadata.SupportedOperations;
import com.hellosatish.generator.metadata.extractors.MetadataExtractorInterface;

import io.swagger.models.ArrayModel;
import io.swagger.models.HttpMethod;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.RefModel;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.HeaderParameter;
import io.swagger.models.parameters.PathParameter;
import io.swagger.models.parameters.QueryParameter;
import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.MapProperty;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.RefProperty;
import io.swagger.parser.SwaggerParser;
import io.swagger.util.Json;

/**
 * 
 * @author satish-s
 * 
 *         <pre>
 * This extractor is responsible for taking Swagger file path as input 
 * And then it will convert this input to the our generator format
 *         </pre>
 */
public class SwaggerExtractor implements MetadataExtractorInterface {

	private static final String swaggerPath = "E:/swagger1.json";

	/*public static void main(String[] args) {
		SwaggerExtractor ex = new SwaggerExtractor();
		ex.extractMetadata(swaggerPath);
	}*/

	private static final Logger logger = LoggerFactory.getLogger(SwaggerExtractor.class);

	@Override
	public ProjectGenerationMetadata extractMetadata(String inputFilePath) {
		ProjectGenerationMetadata project = new ProjectGenerationMetadata();

		Swagger swagger = new SwaggerParser().read(inputFilePath);
		project.setProjectName(swagger.getInfo().getTitle());
	
		project.setEntities(populateEntites(swagger.getDefinitions()));
		project.setExposedPath(populateExposedPaths(swagger.getPaths()));
		logger.debug("Final extarcted model :\n{}", Json.pretty(project));
		return project;
	}

	private List<ExposedPath> populateExposedPaths(Map<String, Path> paths) {
		List<ExposedPath> exposedPaths = new ArrayList<>();
		paths.forEach((pathName, pathDetails) -> {
			pathDetails.getOperationMap().forEach((name, opn) -> {
				ExposedPath exposedPath = new ExposedPath();
				exposedPath.setOperationName(opn.getOperationId());
				exposedPath.setTag(opn.getTags().get(0));
				exposedPath.setOperation(getOperationFromOperationName(name));
				exposedPath.setUrl(pathName);
				logger.debug("pathName : {}", pathName);
				exposedPath.setResponse(populatePathResponse(opn));

				exposedPaths.add(exposedPath);

				exposedPath.setParameters(populateParameters(opn));
			});
		});
		return exposedPaths;
	}

	private List<Parameter> populateParameters(Operation opn) {
		List<Parameter> parameters = new ArrayList<>();

		opn.getParameters().forEach(swaggerParameterDef -> {
			Parameter param = new Parameter();
			param.setPosition(getPositionFromSwaggerPositionDef(swaggerParameterDef.getIn()));
			param.setName(swaggerParameterDef.getName());

			if (swaggerParameterDef instanceof BodyParameter) {
				BodyParameter boodyParam = (BodyParameter) swaggerParameterDef;
				boodyParam.getSchema().getClass();
				Model model = boodyParam.getSchema();
				if(model instanceof ArrayModel){
					ArrayModel mdl = (ArrayModel)model;
					param.setRef(mdl.getReference());
					param.setFormat(SupportedDataFormats.ARRAY);
					param.setType(SupportedDataTypes.OBJECT);
					
				}else if(model instanceof RefModel){
					RefModel mdl = (RefModel)model;
					mdl.getSimpleRef();
					param.setRef(mdl.getSimpleRef());
					param.setFormat(SupportedDataFormats.OBJECT);
					param.setType(SupportedDataTypes.OBJECT);
				}else{
					logger.error("Process Model : {}",boodyParam.getSchema());
				}
				
			}else if (swaggerParameterDef instanceof PathParameter) {
				PathParameter pathParam = (PathParameter) swaggerParameterDef;
				String type = pathParam.getType();
				
				if(type.equalsIgnoreCase("integer") || type.equalsIgnoreCase("number")){
					param.setType(SupportedDataTypes.INT);
				}else if(type.equalsIgnoreCase("boolean") ){
					param.setType(SupportedDataTypes.BOOLEAN);
				}else if(type.equalsIgnoreCase("array") ){	//This will not be the case for path params as they should be in body
					param.setType(SupportedDataTypes.ARRAY);
				}else if(type.equalsIgnoreCase("object") ){ //This will not be the case for path params as they should be in body
					param.setType(SupportedDataTypes.OBJECT);
				}else if(type.equalsIgnoreCase("string") ){ 
					String format = pathParam.getFormat();
					if(format != null){
					if(format.equalsIgnoreCase(SupportedDataFormats.INT32.getValue())){
						param.setType(SupportedDataTypes.INT);
					}else if (format.equalsIgnoreCase(SupportedDataFormats.INT64.getValue())){
						param.setType(SupportedDataTypes.LONG);
					}else if (format.equalsIgnoreCase(SupportedDataFormats.FLOAT.getValue())){
						param.setType(SupportedDataTypes.FLOAT);
					}else if (format.equalsIgnoreCase(SupportedDataFormats.DOUBLE.getValue())){
						param.setType(SupportedDataTypes.DOUBLE);
					}else if (format.equalsIgnoreCase(SupportedDataFormats.DATE.getValue())){
						param.setType(SupportedDataTypes.DATE);
					}else if (format.equalsIgnoreCase(SupportedDataFormats.DATETIME.getValue())){
						param.setType(SupportedDataTypes.DATE);
					}
					}else{
						logger.error("Invalid format for string value pathParam: {} ",pathParam);
					}
				}
			}else if (swaggerParameterDef instanceof QueryParameter) {
				QueryParameter queryParam = (QueryParameter) swaggerParameterDef;
				String type = queryParam.getType();
				
				if(type.equalsIgnoreCase("integer") || type.equalsIgnoreCase("number")){
					param.setType(SupportedDataTypes.INT);
				}else if(type.equalsIgnoreCase("boolean") ){
					param.setType(SupportedDataTypes.BOOLEAN);
				}else if(type.equalsIgnoreCase("array") ){	//This will not be the case for path params as they should be in body
					param.setType(SupportedDataTypes.ARRAY);
				}else if(type.equalsIgnoreCase("object") ){ //This will not be the case for path params as they should be in body
					param.setType(SupportedDataTypes.OBJECT);
				}else if(type.equalsIgnoreCase("string") ){ 
					String format = queryParam.getFormat();
					if(format != null){

						if(format.equalsIgnoreCase(SupportedDataFormats.INT32.getValue())){
							param.setType(SupportedDataTypes.INT);
						}else if (format.equalsIgnoreCase(SupportedDataFormats.INT64.getValue())){
							param.setType(SupportedDataTypes.LONG);
						}else if (format.equalsIgnoreCase(SupportedDataFormats.FLOAT.getValue())){
							param.setType(SupportedDataTypes.FLOAT);
						}else if (format.equalsIgnoreCase(SupportedDataFormats.DOUBLE.getValue())){
							param.setType(SupportedDataTypes.DOUBLE);
						}else if (format.equalsIgnoreCase(SupportedDataFormats.DATE.getValue())){
							param.setType(SupportedDataTypes.DATE);
						}else if (format.equalsIgnoreCase(SupportedDataFormats.DATETIME.getValue())){
							param.setType(SupportedDataTypes.DATE);
						}
					}else{
						logger.error("Invalid format for string value queryParam: {} ",queryParam);
					}
				}
			}else if (swaggerParameterDef instanceof HeaderParameter) {
				logger.error("Parse and process header parameter");
			}
			parameters.add(param);
		});

		return parameters;
	}

	private List<Entity> populateEntites(Map<String, Model> definitions) {
		List<Entity> entities = new ArrayList<Entity>();

		definitions.forEach((modelName, modelInfo) -> {
			// System.out.println(" Model Name : " + modelName);
			Entity entity = new Entity();
			entity.setName(modelName);
			entity.setDto(true);
			modelInfo.getProperties().forEach((propertyName, propertyInfo) -> {
				Field field = new Field();
				field.setName(propertyName);
				populateDataType(propertyInfo, field);
				entity.getFields().add(field);
				// System.out.println("Prop Name :" + propertyName + " format :
				// " + format + " type :" + type+ " modelInfo.getReference();" +
				// modelInfo.getReference());
			});
			entities.add(entity);
		});
		return entities;
	}

	private static ParameterPositions getPositionFromSwaggerPositionDef(String position) {
		// position = position.toUpperCase();
		if (position.equalsIgnoreCase(ParameterPositions.HEADER.getValue())) {
			return ParameterPositions.HEADER;
		} else if (position.equalsIgnoreCase(ParameterPositions.BODY.getValue())) {
			return ParameterPositions.BODY;
		} else if (position.equalsIgnoreCase(ParameterPositions.PATH.getValue())) {
			return ParameterPositions.PATH;
		} else {
			logger.error("Parameter poistion not supported : {}", position);
			return null;
		}
	}

	private static SupportedOperations getOperationFromOperationName(HttpMethod method) {

		switch (method) {
		case GET:
			return SupportedOperations.GET;
		case POST:
			return SupportedOperations.POST;
		case PUT:
			return SupportedOperations.PUT;
		case DELETE:
			return SupportedOperations.DELETE;
		default:
			logger.error("Unsupported HTTP operation : {}", method);
			return null;
		}

	}

	private static Parameter populatePathResponse(Operation opn) {
		Parameter param = new Parameter();

		if (opn.getResponses().containsKey("200")) {
			Property response = opn.getResponses().get("200").getSchema();

			String type = response.getType();
			// The types STRING, INT, NUMBER, BOOLEAN seems not be used
			if (type.equalsIgnoreCase(SupportedDataTypes.STRING.getValue())) {
				param.setType(SupportedDataTypes.STRING);
			} else if (type.equalsIgnoreCase("integer")) {
				param.setType(SupportedDataTypes.INT);
			} else if (type.equalsIgnoreCase(SupportedDataTypes.NUMBER.getValue())) {
				param.setType(SupportedDataTypes.INT);
			} else if (type.equalsIgnoreCase(SupportedDataTypes.BOOLEAN.getValue())) {
				param.setType(SupportedDataTypes.BOOLEAN);
			} else if (type.equalsIgnoreCase(SupportedDataTypes.ARRAY.getValue())) {
				param.setType(SupportedDataTypes.ARRAY);
				ArrayProperty arrayProperty = (ArrayProperty) response;
				RefProperty ref = (RefProperty) arrayProperty.getItems();
				param.setRef(ref.getSimpleRef());
				param.setType(SupportedDataTypes.OBJECT);
				param.setFormat(SupportedDataFormats.ARRAY);
			} else if (type.equalsIgnoreCase(SupportedDataTypes.OBJECT.getValue())) {
				MapProperty mapProp = (MapProperty) response;
				String actualType = mapProp.getAdditionalProperties().getType();
				param.setType(SupportedDataTypes.STRING);
			} else if (type.equalsIgnoreCase("ref")) {
				RefProperty ref = (RefProperty) response;
				param.setType(SupportedDataTypes.OBJECT);
				param.setFormat(SupportedDataFormats.OBJECT);
				param.setRef(ref.getSimpleRef());
			} else {
				logger.error("Invalid property type : {} ", type);
			}

		} else {
			param.setType(SupportedDataTypes.Void);
		}
		return param;
	}

	private static void populateDataType(Property propertyInfo, Field field) {
		String type = propertyInfo.getType();
		if (type.equalsIgnoreCase(SupportedDataTypes.STRING.getValue())) {
			field.setDatatype(SupportedDataTypes.STRING);

		} else if (type.equalsIgnoreCase("integer")) {
			field.setDatatype(SupportedDataTypes.INT);
		} else if (type.equalsIgnoreCase(SupportedDataTypes.NUMBER.getValue())) {
			field.setDatatype(SupportedDataTypes.INT);

		} else if (type.equalsIgnoreCase(SupportedDataTypes.BOOLEAN.getValue())) {
			field.setDatatype(SupportedDataTypes.BOOLEAN);
		} else if (type.equalsIgnoreCase(SupportedDataTypes.ARRAY.getValue())) {
			field.setDatatype(SupportedDataTypes.ARRAY);
			ArrayProperty arrayRef = (ArrayProperty) propertyInfo;
			if (arrayRef.getItems() instanceof RefProperty) {
				RefProperty ref = (RefProperty) arrayRef.getItems();
				field.setRef(ref.getSimpleRef());
			} else {
				field.setRef(arrayRef.getItems().getType());
			}

		} else if (type.equalsIgnoreCase(SupportedDataTypes.OBJECT.getValue())) {

		} else if (type.equalsIgnoreCase("ref")) {
			RefProperty ref = (RefProperty) propertyInfo;
			field.setDatatype(SupportedDataTypes.OBJECT);
			field.setRef(ref.getSimpleRef());

		} else {
			logger.error("Invalid property");
		}
	}

}
