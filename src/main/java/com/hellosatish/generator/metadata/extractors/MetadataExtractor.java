package com.hellosatish.generator.metadata.extractors;

import static com.hellosatish.generator.engine.constants.AnnotationsConstant.DELETE_MAPPING;
import static com.hellosatish.generator.engine.constants.AnnotationsConstant.GET_MAPPING;
import static com.hellosatish.generator.engine.constants.AnnotationsConstant.POST_MAPPING;
import static com.hellosatish.generator.engine.constants.AnnotationsConstant.PUT_MAPPING;
import static com.hellosatish.generator.engine.constants.AnnotationsConstant.TIMED;
import static com.hellosatish.generator.engine.constants.ImportsConstants.IMPORT_CASCADETYPE;
import static com.hellosatish.generator.engine.constants.ImportsConstants.IMPORT_DELETE_MAPPING;
import static com.hellosatish.generator.engine.constants.ImportsConstants.IMPORT_FETCHTYPE;
import static com.hellosatish.generator.engine.constants.ImportsConstants.IMPORT_GET_MAPPING;
import static com.hellosatish.generator.engine.constants.ImportsConstants.IMPORT_HEADER_VARIABLE;
import static com.hellosatish.generator.engine.constants.ImportsConstants.IMPORT_JOINCOLUMN;
import static com.hellosatish.generator.engine.constants.ImportsConstants.IMPORT_LIST;
import static com.hellosatish.generator.engine.constants.ImportsConstants.IMPORT_MANYTOMANY;
import static com.hellosatish.generator.engine.constants.ImportsConstants.IMPORT_MANYTOONE;
import static com.hellosatish.generator.engine.constants.ImportsConstants.IMPORT_ONETOMANY;
import static com.hellosatish.generator.engine.constants.ImportsConstants.IMPORT_ONETOONE;
import static com.hellosatish.generator.engine.constants.ImportsConstants.IMPORT_PATH_VARIABLE;
import static com.hellosatish.generator.engine.constants.ImportsConstants.IMPORT_POST_MAPPING;
import static com.hellosatish.generator.engine.constants.ImportsConstants.IMPORT_PUT_MAPPING;
import static com.hellosatish.generator.engine.constants.ImportsConstants.IMPORT_REQUEST_BODY;
import static com.hellosatish.generator.engine.constants.ImportsConstants.IMPORT_TIMED;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.hellosatish.generator.engine.constants.AnnotationsConstant;
import com.hellosatish.generator.engine.constants.ImportsConstants;
import com.hellosatish.generator.engine.rendering.metadata.ClassRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.ConfigurationRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.ControllerRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.FieldMetadata;
import com.hellosatish.generator.engine.rendering.metadata.JpaRepositoryRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.PararmeterMetadata;
import com.hellosatish.generator.engine.rendering.metadata.PojoEntityRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.PomRendererMetadata;
import com.hellosatish.generator.engine.rendering.metadata.RelationMetadata;
import com.hellosatish.generator.engine.rendering.metadata.UrlMappingMetadata;
import com.hellosatish.generator.engine.util.ProjectPathContext;
import com.hellosatish.generator.metadata.Configs;
import com.hellosatish.generator.metadata.Entity;
import com.hellosatish.generator.metadata.ExposedPath;
import com.hellosatish.generator.metadata.Parameter;
import com.hellosatish.generator.metadata.ParameterPositions;
import com.hellosatish.generator.metadata.ProjectGenerationMetadata;
import com.hellosatish.generator.metadata.SupportedDataFormats;
import com.hellosatish.generator.metadata.SupportedDataTypes;
import com.hellosatish.generator.metadata.SupportedDatabases;
import com.hellosatish.generator.metadata.SupportedOperations;
import com.hellosatish.generator.metadata.SupportedRelationships;
/**
 * 
 * @author satish-s
 * <pre>
 * 	 The class which shall extract the meta-data for different renderer's
 * </pre>
 */
public final class MetadataExtractor {
	private MetadataExtractor(){}

	private static final Logger LOGGER = LoggerFactory.getLogger(MetadataExtractor.class);

