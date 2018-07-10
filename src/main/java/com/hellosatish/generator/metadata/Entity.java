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
 *  <pre>
 *  	 Metadata represnting Entity object which shall represent DB-Entities after rendering
 *  </pre>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String idcolumn;
	private boolean dto;
	private boolean dbentity;
	private List<Field> fields = new ArrayList<Field>();
}
