package com.secure.notes2.controllers;

import com.secure.notes2.models.Note;
import com.secure.notes2.service.NotesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    @Autowired
    NotesServiceImpl notesService;

    @PostMapping
    public Note createNote(String content, @AuthenticationPrincipal UserDetails userDetails){
        System.out.println("USERNAME = " + userDetails.getUsername());
        System.out.println(content);
        return notesService.createNoteForUser(userDetails.getUsername(),content);
    }

    @GetMapping
    public List<Note> getUserNotes(@AuthenticationPrincipal UserDetails userDetails){
        System.out.println("USERNAME = " + userDetails.getUsername());
        return notesService.getNoteForUser(userDetails.getUsername());
    }

    @PutMapping("/{noteId}")
    public Note updateUserNote(@AuthenticationPrincipal UserDetails userDetails,@PathVariable Long noteId,@RequestBody String content){
        System.out.println("USERNAME = " + userDetails.getUsername());
        return notesService.updateNoteForUser(noteId,content,userDetails.getUsername());
    }

    @DeleteMapping("/{noteId}")
    public void deleteUserNote(@AuthenticationPrincipal UserDetails userDetails,@PathVariable Long noteId){
        System.out.println("USERNAME = " + userDetails.getUsername());
        notesService.deleteNoteForUser(noteId,userDetails.getUsername());
    }
}
