package com.senior.devrecruitment.entities.tecnologia.DTO;

import com.senior.devrecruitment.entities.tecnologia.Tecnologia;
import com.senior.devrecruitment.enums.AreaAconselhavel;
import com.senior.devrecruitment.enums.Framework;
import com.senior.devrecruitment.enums.Linguagens;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record DTOListagemTecnologia (@NotBlank Linguagens nome,
                                     @NotBlank Framework framework,
                                     @NotBlank List<AreaAconselhavel> areaAconselhavel,
                                     @NotBlank String descricao){

    public DTOListagemTecnologia(Tecnologia tecnologia){
        this(tecnologia.getNome(), tecnologia.getFramework(),
                tecnologia.getAreaaconselhavel(), tecnologia.getDescricao());
    }
}
