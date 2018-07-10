package com.hellosatish.generator.metadata;
/**
 * 
 * @author satish-s
 * <pre>
 *  Supported data types for fields of the DTO or the db-dntities.
 *  <b> make sure you do not use ARRAY and OBJECT as data type for fields while defining entities.</b> 
 * </pre>
 */
public enum SupportedDataTypes {
	
	STRING("String"), NUMBER("number"),LONG("long"), DOUBLE("double"),DATE("date"),FLOAT("float"),INT("int"), BOOLEAN("boolean"), ARRAY("array"), OBJECT("object"), Void("Void");

	private final String value;

	private SupportedDataTypes(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
