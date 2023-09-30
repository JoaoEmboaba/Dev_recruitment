package com.senior.devrecruitment.entities.vacancy;

import com.senior.devrecruitment.entities.vacancy.DTO.DTOVacancy;
import com.senior.devrecruitment.enums.PerformanceArea;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "Vacancy")
@Table(name = "vacancy")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idVacancy")

public class VacancyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idVacancy;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private PerformanceArea needVacancy;

    private boolean active = true;

    public VacancyEntity(DTOVacancy vacancy) {
        this.description = vacancy.description();
        this.needVacancy = vacancy.needVacancy();
    }
}
