package org.wisdom.pms.bp.service.outer;

import java.util.List;

import org.wisdom.pms.bp.bo.user.UserQueryBo;
import org.wisdom.pms.bp.model.crud.RbxConfigCross;
import org.wisdom.pms.bp.model.crud.User;

import com.github.pagehelper.PageInfo;

public interface IPmsService {
	//获取所有用户信息
	public PageInfo<User> getUserList(UserQueryBo queryBo);
	//根据id获取用户信息
	public User getUser(String id);
	//获取路口基本信息
	public List<RbxConfigCross> getRbxConfigCross();
	//新增用户
	public boolean insertUser(User u);
	//删除用户
	public boolean deleteUser(String id);
	//更新用户
	public boolean updateUser(User u);
}
