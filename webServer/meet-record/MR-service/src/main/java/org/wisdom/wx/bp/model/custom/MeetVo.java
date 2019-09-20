package org.wisdom.wx.bp.model.custom;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.wisdom.wx.bp.model.crud.User;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MeetVo {
    private String id;

    private String content;

    private String header;

    private List<User> members;

    private String picture;

    private Date date;

    private Date updateTime;

    private User organizer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetVo meetVo = (MeetVo) o;
        return Objects.equals(id, meetVo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MeetVo{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", header='" + header + '\'' +
                ", members=" + members +
                ", picture='" + picture + '\'' +
                ", date=" + date +
                ", updateTime=" + updateTime +
                ", organizer=" + organizer +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }
}
