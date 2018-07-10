package com.hellosatish.generator.engine.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author satish-s
 * <pre>
 * 	This class shall objectify all the configurable properties and 
 *  this shall be only central place to access any of the configurable property 
 * </pre>
 *
 */
@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "magiccode", ignoreUnknownFields = false)
public class MagicCodeConfigurations {
	
	private String workspacelocation;
}
