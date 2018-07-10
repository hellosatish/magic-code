package com.hellosatish.generator.engine.rendering.metadata;

import com.hellosatish.generator.engine.util.ProjectPathContext;
import com.hellosatish.generator.metadata.MetricsConfigurations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author satish-s
 *
 * <pre>
 *  The meta data class for rendering configuration related classes
 *  </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ConfigurationRendererMetadata implements GeneratorMetadata {
	
	private String basePackageName;
	private String projectRoot;
	private String projectSourceRoot;
	private String projectName;
	private String artifactId;
	private boolean swaggerEnabled;
	private boolean loadBalancingEnabled;
	private boolean cloudconfigEnabled;
	private boolean serviceRegistrationEnabled;
	private boolean securityEnabled;
	private boolean actuatorEnabled;
	private MetricsConfigurations metrics; 
	private boolean dockerEnabled=Boolean.TRUE;
	
	private ProjectPathContext pathContext;
	
	private boolean renderSwagger, renderMetrics;
	
	public void enableMetricsRendering(){
		renderMetrics = true;
		renderSwagger = false;
	}
	
	public void enableSwaggerRendering(){
		renderSwagger = true;
		renderMetrics = false;
		
	}
}