package org.wisdom.wx.bp.service.inner;

import org.wisdom.wx.bp.model.crud.Meet;
import org.wisdom.wx.bp.model.crud.MeetExample;

import java.util.List;

public interface IMeetService {
    List<Meet> getMeetByExample(MeetExample example);
    Meet getMeetByID(String id);
    boolean creatMeet(Meet meet);
    boolean updateMeet(Meet meet);
    boolean deleteMeet(MeetExample example);
    List<Meet> getMeetByUserId(String id);
}
