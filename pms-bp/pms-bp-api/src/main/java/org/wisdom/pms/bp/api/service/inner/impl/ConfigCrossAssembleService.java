package org.wisdom.pms.bp.api.service.inner.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wisdom.pms.bp.api.service.inner.IConfigCrossAssembleService;
import org.wisdom.pms.bp.model.crud.RbxConfigCross;
import org.wisdom.pms.bp.service.outer.IPmsService;

@Service
public class ConfigCrossAssembleService implements IConfigCrossAssembleService{

	@Autowired
	private IPmsService pmsService;

	@Override
	public List<RbxConfigCross> getRbxConfigCross() {
		return this.pmsService.getRbxConfigCross();
	}

}
