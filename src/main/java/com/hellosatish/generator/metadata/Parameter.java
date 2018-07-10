package com.hellosatish.generator.metadata;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author satish-s
 * <pre>
 *  Metadata for parameters which can be at either of {@link ParameterPositions} in rest request
 * </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parameter implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 *  The name of the parameter this shall be capitalized in case of no primites i.e  ARRAY,OBJECT
	 */
	private String name;
	private String ref;
	private ParameterPositions position;
	private SupportedDataTypes type;
	private SupportedDataFormats format;
}
