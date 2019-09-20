package org.wisdom.wx.bp.dao.crud;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.wisdom.wx.bp.model.crud.User;
import org.wisdom.wx.bp.model.crud.UserExample;
@Repository
public interface UserMapper {
    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}