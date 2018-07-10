package com.hellosatish.generator.metadata;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author satish-s
 * 
 *         <pre>
 *	 Meta data governing the properties of POM and the application-*.propertie/ application-*.yml files.
 *         </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configs implements Serializable {

	private static final long serialVersionUID = 1L;
	private int portNumber = 8080;
	private boolean swaggerEnabled;
	private boolean loadBalancingEnabled;
	private boolean cloudconfigEnabled;
	private boolean serviceRegistrationEnabled;
	private boolean securityEnabled;
	private boolean actuatorEnabled;
	private boolean dockerEnabled=Boolean.TRUE;
	private MetricsConfigurations metrics; 
	private SupportedDatabases devdb;
	private SupportedDatabases proddb;
}
