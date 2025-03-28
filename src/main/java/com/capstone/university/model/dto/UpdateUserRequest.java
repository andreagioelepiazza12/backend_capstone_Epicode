package com.capstone.university.model.dto;

import com.capstone.university.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(

        @NotBlank String nome,
        @NotBlank String cognome,
        @Email @NotBlank String email

) {


}
