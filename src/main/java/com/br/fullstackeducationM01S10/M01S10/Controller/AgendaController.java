package com.br.fullstackeducationM01S10.M01S10.Controller;

import com.br.fullstackeducationM01S10.M01S10.Entity.AgendaEntity;
import com.br.fullstackeducationM01S10.M01S10.Service.AgendaServiceImpl;
import com.br.fullstackeducationM01S10.M01S10.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/agendas")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaServiceImpl agendaServiceImpl;

    @PostMapping
    public ResponseEntity<AgendaEntity> criarAgenda(@RequestBody AgendaEntity agenda) {
        log.info("POST /Agendas -> Início");
        log.info("POST /Agendas -> Cadastrado");
        log.info("POST /Agendas -> 201 CREATED");
        log.info("POST /Agendas -> Response Body: \n{}\n", JsonUtil
                .objetoParaJson(agendaServiceImpl.criarAgenda(agenda)));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(agendaServiceImpl.criarAgenda(agenda));
    }
    @GetMapping
    public ResponseEntity<List<AgendaEntity>> listarAgendas() {
        log.info("GET/Agendas -> Início");
        log.info("GET/Agendas -> Encontrados {} registros", agendaServiceImpl.listarAgendas().size());
        log.info("GET/Agendas -> 200 OK");
        log.info("GET/Agendas -> Response Body: \n{}\n", JsonUtil
                .objetoParaJson(agendaServiceImpl.listarAgendas()));
        return ResponseEntity.ok(agendaServiceImpl.listarAgendas());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AgendaEntity> buscarAgendaPorId(@PathVariable Long id) {
        log.info("GET /Agendas/{} -> Início", id);
        log.info("GET /Agendas/{} -> Encontrados", id);
        log.info("GET /Agendas/{} -> 200 OK", id);
        log.debug("GET /Agendas/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(agendaServiceImpl.buscarAgendaPorId(id)));
        return ResponseEntity.ok(agendaServiceImpl.buscarAgendaPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AgendaEntity> atualizarAgenda(@PathVariable Long id, @RequestBody AgendaEntity agenda) {
        log.info("PUT /Agendas/{}", id);
        log.info("PUT /Agendas/{} -> Atualizado", id);
        log.info("PUT /Agendas/{} -> 200 OK", id);
        log.debug("PUT /Agendas/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(agendaServiceImpl.atualizarAgenda(id, agenda)));
        return ResponseEntity.ok(agendaServiceImpl.atualizarAgenda(id, agenda));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAgenda(@PathVariable Long id) {
        log.info("DELETE /Agendas/{}", id);
        log.info("DELETE /Agendas/{} -> Excluído", id);
        log.info("DELETE /Agendas/{} -> 204 NO CONTENT", id);
        agendaServiceImpl.excluirAgenda(id);
        return ResponseEntity.noContent().build();
    }
    
}