	public static PomRendererMetadata extractPomMetadata(ProjectGenerationMetadata project) {
		Configs cfg = project.getConfigs();
		PomRendererMetadata pomMd = new PomRendererMetadata();
		pomMd.setSwaggerEnabled(cfg.isSwaggerEnabled());
		pomMd.setActuatorEnabled(cfg.isActuatorEnabled());
		pomMd.setArtifactId(project.getArtifactId());
		pomMd.setCloudconfigEnabled(cfg.isCloudconfigEnabled());
		pomMd.setDockerEnabled(cfg.isDockerEnabled());
		pomMd.setGroupId(project.getGroupId());
		pomMd.setLoadBalancingEnabled(cfg.isLoadBalancingEnabled());
		pomMd.setProjectName(project.getProjectName());
		pomMd.setSecurityEnabled(cfg.isSecurityEnabled());
		pomMd.setServiceRegistrationEnabled(cfg.isServiceRegistrationEnabled());
		if (cfg.getDevdb().equals(SupportedDatabases.H2)) {
			pomMd.setDevH2(true);
		}
		if (cfg.getDevdb().equals(SupportedDatabases.MYSQL)) {
			pomMd.setDevMySQL(true);
		}
		if (cfg.getProddb().equals(SupportedDatabases.H2)) {
			LOGGER.info("Use of H2 in production environment is not recomended");
			pomMd.setProdH2(true);
		}
		if (cfg.getProddb().equals(SupportedDatabases.MYSQL)) {
			pomMd.setProdMySQL(true);
		}
		
		//metrics
			pomMd.setMetricsConfig(cfg.getMetrics());

			return pomMd;
	}

	public static ClassRendererMetadata extractDataForMainClass(ProjectGenerationMetadata project) {
		ClassRendererMetadata md = new ClassRendererMetadata();
		Configs cfg = project.getConfigs();

		Set<String> imports = new HashSet<>();
		Set<String> annotations = new HashSet<>();

		if (cfg.isSecurityEnabled()) {
			imports.add(ImportsConstants.WEB_SECURTY);
			annotations.add(AnnotationsConstant.WEB_SECURITY);
		}
		if (cfg.isServiceRegistrationEnabled()) {
			imports.add(ImportsConstants.DISCOVERY_CLIENT);
			annotations.add(AnnotationsConstant.DISCOVERY_CLIENT);
		}
		
		if(cfg.isSwaggerEnabled()){
			imports.add(ImportsConstants.SWAGGER_2);
			annotations.add(AnnotationsConstant.ENABLE_SWAGGER_2);
		}

		md.setMainClass(true);
		md.setClassName(StringUtils.capitalize(project.getProjectName()));
		md.setPackageName(project.getBasePackage());
		md.setAnnotations(annotations);
		md.setImportStatements(imports);
		return md;
	}

	public static ConfigurationRendererMetadata extractConfigurationMetadata(ProjectGenerationMetadata project, ProjectPathContext pathContext) {
		// extract pom information
		Configs cfg = project.getConfigs();
		ConfigurationRendererMetadata configMetadata = new ConfigurationRendererMetadata();
		configMetadata.setArtifactId(project.getArtifactId());
		configMetadata.setActuatorEnabled(cfg.isActuatorEnabled());
		configMetadata.setCloudconfigEnabled(cfg.isCloudconfigEnabled());
		configMetadata.setDockerEnabled(cfg.isDockerEnabled());
		configMetadata.setLoadBalancingEnabled(cfg.isLoadBalancingEnabled());
		configMetadata.setProjectName(project.getProjectName());
		configMetadata.setSecurityEnabled(cfg.isSecurityEnabled());
		configMetadata.setServiceRegistrationEnabled(cfg.isServiceRegistrationEnabled());
		configMetadata.setPathContext(pathContext);
		configMetadata.setSwaggerEnabled(cfg.isSwaggerEnabled());
		configMetadata.setProjectRoot(pathContext.getProjectRootPath());
		configMetadata.setBasePackageName(pathContext.getBasePackage());
		configMetadata.setProjectSourceRoot(pathContext.getMainJavaPath());
		configMetadata.setMetrics(cfg.getMetrics());
		return configMetadata;
	}

