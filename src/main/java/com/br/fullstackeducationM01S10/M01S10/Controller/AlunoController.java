package com.br.fullstackeducationM01S10.M01S10.Controller;

import com.br.fullstackeducationM01S10.M01S10.Entity.AlunoEntity;
import com.br.fullstackeducationM01S10.M01S10.Service.AlunoServiceImpl;
import com.br.fullstackeducationM01S10.M01S10.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoServiceImpl alunoServiceImpl;

    @PostMapping
    public ResponseEntity<AlunoEntity> criarAluno(@RequestBody AlunoEntity aluno) {
        log.info("POST /Alunos -> Início");
        log.info("POST /Alunos -> Cadastrado");
        log.info("POST /Alunos -> 201 CREATED");
        log.info("POST /Alunos -> Response Body: \n{}\n", JsonUtil
                .objetoParaJson(alunoServiceImpl.criarAluno(aluno)));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alunoServiceImpl.criarAluno(aluno));
    }
    @GetMapping
    public ResponseEntity<List<AlunoEntity>> listarAlunos() {
        log.info("GET/Alunos -> Início");
        log.info("GET/Alunos -> Encontrados {} registros", alunoServiceImpl.listarAlunos().size());
        log.info("GET/Alunos -> 200 OK");
        log.info("GET/Alunos -> Response Body: \n{}\n", JsonUtil
                .objetoParaJson(alunoServiceImpl.listarAlunos()));
        return ResponseEntity.ok(alunoServiceImpl.listarAlunos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AlunoEntity> buscarAlunoPorId(@PathVariable Long id) {
        log.info("GET /Alunos/{} -> Início", id);
        log.info("GET /Alunos/{} -> Encontrados", id);
        log.info("GET /Alunos/{} -> 200 OK", id);
        log.debug("GET /Alunos/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(alunoServiceImpl.buscarAlunoPorId(id)));
        return ResponseEntity.ok(alunoServiceImpl.buscarAlunoPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AlunoEntity> atualizarAluno(@PathVariable Long id, @RequestBody AlunoEntity aluno) {
        log.info("PUT /Alunos/{}", id);
        log.info("PUT /Alunos/{} -> Atualizado", id);
        log.info("PUT /Alunos/{} -> 200 OK", id);
        log.debug("PUT /Alunos/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(alunoServiceImpl.atualizarAluno(id, aluno)));
        return ResponseEntity.ok(alunoServiceImpl.atualizarAluno(id, aluno));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAluno(@PathVariable Long id) {
        log.info("DELETE /Alunos/{}", id);
        log.info("DELETE /Alunos/{} -> Excluído", id);
        log.info("DELETE /Alunos/{} -> 204 NO CONTENT", id);
        alunoServiceImpl.excluirAluno(id);
        return ResponseEntity.noContent().build();
    }

}
