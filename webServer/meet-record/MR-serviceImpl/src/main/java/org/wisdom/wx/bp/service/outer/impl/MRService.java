package org.wisdom.wx.bp.service.outer.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wisdom.wx.bp.model.crud.*;
import org.wisdom.wx.bp.model.custom.MeetFavoritesVo;
import org.wisdom.wx.bp.model.custom.MeetVo;
import org.wisdom.wx.bp.service.inner.INoteService;
import org.wisdom.wx.bp.service.inner.IFavoritesService;
import org.wisdom.wx.bp.service.inner.IMUMemberService;
import org.wisdom.wx.bp.service.inner.IMeetService;
import org.wisdom.wx.bp.service.inner.IUserService;
import org.wisdom.wx.bp.service.outer.IMRService;

import java.util.*;

@Service
public class MRService implements IMRService {
    @Autowired
    IUserService userService;
    @Autowired
    IMeetService meetService;
    @Autowired
    IMUMemberService muMemberService;
    @Autowired
    IFavoritesService favoritesService;
    @Autowired
    INoteService noteService;

    @Override
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @Override
    public boolean createMeeting(MeetVo meetVo) {
        Meet meet=new Meet();
        BeanUtils.copyProperties(meetVo,meet);
        List<User> userList = userService.getUserList();
        List<User> members = meetVo.getMembers();
        List<User> MUMUsers=new ArrayList<>();
        if (members!=null&&members.size()>0){
            members=checkUserAndReturn(members);
            User organizer =meetVo.getOrganizer();
            meet.setOrganizerId(organizer.getId());
            meet.setId(UUID.randomUUID().toString());
            if (meet.getDate()==null){
                meet.setDate(new Date());
            }
            meet.setUpdateTime(new Date());
            insertMUM(meet, members);
            return meetService.creatMeet(meet);
        }
        return false;
    }

    @Override
    public boolean deleteMeeting(String id)
    {
        if (id!=null&&!id.equals("")){
            MeetExample meetExample = new MeetExample();
            MeetExample.Criteria criteria = meetExample.createCriteria();
            criteria.andIdEqualTo(id);
            MUMembersExample muMembersExample = new MUMembersExample();
            MUMembersExample.Criteria muMembersExampleCriteria = muMembersExample.createCriteria();
            muMembersExampleCriteria.andMeetIdEqualTo(id);
            muMemberService.deleteMUM(muMembersExample);
            return  meetService.deleteMeet(meetExample);
        }
        return false;
    }

    @Override
    public boolean updateMeeting(MeetVo meetVo)
    {
        if (meetVo!=null){
            Meet meet=new Meet();
            BeanUtils.copyProperties(meetVo,meet);
            List<User> members = meetVo.getMembers();
            if (members!=null&&members.size()>0){
                MUMembersExample muMembersExample = new MUMembersExample();
                MUMembersExample.Criteria muMembersExampleCriteria = muMembersExample.createCriteria();
                muMembersExampleCriteria.andMeetIdEqualTo(meet.getId());
                muMemberService.deleteMUM(muMembersExample);
                members= checkUserAndReturn(members);
                insertMUM(meet, members);
            }
            return  meetService.updateMeet(meet);
        }
        return false;
    }

    private void insertMUM(Meet meet, List<User> members) {
        for (User member : members) {
            MUMembers muMembers = new MUMembers();
            muMembers.setId(UUID.randomUUID().toString());
            muMembers.setMeetId(meet.getId());
            muMembers.setUserId(member.getId());
            muMemberService.addMUM(muMembers);
        }
    }