	public static List<PojoEntityRendererMetadata> extractPojoMetadata(ProjectGenerationMetadata project) {
		return  project.getEntities().parallelStream().filter(Entity::isDto)
				.map(entity -> {
					PojoEntityRendererMetadata pojo = new PojoEntityRendererMetadata();

					pojo.setClassName(StringUtils.capitalize(entity.getName()));

					// extract fields info
					Set<FieldMetadata> fields = new HashSet<>();
					entity.getFields().forEach(fld -> {
						FieldMetadata fldMd = new FieldMetadata();
						fldMd.setName(StringUtils.uncapitalize(fld.getName()));
						LOGGER.info("Entity name  "+entity.getName()+ "\tfldMd.getName() "+fldMd.getName() +"\t"+fld.getDatatype());
						if (fld.getDatatype().equals(SupportedDataTypes.OBJECT)) {
							fldMd.setReference(true);
							fldMd.setDataType(StringUtils.capitalize(fld.getRef()));
						} else if (fld.getDatatype().equals(SupportedDataTypes.ARRAY)) {
							fldMd.setReference(true);
							fldMd.setArray(true);
							fldMd.setDataType(StringUtils.capitalize(fld.getRef()));
							pojo.getImportStatements().add(ImportsConstants.IMPORT_LIST);
						} else {
							fldMd.setDataType(fld.getDatatype().getValue());
						}
						fldMd.setPrimaryColumn(fld.isPrimaryColumn());
						fields.add(fldMd);
					});
					pojo.setFields(fields);
					return pojo;
				}).collect(Collectors.toList());

	}

	public static List<JpaRepositoryRendererMetadata> extractJpaRepositoryMetadata(ProjectGenerationMetadata project, ProjectPathContext packagaePathResolver) {
		return project.getEntities().stream().filter(Entity::isDbentity).map(entity -> {
			return new JpaRepositoryRendererMetadata(StringUtils.capitalize(entity.getName()), "Integer",
					packagaePathResolver.getDbRepositoryPackage(), packagaePathResolver.getDbEntitiesPackage());
		}).collect(Collectors.toList());
	}

