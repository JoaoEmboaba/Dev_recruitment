package com.service.mock;

import devrecruitment.candidacy.Candidacy;
import devrecruitment.candidacy.CandidacyService;
import devrecruitment.candidate.Candidate;
import devrecruitment.candidate.CandidateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import devrecruitment.vacancy.Vacancy;
import devrecruitment.vacancy.VacancyService;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidacyServiceMockTest {

    @InjectMocks
    private CandidacyService candidacyService;
    @Mock
    private CandidateService candidateService;
    @Mock
    private VacancyService vacancyService;
    private Candidacy candidacy;
    private Candidate candidate;
    private Vacancy vacancy;

    @Test
    @DisplayName("Testando a assertividade com o argument captor no teste do service de realização da candidatura")
    void testeCenario001(){

        candidate = new Candidate();
        candidate.setId(UUID.fromString("55ed20d0-1a6c-11ee-be56-0242ac120002"));
        vacancy = new Vacancy();
        vacancy.setId(UUID.fromString("7b198538-1a6c-11ee-be56-0242ac120002"));


        candidacyService.makeAnApplication(candidate.getId(), vacancy.getId());

        when(candidacyService.findById(candidacy.getId())).thenReturn(Optional.of(candidacy));

        verify(candidacyService).findById(candidacy.getId());
        verify(candidacyService).findById(candidate.getId());
        verify(candidacyService).findById(vacancy.getId());
    }
}
