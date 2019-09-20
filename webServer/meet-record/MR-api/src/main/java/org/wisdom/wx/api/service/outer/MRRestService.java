package org.wisdom.wx.api.service.outer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wisdom.wx.bp.model.crud.Favorites;
import org.wisdom.wx.bp.model.crud.Note;
import org.wisdom.wx.bp.model.crud.User;
import org.wisdom.wx.bp.model.custom.MeetFavoritesVo;
import org.wisdom.wx.bp.model.custom.MeetVo;
import org.wisdom.wx.bp.model.custom.Message;
import org.wisdom.wx.bp.service.outer.IMRService;

import java.util.Date;
import java.util.List;

@RestController
public class MRRestService {
    @Autowired
    IMRService mrService;
    @RequestMapping("/getUser")
    public List<User> getUsers(){
        List<User> userList = mrService.getUserList();
        System.out.println(userList);
        return userList;
    }
    @RequestMapping(value = "/searchUser",method = RequestMethod.POST)
    public Message searchUser(@RequestBody User user){
        Message message = new Message();
        if (user!=null){
            List<User> userList = mrService.searchUser(user);
            if (userList!=null){
                message.setData(userList);
            }else {
                message.setData("查询记录为空");
            }

        }else{
            message.setData(getUsers());
        }
        return message;
    }
    @RequestMapping(value = "/createMeet",method = RequestMethod.POST)
    public Message createMeeting(@RequestBody MeetVo meet){
        Message message = new Message();
        if (meet!=null&&!StringUtils.isEmpty(meet.getHeader())&&!StringUtils.isEmpty(meet.getContent())){
            boolean b = mrService.createMeeting(meet);
            if (b){
                message.setData("创建成功");
            }else{
                message.setErrorData("创建失败");
            }
        }
        return message;
    }
    @RequestMapping(value = "/deleteMeet",method = RequestMethod.GET)
    public Message deleteMeeting(String id){
        Message message = new Message();
        if (id!=null&&!id.equals("")){
            boolean b = mrService.deleteMeeting(id);
            if (b){
                message.setData("删除成功");
            }else{
                message.setErrorData("删除失败");
            }
        }
        return message;
    }
    @RequestMapping(value = "/updateMeet",method = RequestMethod.POST)
    public Message updateMeeting(@RequestBody MeetVo meet){
        Message message = new Message();
        if (meet!=null&&!StringUtils.isEmpty(meet.getId())){
            boolean b = mrService.updateMeeting(meet);
            if (b){
                message.setData("修改成功");
            }else{
                message.setErrorData("修改失败");
            }
        }
        return message;
    }
    @RequestMapping(value = "/searchMeet",method = RequestMethod.POST)
    public Message searchMeeting(@RequestBody MeetVo meet){
        Message message = new Message();
        if (meet!=null){
            List<MeetVo> meetingRecord = mrService.findMeetingRecord(meet);
            if (meetingRecord.size()>0){
                message.setData(meetingRecord);
            }else{
                message.setErrorData("没有记录");
            }
        }
        return message;
    }
    @RequestMapping(value = "/searchMeetByUser",method = RequestMethod.POST)
    public Message searchMeetByUser(@RequestBody User user){
        Message message = new Message();
        if (user!=null){
            List<MeetFavoritesVo> meetingRecord = mrService.findMeetingRecordByPartake(user);
            if (meetingRecord.size()>0){
                message.setData(meetingRecord);
            }else{
                message.setErrorData("没有记录");
            }
        }
        return message;
    }
    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    public Message registerUser(@RequestBody User user){
        Message message = new Message();
        if (user!=null){
            user = mrService.addUser(user);
            if (user!=null){
                message.setData(user);
            }else {
                message.setErrorData("注册失败");
            }
        }
        return message;
    }

    @RequestMapping(value = "/setFavorites",method = RequestMethod.POST)
    public Message setFavorites(@RequestBody Favorites favorites){
        Message message = new Message();
        if (favorites!=null){
            favorites= mrService.addFavorites(favorites);
            if (favorites!=null){
                message.setData(favorites);
            }else {
                message.setErrorData("收藏失败");
            }
        }
        return message;
    }
    @RequestMapping(value = "/cancelFavorites",method = RequestMethod.GET)
    public Message cancelFavorites(String favoritesId){
        Message message = new Message();
        if (favoritesId!=null){
            if (mrService.cancelFavorites(favoritesId)){
                message.setData("操作成功");
            }else {
                message.setErrorData("操作失败，检查参数");
            }
        }
        return message;
    }
    @RequestMapping(value = "/addNote",method = RequestMethod.POST)
    public Message addNote(@RequestBody Note note){
        Message message = new Message();
        if (note!=null){
            note = mrService.addNote(note);
            if (note!=null){
                message.setData(note);
            }else {
                message.setErrorData("添加失败");
            }
        }
        return message;
    }
    @RequestMapping(value = "/updateNote",method = RequestMethod.POST)
    public Message upDateNote(@RequestBody Note note){
        Message message = new Message();
        if (note!=null&&!StringUtils.isEmpty(note.getNoteId())){
            boolean b = mrService.updateNote(note);
            if (b){
                message.setData("修改成功");
            }else {
                message.setErrorData("添加失败");
            }
        }else {
            message.setErrorData("操作失败，检查参数");
        }
        return message;
    }
    @RequestMapping(value = "/deleteNote",method = RequestMethod.GET)
    public Message deleteNote(String noteId){
        Message message = new Message();
        if (noteId!=null&&!StringUtils.isEmpty(noteId)){
            Note note = new Note();
            note.setNoteId(noteId);
            boolean b = mrService.deleteNote(note);
            if (b){
                message.setData("删除成功");
            }else {
                message.setErrorData("删除失败");
            }
        }else {
            message.setErrorData("操作失败，检查参数");
        }
        return message;
    }
    @RequestMapping(value = "/searchFavorites",method = RequestMethod.POST)
    public Message searchFavorites(Favorites favorites){
        Message message = new Message();
        if (favorites!=null){
            List<Favorites> favoritesList = mrService.searchFavorites(favorites);
            if (favoritesList.size()>0){
                message.setData(favoritesList);
            }else {
                message.setErrorData("没有收藏记录");
            }
        }else {
            message.setErrorData("操作失败，检查参数");
        }
        return message;
    }
    @RequestMapping(value = "/searchNote",method = RequestMethod.POST)
    public Message searchNote(Note note){
        Message message = new Message();
        if (note!=null){
            List<Note> noteList = mrService.searchNote(note);
            if (noteList.size()>0){
                message.setData(noteList);
            }else {
                message.setErrorData("没有笔记记录");
            }
        }else {
            message.setErrorData("操作失败，检查参数");
        }
        return message;
    }

    @RequestMapping(value = "/searchMeetByRecent",method = RequestMethod.GET)
    public Message searchMeetByRecent(int num){
        Message message = new Message();
        if (num==0){
            num=5;
        }
        List<MeetVo> meetVoList = mrService.searchRecentMeet(num);
        message.setData(meetVoList);
        return message;
    }

}
