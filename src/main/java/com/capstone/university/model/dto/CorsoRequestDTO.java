package com.capstone.university.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CorsoRequestDTO(

        @NotBlank String materia,
        @NotNull LocalDate dataDiInizio,
        @NotNull LocalDate dataDiFine,
        @Min(1) int numPostiDisponibili,
        Long docenteId
) {
}
