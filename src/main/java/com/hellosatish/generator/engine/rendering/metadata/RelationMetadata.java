package com.hellosatish.generator.engine.rendering.metadata;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author satish-s
 * <pre>
 * 	Metadata for rendering relationship between entities.
 * <pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationMetadata implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private boolean biDirectional;
	private Set<String> relationAnnotation = new HashSet<String>();
	private String relationShipName;
	private String joinColumn;
	private String child;
	private String parent;
	
	private boolean parentSide;	
	private boolean parentMany;
	private boolean childMany;
	
	public void addRealationAnnotation(String annotaion){
		this.relationAnnotation.add(annotaion);
	}
	
}
