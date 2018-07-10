package com.hellosatish.generator.metadata;
/**
 * 
 * @author satish-s
 * <pre>
 *  Supported HTTP methods for rendering
 * </pre>
 */
public enum SupportedOperations {
	
	GET("get"), POST("post"), DELETE("delete"), PUT("put");
	private final String value;

	private SupportedOperations(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
