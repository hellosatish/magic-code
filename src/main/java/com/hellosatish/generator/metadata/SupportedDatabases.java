package com.hellosatish.generator.metadata;
/**
 * 
 * @author satish-s
 * <pre>
 * 	List of supported databases 
 *  These shall be used while generating data sources properties
 * </pre>
 */
public enum SupportedDatabases {
	H2("h2db"), MYSQL("mysql");
	private final String value;

	private SupportedDatabases(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
