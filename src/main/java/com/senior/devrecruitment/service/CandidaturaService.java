package com.senior.devrecruitment.service;

import com.senior.devrecruitment.entities.candidato.Candidato;
import com.senior.devrecruitment.entities.candidatura.Candidatura;
import com.senior.devrecruitment.entities.vaga.Vaga;
import com.senior.devrecruitment.repository.CandidatoRepository;
import com.senior.devrecruitment.repository.CandidaturaRepository;
import com.senior.devrecruitment.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CandidaturaService {

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private VagaRepository vagaRepository;

//    public List<CandidaturaProjection> buscarCandidatosPelaVaga(UUID idVaga) {
//        return candidaturaRepository.buscarCandidatosPelaVaga(idVaga);
//    }

    public void realizarCandidatura(UUID idCandidato, UUID idVaga) {

        // Validações do lado candidato
        Candidato candidato = candidatoRepository.findById(idCandidato).orElseThrow(
                () -> new RuntimeException("O id do candidato é nulo ou não foi encontrado!"));
        if (!vagaRepository.existsById(idVaga)) {
            throw new RuntimeException("O id da vaga não existe!");
        }
        if (!candidato.isAtivo()) {
            throw new RuntimeException("O candidato que está realizando a candidatura está inativo!");
        }

        // Validações do lado vaga
        Vaga vaga = vagaRepository.findById(idVaga).orElseThrow(
                () -> new RuntimeException("O id da vaga é nulo ou não foi encontrado!"));
        if (!candidatoRepository.existsById(idCandidato)) {
            throw new RuntimeException("O id do candidato não existe!");
        }
        if (!vaga.isAtivo()) {
            throw new RuntimeException("A vaga que está recebendo a candidatura está inativa!");
        }

        Candidatura candidatura = new Candidatura(candidato, vaga);
        candidaturaRepository.save(candidatura);
    }
}
