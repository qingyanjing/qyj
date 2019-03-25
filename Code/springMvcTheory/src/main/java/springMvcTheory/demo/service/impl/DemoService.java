package springMvcTheory.demo.service.impl;

import springMvcTheory.demo.service.IDemoService;
import springMvcTheory.webmvc.annotation.HPService;

/** 
* @author 作者：huangpeng E-mail: 
* @date 创建时间：2019年3月14日 下午3:25:24 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
@HPService
public class DemoService implements IDemoService{

	@Override
	public String query(String name) {
		String text = "";
		if("huangpeng".equals(name))
		{
			text = "小姐姐，欢迎您！！！";
		}
		return text;
	}

	@Override
	public String add() {
		return "消息新增成功！";
	}

	@Override
	public String remove(String id) {
		String text = "";
		if("123456".equals(id)) {
			text = "消息删除成功！";
		}
		return text;
	}

}