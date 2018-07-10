package com.hellosatish.generator.metadata;
/**
 * 
 * @author satish-s
 * <pre>
 * 	Supported dta formats for fields.
 * </pre>
 */
public enum SupportedDataFormats {
	INT32("int32"), INT64("int64"),  LONG("long"),FLOAT("float"), DOUBLE("double"), BYTE("byte"), DATE("date"), DATETIME("datetime"),OBJECT("object"),ARRAY("array");

	private final String value;

	private SupportedDataFormats(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
