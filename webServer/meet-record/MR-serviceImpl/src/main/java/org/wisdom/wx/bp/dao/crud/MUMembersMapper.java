package org.wisdom.wx.bp.dao.crud;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.wisdom.wx.bp.model.crud.MUMembers;
import org.wisdom.wx.bp.model.crud.MUMembersExample;
@Repository
public interface MUMembersMapper {
    int deleteByExample(MUMembersExample example);

    int deleteByPrimaryKey(String id);

    int insert(MUMembers record);

    int insertSelective(MUMembers record);

    List<MUMembers> selectByExample(MUMembersExample example);

    MUMembers selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MUMembers record);

    int updateByPrimaryKey(MUMembers record);
}