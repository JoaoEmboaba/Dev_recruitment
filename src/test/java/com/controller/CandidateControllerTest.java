package com.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class CandidateControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Deve retornar código 400 caso haja algum dado inválido ou não informado")
    void cadastrarCandidatoCenario01() throws Exception {
        var response = mvc.perform(post("/candidato"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("Deve retornar código 405 caso o método seja inválido ao da requisição")
    void listarCandidatosCenario01() throws Exception {
        var response = mvc.perform(post("/candidato/contratados"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED.value());
    }
}
