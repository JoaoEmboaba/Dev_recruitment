package com.senior.devrecruitment.service;

import com.senior.devrecruitment.entities.tecnologia.DTO.DTOListagemTecnologia;
import com.senior.devrecruitment.entities.tecnologia.DTO.DTOTecnologia;
import com.senior.devrecruitment.entities.tecnologia.Tecnologia;
import com.senior.devrecruitment.enums.AreaAconselhavel;
import com.senior.devrecruitment.enums.Framework;
import com.senior.devrecruitment.enums.Linguagens;
import com.senior.devrecruitment.exception.NotCompatibleException;
import com.senior.devrecruitment.repository.TecnologiaRepository;

import java.util.List;
import java.util.UUID;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.http.ResponseEntity.*;

@Service
public class TecnologiaService {

    @Autowired
    private TecnologiaRepository repository;

    public ResponseEntity cadastrarTecnologia(DTOTecnologia dtoTecnologia) {

        Linguagens linguagens = dtoTecnologia.nome();
        Framework framework = dtoTecnologia.framework();

        if (linguagens == Linguagens.JS) {
            if (!framework.equals(Framework.ANGULAR) && !framework.equals(Framework.NEXT) && framework != Framework.NEXTJS) {
                return new NotCompatibleException().notCompatibleException();
            } else {
                repository.save(new Tecnologia(dtoTecnologia)).setAreaaconselhavel(List.of(AreaAconselhavel.FRONTEND));
                return ResponseEntity.ok("Tecnologia cadastrada com sucesso!!");
            }
        } else if (linguagens == Linguagens.JAVA) {
            if (!framework.equals(Framework.SPRING) && !framework.equals(Framework.SPRING_BOOT)) {
                return new NotCompatibleException().notCompatibleException();
            } else {
                repository.save(new Tecnologia(dtoTecnologia)).setAreaaconselhavel(List.of(AreaAconselhavel.BACKEND, AreaAconselhavel.DESENVOLVIMENTO_WEB));
                return ResponseEntity.ok("Tecnologia cadastrada com sucesso!!");
            }
        } else if (linguagens.equals(Linguagens.CS)) {
            if (!framework.equals(Framework.ASPNET)) {
                return new NotCompatibleException().notCompatibleException();
            } else {
                repository.save(new Tecnologia(dtoTecnologia)).
                        setAreaaconselhavel(List.of(AreaAconselhavel.DESENVOLVIMENTO_DESKTOP, AreaAconselhavel.DESENVOLVIMENTO_WEB));
                return ResponseEntity.ok("Tecnologia cadastrada com sucesso");
            }
        }

        return ResponseEntity.ok("Tecnologia cadastrada com sucesso!!");
    }

    public Page<DTOListagemTecnologia> listarTodasAsTecnologias(Pageable page) {
        return repository.findAll(page).map(DTOListagemTecnologia::new);
    }

    public ResponseEntity<DTOListagemTecnologia> buscarTecnologiaPorId(UUID idTecnologia) {
        return ok(new DTOListagemTecnologia(repository.getReferenceById(idTecnologia)));
    }

    public ResponseEntity inativarTecnologiaPorId(UUID idTecnologia) {
        repository.findById(idTecnologia).ifPresent(tecnologia -> {
            tecnologia.setAtivo(false);
            repository.save(tecnologia);
        });

        return noContent().build();
    }
}
