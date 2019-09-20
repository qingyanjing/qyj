package org.wisdom.wx.bp.service.outer;

import org.wisdom.wx.bp.model.crud.Favorites;
import org.wisdom.wx.bp.model.crud.Note;
import org.wisdom.wx.bp.model.crud.User;
import org.wisdom.wx.bp.model.custom.MeetFavoritesVo;
import org.wisdom.wx.bp.model.custom.MeetVo;

import java.util.List;

public interface IMRService {
    List<User> getUserList();
    /***
     *
     *创建会议记录
     */
    boolean createMeeting(MeetVo meetVo);

    /***
     * 删除会议记录
     * */
    boolean deleteMeeting(String id);
    /***
     * 修改会议记录
     * */
    boolean updateMeeting(MeetVo meetVo);
    /**
     * 查找会议记录
     * **/
    List<MeetVo> findMeetingRecord(MeetVo meetVo);
    /**
     * 注册用户
     * **/
    User addUser(User user);
    /**
     * 搜索用户
     * */
    List<User> searchUser(User user);
    /**
     * 通过参会人员查找会议记录
     * */
    List<MeetFavoritesVo> findMeetingRecordByPartake(User user);
    /***
     * 追加会议收藏
     * */
    Favorites addFavorites(Favorites favorites);
    /***
     * 取消会议收藏
     * */
    boolean cancelFavorites(String favoritesId);
    /***
     * 添加会议笔记
     * */
    Note addNote(Note note);
    /***
     * 修改会议笔记
     * */
    Boolean updateNote(Note note);
    /**
     * 删除会议笔记
     * **/
    boolean deleteNote(Note note);
    /***
     * 查询会议笔记
     * */
    List<Note> searchNote(Note note);
    /***
     * 查询收藏
     * */
    List<Favorites> searchFavorites(Favorites favorites);
    /**
     * 查询近期会议记录
     * **/
    List<MeetVo> searchRecentMeet(int num);
}