    @Override
    public List<MeetVo> findMeetingRecord(MeetVo meetVo)
    {
        List<MeetVo> meetVoList=new ArrayList<>();
        List<User> members = meetVo.getMembers();
        if (meetVo!=null){
            Meet meet=new Meet();
            BeanUtils.copyProperties(meetVo,meet);
            MeetExample meetExample = new MeetExample();
            MeetExample.Criteria criteria = meetExample.createCriteria();
            boolean flag=false;
            if (meet.getContent()!=null&&!meet.getContent().equals("")){//根据内容模糊查询
                criteria.andHeaderLike(likeSearch(meet.getContent()));
                flag=true;
            }
            if (meet.getHeader()!=null&&!meet.getHeader().equals("")){//根据标题模糊查询
                criteria.andHeaderLike(likeSearch(meet.getContent()));
                flag=true;
            }
            HashSet<String> userIdSet=new HashSet<>();
            if (meetVo.getOrganizer()!=null){//根据组织者模糊查询
                User organizer = meetVo.getOrganizer();
                UserExample example = new UserExample();
                example.createCriteria().andNameLike(likeSearch(organizer.getName()));
                List<User> users= users = userService.getUserByExample(example);
                if (users!=null&&users.size()>0){
                    for (User user : users) {
                        if (userIdSet.add(user.getId())){
                            if (members!=null){
                                members.add(user);
                            }

                        }
                    }
                    flag=true;
                }

            }
            if (flag){
                List<Meet> meetList = meetService.getMeetByExample(meetExample);
                for (Meet meet1 : meetList) {
                    if (userIdSet.contains(meet1.getOrganizerId())){
                        List<User> userList = userService.getUsersByMeet(meet1.getId());
                        MeetVo vo = new MeetVo();
                        BeanUtils.copyProperties(meet1,vo);
                        vo.setMembers(userList);
                        vo.setOrganizer(userService.getUserById(meet1.getOrganizerId()));
                        meetVoList.add(vo);
                    }
                }
                HashSet<MeetVo> voHashSet = null;
                if (members!=null&&members.size()>0){
                    voHashSet=new HashSet<>();
                    members=checkUserAndReturn(members);
                    for (User member : members) {
                        List<MeetFavoritesVo> meetFavoritesVoList = this.findMeetingRecordByPartake(member);
                        List<MeetVo> meetingRecordByPartake=new ArrayList<>();
                        for (MeetFavoritesVo favoritesVo : meetFavoritesVoList) {
                                    MeetVo meetVo1 = new MeetVo();
                                    BeanUtils.copyProperties(favoritesVo,meetVo1);
                                    meetingRecordByPartake.add(meetVo1);
                        }
                        voHashSet.addAll(Arrays.asList(meetingRecordByPartake.toArray(new MeetVo[meetingRecordByPartake.size()])));
                    }

                }
                if (meetVoList.size()>0&&voHashSet!=null){
                    //去重
                    if (voHashSet.size()!=0){
                        for (int i = 0; i < meetVoList.size(); i++) {
                            if(!voHashSet.contains(meetVoList.get(i))){
                                meetVoList.remove(i--);
                            }
                        }
                    }else{
                        meetVoList.clear();
                    }

                }
                return  checkOnlyMeetByMembers(meetVoList,members);
            }
        }
        return meetVoList;
    }
    private List<MeetVo> checkOnlyMeetByMembers(List<MeetVo> meetVoList,List<User> userList){
        if (userList!=null){
            for (int i = 0; i < meetVoList.size(); i++) {
                HashSet<String> userSet=new HashSet<>();
                for (User user : userList) {
                    userSet.add(user.getId());
                }
                MeetVo meetVo = meetVoList.get(i);
                HashSet<String> memberSet=new HashSet<>();
                memberSet.add(meetVo.getOrganizer().getId());
                List<User> members = meetVo.getMembers();
                for (User meetMem : members) {
                    memberSet.add(meetMem.getId());
                }
                userSet.removeAll(memberSet);

                if (userSet.size()!=0){
                    meetVoList.remove(i--);
                }

            }
        }
        return meetVoList;
    }
    @Override
    public User addUser(User user) {
        return insertUser(user);
    }

    @Override
    public List<User> searchUser(User user) {
        if (user!=null){
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            if (!StringUtils.isEmpty(user.getName())){
                criteria.andNameLike(likeSearch(user.getName()));
            }
            if (!StringUtils.isEmpty(user.getNickName())){
                criteria.andNickNameEqualTo(user.getNickName());
            }
            if (!StringUtils.isEmpty(user.getId())){
                criteria.andIdEqualTo(user.getId());
            }
            return userService.getUserByExample(example);
        }
        return null;
    }

    @Override
    public List<MeetFavoritesVo> findMeetingRecordByPartake(User user) {
        List<MeetFavoritesVo> meetVoList=new ArrayList<>();
        if (user!=null){
            if (user.getId()==null&&user.getName()!=null){
                user=insertUser(user);
            }
            //新增收藏
            Favorites favorites = new Favorites();
            favorites.setUserId(user.getId());
            List<Favorites> favoritesList = this.searchFavorites(favorites);
            Map<String,Favorites> favoritesMap=new HashMap<>();
            for (Favorites favorite : favoritesList) {
                favoritesMap.put(favorite.getMeetId(),favorite);
            }
            List<Meet> meetList = meetService.getMeetByUserId(user.getId());
            if (meetList!=null&&meetList.size()>0){
                for (Meet meet : meetList) {
                        MeetFavoritesVo vo = new MeetFavoritesVo();
                        BeanUtils.copyProperties(meet,vo);
                        vo.setMembers(userService.getUsersByMeet(meet.getId()));
                        vo.setOrganizer(userService.getUserById(meet.getOrganizerId()));
                        vo.setFavorites(favoritesMap.containsKey(meet.getId()));
                        meetVoList.add(vo);
                }
            }
        }
        return meetVoList;
    }

    @Override
    public Favorites addFavorites(Favorites favorites) {
        if (favorites!=null&& !StringUtils.isEmpty(favorites.getMeetId())&&!StringUtils.isEmpty(favorites.getUserId())){
            favorites.setFavoritesId(UUID.randomUUID().toString());
            favorites.setCreateTime(new Date());
            if (favoritesService.insertFavorites(favorites)){
                return favorites;
            }
        }
        return null;
    }

