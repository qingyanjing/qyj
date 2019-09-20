package org.wisdom.pms.bp.service.inner.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wisdom.pms.bp.am.util.StringUtils;
import org.wisdom.pms.bp.bo.user.UserQueryBo;
import org.wisdom.pms.bp.dao.crud.UserMapper;
import org.wisdom.pms.bp.model.crud.User;
import org.wisdom.pms.bp.service.inner.IUsers;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class Users implements IUsers{

	@Autowired
	private UserMapper userMapper;

	@Override
	@Cacheable(value="getUserList" ,key="'getUserList'+#uid")
	public PageInfo<User> getUserList(UserQueryBo queryBo) {
		PageInfo<User> pageInfo = null;
		if(queryBo != null)
		{
			PageHelper.startPage(queryBo.getPageIndex() + 1, queryBo.getPageSize());
			List<User> modeList = this.userMapper.selectByExample(queryBo.createExample());
			System.out.println("未使用缓存");
			if(modeList != null)
			{
				pageInfo = new PageInfo<User>(modeList);
			}
		}
		
		return pageInfo;
	}

	@Override
	@Cacheable(value="getUser" ,key="'getUser'+#id")
	public User getUser(String id) {
		System.out.println("未使用缓存");
		/*
		 * UserExample example = new UserExample(); UserExample.Criteria criteria = example.createCriteria(); criteria.andIdEqualTo(id);
		 */
		return this.userMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean insertUser(User u) {
		String uid = UUID.randomUUID().toString();
		
		boolean isSuccess = false;
		if(u != null)
		{
			if(StringUtils.isEmpty(u.getId()))
			{
				u.setId(uid);
			}
			int count = this.userMapper.insert(u);
			if(count > 0)
			{
				isSuccess = true;
			}
		}
		return isSuccess;
	}

	@Override
	public boolean deleteUser(String id) {
		boolean isSuccess = false;
		int count = this.userMapper.deleteByPrimaryKey(id);
		if(count > 0)
		{
			isSuccess = true;
		}
		return isSuccess;
	}

	@Override
	public boolean updateUser(User u) {
		boolean isSuccess = false;
		if(u != null)
		{
			int count = this.userMapper.updateByPrimaryKeySelective(u);
			if(count > 0)
			{
				isSuccess = true;
			}
		}
		return isSuccess;
	}
	

}
