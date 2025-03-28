package com.capstone.university.model.dto;

import com.capstone.university.model.Corso;

import java.time.LocalDate;

public record CorsoResponseDTO(
        Long id,
        String materia,
        LocalDate dataInizio,
        LocalDate dataFine,
        Integer postiDisponibili,
        String stato,
        DocenteInfoDTO docente
) {
    public static CorsoResponseDTO fromEntity(Corso corso) {
        DocenteInfoDTO docente = new DocenteInfoDTO(corso.getDocente().getId(), corso.getDocente().getNomeCompleto());
        return new CorsoResponseDTO(
                corso.getId(),
                corso.getMateria(),
                corso.getDataDiInizio(),
                corso.getDataDiFine(),
                corso.getNumPostiDisponibili(),
                corso.getStato(),
                docente
        );
    }
}
