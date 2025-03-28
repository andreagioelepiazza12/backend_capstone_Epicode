package com.capstone.university.controller;

import com.capstone.university.model.Corso;
import com.capstone.university.model.Iscrizione;
import com.capstone.university.model.Studente;
import com.capstone.university.model.Utente;
import com.capstone.university.model.dto.CorsoDTO;
import com.capstone.university.model.dto.CorsoDocenteDTO;
import com.capstone.university.model.dto.IscrizioneRequestDTO;
import com.capstone.university.model.dto.IscrizioneResponseDTO;
import com.capstone.university.service.AuthService;

import com.capstone.university.service.IscrizioneService;
import com.capstone.university.service.StudenteService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/studente")
@PreAuthorize("hasRole('STUDENTE')")
public class StudenteController {

    @Autowired
    private StudenteService studenteService;

    @Autowired
    IscrizioneService iscrizioneService;

    @PostMapping("/iscrizioni")
    public ResponseEntity<IscrizioneResponseDTO> iscrivitiACorso(
            @Valid @RequestBody IscrizioneRequestDTO request,
            @AuthenticationPrincipal Studente studente
    ) {
        Iscrizione iscrizione = iscrizioneService.iscriviStudente(studente.getId(), request.corsoId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(IscrizioneResponseDTO.fromEntity(iscrizione));
    }

    @GetMapping("/iscrizioni")
    public ResponseEntity<List<IscrizioneResponseDTO>> getIscrizioni(
            @AuthenticationPrincipal Studente studente
    ) {
        List<Iscrizione> iscrizioni = iscrizioneService.getIscrizioniStudente(studente.getId());
        List<IscrizioneResponseDTO> response = iscrizioni.stream()
                .map(IscrizioneResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/corsi")
    public ResponseEntity<List<CorsoDTO>> getCorsiDocente(
            @AuthenticationPrincipal Utente studente
    ) {
        List<Corso> corsi = studenteService.getCorsi(studente.getId());
                List<CorsoDTO> response = corsi.stream()
                .map(CorsoDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(response);
    }


}
