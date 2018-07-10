package com.hellosatish.generator.metadata;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author satish-s
 *
 * Model class for metrics configurations
 *
 *"metrics":{
        "jmx":true,
        "graphite":false,
        "prometheus":false,
        "atlas":false,        
        "datadog":false
    } 
    
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetricsConfigurations implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean jmx;
	private boolean graphite;
	private boolean prometheus;
	private boolean atlas;
	private boolean datadog;
}
