package com.capstone.university.model.dto;

import jakarta.validation.constraints.NotBlank;

public record ArticoloRequest(
        @NotBlank String titolo,
        @NotBlank String testo,
        @NotBlank String autore,
        String urlImg,
        String descrizione
) {
}
