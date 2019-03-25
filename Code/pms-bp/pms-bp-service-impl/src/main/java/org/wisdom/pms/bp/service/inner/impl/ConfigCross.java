package org.wisdom.pms.bp.service.inner.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wisdom.pms.bp.dao.crud.RbxConfigCrossMapper;
import org.wisdom.pms.bp.model.crud.RbxConfigCross;
import org.wisdom.pms.bp.service.inner.IConfigCross;

@Service
public class ConfigCross implements IConfigCross{

	@Autowired
	private RbxConfigCrossMapper rbxConfigCrossMapper;
	
	String uid = UUID.randomUUID().toString();
	
	@Override
	@Cacheable(value="getRbxConfigCross" ,key="'getRbxConfigCross'+#uid")
	public List<RbxConfigCross> getRbxConfigCross() {
		System.out.println("未使用缓存");
		return this.rbxConfigCrossMapper.selectByExample(null);
	}

}
