package com.hellosatish.generator.engine.rendering;

import java.util.concurrent.Callable;

import com.hellosatish.generator.engine.rendering.response.RendererResponse;

/**
 * 
 * @author satish-s Marker interface for all renderer's
 */
public interface Renderer extends Callable<RendererResponse>{
	//Renderer getRendererInstance(MustacheFactory mf,GeneratorMetadata data);
}
