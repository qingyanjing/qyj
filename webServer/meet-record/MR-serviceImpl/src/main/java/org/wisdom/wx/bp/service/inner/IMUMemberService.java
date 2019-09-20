package org.wisdom.wx.bp.service.inner;

import org.wisdom.wx.bp.model.crud.MUMembers;
import org.wisdom.wx.bp.model.crud.MUMembersExample;

import java.util.List;

public interface IMUMemberService {
    List<MUMembers> getMUMembers(MUMembersExample example);
    boolean addMUM(MUMembers muMembers);
    boolean updateMUM(MUMembers muMembers);
    boolean deleteMUM(MUMembersExample example);
}
