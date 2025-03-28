package com.capstone.university.config;

import com.capstone.university.model.Admin;
import com.capstone.university.model.Docente;
import com.capstone.university.model.Studente;
import com.capstone.university.model.dto.RegisterRequest;
import com.capstone.university.model.enums.Role;
import com.capstone.university.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UtenteRepository utenteRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utenteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Studente registraStudente(RegisterRequest request) {
        if (utenteRepository.existsByUsername(request.nome())) {
            throw new RuntimeException("Username già in uso");
        }

        if (utenteRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email già in uso");
        }

        Studente studente = new Studente();
        studente.setNome(request.nome());
        studente.setCognome(request.cognome());
        studente.setEmail(request.email());
        studente.setUsername(request.username());
        studente.setPassword(new BCryptPasswordEncoder().encode(request.password()));
        studente.setRole(Role.STUDENTE);
        studente.setCodiceMatricola(request.matricola());
        // Altri campi specifici

        return utenteRepository.save(studente);
    }

    public Docente registraDocente(RegisterRequest request) {
        Docente docente = new Docente();
        docente.setNome(request.nome());
        docente.setCognome(request.cognome());
        docente.setEmail(request.email());
        docente.setUsername(request.username());
        docente.setPassword(request.password());
        docente.setRole(request.role());

        return utenteRepository.save(docente);
    }

    public Admin registraAdmin(RegisterRequest request) {
        Admin admin = new Admin ();
        admin.setNome(request.nome());
        admin.setCognome(request.cognome());
        admin.setEmail(request.email());
        admin.setUsername(request.username());
        admin.setPassword(request.password());
        admin.setRole(request.role());

        return utenteRepository.save(admin);

    }

}
