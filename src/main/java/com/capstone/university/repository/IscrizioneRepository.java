package com.capstone.university.repository;


import com.capstone.university.model.Iscrizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Repository
public interface IscrizioneRepository extends JpaRepository <Iscrizione, Long> {

    boolean existsByStudenteIdAndCorsoId(Long studenteId, Long corsoId);

    List<Iscrizione> findByStudenteId(Long id);

    List<Iscrizione> findByCorsoIdAndCorsoDocenteId(Long corsoId, Long docenteId);

    Optional<Iscrizione> findByStudenteIdAndCorsoId(Long studenteId, Long corsoId);
}
