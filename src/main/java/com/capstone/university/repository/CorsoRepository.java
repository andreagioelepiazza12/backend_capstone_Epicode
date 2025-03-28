package com.capstone.university.repository;

import com.capstone.university.model.Corso;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CorsoRepository extends JpaRepository <Corso, Long> {

    List<Corso> findByDocenteId(Long docenteId);

    Optional<Corso> findByIdAndDocenteId(Long corsoId, Long docenteId);

    @Query("SELECT c FROM Corso c LEFT JOIN FETCH c.docente")
    List<Corso> findAllWithDocente();

    @Query("SELECT c FROM Corso c WHERE c.id NOT IN (SELECT i.corso.id FROM Iscrizione i WHERE i.studente.id = :studenteId)")
    List<Corso> findCorsiNonIscritti(@Param("studenteId") Long studenteId);
}