    @Override
    public boolean cancelFavorites(String favoritesId) {
        if (!StringUtils.isEmpty(favoritesId)){
             return  favoritesService.cancelFavorites(favoritesId);
        }
        return false;
    }

    @Override
    public Note addNote(Note note) {
        if (note!=null){
            note.setNoteId(UUID.randomUUID().toString());
            note.setCreateTime(new Date());
            if (noteService.insertNote(note)){
                return note;
            }
        }
        return null;
    }

    @Override
    public Boolean updateNote(Note note) {
        if (note!=null&&!StringUtils.isEmpty(note.getNoteContent())){
            Note note1 = new Note();
            note1.setNoteId(note.getNoteId());
            note1.setNoteContent(note.getNoteContent());
            note1.setCreateTime(new Date());
            return noteService.upDateNote(note1);
        }
        return false;
    }

    @Override
    public boolean deleteNote(Note note) {
        if (note!=null&&!StringUtils.isEmpty(note.getNoteId())){
            return noteService.deleteNote(note.getNoteId());
        }
        return false;
    }

    @Override
    public List<Note> searchNote(Note note) {
        List<Note> notes=new ArrayList<>();
        if (note!=null){
            NoteExample example = new NoteExample();
            NoteExample.Criteria criteria = example.createCriteria();
            if (!StringUtils.isEmpty(note.getNoteId())){
                criteria.andNoteIdEqualTo(note.getNoteId());
            }else{
                if (!StringUtils.isEmpty(note.getMeetId())){
                    criteria.andMeetIdEqualTo(note.getMeetId());
                }
                if (!StringUtils.isEmpty(note.getUserId())){
                    criteria.andUserIdEqualTo(note.getUserId());
                }
            }
            notes=noteService.selectNote(example);
        }
        return notes;
    }

    @Override
    public List<Favorites> searchFavorites(Favorites favorites) {
        List<Favorites> favoritesList=new ArrayList<>();
        if (favorites!=null){
            FavoritesExample example = new FavoritesExample();
            FavoritesExample.Criteria criteria = example.createCriteria();
            if (!StringUtils.isEmpty(favorites.getFavoritesId())){
                criteria.andFavoritesIdEqualTo(favorites.getFavoritesId());
            }else{
                if (!StringUtils.isEmpty(favorites.getUserId())){
                    criteria.andUserIdEqualTo(favorites.getUserId());
                }
                if (!StringUtils.isEmpty(favorites.getMeetId())){
                    criteria.andMeetIdEqualTo(favorites.getMeetId());
                }
            }
            favoritesList=favoritesService.selectFavorites(example);
        }
        return favoritesList;
    }

    @Override
    public List<MeetVo> searchRecentMeet(int num) {
        List<MeetVo> meetVoList=new ArrayList<>();
        MeetExample meetExample = new MeetExample();
        meetExample.setOrderByClause("M_DATE desc limit "+num);
        List<Meet> meetList = meetService.getMeetByExample(meetExample);
        for (Meet meet : meetList) {
            MeetVo meetVo = new MeetVo();
            BeanUtils.copyProperties(meet,meetVo);
            meetVo.setOrganizer(userService.getUserById(meet.getOrganizerId()));
            meetVo.setMembers(userService.getUsersByMeet(meet.getId()));
            meetVoList.add(meetVo);
        }
        return meetVoList;
    }

    private User insertUser(User user){
        if (user!=null&&user.getName()!=null){
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andNameEqualTo(user.getName());
            List<User> userList = userService.getUserByExample(example);
            if (userList!=null&&userList.size()==0){
                user.setId(UUID.randomUUID().toString());
                userService.addUser(user);
                return user;
            }else if (userList!=null&&userList.size()>0){
                return userList.get(0);
            }
        }
        return  user;
    }
    private List<User> checkUserAndReturn(List<User> memberList){
        List<User> userList=userService.getUserList();
        HashMap<String, User> map = new HashMap<>();
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            map.put(user.getName(), user);
        }
        for (int i = 0; i < memberList.size(); i++) {
            User member = memberList.get(i);
            if (StringUtils.isEmpty(member.getId())){//没有ID查询操作
                memberList.remove(i);
                i--;
                Set<String> keySet = map.keySet();
                for (String s : keySet) {
                    if (s.contains(member.getName())){
                        memberList.add(map.get(s));
                    }
                }
            }else{
                memberList.set(i,userService.getUserById(member.getId()));
            }
        }
        return memberList;
    }
    private  String likeSearch(String like){
        StringBuffer stringBuffer = new StringBuffer("%");
        stringBuffer.append(like).append("%");
        return stringBuffer.toString();
    }
}
