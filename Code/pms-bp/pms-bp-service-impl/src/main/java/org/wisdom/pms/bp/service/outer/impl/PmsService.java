package org.wisdom.pms.bp.service.outer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wisdom.pms.bp.bo.user.UserQueryBo;
import org.wisdom.pms.bp.model.crud.RbxConfigCross;
import org.wisdom.pms.bp.model.crud.User;
import org.wisdom.pms.bp.service.inner.IConfigCross;
import org.wisdom.pms.bp.service.inner.IUsers;
import org.wisdom.pms.bp.service.outer.IPmsService;

import com.github.pagehelper.PageInfo;

@Service
public class PmsService implements IPmsService{
	
	@Autowired
	private IUsers users;
	@Autowired
	private IConfigCross configCross;

	@Override
	public PageInfo<User> getUserList(UserQueryBo queryBo) {
		return this.users.getUserList(queryBo);
	}

	@Override
	public User getUser(String id) {
		return this.users.getUser(id);
	}

	@Override
	public List<RbxConfigCross> getRbxConfigCross() {
		return this.configCross.getRbxConfigCross();
	}

	@Override
	public boolean insertUser(User u) {
		return this.users.insertUser(u);
	}

	@Override
	public boolean deleteUser(String id) {
		return this.users.deleteUser(id);
	}

	@Override
	public boolean updateUser(User u) {
		return this.users.updateUser(u);
	}

}
