package com.br.fullstackeducationM01S10.M01S10.Controller;

import com.br.fullstackeducationM01S10.M01S10.Entity.MaterialEntity;
import com.br.fullstackeducationM01S10.M01S10.Service.MaterialServiceImpl;
import com.br.fullstackeducationM01S10.M01S10.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/materiais")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialServiceImpl materialServiceImpl;

    @PostMapping
    public ResponseEntity<MaterialEntity> criarMaterial(@RequestBody MaterialEntity material) {
        log.info("POST /Materiais -> Início");
        log.info("POST /Materiais -> Cadastrado");
        log.info("POST /Materiais -> 201 CREATED");
        log.info("POST /Materiais -> Response Body: \n{}\n", JsonUtil
                .objetoParaJson(materialServiceImpl.criarMaterial(material)));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(materialServiceImpl.criarMaterial(material));
    }
    @GetMapping
    public ResponseEntity<List<MaterialEntity>> listarMateriais() {
        log.info("GET/Materiais -> Início");
        log.info("GET/Materiais -> Encontrados {} registros", materialServiceImpl.listarMateriais().size());
        log.info("GET/Materiais -> 200 OK");
        log.info("GET/Materiais -> Response Body: \n{}\n", JsonUtil
                .objetoParaJson(materialServiceImpl.listarMateriais()));
        return ResponseEntity.ok(materialServiceImpl.listarMateriais());
    }
    @GetMapping("/{id}")
    public ResponseEntity<MaterialEntity> buscarMaterialPorId(@PathVariable Long id) {
        log.info("GET /Materiais/{} -> Início", id);
        log.info("GET /Materiais/{} -> Encontrados", id);
        log.info("GET /Materiais/{} -> 200 OK", id);
        log.debug("GET /Materiais/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(materialServiceImpl.buscarMaterialPorId(id)));
        return ResponseEntity.ok(materialServiceImpl.buscarMaterialPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<MaterialEntity> atualizarMaterial(@PathVariable Long id, @RequestBody MaterialEntity material) {
        log.info("PUT /Materiais/{}", id);
        log.info("PUT /Materiais/{} -> Atualizado", id);
        log.info("PUT /Materiais/{} -> 200 OK", id);
        log.debug("PUT /Materiais/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(materialServiceImpl.atualizarMaterial(id, material)));
        return ResponseEntity.ok(materialServiceImpl.atualizarMaterial(id, material));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMaterial(@PathVariable Long id) {
        log.info("DELETE /Materiais/{}", id);
        log.info("DELETE /Materiais/{} -> Excluído", id);
        log.info("DELETE /Materiais/{} -> 204 NO CONTENT", id);
        materialServiceImpl.excluirMaterial(id);
        return ResponseEntity.noContent().build();
    }
    
}
