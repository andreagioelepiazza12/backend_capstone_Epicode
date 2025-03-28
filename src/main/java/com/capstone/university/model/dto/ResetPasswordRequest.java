package com.capstone.university.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequest (

        @Email @NotBlank String email,
        String token,  // Per la fase di conferma
        String newPassword
){
}
