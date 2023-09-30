package com.senior.devrecruitment.entities.candidacy;

import com.senior.devrecruitment.entities.candidate.CandidateEntity;
import com.senior.devrecruitment.entities.vacancy.VacancyEntity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "Candidatura")
@Table(name = "candidatura")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_candidatura")
public class CandidacyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCandidacy;
    private LocalDate candidacyDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCandidate", referencedColumnName = "idCandidate")
    private CandidateEntity candidateEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVacancy")
    private VacancyEntity vacancyEntity;

    public CandidacyEntity(@Valid CandidateEntity candidateEntity, @Valid VacancyEntity vacancyEntity) {
        this.candidateEntity = candidateEntity;
        this.vacancyEntity = vacancyEntity;
        this.candidacyDate = LocalDate.now();
    }
}
