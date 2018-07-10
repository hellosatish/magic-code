package com.hellosatish.generator.metadata;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author satish-s
 * <pre>
 *  Meta data representing field's in case of entities or pojo's/DTO's
 *  </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private boolean primaryColumn;
	private SupportedDataTypes datatype;
	private SupportedDataFormats format;
	private String ref;

}
