package com.senior.devrecruitment.entities.candidacy.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.*;

public record DTOCandidacy(@NotNull UUID id_candidato, @NotNull UUID id_vaga) {

}