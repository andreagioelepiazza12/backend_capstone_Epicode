package com.capstone.university.service;

import com.capstone.university.exceptions.*;
import com.capstone.university.model.Corso;
import com.capstone.university.model.Docente;
import com.capstone.university.model.Utente;
import com.capstone.university.model.dto.CorsoRequestDTO;
import com.capstone.university.model.enums.Role;
import com.capstone.university.repository.CorsoRepository;
import com.capstone.university.repository.DocenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CorsoService {

    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    DocenteRepository docenteRepository;

    @Transactional
    public Corso createCorso(CorsoRequestDTO corsoDTO, Utente currentUser) {
        // Validazione date
        if (corsoDTO.dataDiFine().isBefore(corsoDTO.dataDiInizio())) {
            throw new InvalidDateException("La data di fine deve essere successiva alla data di inizio");
        }

        Corso corso = new Corso();
        corso.setMateria(corsoDTO.materia());
        corso.setDataDiInizio(corsoDTO.dataDiInizio());
        corso.setDataDiFine(corsoDTO.dataDiFine());
        corso.setNumPostiDisponibili(corsoDTO.numPostiDisponibili());
        corso.setStato("IN_CORSO");

        // Assegnazione docente
        if (currentUser.getRole() == Role.ADMIN) {
            // Admin può specificare il docente
            Docente docente = docenteRepository.findById(corsoDTO.docenteId())
                    .orElseThrow(() -> new DocenteNotFoundException("Docente non trovato"));
            corso.setDocente(docente);
        } else if (currentUser.getRole() == Role.DOCENTE) {
            // Docente può solo auto-assegnarsi
            Docente docente = (Docente) currentUser;
            corso.setDocente(docente);
        } else {
            throw new UnauthorizedActionException("Solo Admin o Docente possono creare corsi");
        }

        return corsoRepository.save(corso);
    }

    public void deleteCorso(Long id) {
        Corso corso = corsoRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Corso non trovato"));
        corsoRepository.delete(corso);
    }
}
