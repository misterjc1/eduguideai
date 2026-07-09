package com.wouti.zoom_academia.repositories;

import com.wouti.zoom_academia.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByMatriculeAndIsDeletedFalse(String matricule);

    List<Note> findByInscrit_IdInscritAndIsDeletedFalse(Long idInscrit);
}
