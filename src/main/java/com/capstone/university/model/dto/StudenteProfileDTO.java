package com.capstone.university.model.dto;

import com.capstone.university.model.Studente;

import java.time.LocalDate;
import java.util.List;

public record StudenteProfileDTO(
        Long id,
        String nome,
        String cognome,
        String email,
        String username,
        String codiceMatricola,
        LocalDate dataNascita,
        List<IscrizioneResponseDTO> iscrizioni
) {
    public static StudenteProfileDTO fromEntity(Studente studente) {
        return new StudenteProfileDTO(
                studente.getId(),
                studente.getNome(),
                studente.getCognome(),
                studente.getEmail(),
                studente.getUsername(),
                studente.getCodiceMatricola(),
                studente.getDataNascita(),
                studente.getIscrizioni().stream()
                        .map(IscrizioneResponseDTO::fromEntity)
                        .toList()
        );
    }

}
