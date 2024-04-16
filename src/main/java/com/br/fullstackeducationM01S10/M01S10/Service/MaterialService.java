package com.br.fullstackeducationM01S10.M01S10.Service;

import com.br.fullstackeducationM01S10.M01S10.Entity.MaterialEntity;

import java.util.List;

public interface MaterialService {

    MaterialEntity criarMaterial(MaterialEntity material);
    List<MaterialEntity> listarMateriais();

    MaterialEntity buscarMaterialPorId(Long id);

    MaterialEntity atualizarMaterial(Long id, MaterialEntity material);

    void excluirALuno(Long id);
    
}
