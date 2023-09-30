package com.senior.devrecruitment.entities.technology.DTO;

import com.senior.devrecruitment.entities.technology.TechnologyEntity;
import com.senior.devrecruitment.enums.Framework;
import com.senior.devrecruitment.enums.Language;
import com.senior.devrecruitment.enums.PerformanceArea;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record DTOListTechnologies(@NotBlank Language name,
                                  @NotBlank Framework framework,
                                  @NotBlank List<PerformanceArea> recommendedArea,
                                  @NotBlank String description){

    public DTOListTechnologies(TechnologyEntity technologyEntity){
        this(technologyEntity.getName(), technologyEntity.getFramework(),
                technologyEntity.getRecommendedArea(), technologyEntity.getDescription());
    }
}
