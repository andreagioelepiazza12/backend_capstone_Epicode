package com.capstone.university.repository;

import com.capstone.university.model.Articolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticoloRepository extends JpaRepository<Articolo, Long> {
    List<Articolo> findAllByOrderByDataPubblicazioneDesc();
}
