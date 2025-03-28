package com.capstone.university.model.dto;

import java.time.LocalDate;

public record CorsoDocenteDTO(

        Long id,
        String materia,
        LocalDate dataInizio,
        LocalDate dataFine,
        String stato,
        int postiDisponibili,
        int studentiIscritti
) {
}
