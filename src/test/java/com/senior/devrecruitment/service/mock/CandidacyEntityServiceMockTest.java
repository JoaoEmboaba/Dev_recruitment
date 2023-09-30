package com.senior.devrecruitment.service.mock;

import com.senior.devrecruitment.entities.candidacy.CandidacyEntity;
import com.senior.devrecruitment.entities.candidacy.CandidacyService;
import com.senior.devrecruitment.entities.candidate.CandidateEntity;
import com.senior.devrecruitment.entities.candidate.CandidateService;
import com.senior.devrecruitment.entities.vacancy.VacancyEntity;
import com.senior.devrecruitment.entities.vacancy.VacancyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidacyEntityServiceMockTest {

    @InjectMocks
    private CandidacyService candidacyService;
    @Mock
    private CandidateService candidateService;
    @Mock
    private VacancyService vacancyService;
    private CandidacyEntity candidacyEntity;
    private CandidateEntity candidateEntity;
    private VacancyEntity vacancyEntity;

    @Test
    @DisplayName("Testando a assertividade com o argument captor no teste do service de realização da candidatura")
    void testeCenario001(){

        candidateEntity = new CandidateEntity();
        candidateEntity.setIdCandidate(UUID.fromString("55ed20d0-1a6c-11ee-be56-0242ac120002"));
        vacancyEntity = new VacancyEntity();
        vacancyEntity.setIdVacancy(UUID.fromString("7b198538-1a6c-11ee-be56-0242ac120002"));


        candidacyService.realizarCandidatura(candidateEntity.getIdCandidate(), vacancyEntity.getIdVacancy());

        when(candidacyService.findById(candidacyEntity.getIdCandidacy())).thenReturn(Optional.of(candidacyEntity));

        verify(candidacyService).findById(candidacyEntity.getIdCandidacy());
        verify(candidacyService).findById(candidateEntity.getIdCandidate());
        verify(candidacyService).findById(vacancyEntity.getIdVacancy());
    }
}
