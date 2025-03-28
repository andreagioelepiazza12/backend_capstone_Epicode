package com.capstone.university.repository;

import com.capstone.university.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {

    List<Docente> findAllByOrderByCognomeAsc();
}
