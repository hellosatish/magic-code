package com.hellosatish.generator.metadata.extractors;

/**
 * 
 * @author satish-s
 * <pre>
 *  All the supported extractors. This class is kept to list all the supported formats from wherer
 *  we can extract metadata.
 *  	Currently we are only supporting Swagger 2.
 * </pre>
 */
public enum SupportedSourceFormats {
	SWAGGER("swagger");
	

	private final String value;

	private SupportedSourceFormats(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
