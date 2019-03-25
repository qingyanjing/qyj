package org.wisdom.pms.bp.api.service.outer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wisdom.pms.bp.am.combo.Message;
import org.wisdom.pms.bp.am.vo.MiniuiPage;
import org.wisdom.pms.bp.api.service.inner.IUserAssembleService;
import org.wisdom.pms.bp.bo.user.UserQueryBo;
import org.wisdom.pms.bp.model.crud.User;

@RestController
@RequestMapping("/user")
public class UserRestService {

	@Autowired
	private IUserAssembleService userAssembleService;
	
	@RequestMapping("/getUserList")
	public MiniuiPage<User> getUserList(UserQueryBo queryBo){
		MiniuiPage<User> voList = this.userAssembleService.getUserList(queryBo);
		return voList;
	}
	
	@RequestMapping("/getUser")
	public User getUser(){
		String id = "23c4bcec-c781-40ca-8d41-a1c498052c32";
		User vo = this.userAssembleService.getUser(id);
		return vo;
	}
	
	@RequestMapping("/insertUser")
	public Message insertUser(@RequestBody User u)
	{
		Message msg = new Message();
		
		boolean isSuccess = this.userAssembleService.insertUser(u);
		if(isSuccess)
		{
			msg.setText("新增成功");
		}
		else
		{
			msg.setText("新增失败");
		}
		
		return msg;
	}
	
	@RequestMapping("/deleteUser")
	public Message deleteUser(String id)
	{
		Message msg = new Message();
		
		boolean isSuccess = this.userAssembleService.deleteUser(id);
		if(isSuccess)
		{
			msg.setText("删除成功");
		}
		else
		{
			msg.setText("删除失败");
		}
		
		return msg;
	}
	
	@RequestMapping("")
	public Message updateUser(@RequestBody User u)
	{
		Message msg = new Message();
		
		boolean isSuccess = this.userAssembleService.updateUser(u);
		if(isSuccess)
		{
			msg.setText("修改成功");
		}
		else
		{
			msg.setText("修改失败");
		}
		
		return msg;
	}
}
