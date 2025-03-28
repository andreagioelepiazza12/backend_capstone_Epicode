package com.capstone.university.controller;

import com.capstone.university.model.Corso;
import com.capstone.university.model.Utente;
import com.capstone.university.model.dto.CorsoRequestDTO;
import com.capstone.university.model.dto.CorsoResponseDTO;
import com.capstone.university.model.dto.DocenteInfoDTO;
import com.capstone.university.service.CorsoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/corsi")
public class CorsoController {

    @Autowired
    CorsoService corsoService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    public ResponseEntity<CorsoResponseDTO> createCorso(
                                                         @Valid @RequestBody CorsoRequestDTO corsoDTO,
                                                         @AuthenticationPrincipal Utente currentUser
    ) {
        Corso createdCorso = corsoService.createCorso(corsoDTO, currentUser);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToCorsoResponseDTO(createdCorso));
    }

    private CorsoResponseDTO convertToCorsoResponseDTO(Corso corso) {
        return new CorsoResponseDTO(
                corso.getId(),
                corso.getMateria(),
                corso.getDataDiInizio(),
                corso.getDataDiFine(),
                corso.getNumPostiDisponibili(),
                corso.getStato(),
                new DocenteInfoDTO( //
                        corso.getDocente().getId(),
                        corso.getDocente().getNomeCompleto()
                )
        );
    }
}
