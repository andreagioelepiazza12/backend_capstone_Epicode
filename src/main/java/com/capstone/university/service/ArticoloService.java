package com.capstone.university.service;

import com.capstone.university.exceptions.UserNotFoundException;
import com.capstone.university.model.Articolo;
import com.capstone.university.model.Utente;
import com.capstone.university.model.dto.ArticoloRequest;
import com.capstone.university.repository.ArticoloRepository;
import com.capstone.university.repository.UtenteRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticoloService {

    @Autowired
    ArticoloRepository articoloRepository;

    @Autowired
    UtenteRepository utenteRepository;

    @Transactional
    public Articolo creaArticolo(ArticoloRequest request, Long adminId) {
        Utente admin = utenteRepository.findById(adminId)
                .orElseThrow(() -> new UserNotFoundException("Admin non trovato"));

        Articolo articolo = new Articolo();
        articolo.setTitolo(request.titolo());
        articolo.setTesto(request.testo());
        articolo.setUrlImg(request.urlImg());
        articolo.setDescrizione(request.descrizione());
        articolo.setAutore(request.autore());

        return articoloRepository.save(articolo);
    }

    public List<Articolo> getAllArticoli() {
        return articoloRepository.findAllByOrderByDataPubblicazioneDesc();
    }

    public Articolo getArticoloById(Long id) {
        return articoloRepository.findById(id).orElseThrow();
    }

    public void deleteArticolo(Long id) {
        Articolo articolo = articoloRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Articolo non trovato"));

        articoloRepository.delete(articolo);

    }

}
