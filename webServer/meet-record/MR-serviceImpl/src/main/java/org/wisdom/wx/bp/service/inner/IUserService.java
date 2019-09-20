package org.wisdom.wx.bp.service.inner;

import org.wisdom.wx.bp.model.crud.User;
import org.wisdom.wx.bp.model.crud.UserExample;

import java.util.List;

public interface IUserService {
    List<User> getUserList();
    User getUserById(String id);
    List<User> getUserByExample(UserExample example);
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(UserExample example);
    List<User> getUsersByMeet(String meetId);
}
