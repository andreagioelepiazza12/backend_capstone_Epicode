package com.capstone.university.model.dto;

public record StudenteIscrittoDTO(
        Long id,
        String nomeCompleto,
        String email,
        String codiceMatricola,
        Double voto
) {
}
