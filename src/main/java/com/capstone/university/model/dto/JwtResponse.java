package com.capstone.university.model.dto;

public record JwtResponse(

        String token,
        String username,
        String role,
        String nomeCompleto
) {
}
