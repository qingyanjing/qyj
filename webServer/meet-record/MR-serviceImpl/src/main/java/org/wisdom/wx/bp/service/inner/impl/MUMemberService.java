package org.wisdom.wx.bp.service.inner.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wisdom.wx.bp.dao.crud.MUMembersMapper;
import org.wisdom.wx.bp.model.crud.MUMembers;
import org.wisdom.wx.bp.model.crud.MUMembersExample;
import org.wisdom.wx.bp.service.inner.IMUMemberService;

import java.util.List;
@Service
public class MUMemberService implements IMUMemberService {
    @Autowired
    MUMembersMapper muMembersMapper;
    @Override
    public List<MUMembers> getMUMembers(MUMembersExample example) {
        return muMembersMapper.selectByExample(example);
    }

    @Override
    public boolean addMUM(MUMembers muMembers) {
        return muMembersMapper.insert(muMembers)>0;
    }

    @Override
    public boolean updateMUM(MUMembers muMembers) {
        return muMembersMapper.updateByPrimaryKeySelective(muMembers)>0;
    }

    @Override
    public boolean deleteMUM(MUMembersExample example) {
        return muMembersMapper.deleteByExample(example)>0;
    }
}
