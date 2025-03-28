package com.capstone.university.controller;

import com.capstone.university.model.*;
import com.capstone.university.model.dto.*;
import com.capstone.university.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    StudenteService studenteService;

    @Autowired
    ArticoloService articoloService;

    @Autowired
    CorsoService corsoService;

    @Autowired
    AuthService authService;


    @PostMapping("/articoli")
    public ResponseEntity<ArticoloResponse> creaArticolo(
            @Valid @RequestBody ArticoloRequest request,
            @AuthenticationPrincipal Admin admin) {

        Articolo articolo = articoloService.creaArticolo(request, admin.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ArticoloResponse.fromEntity(articolo));
    }

    @PutMapping("/articoli/{id}")
    public ResponseEntity<ArticoloResponse> updateArticolo (
            @PathVariable Long id,
            @Valid @RequestBody ArticoloRequest request){
        Articolo updated = adminService.updateArticolo(id, request);
        return ResponseEntity.ok(ArticoloResponse.fromEntity(updated));
    }

    @DeleteMapping("/articoli/{id}")
    public ResponseEntity<Void> deleteArticolo(@PathVariable Long id){
        articoloService.deleteArticolo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/articoli")
    public ResponseEntity<List<ArticoloResponse>> getAllArticoli() {
        List<Articolo> articoli = articoloService.getAllArticoli();
        List<ArticoloResponse> response = articoli.stream()
                .map(ArticoloResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/corsi")
    public ResponseEntity<List<CorsoDTO>> getAllCorsi() {
        List<Corso> corsi = adminService.getAllCorsi();
        List<CorsoDTO> response = corsi.stream()
                .map(CorsoDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/corsi/{id}")
    public ResponseEntity<Void> deleteCorso(@PathVariable Long id){
        corsoService.deleteCorso(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/corsi/{id}")
    public ResponseEntity<CorsoResponseDTO> updateCorso (
            @PathVariable Long id,
            @Valid @RequestBody CorsoRequestDTO request){
        Corso updated = adminService.updateCorso(id,request);
        return ResponseEntity.ok(CorsoResponseDTO.fromEntity(updated));
    }

    @GetMapping("/utenti")
    public ResponseEntity<List<UtenteDTO>> getAllNonAdminUsers() {
        List<Utente> utenti = adminService.getAllNonAdminUsers();
        List<UtenteDTO> response = utenti.stream()
                .map(UtenteDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PutMapping("/utenti/{id}")
    public ResponseEntity<UtenteDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        Utente updated = adminService.updateUser(id, request);
        return ResponseEntity.ok(UtenteDTO.fromEntity(updated));
    }

    @DeleteMapping("/utenti/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/studenti/{id}")
    public ResponseEntity<StudenteProfileDTO> getStudenteProfile(@PathVariable Long id) {
        Studente studente = studenteService.getStudenteById(id);
        return ResponseEntity.ok(StudenteProfileDTO.fromEntity(studente));
    }

}
