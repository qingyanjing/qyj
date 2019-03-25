package springMvcTheory.demo.action;

import springMvcTheory.demo.service.IDemoService;
import springMvcTheory.webmvc.annotation.HPAutowired;
import springMvcTheory.webmvc.annotation.HPController;
import springMvcTheory.webmvc.annotation.HPRequestMapping;
import springMvcTheory.webmvc.annotation.HPRequestParam;

/** 
* @author 作者：huangpeng E-mail: 
* @date 创建时间：2019年3月14日 下午3:25:56 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
@HPController
@HPRequestMapping("/demo")
public class DemoAction {

	@HPAutowired
	private IDemoService demoService;
	
	@HPRequestMapping("/query.data")
	public String query(@HPRequestParam("name")String name){
		String text = this.demoService.query(name);
		System.out.println("text：" +text);
		return text;
	}
	
	@HPRequestMapping("/add.data")
	public String add() {
		String text = this.add();
		return text;
	}
	
	@HPRequestMapping("/remove.data")
	public String remove(@HPRequestParam("id")String id){
		String text = this.remove(id);
		return text;
	}
	
}
