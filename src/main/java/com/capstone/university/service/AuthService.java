package com.capstone.university.service;

import com.capstone.university.config.JwtTokenUtil;
import com.capstone.university.exceptions.EmailAlreadyExistsException;
import com.capstone.university.exceptions.InvalidCredentialsException;
import com.capstone.university.exceptions.UserAlreadyExistsException;
import com.capstone.university.model.Admin;
import com.capstone.university.model.Docente;
import com.capstone.university.model.Studente;
import com.capstone.university.model.Utente;
import com.capstone.university.model.dto.AuthResponse;
import com.capstone.university.model.dto.LoginRequest;
import com.capstone.university.model.dto.RegisterRequest;
import com.capstone.university.model.enums.Role;
import com.capstone.university.repository.UtenteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    PasswordEncoder pe;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        var user = (Utente) auth.getPrincipal();
        return new AuthResponse(
                jwtTokenUtil.generateToken(user),
                user.getUsername(),
                user.getRole().name(),
                user.getNomeCompleto()
        );
    }

    public AuthResponse register(RegisterRequest request) {
        if (utenteRepository.existsByUsername(request.username())) {
            throw new UserAlreadyExistsException("Username già in uso in un altro profilo");
        }

        if (utenteRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("Email già in uso in un altro profilo");
        }

        Utente newUser = createUserByRole(request);
        utenteRepository.save(newUser);

        return new AuthResponse(
                jwtTokenUtil.generateToken(newUser),
                newUser.getUsername(),
                newUser.getRole().name(),
                newUser.getNomeCompleto()
        );
    }

    private Utente createUserByRole(RegisterRequest request) {
        return switch (request.role()) {
            case STUDENTE -> {
                var s = new Studente();
                s.setCodiceMatricola(request.matricola());
                yield buildUser(s, request);
            }
            case DOCENTE -> {
                var d = new Docente();
                yield buildUser(d, request);
            }
            case ADMIN -> {
                var a = new Admin();
                yield buildUser(a, request);
            }
        };
    }

    private Utente buildUser(Utente user, RegisterRequest request) {
        user.setNome(request.nome());
        user.setCognome(request.cognome());
        user.setEmail(request.email());
        user.setUsername(request.username());
        user.setPassword(pe.encode(request.password()));
        user.setRole(request.role());
        return user;
    }

}
