package com.capstone.university.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Articolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titolo;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String testo;

    private String urlImg;

    @Column(columnDefinition = "TEXT")
    private String descrizione;

    @NotNull
    private LocalDate dataPubblicazione;

    private String autore;

    @PrePersist
    protected void onCreate() {
        dataPubblicazione = LocalDate.now();
    }
}
