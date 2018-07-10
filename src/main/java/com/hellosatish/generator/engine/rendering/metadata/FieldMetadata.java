package com.hellosatish.generator.engine.rendering.metadata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author satish-s
 *<p>
 *	Metadata related to fields
 *<p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldMetadata implements GeneratorMetadata{
	
	private String name;
	private String dataType;
	private boolean primaryColumn;
	private boolean array;
	private boolean reference;
}
