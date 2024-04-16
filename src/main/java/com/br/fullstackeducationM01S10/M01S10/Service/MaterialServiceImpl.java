package com.br.fullstackeducationM01S10.M01S10.Service;

import com.br.fullstackeducationM01S10.M01S10.Entity.MaterialEntity;
import com.br.fullstackeducationM01S10.M01S10.Exception.NotFoundException;
import com.br.fullstackeducationM01S10.M01S10.Repository.MaterialRepository;
import com.br.fullstackeducationM01S10.M01S10.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class MaterialServiceImpl {

    private final MaterialRepository materialRepository;

    @PostMapping
    public MaterialEntity criarMaterial(@NotNull MaterialEntity materialNovo) {
        materialNovo.setId(null);
        log.info("Criando material -> Salvar: \n{}\n", JsonUtil.objetoParaJson(materialNovo));
        MaterialEntity material = materialRepository.save(materialNovo);
        log.info("Criando material -> Salvo com sucesso");
        log.debug("Criando material -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(materialNovo));
        return material;
    }

    @GetMapping
    public List<MaterialEntity> listarMateriais() {
        log.info("Buscando todos os materials");
        List<MaterialEntity> materials = materialRepository.findAll();
        log.info("Buscando todos os materials -> {} materials encontrados", materialRepository.findAll().size());
        log.debug("Buscando todos os materials -> Registros encontrados:\n{}\n",
                JsonUtil.objetoParaJson(materialRepository));
        return materials;
    }

    @GetMapping("/{id}")
    public MaterialEntity buscarMaterialPorId(Long id) {
        log.info("Buscando material por ID: {}", id);
        Optional<MaterialEntity> material = materialRepository.findById(id);
        if (material.isEmpty()) {
            log.error("Buscando material por id {} -> NÃO Encontrado", id);
            return materialRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Material não encontrado com o ID: " + id));
        }
        log.info("Buscando material por id ({}) -> Encontrado", id);
        log.debug("Buscando material por id ({}) -> Registro encontrado:\n{}\n", id,
                JsonUtil.objetoParaJson(material.get()));
        return material.get();
    }
    @PostMapping("/{id}")
    public MaterialEntity atualizarMaterial(Long id, MaterialEntity material) {
        if (!materialRepository.existsById(id)) {
            log.info("Alterando material com id ({}) -> Salvar: \n{}\n", id, JsonUtil.objetoParaJson(material));
            throw new NotFoundException("Material não encontrado com o ID: " + id);
        }
        material.setId(id);
        log.info("Alterando material -> Salvo com sucesso");
        log.debug("Alterando material -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(material));
        return materialRepository.save(material);
    }
    @DeleteMapping("/{id}")
    public void excluirMaterial(Long id) {
        if (!materialRepository.existsById(id)) {
            log.info("Excluindo material com id ({}) -> Excluindo", id);
            throw new NotFoundException("Material não encontrada com o ID: " + id);
        }
        materialRepository.deleteById(id);
        log.info("Excluindo material com id ({}) -> Excluído com sucesso", id);
    }
    
}
