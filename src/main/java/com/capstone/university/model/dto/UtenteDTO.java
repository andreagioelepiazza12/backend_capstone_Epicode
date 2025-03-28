package com.capstone.university.model.dto;

import com.capstone.university.model.Utente;
import com.capstone.university.model.enums.Role;

public record UtenteDTO(
        Long id,
        String nome,
        String cognome,
        String email,
        String username,
        Role role,
        String tipoUtente
) {

    public static UtenteDTO fromEntity(Utente utente) {
        String tipoUtente = switch (utente.getRole()) {
            case DOCENTE -> "Docente";
            case STUDENTE -> "Studente";
            default -> "Admin";
        };
        return new UtenteDTO(
                utente.getId(),
                utente.getNome(),
                utente.getCognome(),
                utente.getEmail(),
                utente.getUsername(),
                utente.getRole(),
                tipoUtente
        );
    }

}
