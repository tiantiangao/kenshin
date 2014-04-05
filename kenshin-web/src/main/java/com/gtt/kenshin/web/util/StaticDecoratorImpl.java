package com.gtt.kenshin.web.util;

/**
 * @author tiantiangao
 */
public class StaticDecoratorImpl implements StaticDecorator {

	@Override
	public String decorate(String resource) {
		return getStaticServer(resource) + resource;
	}

	protected String getStaticServer(String resource) {
		return "";
	}

}
