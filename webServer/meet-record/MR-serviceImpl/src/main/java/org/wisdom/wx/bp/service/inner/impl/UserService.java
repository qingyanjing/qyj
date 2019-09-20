package org.wisdom.wx.bp.service.inner.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wisdom.wx.bp.dao.crud.UserMapper;
import org.wisdom.wx.bp.dao.custom.UserCustomMapper;
import org.wisdom.wx.bp.model.crud.User;
import org.wisdom.wx.bp.model.crud.UserExample;
import org.wisdom.wx.bp.service.inner.IUserService;

import java.util.List;
@Service
public class UserService implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserCustomMapper userCustomMapper;
    @Override
    public List<User> getUserList() {
        UserExample example=new UserExample();
        return userMapper.selectByExample(example);
    }

    @Override
    public User getUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getUserByExample(UserExample example) {
        return userMapper.selectByExample(example);
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insert(user)>0;
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user)>0;
    }

    @Override
    public boolean deleteUser(UserExample example) {
        return userMapper.deleteByExample(example)>0;
    }

    @Override
    public List<User> getUsersByMeet(String meetId) {
        return userCustomMapper.selectUsersByMeet(meetId);
    }
}
