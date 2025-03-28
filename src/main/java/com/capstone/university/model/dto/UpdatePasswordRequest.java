package com.capstone.university.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordRequest(

        @NotBlank String oldPassword,

        @NotBlank
        @Size(min = 8)
        String newPassword,

        @NotBlank
        @Size(min = 8)
        String confirmPassword
) {
}
