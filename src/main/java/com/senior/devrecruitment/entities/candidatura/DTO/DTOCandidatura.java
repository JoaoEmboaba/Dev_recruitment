package com.senior.devrecruitment.entities.candidatura.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.*;

public record DTOCandidatura(@NotNull UUID id_candidato, @NotNull UUID id_vaga) {

}