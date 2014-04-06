package com.gtt.kenshin.web.util;

/**
 * @author tiantiangao
 */
public class StaticDecoratorImpl implements StaticDecorator {

	@Override
	public String decorate(String resource, boolean decorate) {
		String fullPathResource = fillFullPath(resource);
		return decorate ? decorate(fullPathResource) : fullPathResource;
	}

	/**
	 * @param resource
	 * @return
	 */
	protected String fillFullPath(String resource) {
		return getStaticServer(resource) + resource;
	}

	/**
	 * 获取静态文件前缀地址
	 *
	 * @param resource
	 * @return
	 */
	protected String getStaticServer(String resource) {
		return "";
	}

	/**
	 * 将静态文件地址，装饰为完整的html标签
	 *
	 * @param fullPathResource
	 * @return
	 */
	protected String decorate(String fullPathResource) {
		return fullPathResource;
	}

}
