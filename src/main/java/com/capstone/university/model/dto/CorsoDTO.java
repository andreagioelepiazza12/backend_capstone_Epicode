package com.capstone.university.model.dto;

import com.capstone.university.model.Corso;

import java.time.LocalDate;

public record CorsoDTO(
        Long id,
        String materia,
        String docenteNome,
        LocalDate dataDiInizio,
        LocalDate dataDiFine,
        String stato,
        int numPostiDisponibili
) {

    public static CorsoDTO fromEntity(Corso corso) {
        return new CorsoDTO(
                corso.getId(),
                corso.getMateria(),
                corso.getDocente() != null ? corso.getDocente().getNomeCompleto() : "N/D",
                corso.getDataDiInizio(),
                corso.getDataDiFine(),
                corso.getStato(),
                corso.getNumPostiDisponibili()
        );
    }
}
