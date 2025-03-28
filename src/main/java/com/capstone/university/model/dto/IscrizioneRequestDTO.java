package com.capstone.university.model.dto;

import jakarta.validation.constraints.NotNull;

public record IscrizioneRequestDTO(
        @NotNull Long corsoId
) {
}
