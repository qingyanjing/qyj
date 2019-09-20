package org.wisdom.pms.bp.am.combo;

import java.util.List;

public class Message {
	/*
	 * 文本提示
	 */
	private String text;
	/*
	 * 数据集合
	 */
	private List<Object> dataList;
	/*
	 * 数据对象
	 */
	private Object data;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Object> getDataList() {
		return dataList;
	}
	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
