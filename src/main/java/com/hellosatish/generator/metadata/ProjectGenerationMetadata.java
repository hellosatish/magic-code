package com.hellosatish.generator.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author satish-s
 * <pre>
 *  Root model object of the meta-data required by our renderer's to render the source code and files.
 *   This represents the whole project and all the supported operations by the generator engine
 * </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ProjectGenerationMetadata implements Serializable {

	private static final long serialVersionUID = 1L;

	private String projectName;
	private String groupId;
	private String artifactId;
	private String basePackage;
	private Configs configs;
	private List<Entity> entities = new ArrayList<Entity>();
	private Set<String> tags = new HashSet<String>();
	private List<ExposedPath> exposedPath = new ArrayList<ExposedPath>();
	private List<RelationShip> relationships = new ArrayList<RelationShip>();
	

}
