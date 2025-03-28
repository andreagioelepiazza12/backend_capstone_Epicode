package com.capstone.university.model.dto;

import com.capstone.university.model.Iscrizione;

public record IscrizioneResponseDTO(
        Long id,
        StudenteInfoDTO studente,
        CorsoInfoDTO corso,
        Double voto
) {

    public static IscrizioneResponseDTO fromEntity(Iscrizione iscrizione) {
        return new IscrizioneResponseDTO(
                iscrizione.getId(),
                new StudenteInfoDTO(iscrizione.getStudente().getId(), iscrizione.getStudente().getNomeCompleto()),
                new CorsoInfoDTO(iscrizione.getCorso().getId(), iscrizione.getCorso().getMateria()),
                iscrizione.getVoto()
        );
    }

}
