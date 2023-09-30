package com.senior.devrecruitment.entities.technology.DTO;

import com.senior.devrecruitment.enums.Framework;
import com.senior.devrecruitment.enums.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOTechnology(@NotNull Language name,
                            @NotNull Framework framework,
                            @NotBlank String description) {
}
