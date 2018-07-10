package com.hellosatish.generator.engine.rendering.metadata;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author satish-s
 * <pre>
 * 	 Parameter metadata for controller this shall represent both the request and response types.
 * </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PararmeterMetadata implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String type;
	
}
