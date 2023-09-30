package com.senior.devrecruitment.entities.vacancy;

import com.senior.devrecruitment.entities.vacancy.DTO.DTOListVacancies;
import com.senior.devrecruitment.entities.vacancy.DTO.DTOVacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VacancyService {

    @Autowired
    private VacancyRepository vacancyRepository;

    public void createVacancy(DTOVacancy dtoVacancy) {
        vacancyRepository.save(new VacancyEntity(dtoVacancy));
    }

    public Page<DTOListVacancies> listAllVacancies(Pageable page) {
        return vacancyRepository.findAll(page).map(DTOListVacancies::new);
    }

    public Optional<VacancyEntity> findVacancyById(UUID idVacancy) {
        return vacancyRepository.findById(idVacancy);
    }

    public ResponseEntity<VacancyEntity> inativateVacancy(UUID idVaga) {
        vacancyRepository.findById(idVaga).ifPresent(vaga -> {
            vaga.setActive(false);
            vacancyRepository.save(vaga);
        });

        return ResponseEntity.noContent().build();
    }
}
