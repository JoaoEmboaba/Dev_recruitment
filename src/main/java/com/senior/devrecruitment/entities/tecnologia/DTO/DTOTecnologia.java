package com.senior.devrecruitment.entities.tecnologia.DTO;

import com.senior.devrecruitment.enums.AreaAconselhavel;
import com.senior.devrecruitment.enums.Framework;
import com.senior.devrecruitment.enums.Linguagens;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOTecnologia(@NotNull Linguagens nome,
                            @NotNull Framework framework,
                            @NotBlank String descricao) {
}
