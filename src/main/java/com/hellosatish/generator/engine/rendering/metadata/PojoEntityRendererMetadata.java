package com.hellosatish.generator.engine.rendering.metadata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class PojoEntityRendererMetadata  extends ClassRendererMetadata  {
	private static final long serialVersionUID = 1L;
	private boolean entityClass;
	protected Set<FieldMetadata> fields = new HashSet<>();
	
	private List<RelationMetadata> relations = new ArrayList<RelationMetadata>();
	
	public void addRelation(RelationMetadata relation){
		this.relations.add(relation); 
	}
}
