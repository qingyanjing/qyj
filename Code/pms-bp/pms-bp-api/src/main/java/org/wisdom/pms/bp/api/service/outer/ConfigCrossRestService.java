package org.wisdom.pms.bp.api.service.outer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wisdom.pms.bp.api.service.inner.IConfigCrossAssembleService;
import org.wisdom.pms.bp.model.crud.RbxConfigCross;

@RestController
@RequestMapping("/configCross")
public class ConfigCrossRestService {

	@Autowired
	private IConfigCrossAssembleService configCrossAssembleService;
	
	@RequestMapping("/getRbxConfigCross")
	public List<RbxConfigCross> getRbxConfigCross(){
		//Logger log = LoggerUtils.getDBLogger();
		List<RbxConfigCross> volist = this.configCrossAssembleService.getRbxConfigCross();
		//log.error("错误信息！");
		return volist;
	}
			
}
