package com.gtt.kenshin.web.util;

/**
 * @author tiantiangao
 */
public class StaticFile {

	public static String decorate(String resource) {
		StaticDecorator decorator = null;
		try {
			decorator = SpringLocator.getBean("CustomStaticProvider");
		} catch (Exception e) {
		}
		if (decorator == null) {
			decorator = SpringLocator.getBean("KenshinStaticProvider");
		}
		return decorator.decorate(resource);
	}

}
