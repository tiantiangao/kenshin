package com.gtt.kenshin.dao.model;

import java.util.List;

/**
 * 分页模型
 * 
 * @author tiantian.gao
 * @date 2011-5-24
 * 
 */
public class PageModel<T> {

	private static final long serialVersionUID = -8802145055234572275L;

	private int recordCount;// 总记录数

	private int pageSize;// 每页数量

	private int page = 1;// 当前页数

	private List<T> records;// 记录集合

	private String sortField;// 排序字段

	private boolean sortAsc = true;// 排序方式

	/**
	 * 获取下一页开始
	 * 
	 * @return
	 */
	public int getNextStart() {
		if (page < 1) {
			return 1;
		}
		return (page - 1) * pageSize + (records != null ? records.size() : 0) + 1;
	}

	/**
	 * 计算总页数
	 */
	public int getPageCount() {
		if (recordCount == 0) {
			return 0;
		}

		return (recordCount + pageSize - 1) / pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public boolean isSortAsc() {
		return sortAsc;
	}

	public void setSortAsc(boolean sortAsc) {
		this.sortAsc = sortAsc;
	}
}
