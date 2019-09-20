package org.wisdom.pms.bp.am.combo;

public class BaseQueryBo {
	/**
	 * 页面索引
	 */
	protected int pageIndex = 0;
	/**
	 * 页面大小
	 */
	protected int pageSize = 15;
	/**
	 * 排序字段
	 */
	protected String orderBy;
 
     public BaseQueryBo() {
         super();
     }
 
     public BaseQueryBo(Integer pageIndex, Integer pageSize, String orderBy) {
         super();
         this.pageIndex = pageIndex;
         this.pageSize = pageSize;
         this.orderBy = orderBy;
     }

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
     
}
