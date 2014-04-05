package com.gtt.kenshin.web.tag;

/**
 * Project: avatar-biz
 * 
 * File Created at 2010-7-27
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
import java.util.Arrays;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.struts2.util.TextProviderHelper;
import org.apache.struts2.views.jsp.StrutsBodyTagSupport;

import com.gtt.kenshin.log.KenshinLogger;
import com.gtt.kenshin.log.KenshinLoggerFactory;

/**
 * Extended parent tag class, every tag in dianping should extend this class.
 * 
 */
public abstract class ExtendedStrutsBodyTagSupport extends StrutsBodyTagSupport implements TagWritable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4769765205308591318L;

	/**
	 * logger object, every child class can use this same logger
	 */
	protected final transient KenshinLogger logger = KenshinLoggerFactory.getLogger(getClass());

	/**
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		// init property from request or session
		setProperties();
		// do your business in doFinalStartTag, and you must choose your tag
		// return value as doStartTag
		return doFinalStartTag();
	}

	/**
	 * This function used to render tag after setProperties, you should override
	 * this method to implement your business
	 */
	protected abstract int doFinalStartTag() throws JspException;

	/**
	 * initialize properties before run tags
	 */
	protected void setProperties() {
	}

	/**
	 * get i18n message resource text
	 */
	protected String getResourceText(String resName, Object... args) {
		return TextProviderHelper.getText(resName, null, Arrays.asList(args), getStack(), false);
	}

	protected JspWriter getWriter() {
		return pageContext.getOut();
	}

	@Override
	public TagWritable append2Writer(Object content) throws IOException {
		getWriter().write(content.toString());
		return this;
	}

	protected ServletResponse getResponse() {
		return pageContext.getResponse();
	}

	protected ServletRequest getRequest() {
		return pageContext.getRequest();
	}

	protected HttpSession getSession() {
		return pageContext.getSession();
	}

	protected Cookie[] getCookies() {
		return ((HttpServletRequest) getRequest()).getCookies();
	}

	/**
	 * Retrieve object from request
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getRequestAttribute(String name) {
		return (T) getRequest().getAttribute(name);
	}

	/**
	 * Retrieve object from session
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getSessionAttribute(String name) {
		HttpSession session = getSession();
		return (T) (session != null ? session.getAttribute(name) : null);
	}

}
