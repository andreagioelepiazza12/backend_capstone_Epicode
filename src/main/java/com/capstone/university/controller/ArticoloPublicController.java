package com.capstone.university.controller;

import com.capstone.university.model.Articolo;
import com.capstone.university.model.dto.ArticoloResponse;
import com.capstone.university.service.ArticoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/articoli")
public class ArticoloPublicController {

    @Autowired
    private ArticoloService articoloService;

    @GetMapping
    public ResponseEntity<List<ArticoloResponse>> getArticoliPubblici() {
        List<Articolo> articoli = articoloService.getAllArticoli();
        List<ArticoloResponse> response = articoli.stream()
                .map(ArticoloResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticoloResponse> getArticoloById(@PathVariable Long id) {
        Articolo articolo = articoloService.getArticoloById(id);
        return ResponseEntity.ok(ArticoloResponse.fromEntity(articolo));
    }
}
