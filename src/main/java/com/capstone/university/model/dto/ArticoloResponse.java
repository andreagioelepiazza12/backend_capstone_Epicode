package com.capstone.university.model.dto;

import com.capstone.university.model.Articolo;

import java.time.LocalDate;

public record ArticoloResponse(
        Long id,
        String titolo,
        String testo,
        String urlImg,
        String descrizione,
        LocalDate dataPubblicazione,
        String autoreNomeCompleto
) {

    public static ArticoloResponse fromEntity(Articolo articolo) {
        return new ArticoloResponse(
                articolo.getId(),
                articolo.getTitolo(),
                articolo.getTesto(),
                articolo.getUrlImg(),
                articolo.getDescrizione(),
                articolo.getDataPubblicazione(),
                articolo.getAutore()
        );
    }

}
