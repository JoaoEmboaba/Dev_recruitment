package com.senior.devrecruitment.entities.vacancy.DTO;

import com.senior.devrecruitment.enums.PerformanceArea;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOVacancy(@NotBlank String description, @NotNull PerformanceArea needVacancy) {
}
