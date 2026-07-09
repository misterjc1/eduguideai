package com.wouti.zoom_academia.controllers;

import com.wouti.zoom_academia.entities.Note;
import com.wouti.zoom_academia.repositories.InscritRepository;
import com.wouti.zoom_academia.repositories.NiveauRepository;
import com.wouti.zoom_academia.repositories.NoteRepository;
import com.wouti.zoom_academia.repositories.ServiceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final NoteRepository noteRepo;
    private final InscritRepository inscritRepo;
    private final NiveauRepository niveauRepo;
    private final ServiceRepository serviceRepo;

    public DashboardController(NoteRepository noteRepo,
                               InscritRepository inscritRepo,
                               NiveauRepository niveauRepo,
                               ServiceRepository serviceRepo) {
        this.noteRepo = noteRepo;
        this.inscritRepo = inscritRepo;
        this.niveauRepo = niveauRepo;
        this.serviceRepo = serviceRepo;
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats(@RequestParam(required = false) String username) {
        Map<String, Object> stats = new LinkedHashMap<>();

        stats.put("nbInscrits", inscritRepo.count());
        stats.put("nbNiveaux", niveauRepo.count());
        stats.put("nbServices", serviceRepo.count());

        List<Note> validNotes = noteRepo.findAll().stream()
                .filter(n -> !n.isDeleted() && n.getValeur() != null)
                .collect(Collectors.toList());

        double moyenne = validNotes.stream()
                .mapToDouble(Note::getValeur)
                .average().orElse(0.0);

        stats.put("nbNotes", (long) validNotes.size());
        stats.put("moyenneGenerale", Math.round(moyenne * 100.0) / 100.0);

        if (username != null && !username.isEmpty()) {
            List<Note> perso = noteRepo.findByMatriculeAndIsDeletedFalse(username);
            double moyPerso = perso.stream()
                    .filter(n -> n.getValeur() != null)
                    .mapToDouble(Note::getValeur)
                    .average().orElse(0.0);
            double bestNote = perso.stream()
                    .filter(n -> n.getValeur() != null)
                    .mapToDouble(Note::getValeur)
                    .max().orElse(0.0);
            long nbSem = perso.stream()
                    .filter(n -> n.getSemestre() != null)
                    .map(Note::getSemestre)
                    .distinct().count();

            stats.put("nbNotesPerso", (long) perso.size());
            stats.put("moyennePerso", Math.round(moyPerso * 100.0) / 100.0);
            stats.put("bestNote", Math.round(bestNote * 100.0) / 100.0);
            stats.put("nbSemestres", nbSem);
        }

        return stats;
    }
}
