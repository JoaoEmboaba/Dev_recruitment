package com.senior.devrecruitment.service;

import com.senior.devrecruitment.entities.vaga.DTO.DTOListagemVagas;
import com.senior.devrecruitment.entities.vaga.Vaga;
import com.senior.devrecruitment.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@Service
public class VagaService {

    @Autowired
    private VagaRepository repository;

    public void cadastrarVaga(Vaga vaga) {
        repository.save(vaga);
    }

    public Page<DTOListagemVagas> listarTodasAsVagas(Pageable page) {
        return repository.findAll(page).map(DTOListagemVagas::new);
    }

    public ResponseEntity<DTOListagemVagas> buscarVagaPorId(UUID idVaga) {
        return ok(new DTOListagemVagas(repository.getReferenceById(idVaga)));
    }

    public ResponseEntity inativarVagaPorId(UUID idVaga) {
        repository.findById(idVaga).ifPresent(vaga -> {
            vaga.setAtivo(false);
            repository.save(vaga);
        });

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity excluirVagaPorId(UUID idVaga) {
        repository.deleteById(idVaga);
        return noContent().build();
    }
}
