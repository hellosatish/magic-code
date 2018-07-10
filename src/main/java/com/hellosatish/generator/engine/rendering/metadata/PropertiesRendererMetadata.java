package com.hellosatish.generator.engine.rendering.metadata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesRendererMetadata implements GeneratorMetadata {

	
	private String projectName;
	private int portnumber;
	protected boolean loadBalancingEnabled;
	protected boolean cloudconfigEnabled;
	protected boolean serviceRegistrationEnabled;
	protected boolean securityEnabled;
	protected boolean actuatorEnabled;
	
	private boolean renderH2Properties;
	private boolean renderMySQLProerties;
	
	private boolean cloudDependencyEnabled;
	
	public boolean isCloudDependencyEnabled(){
		return loadBalancingEnabled || cloudconfigEnabled || serviceRegistrationEnabled;
	}

}
