package com.hellosatish.generator.engine.util;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author satish-s
 * <p>
 * 	Utility class to help with file/directory operations
 * </p>
 */
public class ProjectFilesHelper {
	
	private final static Logger logger = LoggerFactory.getLogger( ProjectFilesHelper.class);
	/**
	 * 
	 * @param rootPath Parent directory 
	 * @param directoriesToCreate Path which needs to be created
	 * @return true/false denoting success failure of operation
	 * 
	 *  Shall create all the directories including parent directories if required
	 */
	public static boolean createDirectories(String rootPath, String directoriesToCreate) {
		try {
			Files.createDirectories(Paths.get(rootPath, directoriesToCreate));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 
	 * @param directoryPath Path for the directory
	 * @return
	 * 
	 * Shall create the directory including all parent directories if required
	 */
	public static boolean createDirectory(String directoryPath) {
		try {
			Files.createDirectories(Paths.get(directoryPath));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param basePackagePath path for base package
	 * @return True is structure create otherwise returns false
	 */
	public static boolean createBaseStructureForJavaProject( ProjectPathContext javaProjectPathContext) {
			createDirectory(javaProjectPathContext.getMainJavaPath());
			createDirectory(javaProjectPathContext.getMainResourcesPath());
			createDirectory(javaProjectPathContext.getBasePackagePath());
			createDirectory(javaProjectPathContext.getConfigPackagePath());
			createDirectory(javaProjectPathContext.getTestJavaPath());
			createDirectory(javaProjectPathContext.getTestResourcesPath());
			createDirectory(javaProjectPathContext.getBasePackagePath());
			
			return true;
	}
	
	

	/**
	 * 
	 * @param directory Directory path where the file has to be created
	 * @param fileNameWithExtn Name of the file with extension
	 * @return {@link Writer} to the supplied file name in supplied directory.
	 * 
	 */
	public static Writer getWriterForFile(String directory, String fileNameWithExtn) {
		try {
			return new FileWriter(new File(Paths.get(directory,fileNameWithExtn).toUri()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean writeFile(String directory, String fileNameWithExtension, List<String> lines){
		try {
			Path path = 	Files.createDirectories(Paths.get(directory));
			Files.write(Paths.get(path.toString(),fileNameWithExtension), lines, StandardCharsets.UTF_8,StandardOpenOption.CREATE);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static List<String> getLinesOfFile( String path){
		try {
			return Files.lines(Paths.get(path)).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
	}
	
	public static List<String> getLinesOfFile( Path path){
		try {
			return Files.lines(path).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
	}
	
	
	/**
	 * 
	 * @param source path to source file
	 * @param target Path to target file
	 * @return true/false denoting the success or failure of operation
	 * 
	 */
	public static boolean copyFile(Path source,Path target){
		CopyOption[] options = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.COPY_ATTRIBUTES};
		try {
			Files.copy(source, target,options);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public static void updatePackageNameAndCopyBaseFiles(File source, File destination, String packageName) throws IOException{
			if(source.isDirectory()){
				 if(!destination.exists()){
					 destination.mkdir();
				 }
				 
				 for (String file : source.list()) 
		            {
		                File srcFile = new File(source, file);
		                File destFile = new File(destination, file);
		                 
		                String packageN = extractPackageNameFromPath(destination.getPath());
		                //Recursive function call
		                
		                updatePackageNameAndCopyBaseFiles(srcFile, destFile,packageN );
		            }
			}else{
				List<String> lines = getLinesOfFile(source.toPath());
				if(source.getPath().endsWith(".java")){
					lines.set(0, packageName);
				}
				writeFile(destination.getParent(), source.getName(), lines);
			}
	}
	
	public static String extractPackageNameFromPath(String path) {
		
		if(path.contains("java"+File.separator)){
			int begin = path.indexOf("java");
		return 	"package " +path.substring(begin+5, path.length()).replace(File.separator, ".")+";";
		}else{
			logger.error("Invalid path supplied for package name extraction : "+path);
		}
		
		return null;
	}
}






