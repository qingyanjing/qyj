package org.wisdom.wx.bp.dao.crud;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.wisdom.wx.bp.model.crud.Meet;
import org.wisdom.wx.bp.model.crud.MeetExample;
@Repository
public interface MeetMapper {
    int deleteByExample(MeetExample example);

    int deleteByPrimaryKey(String id);

    int insert(Meet record);

    int insertSelective(Meet record);

    List<Meet> selectByExample(MeetExample example);

    Meet selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Meet record);

    int updateByPrimaryKey(Meet record);
}