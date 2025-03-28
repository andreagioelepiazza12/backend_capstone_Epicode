package com.capstone.university.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AssegnaVotoDTO(
        @NotNull Long studenteId,
        @NotNull Long corsoId,
        @Min(0) @Max(30) Double voto
) {
}
