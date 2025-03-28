package com.capstone.university.service;

import com.capstone.university.exceptions.*;
import com.capstone.university.model.Corso;
import com.capstone.university.model.Iscrizione;
import com.capstone.university.model.Studente;
import com.capstone.university.repository.CorsoRepository;
import com.capstone.university.repository.IscrizioneRepository;
import com.capstone.university.repository.StudenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
@Transactional
public class IscrizioneService {

    @Autowired
    IscrizioneRepository iscrizioneRepository;

    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    StudenteRepository studenteRepository;


    @Transactional
    public Iscrizione iscriviStudente(Long studenteId, Long corsoId) {

        log.debug("Tentativo iscrizione studente {} a corso {}", studenteId, corsoId);
        try {
            // 1. Verifica esistenza corso
            Corso corso = corsoRepository.findById(corsoId)
                    .orElseThrow(() -> new CourseNotFoundException(corsoId));

            // 2. Verifica disponibilità
            if (corso.getNumPostiDisponibili() <= 0) {
                throw new IllegalStateException("Posti esauriti per questo corso");
            }

            // 3. Verifica se già iscritto
            if (iscrizioneRepository.existsByStudenteIdAndCorsoId(studenteId, corsoId)) {
                throw new IllegalStateException("Sei già iscritto a questo corso");
            }

            // 4. Crea iscrizione
            Studente studente = studenteRepository.findById(studenteId)
                    .orElseThrow(() -> new UserNotFoundException("Studente non trovato"));

            Iscrizione iscrizione = new Iscrizione();
            iscrizione.setStudente(studente);
            iscrizione.setCorso(corso);
            iscrizione.setVoto(null);

            // 5. Aggiorna posti disponibili
            corso.setNumPostiDisponibili(corso.getNumPostiDisponibili() - 1);
            corsoRepository.save(corso);

            return iscrizioneRepository.save(iscrizione);
        }catch (Exception e) {
            log.error("Fallita iscrizione", e);
            throw e;
        }

    }

    public List<Iscrizione> getIscrizioniStudente(Long studenteId) {
        return iscrizioneRepository.findByStudenteId(studenteId);
    }


}
