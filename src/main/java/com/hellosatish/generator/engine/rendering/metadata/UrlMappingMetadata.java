package com.hellosatish.generator.engine.rendering.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author satish-s
 * 
 *         <pre>
 *         Meta data class representing single URL mapping
 * 
 *         <pre>
 */
@Data
@NoArgsConstructor
public class UrlMappingMetadata implements Serializable {

	private static final long serialVersionUID = 1L;
	private String url;
	private String urlAnnotations;
	private Set<String> methodAnnotations = new HashSet<>();
	private String operationName;
	private String responseType;
	private List<PararmeterMetadata> headerParams = new ArrayList<>();
	private List<PararmeterMetadata> pathParams = new ArrayList<>();
	private PararmeterMetadata bodyParam;

	public void addMethodAnnotation(String annotation){
		this.methodAnnotations.add(annotation);
	}
	public void addHeaderParam(PararmeterMetadata parameter) {
		this.headerParams.add(parameter);
	}

	public void addPathParam(PararmeterMetadata parameter) {
		this.pathParams.add(parameter);
	}

	public boolean hadHeaderParam() {
		return headerParams.size() > 0 ? true : false;
	}

	public boolean hadPathParam() {
		return pathParams.size() > 0 ? true : false;
	}
	
	public String getURL(){
		StringBuffer parsedURL = new StringBuffer(url);
		
		if (!pathParams.isEmpty()){
			for(PararmeterMetadata pmd:pathParams){
				parsedURL.append("/{"+pmd.getName()+"}");
			}
		}
		return parsedURL.toString();
		
	}
}
