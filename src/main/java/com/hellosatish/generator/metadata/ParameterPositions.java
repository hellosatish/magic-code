package com.hellosatish.generator.metadata;
/**
 * 
 * @author satish-s
 * 
 * <pre>
 * 	 Supported parameter position for a rest request <b>(HEADER/BODY/PATH)</b>.
 * </pre>
 *
 */
public enum ParameterPositions {
	
	HEADER("header"), BODY("body"), PATH("path");
	
	private final String value;

	private ParameterPositions(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
