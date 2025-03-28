package com.capstone.university.model.dto;

public record UserInfoResponse(

        Long id,
        String username,
        String email,
        String nomeCompleto,
        String role,

        // Campi specifici per tipo utente
        String matricola
) {
}
