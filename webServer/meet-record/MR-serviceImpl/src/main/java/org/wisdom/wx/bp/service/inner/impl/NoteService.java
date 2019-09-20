package org.wisdom.wx.bp.service.inner.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wisdom.wx.bp.dao.crud.NoteMapper;
import org.wisdom.wx.bp.model.crud.Note;
import org.wisdom.wx.bp.model.crud.NoteExample;
import org.wisdom.wx.bp.service.inner.INoteService;

import java.util.List;

@Service
public class NoteService implements INoteService {
    @Autowired
    NoteMapper noteMapper;

    @Override
    public Boolean insertNote(Note note) {
        return noteMapper.insertSelective(note)>0;
    }

    @Override
    public Boolean upDateNote(Note note) {
        return noteMapper.updateByPrimaryKeySelective(note)>0;
    }
    @Override
    public boolean deleteNote(String  noteId) {
        return noteMapper.deleteByPrimaryKey(noteId)>0;
    }

    @Override
    public List<Note> selectNote(NoteExample note) {
        return noteMapper.selectByExample(note);
    }
}
