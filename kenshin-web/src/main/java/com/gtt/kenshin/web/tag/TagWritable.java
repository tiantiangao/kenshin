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

/**
 * Tag content writer interface
 * 
 */
public interface TagWritable {

	TagWritable append2Writer(Object content) throws IOException;

}
