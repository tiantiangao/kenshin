package com.gtt.kenshin.dao.util;

import com.gtt.kenshin.dao.model.PageModel;

/**
 * @author tiantiangao
 */
public class PageModelUtil {

	public static <T, S> PageModel<T> transfer(PageModel<S> model) {
		PageModel<T> tmodel = new PageModel<T>();
		tmodel.setRecordCount(model.getRecordCount());
		tmodel.setPage(model.getPage());
		tmodel.setPageSize(model.getPageSize());
		tmodel.setSortField(model.getSortField());
		tmodel.setSortAsc(model.isSortAsc());
		return tmodel;
	}
}
