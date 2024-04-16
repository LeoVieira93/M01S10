package com.br.fullstackeducationM01S10.M01S10.Controller;

import com.br.fullstackeducationM01S10.M01S10.Entity.TutorEntity;
import com.br.fullstackeducationM01S10.M01S10.Service.TutorServiceImpl;
import com.br.fullstackeducationM01S10.M01S10.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tutores")
@RequiredArgsConstructor
public class TutorController {

    private final TutorServiceImpl tutorServiceImpl;

    @PostMapping
    public ResponseEntity<TutorEntity> criarTutor(@RequestBody TutorEntity tutor) {
        log.info("POST /Tutores -> Início");
        log.info("POST /Tutores -> Cadastrado");
        log.info("POST /Tutores -> 201 CREATED");
        log.info("POST /Tutores -> Response Body: \n{}\n", JsonUtil
                .objetoParaJson(tutorServiceImpl.criarTutor(tutor)));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tutorServiceImpl.criarTutor(tutor));
    }
    @GetMapping
    public ResponseEntity<List<TutorEntity>> listarTutores() {
        log.info("GET/Tutores -> Início");
        log.info("GET/Tutores -> Encontrados {} registros", tutorServiceImpl.listarTutores().size());
        log.info("GET/Tutores -> 200 OK");
        log.info("GET/Tutores -> Response Body: \n{}\n", JsonUtil
                .objetoParaJson(tutorServiceImpl.listarTutores()));
        return ResponseEntity.ok(tutorServiceImpl.listarTutores());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TutorEntity> buscarTutorPorId(@PathVariable Long id) {
        log.info("GET /Tutores/{} -> Início", id);
        log.info("GET /Tutores/{} -> Encontrados", id);
        log.info("GET /Tutores/{} -> 200 OK", id);
        log.debug("GET /Tutores/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(tutorServiceImpl.buscarTutorPorId(id)));
        return ResponseEntity.ok(tutorServiceImpl.buscarTutorPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TutorEntity> atualizarTutor(@PathVariable Long id, @RequestBody TutorEntity tutor) {
        log.info("PUT /Tutores/{}", id);
        log.info("PUT /Tutores/{} -> Atualizado", id);
        log.info("PUT /Tutores/{} -> 200 OK", id);
        log.debug("PUT /Tutores/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(tutorServiceImpl.atualizarTutor(id, tutor)));
        return ResponseEntity.ok(tutorServiceImpl.atualizarTutor(id, tutor));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTutor(@PathVariable Long id) {
        log.info("DELETE /Tutores/{}", id);
        log.info("DELETE /Tutores/{} -> Excluído", id);
        log.info("DELETE /Tutores/{} -> 204 NO CONTENT", id);
        tutorServiceImpl.excluirTutor(id);
        return ResponseEntity.noContent().build();
    }
    
}
