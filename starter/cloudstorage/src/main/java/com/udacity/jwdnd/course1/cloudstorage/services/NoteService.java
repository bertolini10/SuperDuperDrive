package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;
    @Autowired
    private  UserMapper userMapper;


    public NoteService(NoteMapper noteMapper) {

        this.noteMapper = noteMapper;

    }

    public int addNote(Note note) {
        Note newNote = new Note(0,note.getNotetitle(),note.getNotedescription(),note.getUserid());
             return noteMapper.insert(newNote);
    }

    public int deleteNote(Integer noteId) {
        return noteMapper.delete(noteMapper.getNote(noteId));
    }

    public int updateNote(Note note) {
        return noteMapper.update(note);
    }

    public Note getNote(Integer noteid) {
        return noteMapper.getNote(noteid);
    }

    public List<Note> getNotes(User user) {
        return noteMapper.getAllNotesbyUser(user.getUserid());
    }
}
