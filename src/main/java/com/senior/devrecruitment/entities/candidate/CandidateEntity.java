package com.senior.devrecruitment.entities.candidate;

import com.senior.devrecruitment.entities.candidate.DTO.DTOCandidate;
import com.senior.devrecruitment.entities.candidacy.CandidacyEntity;
import com.senior.devrecruitment.entities.technology.TechnologyEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Candidate")
@Table(name = "candidate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "idCandidate")

public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCandidate;
    private String name;
    private String nacionality;
    private LocalDate birthDate;
    private boolean hired;
    private boolean active = true;

    @ManyToMany
    @JoinTable(name = "candidate-technology", joinColumns = @JoinColumn(name = "idCandidate"), inverseJoinColumns = @JoinColumn(name = "id_tech"))
    private List<TechnologyEntity> technologies = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "candidacy", joinColumns = @JoinColumn(name = "idCandidate"), inverseJoinColumns = @JoinColumn(name = "idVacancy"))
    private List<CandidacyEntity> candidacies = new ArrayList<>();

    public CandidateEntity(DTOCandidate candidate) {
        this.idCandidate = UUID.randomUUID();
        this.name = candidate.name();
        this.nacionality = candidate.nacionality();
        this.birthDate = candidate.birthDate();
        this.hired = candidate.hired();
    }
}
