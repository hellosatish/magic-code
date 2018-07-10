package com.hellosatish.generator.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hellosatish.generator.engine.config.generator.SpringBootGenerator;
import com.hellosatish.generator.metadata.ProjectGenerationMetadata;
import com.hellosatish.generator.metadata.extractors.MetadataExtractorFactory;
import com.hellosatish.generator.metadata.extractors.SupportedSourceFormats;

/**
 * 
 * @author satish-s
 *<pre>
 *		Controller to take input and generate the application based on the type supplied
 *</pre>
 */
@RestController
public class CodeGenResource {

	
	@Autowired
	private SpringBootGenerator gen;
	
	@PostMapping("/generate")
	public ResponseEntity<ProjectGenerationMetadata> generateApplication(@RequestBody ProjectGenerationMetadata project) throws IOException{
		
		gen.generate(project);
		return new ResponseEntity<ProjectGenerationMetadata>(project, HttpStatus.OK);
	}
	
	@PostMapping("/convert/{sourceformat}")
	public ResponseEntity<ProjectGenerationMetadata> testModel(@PathVariable("sourceformat") SupportedSourceFormats sourceFormat) throws IOException{
		String inputFilePath = "E:/swagger1.json";
		ProjectGenerationMetadata metaData = MetadataExtractorFactory.getExtractor(sourceFormat).extractMetadata(inputFilePath);
		return new ResponseEntity<ProjectGenerationMetadata>(metaData, HttpStatus.OK);
	}
	
	@GetMapping("/testMe")
	public void renderPojo(){
		
			
		/*PojoRendererMetadata md = new PojoRendererMetadata("com.hcl.sk",  "MyFirstPojo",imports, fields);
		renderer.sumitForRendering(new OutputStreamWriter(System.out),md);*/
		//renderer.setMetadata(md);
	}

}
