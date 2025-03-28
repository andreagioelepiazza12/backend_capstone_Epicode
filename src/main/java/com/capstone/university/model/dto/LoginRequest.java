package com.capstone.university.model.dto;

import jakarta.validation.constraints.NotBlank;


public record LoginRequest(

        @NotBlank(message = "Username obbligatorio")
        String username,

        @NotBlank(message = "Password obbligatoria")
        String password
) {

}
