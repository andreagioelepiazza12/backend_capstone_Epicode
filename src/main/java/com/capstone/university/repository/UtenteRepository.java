package com.capstone.university.repository;

import com.capstone.university.model.Docente;
import com.capstone.university.model.Studente;
import com.capstone.university.model.Utente;
import com.capstone.university.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Optional<Utente> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    List<Utente> findByRoleNot(Role role);

    @Query("SELECT u FROM Utente u WHERE TYPE(u) = Studente")
    List<Studente> findAllStudenti();

    @Query("SELECT u FROM Utente u WHERE TYPE(u) = Docente")
    List<Docente> findAllDocenti();
}
