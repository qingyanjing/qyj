package org.wisdom.wx.bp.dao.custom;

import org.springframework.stereotype.Repository;
import org.wisdom.wx.bp.model.crud.User;

import java.util.List;
@Repository
public interface UserCustomMapper {
    List<User> selectUsersByMeet(String id);
}
