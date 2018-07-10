package com.hellosatish.generator.engine.util;

import com.hellosatish.generator.engine.config.generator.SpringBootGenerator;

import lombok.Getter;
import lombok.ToString;

/**
 * 
 * @author satish-s
 * 
 *   <pre>
 *	 This class is intended to help find the different packages and paths for files.
 *	 TODO A super interface can be made which will unify the path/package resolvation process in case we plan to have multiple generators.
 *   We currently focusing on single generator {@link SpringBootGenerator} so this is implemented as class.
 *    </pre>
 */
@Getter
@ToString
public final class ProjectPathContext {
	// Directory Paths
	public String projectName;
	public String projectRootPath;
	public String mainResourcesPath;
	public String testResourcesPath;
	public String mainJavaPath;
	public String testJavaPath;

	// project related folders
	public String basePackage;
	public String configPackage;
	public String dtoPackage;
	public String utilityPackage;
	public String servicesPackage;
	public String dbEntitiesPackage;
	public String dbRepositoryPackage;
	public String controllersPackage;

	
	public ProjectPathContext(String projectName, String projectRootPath, String basePackage) {
		super();
		this.projectName = projectName;
		this.projectRootPath = projectRootPath;
		this.mainResourcesPath = projectRootPath+"/src/main/resources/";
		this.testResourcesPath = projectRootPath+"/src/test/resources/";
		this.mainJavaPath = projectRootPath+"/src/main/java/";
		this.testJavaPath = projectRootPath+"/src/test/java/";
		
		this.basePackage = basePackage;
		this.configPackage = basePackage+".config";
		this.dtoPackage = basePackage+".dto";
		this.utilityPackage = basePackage+".util";
		this.servicesPackage = basePackage+".services";
		this.dbEntitiesPackage = basePackage+".db.entities";
		this.dbRepositoryPackage = basePackage+".db.repositories";
		this.controllersPackage = basePackage+".web.rest";
	}
	

	public String getBasePackagePath() {
		return mainJavaPath+basePackage.replace(".", "/");
	}

	public String getConfigPackagePath() {
		return this.mainJavaPath+configPackage.replace(".", "/");
	}

	public String getDtoPackagePath() {
		return this.mainJavaPath+dtoPackage.replace(".", "/");
	}

	public String getUtilityPackagePath() {
		return this.mainJavaPath+utilityPackage.replace(".", "/");
	}

	public String getServicesPackagePath() {
		return this.mainJavaPath+servicesPackage.replace(".", "/");
	}

	public String getDbEntitiesPackagePath() {
		return this.mainJavaPath+dbEntitiesPackage.replace(".", "/");
	}

	public String getDbRepositoryPackagePath() {
		return this.mainJavaPath+dbRepositoryPackage.replace(".", "/");
	}

	public String getControllersPackagePath() {
		return this.mainJavaPath+controllersPackage.replace(".", "/");
	}



}
