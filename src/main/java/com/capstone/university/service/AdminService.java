package com.capstone.university.service;

import com.capstone.university.exceptions.*;
import com.capstone.university.model.*;
import com.capstone.university.model.dto.ArticoloRequest;
import com.capstone.university.model.dto.CorsoRequestDTO;
import com.capstone.university.model.dto.UpdateUserRequest;
import com.capstone.university.model.enums.Role;
import com.capstone.university.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private StudenteRepository studenteRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private ArticoloRepository articoloRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    CorsoRepository corsoRepository;


    public List<Utente> getAllNonAdminUsers() {
        return utenteRepository.findByRoleNot(Role.ADMIN);
    }

    public Utente updateUser(Long id, UpdateUserRequest request) {
        Utente utente = utenteRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

        if(utente.getRole() == Role.ADMIN) {
            throw new OperationNotAllowedException("Non puoi modificare un admin");
        }

        utente.setNome(request.nome());
        utente.setCognome(request.cognome());
        utente.setEmail(request.email());

        return utenteRepository.save(utente);
    }

    public Articolo updateArticolo(Long id, ArticoloRequest request) {
        Articolo articolo = articoloRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Articolo non trovato"));
        articolo.setAutore(request.autore());
        articolo.setTitolo(request.titolo());
        articolo.setTesto(request.testo());
        articolo.setUrlImg(request.urlImg());
        articolo.setDescrizione(request.descrizione());

        return articoloRepository.save(articolo);
    }

    public Corso updateCorso(Long id, CorsoRequestDTO request) {
        Corso corso = corsoRepository.findById(id)
                .orElseThrow(() ->new CourseNotFoundException(id));
        corso.setDocente(docenteRepository.findById(request.docenteId()).orElseThrow(() -> new UserNotFoundException("Docente non trovato")));
        corso.setMateria(request.materia());
        corso.setNumPostiDisponibili(request.numPostiDisponibili());
        corso.setDataDiInizio(request.dataDiInizio());
        corso.setDataDiFine(request.dataDiFine());
        corso.setNumPostiDisponibili(request.numPostiDisponibili());

        return corsoRepository.save(corso);
    }


    public void deleteUser(Long id) {
        Utente utente = utenteRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato"));

        if(utente.getRole() == Role.ADMIN) {
            throw new OperationNotAllowedException("Non puoi eliminare un admin");
        }


        if(utente instanceof Studente) {
            studenteRepository.delete((Studente) utente);
        } else if(utente instanceof Docente) {
            docenteRepository.delete((Docente) utente);
        } else {
            utenteRepository.delete(utente);
        }
    }

    public List<Corso> getAllCorsi() {
        return corsoRepository.findAllWithDocente();
    }


}
