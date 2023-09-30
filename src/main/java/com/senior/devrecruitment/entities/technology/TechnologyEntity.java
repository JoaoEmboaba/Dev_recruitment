package com.senior.devrecruitment.entities.technology;

import com.senior.devrecruitment.entities.candidate.CandidateEntity;
import com.senior.devrecruitment.entities.technology.DTO.DTOTechnology;
import com.senior.devrecruitment.enums.Framework;
import com.senior.devrecruitment.enums.Language;
import com.senior.devrecruitment.enums.PerformanceArea;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Technology")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idTech")
@Getter
@Builder
@Setter
public class TechnologyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idTech;

    @Enumerated(EnumType.STRING)
    private Language name;

    @Enumerated(EnumType.STRING)
    private Framework framework;

    @Enumerated(EnumType.STRING)
    private List<PerformanceArea> recommendedArea;

    private String description;

    private boolean active = true;

    @ManyToMany(mappedBy = "candidateTechnologies")
    private List<CandidateEntity> candidateEntities;

    public TechnologyEntity(DTOTechnology technology, CandidateEntity candidateEntity) {
        this.setCandidateEntities(new ArrayList<>());
        this.getCandidateEntities().add(candidateEntity);
        this.name = technology.name();
        this.framework = technology.framework();
        this.description = technology.description();
    }

    public TechnologyEntity(DTOTechnology technology) {
        this.idTech = UUID.randomUUID();
        this.name = technology.name();
        this.framework = technology.framework();
        this.description = technology.description();
    }
}
