package com.gtt.kenshin.web.util;

/**
 * @author tiantiangao
 */
public class StaticFile {

	public static String decorate(String resource) {
		StaticDecorator decorator = SpringLocator.getBean("KenshinStaticProvider");
		return decorator.decorate(resource);
	}

}