	// extract relationships/associations info
	public static List<PojoEntityRendererMetadata> extractEntityMetadata(ProjectGenerationMetadata project) {
		Map<String, PojoEntityRendererMetadata> entityNameMap = new HashMap<>();

		// Generate fields metadata for each DB entity;
		project.getEntities().parallelStream().forEach(entity -> {
			if (entity.isDbentity()) {

				PojoEntityRendererMetadata pojo = new PojoEntityRendererMetadata();

				pojo.setClassName(StringUtils.capitalize(entity.getName()));
				pojo.setEntityClass(true);

				Set<FieldMetadata> fields = new HashSet<>();
				// TODO this loop can be used to handle composite keys
				entity.getFields().forEach(fld -> {
					FieldMetadata fldMd = new FieldMetadata();
					fldMd.setName(StringUtils.uncapitalize(fld.getName()));
					if (fld.getDatatype().equals(SupportedDataTypes.OBJECT)) {
						LOGGER.error("Entities should not contain Object type as fields unless this is intentional");
						fldMd.setReference(true);
						fldMd.setDataType(StringUtils.capitalize(fld.getRef()));
					} else if (fld.getDatatype().equals(SupportedDataTypes.ARRAY)) {
						LOGGER.error("Entities should not contain Array type as fields unless this is intentional");
						fldMd.setReference(true);
						fldMd.setArray(true);
						fldMd.setDataType(StringUtils.capitalize(fld.getRef()));
						pojo.getImportStatements().add(ImportsConstants.IMPORT_LIST);
					} else {
						fldMd.setDataType(fld.getDatatype().getValue());
					}
					fldMd.setPrimaryColumn(fld.isPrimaryColumn());
					fields.add(fldMd);
				});
				pojo.setFields(fields);

				entityNameMap.put(pojo.getClassName(), pojo);
				// return pojo;

			} else {
				LOGGER.error("Not a db entity : {}",entity.getName());
			}
		});

		// extract relationships/associations info
		project.getRelationships().parallelStream().forEach(relationMd -> {

			boolean isBidirectional = relationMd.isBidirectional();
			String parent = StringUtils.capitalize(relationMd.getParent());
			String child = StringUtils.capitalize(relationMd.getChild());
			String parentFieldName = StringUtils.uncapitalize(relationMd.getParentRelationshipName());
			String childFieldName = StringUtils.uncapitalize(relationMd.getChildRelationshipName());
			SupportedRelationships association = relationMd.getRelationType();

			String parentJoinCol = relationMd.getParentJoinColumn();
			String childJoinCol = relationMd.getChildJoinColumn();

			// Handle parent side relationship
			RelationMetadata p = new RelationMetadata();
			p.setChild(child);
			p.setParentSide(true);
			p.setBiDirectional(isBidirectional);
			p.setRelationShipName(parentFieldName);

			// Handle child side relationship
			RelationMetadata c = new RelationMetadata();
			c.setParent(parent);
			c.setParentSide(false);
			c.setBiDirectional(isBidirectional);
			c.setRelationShipName(childFieldName);

			PojoEntityRendererMetadata parentEntity = entityNameMap.get(parent);
			PojoEntityRendererMetadata childEntity = entityNameMap.get(child);

			switch (association) {
			case ONETOONE:
				p.setChildMany(false);
				c.setParentMany(false);

				if (p.isBiDirectional()) {
					p.addRealationAnnotation(association.getValue() + "(mappedBy = \"" + childFieldName
							+ "\", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)");
					parentEntity.addImportStatement(IMPORT_CASCADETYPE);
					parentEntity.addImportStatement(IMPORT_FETCHTYPE);

					c.addRealationAnnotation(association.getValue() + "(fetch = FetchType.LAZY)");
					c.addRealationAnnotation("@JoinColumn(name = \"" + childJoinCol + "\")");
					childEntity.addRelation(c);

					childEntity.addImportStatement(IMPORT_FETCHTYPE);
					childEntity.addImportStatement(IMPORT_JOINCOLUMN);
					childEntity.addImportStatement(IMPORT_ONETOONE);
				} else {
					p.addRealationAnnotation(association.getValue());
					p.addRealationAnnotation("@JoinColumn(name = \"" + parentJoinCol + "\")");

					parentEntity.addImportStatement(IMPORT_JOINCOLUMN);
				}
				parentEntity.addRelation(p);
				parentEntity.addImportStatement(IMPORT_ONETOONE);

				break;
			case ONETOMANY:
				p.setChildMany(true);
				c.setParentMany(false);

				if (p.isBiDirectional()) {
					p.addRealationAnnotation(association.getValue() + "(mappedBy=\"" + childFieldName
							+ "\", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)");

					parentEntity.addImportStatement(IMPORT_ONETOMANY);
					parentEntity.addImportStatement(IMPORT_CASCADETYPE);
					parentEntity.addImportStatement(IMPORT_FETCHTYPE);
					parentEntity.addImportStatement(IMPORT_LIST);

					c.addRealationAnnotation(SupportedRelationships.MANYTOONE.getValue() + "(fetch = FetchType.LAZY)");
					c.addRealationAnnotation("@JoinColumn(name=\"" + childJoinCol + "\", nullable=false)");
					childEntity.addRelation(c);

					childEntity.addImportStatement(IMPORT_FETCHTYPE);
					childEntity.addImportStatement(IMPORT_JOINCOLUMN);
					childEntity.addImportStatement(IMPORT_MANYTOONE);
				} else {
					p.addRealationAnnotation(
							association.getValue() + "(cascade = CascadeType.ALL, orphanRemoval = true)");
					p.addRealationAnnotation("@JoinColumn(name=\"" + childJoinCol + "\", nullable=false)");

					parentEntity.addImportStatement(IMPORT_JOINCOLUMN);
					parentEntity.addImportStatement(IMPORT_ONETOMANY);
					parentEntity.addImportStatement(IMPORT_CASCADETYPE);

				}
				parentEntity.addRelation(p);
				break;
			case MANYTOMANY:
				p.setChildMany(true);
				c.setParentMany(true);

				if (p.isBiDirectional()) {
					p.addRealationAnnotation(association.getValue() + "(mappedBy = \"" + childFieldName + "\")");
					parentEntity.addRelation(p);
					c.addRealationAnnotation(association.getValue() + "(fetch = FetchType.LAZY)");
					c.addRealationAnnotation("@JoinColumn(name = \"" + childJoinCol + "\")");
					childEntity.addRelation(c);

				} else {
					p.addRealationAnnotation(association.getValue());
					p.addRealationAnnotation("@JoinColumn(name = \"" + parentJoinCol + "\")");
					parentEntity.addRelation(p);
				}
				parentEntity.addImportStatement(IMPORT_LIST);
				parentEntity.addImportStatement(IMPORT_MANYTOMANY);
				childEntity.addImportStatement(IMPORT_LIST);
				childEntity.addImportStatement(IMPORT_MANYTOMANY);
				break;
			case MANYTOONE: // TODO validate and implement
				p.setChildMany(false);
				p.addRealationAnnotation(association.getValue());
				c.setParentMany(true);
				c.addRealationAnnotation(SupportedRelationships.ONETOMANY.getValue());
				break;
			default:
				break;
			}

		});

		return new ArrayList<>(entityNameMap.values());
	}

