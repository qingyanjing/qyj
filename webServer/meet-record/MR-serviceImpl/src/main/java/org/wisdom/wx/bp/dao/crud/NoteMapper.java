package org.wisdom.wx.bp.dao.crud;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.wisdom.wx.bp.model.crud.Note;
import org.wisdom.wx.bp.model.crud.NoteExample;
@Repository
public interface NoteMapper {
    int deleteByExample(NoteExample example);

    int deleteByPrimaryKey(String noteId);

    int insert(Note record);

    int insertSelective(Note record);

    List<Note> selectByExample(NoteExample example);

    Note selectByPrimaryKey(String noteId);

    int updateByPrimaryKeySelective(Note record);

    int updateByPrimaryKey(Note record);
}