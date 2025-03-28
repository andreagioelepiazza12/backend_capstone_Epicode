package com.capstone.university.service;


import com.capstone.university.exceptions.UserNotFoundException;
import com.capstone.university.model.Docente;
import com.capstone.university.model.Studente;
import com.capstone.university.model.Utente;
import com.capstone.university.model.enums.Role;
import com.capstone.university.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UtenteService {

    @Autowired
    UtenteRepository utenteRepository;


}
