package com.senior.devrecruitment.entities.vacancy.DTO;

import com.senior.devrecruitment.entities.vacancy.VacancyEntity;
import com.senior.devrecruitment.enums.PerformanceArea;
import jakarta.validation.constraints.NotBlank;

public record DTOListVacancies(@NotBlank String description, @NotBlank PerformanceArea needVacancy) {

    public DTOListVacancies(VacancyEntity vacancyEntity) {
        this(vacancyEntity.getDescription(), vacancyEntity.getNeedVacancy());
    }
}
