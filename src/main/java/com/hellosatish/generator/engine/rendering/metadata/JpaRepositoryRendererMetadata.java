package com.hellosatish.generator.engine.rendering.metadata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JpaRepositoryRendererMetadata implements GeneratorMetadata{
	
	private String repositoryName;
	private String keyType;
	private String packageName;
	private String entitiesPackageName;

}
