package com.senior.devrecruitment.repository;

import com.senior.devrecruitment.entities.candidato.Candidato;
import com.senior.devrecruitment.entities.tecnologia.DTO.DTOTecnologia;
import com.senior.devrecruitment.entities.tecnologia.Tecnologia;
import com.senior.devrecruitment.enums.AreaAconselhavel;
import com.senior.devrecruitment.enums.Framework;
import com.senior.devrecruitment.enums.Linguagens;
import com.senior.devrecruitment.projection.TecnologiaProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CandidatoRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CandidatoRepository candidatoRepository;
    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    @Test
    void findTecnologiasByIdCandidato() {


        Candidato candidato = Candidato.builder().nome("João").nacionalidade("Brasileiro").dataNascimento(LocalDate.now()).ativo(true).contratado(true).build();
        candidatoRepository.save(candidato);

        Tecnologia tecnologia1 = Tecnologia.builder().nome(Linguagens.JS).framework(Framework.EXPRESS).areaaconselhavel(List.of(AreaAconselhavel.BACKEND)).descricao("Linguagem de tipagem fraca").candidatoes(List.of(candidato)).build();
        tecnologiaRepository.save(tecnologia1);

        Tecnologia tecnologia2 = Tecnologia.builder().nome(Linguagens.JS).framework(Framework.EXPRESS).areaaconselhavel(List.of(AreaAconselhavel.CIENCIA_DE_DADOS)).descricao("Linguagem de tipagem fraca").candidatoes(List.of(candidato)).build();
        tecnologiaRepository.save(tecnologia2);

        candidato.setTecnologiasCandidatoes(List.of(tecnologia1, tecnologia2));
        tecnologia1.setCandidatoes(List.of(candidato));
        tecnologia2.setCandidatoes(List.of(candidato));

        assertThat(candidatoRepository.findTecnologiasByIdCandidato(candidato.getIdCandidato()).get(0).getNome()).isEqualTo(Linguagens.JS);
        assertThat(candidatoRepository.findTecnologiasByIdCandidato(candidato.getIdCandidato())).isNotNull();

    }
}