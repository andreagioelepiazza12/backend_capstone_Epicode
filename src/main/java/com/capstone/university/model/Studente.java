package com.capstone.university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Studente extends Utente{


    private String codiceMatricola;
    private LocalDate dataNascita;

    @OneToMany(mappedBy = "studente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Iscrizione> iscrizioni;
}
