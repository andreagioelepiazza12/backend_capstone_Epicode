package com.capstone.university.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterAdminRequest(

        @NotBlank String nome,
        @NotBlank String cognome,
        @Email @NotBlank String email,
        @NotBlank String username,
        @NotBlank @Size(min = 8) String password
) {
}
