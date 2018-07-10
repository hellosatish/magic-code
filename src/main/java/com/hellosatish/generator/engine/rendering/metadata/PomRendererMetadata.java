package com.hellosatish.generator.engine.rendering.metadata;

import com.hellosatish.generator.metadata.MetricsConfigurations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PomRendererMetadata implements GeneratorMetadata {

	private String groupId;
	private String artifactId;
	private String projectName;
	
	private boolean swaggerEnabled; 
	//
	private boolean loadBalancingEnabled;
	private boolean cloudconfigEnabled;
	private boolean serviceRegistrationEnabled;
	private boolean securityEnabled;
	private boolean actuatorEnabled;
	private boolean dockerEnabled;
	//
	private boolean isDevH2;
	private boolean isDevMySQL;
	private boolean isProdH2;
	private boolean isProdMySQL;
	
	private boolean cloudDependencyEnabled;
	
	private MetricsConfigurations metricsConfig;
	
	public boolean isCloudDependencyEnabled(){
		return loadBalancingEnabled || cloudconfigEnabled || serviceRegistrationEnabled;
	}

}
