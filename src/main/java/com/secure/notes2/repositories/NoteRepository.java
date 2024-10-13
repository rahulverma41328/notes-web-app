package com.secure.notes2.repositories;

import com.secure.notes2.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

    List<Note> findByOwnerUsername(String ownerUsername);
}
