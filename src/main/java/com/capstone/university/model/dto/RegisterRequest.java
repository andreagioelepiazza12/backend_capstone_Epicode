package com.capstone.university.model.dto;

import com.capstone.university.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest (

        @NotBlank String nome,
        @NotBlank String cognome,
        @Email @NotBlank String email,
        @NotBlank String username,
        @NotBlank @Size(min = 8) String password,
        Role role,
        String matricola
) {
}
