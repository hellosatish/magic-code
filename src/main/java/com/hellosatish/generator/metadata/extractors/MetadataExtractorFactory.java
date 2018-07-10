package com.hellosatish.generator.metadata.extractors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.hellosatish.generator.metadata.extractors.impl.SwaggerExtractor;

import io.swagger.models.Swagger;

/**
 * 
 * @author satish-s
 * <pre>
 * 	 The class responsible for creating the instance of proper {@link MetadataExtractorInterface} based on the format selected.
 *   Currently we only support {@link Swagger}
 * </pre>
 */
public final class MetadataExtractorFactory {
	
	private MetadataExtractorFactory(){}
	
	private static final Logger LOGGER = LogManager.getLogger(MetadataExtractorFactory.class);
	
	public static MetadataExtractorInterface getExtractor(SupportedSourceFormats selectedSourceFormat){
		MetadataExtractorInterface extractorInstance = null;
		switch (selectedSourceFormat) {
		case SWAGGER:
			extractorInstance = new SwaggerExtractor();
			break;
		default:
			LOGGER.error("Source Format not supported : "+selectedSourceFormat);
			break;
		}
		return extractorInstance;
	}

}
