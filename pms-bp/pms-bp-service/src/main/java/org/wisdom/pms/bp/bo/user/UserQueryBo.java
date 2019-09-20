package org.wisdom.pms.bp.bo.user;

import org.wisdom.pms.bp.am.combo.BaseQueryBo;
import org.wisdom.pms.bp.am.util.StringUtils;
import org.wisdom.pms.bp.model.crud.UserExample;

public class UserQueryBo extends BaseQueryBo{
	//用户编号
	private int id;
	//用户名
	private String name;
	
	public UserExample createExample()
	{
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		
		if(StringUtils.isNotEmpty(this.name))
		{
			criteria.andNameEqualTo(this.name);
		}
		
		//example.setOrderByClause("UPD_TIME desc");
		
		return example;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