	public static List<ControllerRendererMetadata> extractControllerMetadata(ProjectGenerationMetadata project, ProjectPathContext pathContext) {
		List<ControllerRendererMetadata> controllerMd = new ArrayList<>();
		Map<String, List<ExposedPath>> groupByTag = project.getExposedPath().parallelStream()
				.collect(Collectors.groupingBy(ExposedPath::getTag));

		groupByTag.forEach((tagName, exposedPathsForTag) -> {
			ControllerRendererMetadata md = new ControllerRendererMetadata();
			md.setPackageName(project.getBasePackage());
			md.setClassName(StringUtils.capitalize(tagName));
			exposedPathsForTag.forEach(path -> {
				UrlMappingMetadata urlMd = new UrlMappingMetadata();
				urlMd.setOperationName(path.getOperationName());
				String ops = path.getOperation().getValue();
				
				if (project.getConfigs() != null) {
					md.addImportStatement(IMPORT_TIMED);
				}
				//populate response
				Parameter resp = path.getResponse();
					if(resp.getType().equals(SupportedDataTypes.OBJECT)){
						String responseType = StringUtils.capitalize(resp.getRef());
						 md.addImportStatement(pathContext.getDtoPackage()+"."+responseType);
						if(resp.getFormat()!= null && resp.getFormat().equals(SupportedDataFormats.ARRAY)){
							md.addImportStatement(IMPORT_LIST);
							urlMd.setResponseType("List<"+responseType+">");
						}else{
							urlMd.setResponseType(responseType);
						}
					}else{
						urlMd.setResponseType(StringUtils.capitalize(resp.getType().getValue()));
					}
				
				//populate parameters
				path.getParameters().forEach(param -> {
					PararmeterMetadata pm = new PararmeterMetadata();
					pm.setName(param.getName());
					ParameterPositions position = param.getPosition();
					if (param.getType().equals(SupportedDataTypes.OBJECT)) {
						String parameterType = StringUtils.capitalize(param.getRef());
						 md.addImportStatement(pathContext.getDtoPackage()+"."+parameterType);
						 
						if (param.getFormat().equals(SupportedDataFormats.ARRAY)){
							md.addImportStatement(IMPORT_LIST);
							pm.setType("List<" +parameterType + ">");
						}
						else{
							pm.setType(parameterType);
						}
					} else {
						pm.setType(StringUtils.uncapitalize(param.getType().getValue()));
					}

					switch (position) {
					case HEADER:
						md.addImportStatement(IMPORT_HEADER_VARIABLE);
						urlMd.addHeaderParam(pm);
						break;
					case BODY:
						md.addImportStatement(IMPORT_REQUEST_BODY);
						urlMd.setBodyParam(pm);
						break;
					case PATH:
						md.addImportStatement(IMPORT_PATH_VARIABLE);
						urlMd.addPathParam(pm);
						break;

					default:
						LOGGER.error("Invalid parameter position skipping this");
						break;
					}

				});
				
				urlMd.setUrl(path.getUrl());	
				if (project.getConfigs() != null) {
					urlMd.addMethodAnnotation(TIMED);
				} 
				if (ops.equalsIgnoreCase(SupportedOperations.GET.getValue())) {
					urlMd.setUrlAnnotations(GET_MAPPING);
					md.addImportStatement(IMPORT_GET_MAPPING);
				} else if (ops.equalsIgnoreCase(SupportedOperations.POST.getValue())) {
					urlMd.setUrlAnnotations(POST_MAPPING);
					md.addImportStatement(IMPORT_POST_MAPPING);
				} else if (ops.equalsIgnoreCase(SupportedOperations.PUT.getValue())) {
					urlMd.setUrlAnnotations(PUT_MAPPING);
					md.addImportStatement(IMPORT_PUT_MAPPING);
				} else if (ops.equalsIgnoreCase(SupportedOperations.DELETE.getValue())) {
					urlMd.setUrlAnnotations(DELETE_MAPPING);
					md.addImportStatement(IMPORT_DELETE_MAPPING);
				} else {
					LOGGER.equals("Invalid operation this shall be skipped while URL mapping generation : " + ops);
				}
				md.addURLMapping(urlMd);
			});
			controllerMd.add(md);
		});

		return controllerMd;
	}

}
