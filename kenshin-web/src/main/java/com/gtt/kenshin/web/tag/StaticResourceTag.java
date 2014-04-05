package com.gtt.kenshin.web.tag;

/**
 * Project: avatar-biz
 * 
 * File Created at 2010-7-26
 * $Id$
 * 
 * Copyright 2010 Dianping.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Dianping.com.
 */

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang3.StringUtils;

import com.gtt.kenshin.exception.SystemException;
import com.gtt.kenshin.web.util.StaticFile;

/**
 * parse static file url with specified resource
 * 
 */
public class StaticResourceTag extends ExtendedStrutsBodyTagSupport {

	private static final long serialVersionUID = -9145241029635261085L;

	private String resource;

	@Override
	public int doFinalStartTag() throws JspException {
		try {
			if (StringUtils.isNotBlank(resource)) {
				append2Writer(generateDecorateResourceContent(resource));
			} else {
				throw new SystemException("Tag 'StaticResourceTag', field 'resource' is required.");
			}
		} catch (IOException e) {
			logger.error("Error render StaticResource Tag.", e);
		}
		return SKIP_BODY;
	}

	private String generateDecorateResourceContent(String resource) {
		return StaticFile.decorate(resource);
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

}
