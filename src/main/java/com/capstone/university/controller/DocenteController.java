package com.capstone.university.controller;

import com.capstone.university.model.Docente;
import com.capstone.university.model.Iscrizione;
import com.capstone.university.model.Utente;
import com.capstone.university.model.dto.AssegnaVotoDTO;
import com.capstone.university.model.dto.CorsoDocenteDTO;
import com.capstone.university.model.dto.IscrizioneResponseDTO;
import com.capstone.university.model.dto.StudenteIscrittoDTO;
import com.capstone.university.service.AuthService;
import com.capstone.university.service.DocenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/docente")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private AuthService authenticationService;

    @GetMapping("/corsi")
    public ResponseEntity<Map<String, List<CorsoDocenteDTO>>> getCorsiDocente(
            @AuthenticationPrincipal Utente docente
    ) {
        Map<String, List<CorsoDocenteDTO>> corsi = docenteService.getCorsiOrganizzati(docente.getId());
        return ResponseEntity.ok(corsi);
    }

    @GetMapping("/corsi/{corsoId}/studenti")
    public ResponseEntity<List<StudenteIscrittoDTO>> getStudentiIscritti(
            @PathVariable Long corsoId,
            @AuthenticationPrincipal Docente docente
    ) {
        List<StudenteIscrittoDTO> studenti = docenteService.getStudentiIscrittiAlCorso(corsoId, docente.getId());
        return ResponseEntity.ok(studenti);
    }

    @PutMapping("/valutazioni")
    public ResponseEntity<IscrizioneResponseDTO> assegnaVoto(
            @Valid @RequestBody AssegnaVotoDTO request,
            @AuthenticationPrincipal Docente docente
    ) {
        Iscrizione iscrizione = docenteService.assegnaVoto(
                request.studenteId(),
                request.corsoId(),
                request.voto(),
                docente.getId()
        );
        return ResponseEntity.ok(IscrizioneResponseDTO.fromEntity(iscrizione));
    }

}
