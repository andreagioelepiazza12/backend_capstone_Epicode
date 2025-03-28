package com.capstone.university.model.dto;

public record AuthResponse(

        String token,
        String username,
        String role,
        String fullName
) {
}
