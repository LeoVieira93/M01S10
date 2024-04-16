package com.br.fullstackeducationM01S10.M01S10.Service;

import com.br.fullstackeducationM01S10.M01S10.Entity.AgendaEntity;

import java.util.List;

public interface AgendaService {

    AgendaEntity criarAgenda(AgendaEntity agenda);
    List<AgendaEntity> listarAgendas();

    AgendaEntity buscarAgendaPorId(Long id);

    AgendaEntity atualizarAgenda(Long id, AgendaEntity agenda);

    void excluirALuno(Long id);
    
}
