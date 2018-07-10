package com.hellosatish.generator.metadata;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author satish-s
 *  <pre> 
 *  	<b>Unused</b>
 *  	Was kept for separating the API response for each exposed URL. 
 *  	But the same has been handled using the Parameter itself
 *  </pre>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private SupportedDataTypes type;
	private SupportedDataFormats format;
	private String reference;

}
