package com.hellosatish.generator.engine.constants;

public interface ImportsConstants {
	String DISCOVERY_CLIENT = "org.springframework.cloud.client.discovery.EnableDiscoveryClient";
	String CLOUD_CONFIG = "";
	String WEB_SECURTY = "org.springframework.security.config.annotation.web.configuration.EnableWebSecurity";
	String ACCUATOR = "";
	String REFRESH_SCOPE = "org.springframework.cloud.context.config.annotation.RefreshScope";
	String SWAGGER_2="springfox.documentation.swagger2.annotations.EnableSwagger2";
	
	String IMPORT_LIST = "java.util.List";

	String IMPORT_ONETOONE = "javax.persistence.OneToOne";
	String IMPORT_ONETOMANY = "javax.persistence.OneToMany";
	String IMPORT_MANYTOONE = "javax.persistence.ManyToOne";
	String IMPORT_MANYTOMANY = "javax.persistence.ManyToMany";
	String IMPORT_JOINCOLUMN = "javax.persistence.JoinColumn";
	String IMPORT_FETCHTYPE = "javax.persistence.FetchType";
	String IMPORT_CASCADETYPE = "javax.persistence.CascadeType";

	String IMPORT_GET_MAPPING = "org.springframework.web.bind.annotation.GetMapping";
	String IMPORT_POST_MAPPING = "org.springframework.web.bind.annotation.PostMapping";
	String IMPORT_PUT_MAPPING = "org.springframework.web.bind.annotation.PutMapping";
	String IMPORT_DELETE_MAPPING = "org.springframework.web.bind.annotation.DeleteMapping";
	
	String IMPORT_PATH_VARIABLE = "org.springframework.web.bind.annotation.PathVariable";
	String IMPORT_HEADER_VARIABLE = "org.springframework.web.bind.annotation.RequestHeader";
	String IMPORT_REQUEST_BODY="org.springframework.web.bind.annotation.RequestBody";

	//monitoring related
	String IMPORT_TIMED = "io.micrometer.core.annotation.Timed";
}
