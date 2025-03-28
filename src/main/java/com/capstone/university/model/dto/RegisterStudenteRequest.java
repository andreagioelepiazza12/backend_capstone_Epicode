package com.capstone.university.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterStudenteRequest(

        @NotBlank String nome,
        @NotBlank String cognome,
        @Email @NotBlank String email,
        @NotBlank String username,
        @NotBlank @Size(min = 8) String password,

        @NotBlank(message = "Matricola obbligatoria")
        @Pattern(regexp = "^[0-9]{6}$", message = "Matricola deve avere 6 cifre")
        String matricola
) {
}
