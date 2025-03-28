package com.capstone.university.service;

import com.capstone.university.exceptions.*;
import com.capstone.university.model.Corso;
import com.capstone.university.model.Studente;
import com.capstone.university.model.dto.CorsoDTO;
import com.capstone.university.repository.CorsoRepository;
import com.capstone.university.repository.IscrizioneRepository;
import com.capstone.university.repository.StudenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudenteService {

    @Autowired
    private StudenteRepository studenteRepository;

    @Autowired
    private CorsoRepository corsoRepository;

    @Autowired
    private IscrizioneRepository iscrizioneRepository;

    public Studente getStudenteById(Long id) {
        return studenteRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Studente non trovato con ID: " + id));
    }

    public List<Corso> getCorsi(Long id) {
        return corsoRepository.findCorsiNonIscritti(id);
    }
}
