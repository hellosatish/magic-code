package com.hellosatish.generator.metadata;
/**
 * 
 * @author satish-s
 * <pre>
 *  Supported JPA relationships
 *  //TODO need to test on ManyToMany
 * </pre>
 */
public enum SupportedRelationships {

	ONETOONE("@OneToOne"), ONETOMANY("@OneToMany"), MANYTOMANY("@ManyToMany"), MANYTOONE("@ManyToOne");
	
	private final String value;

	private SupportedRelationships(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
