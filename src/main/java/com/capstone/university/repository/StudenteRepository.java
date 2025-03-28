package com.capstone.university.repository;

import com.capstone.university.model.Studente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudenteRepository extends JpaRepository<Studente, Long> {

    List<Studente> findAllByOrderByCognomeAsc();

}
