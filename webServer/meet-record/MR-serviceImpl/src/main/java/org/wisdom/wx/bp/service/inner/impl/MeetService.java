package org.wisdom.wx.bp.service.inner.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wisdom.wx.bp.dao.crud.MeetMapper;
import org.wisdom.wx.bp.dao.custom.MeetCustomMapper;
import org.wisdom.wx.bp.model.crud.Meet;
import org.wisdom.wx.bp.model.crud.MeetExample;
import org.wisdom.wx.bp.service.inner.IMeetService;

import java.util.List;
@Service
public class MeetService implements IMeetService {
    @Autowired
    MeetMapper meetMapper;
    @Autowired
    MeetCustomMapper meetCustomMapper;
    @Override
    public List<Meet> getMeetByExample(MeetExample example) {
        return meetMapper.selectByExample(example);
    }

    @Override
    public Meet getMeetByID(String id) {
        return meetMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean creatMeet(Meet meet) {
        return meetMapper.insert(meet)>0;
    }

    @Override
    public boolean updateMeet(Meet meet) {
        return meetMapper.updateByPrimaryKeySelective(meet)>0;
    }

    @Override
    public boolean deleteMeet(MeetExample example) {
        return meetMapper.deleteByExample(example)>0;
    }

    @Override
    public List<Meet> getMeetByUserId(String id) {
        return meetCustomMapper.getMeetByMember(id);
    }
}
