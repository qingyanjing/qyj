package org.wisdom.pms.bp.api.service.inner.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wisdom.pms.bp.am.vo.MiniuiPage;
import org.wisdom.pms.bp.api.service.inner.IUserAssembleService;
import org.wisdom.pms.bp.bo.user.UserQueryBo;
import org.wisdom.pms.bp.model.crud.RbxConfigCross;
import org.wisdom.pms.bp.model.crud.User;
import org.wisdom.pms.bp.service.outer.IPmsService;

import com.github.pagehelper.PageInfo;

@Service
public class UserAssembleService implements IUserAssembleService{

	@Autowired
	private IPmsService pmsService;
	
	@Override
	public MiniuiPage<User> getUserList(UserQueryBo queryBo) {
		MiniuiPage<User> miniuiPage = null;
		if(queryBo != null)
		{
			PageInfo<User> voListForPage = this.pmsService.getUserList(queryBo);
			if(voListForPage != null)
			{
				miniuiPage = new MiniuiPage<User>();
				miniuiPage.setTotal((int) voListForPage.getTotal());
				miniuiPage.setData(voListForPage.getList());
			}
		}
		
		return miniuiPage;
	}

	@Override
	public User getUser(String id) {
		return this.pmsService.getUser(id);
	}

	@Override
	public List<RbxConfigCross> getRbxConfigCross() {
		return this.pmsService.getRbxConfigCross();
	}

	@Override
	public boolean insertUser(User u) {
		return this.pmsService.insertUser(u);
	}

	@Override
	public boolean deleteUser(String id) {
		return this.pmsService.deleteUser(id);
	}

	@Override
	public boolean updateUser(User u) {
		return this.pmsService.updateUser(u);
	}

}
