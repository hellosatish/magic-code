package com.hellosatish.generator.metadata;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author satish-s
 * <pre>
 *	Model class representing Relationship.
 * </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationShip  implements Serializable{
	private static final long serialVersionUID = 1L;
	private SupportedRelationships relationType;
	private boolean bidirectional;
	private String parent;
	private String parentRelationshipName;
	private String parentJoinColumn;
	private String child;
	private String childRelationshipName;
	private String childJoinColumn;
}
