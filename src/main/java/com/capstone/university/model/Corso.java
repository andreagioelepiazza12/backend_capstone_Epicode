package com.capstone.university.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String materia;

    @NotNull
    private LocalDate dataDiInizio;

    @NotNull
    private LocalDate dataDiFine;

    @Min(1)
    private int numPostiDisponibili;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docente_id")
    private Docente docente;

    @OneToMany(mappedBy = "corso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Iscrizione> iscrizioni = new ArrayList<>();

    public boolean isConcluso() {
        return LocalDate.now().isAfter(dataDiFine);
    }

    private String stato;

    public String getStato() {
        return isConcluso() ? "CONCLUSO" : "IN_CORSO";
    }

}
