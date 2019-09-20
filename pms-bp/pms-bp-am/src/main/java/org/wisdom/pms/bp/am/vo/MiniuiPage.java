package org.wisdom.pms.bp.am.vo;

import java.util.List;

public class MiniuiPage<T> {
	private List<T> data;

	private int total = 0;

	public MiniuiPage() {
	}

	public MiniuiPage(int total, List<T> data) {
		super();
		this.data = data;
		this.total = total;
	}

	public List<T> getData()
	{
		return this.data;
	}

	public void setData(List<T> data)
	{
		this.data = data;
	}

	public int getTotal()
	{
		return this.total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}
}
