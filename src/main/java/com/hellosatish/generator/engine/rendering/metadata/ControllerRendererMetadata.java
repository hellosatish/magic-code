package com.hellosatish.generator.engine.rendering.metadata;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ControllerRendererMetadata extends ClassRendererMetadata  {
	private static final long serialVersionUID = 1L;
	
	private Set<UrlMappingMetadata> urlMappings = new HashSet<UrlMappingMetadata>();
	
	public void addURLMapping(UrlMappingMetadata mapping){
		this.urlMappings.add(mapping);
	}
}
