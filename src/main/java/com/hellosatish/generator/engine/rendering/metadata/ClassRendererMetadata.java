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
 *
 * <pre>
 *  The base metadata class for all generated java classes. entity, pojo, others
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassRendererMetadata implements GeneratorMetadata, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String className;
	protected String packageName;
	private boolean mainClass;
	protected Set<String> annotations = new HashSet<String>();
	protected Set<String> importStatements = new HashSet<>();
	
	public void addImportStatement(String importStatement){
		importStatements.add(importStatement);
	}

}
