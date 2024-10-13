package com.secure.notes2.service;

import com.secure.notes2.models.Note;

import java.util.List;

public interface  NotesService {

     Note createNoteForUser(String username,String content);
     Note updateNoteForUser(Long noteId,String content,String username);
     void deleteNoteForUser(Long noteId,String username);
     List<Note> getNoteForUser(String username);
}
