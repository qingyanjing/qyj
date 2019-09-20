package org.wisdom.wx.bp.service.inner;

import org.wisdom.wx.bp.model.crud.Note;
import org.wisdom.wx.bp.model.crud.NoteExample;

import java.util.List;

public interface INoteService {

    Boolean insertNote(Note note);
    Boolean upDateNote(Note note);
    boolean deleteNote(String noteId);
    List<Note> selectNote(NoteExample note);

}
