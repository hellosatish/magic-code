package com.hellosatish.generator.metadata.extractors;

import com.hellosatish.generator.metadata.ProjectGenerationMetadata;

/**
 * 
 * @author satish-s
 * <pre>
 *  All the extractor classes should be implementing this interface and providing there implementations
 * </pre>
 */
public interface MetadataExtractorInterface {
	public ProjectGenerationMetadata extractMetadata(String inputFilePath);
}
