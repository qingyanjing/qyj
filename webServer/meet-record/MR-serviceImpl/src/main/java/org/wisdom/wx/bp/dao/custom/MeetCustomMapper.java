package org.wisdom.wx.bp.dao.custom;

import org.springframework.stereotype.Repository;
import org.wisdom.wx.bp.model.crud.Meet;

import java.util.List;
@Repository
public interface MeetCustomMapper {
    List<Meet> getMeetByMember(String id);
}
