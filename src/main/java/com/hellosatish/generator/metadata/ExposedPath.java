package com.hellosatish.generator.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author satish-s
 * <pre>
 * 	Metadata representing Exposed path by the rest controllers.
 *  We create new controller for each tag.
 * </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExposedPath implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String tag;
	private String url;
	private String operationName;
	private SupportedOperations operation;
	private List<Parameter>  parameters = new ArrayList<Parameter>();
	private Parameter response;
}
