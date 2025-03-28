package com.capstone.university.service;

import com.capstone.university.exceptions.CourseNotFoundException;
import com.capstone.university.exceptions.EnrollmentNotFoundException;
import com.capstone.university.exceptions.InvalidGradeAssignmentException;
import com.capstone.university.model.Corso;
import com.capstone.university.model.Iscrizione;
import com.capstone.university.model.dto.CorsoDocenteDTO;
import com.capstone.university.model.dto.StudenteIscrittoDTO;
import com.capstone.university.repository.CorsoRepository;
import com.capstone.university.repository.DocenteRepository;
import com.capstone.university.repository.IscrizioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private CorsoRepository corsoRepository;

    @Autowired
    private IscrizioneRepository iscrizioneRepository;

    public Map<String, List<CorsoDocenteDTO>> getCorsiOrganizzati(Long docenteId) {
        List<Corso> corsi = corsoRepository.findByDocenteId(docenteId);

        List<CorsoDocenteDTO> corsiInCorso = corsi.stream()
                .filter(c -> !c.isConcluso())
                .map(this::convertToDTO)
                .toList();

        List<CorsoDocenteDTO> corsiConclusi = corsi.stream()
                .filter(Corso::isConcluso)
                .map(this::convertToDTO)
                .toList();

        return Map.of(
                "in_corso", corsiInCorso,
                "conclusi", corsiConclusi
        );
    }

    private CorsoDocenteDTO convertToDTO(Corso corso) {
        return new CorsoDocenteDTO(
                corso.getId(),
                corso.getMateria(),
                corso.getDataDiInizio(),
                corso.getDataDiFine(),
                corso.isConcluso() ? "CONCLUSO" : "IN_CORSO",
                corso.getNumPostiDisponibili(),
                corso.getIscrizioni().size()
        );
    }

    public List<StudenteIscrittoDTO> getStudentiIscrittiAlCorso(Long corsoId, Long docenteId) {
        return iscrizioneRepository.findByCorsoIdAndCorsoDocenteId(corsoId, docenteId)
                .stream()
                .map(iscrizione -> new StudenteIscrittoDTO(
                        iscrizione.getStudente().getId(),
                        iscrizione.getStudente().getNomeCompleto(),
                        iscrizione.getStudente().getEmail(),
                        iscrizione.getStudente().getCodiceMatricola(),
                        iscrizione.getVoto()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public Iscrizione assegnaVoto(Long studenteId, Long corsoId, Double voto, Long docenteId) {

        Corso corso = corsoRepository.findByIdAndDocenteId(corsoId, docenteId)
                .orElseThrow(() -> new CourseNotFoundException(corsoId));

        if (!corso.isConcluso()) {
            throw new IllegalStateException("Impossibile assegnare voti: il corso non è concluso");
        }


        Iscrizione iscrizione = iscrizioneRepository.findByStudenteIdAndCorsoId(studenteId, corsoId)
                .orElseThrow(() -> new EnrollmentNotFoundException("Studente non iscritto al corso"));

        iscrizione.setVoto(voto);
        return iscrizioneRepository.save(iscrizione);
    }

}
