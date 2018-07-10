package com.hellosatish.generator.engine.rendering.renderers;

import java.io.Writer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.hellosatish.generator.engine.rendering.Renderer;
import com.hellosatish.generator.engine.rendering.metadata.ClassRendererMetadata;
import com.hellosatish.generator.engine.rendering.response.RendererResponse;
import com.hellosatish.generator.engine.util.TemplateResolver;
import com.samskivert.mustache.Mustache;

public class ClassRenderer implements Renderer {

	private ClassRendererMetadata metadata;
	private Writer writer;

	private static final Logger LOGGER = LogManager.getLogger(ClassRenderer.class);

	public ClassRenderer( Writer writer, ClassRendererMetadata metadata) {
		this.writer = writer;
		this.metadata = metadata;
	}

	@Override
	public RendererResponse call() throws Exception {
		LOGGER.debug("Compiling and rendering template");
		Mustache.compiler().compile(TemplateResolver.getClassTemplate()).execute( metadata,writer);
		writer.close();
		LOGGER.info("Rendering Compelted.....");
		return null;
	}
}
