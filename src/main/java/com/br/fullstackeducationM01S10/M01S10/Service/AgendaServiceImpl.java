package com.br.fullstackeducationM01S10.M01S10.Service;

import com.br.fullstackeducationM01S10.M01S10.Entity.AgendaEntity;
import com.br.fullstackeducationM01S10.M01S10.Exception.NotFoundException;
import com.br.fullstackeducationM01S10.M01S10.Repository.AgendaRepository;
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
public class AgendaServiceImpl {

    private final AgendaRepository agendaRepository;

    @PostMapping
    public AgendaEntity criarAgenda(@NotNull AgendaEntity agendaNovo) {
        agendaNovo.setId(null);
        log.info("Criando agenda -> Salvar: \n{}\n", JsonUtil.objetoParaJson(agendaNovo));
        AgendaEntity agenda = agendaRepository.save(agendaNovo);
        log.info("Criando agenda -> Salvo com sucesso");
        log.debug("Criando agenda -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(agendaNovo));
        return agenda;
    }

    @GetMapping
    public List<AgendaEntity> listarAgendas() {
        log.info("Buscando todos os agendas");
        List<AgendaEntity> agendas = agendaRepository.findAll();
        log.info("Buscando todos os agendas -> {} agendas encontrados", agendaRepository.findAll().size());
        log.debug("Buscando todos os agendas -> Registros encontrados:\n{}\n",
                JsonUtil.objetoParaJson(agendaRepository));
        return agendas;
    }

    @GetMapping("/{id}")
    public AgendaEntity buscarAgendaPorId(Long id) {
        log.info("Buscando agenda por ID: {}", id);
        Optional<AgendaEntity> agenda = agendaRepository.findById(id);
        if (agenda.isEmpty()) {
            log.error("Buscando agenda por id {} -> NÃO Encontrado", id);
            return agendaRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Agenda não encontrado com o ID: " + id));
        }
        log.info("Buscando agenda por id ({}) -> Encontrado", id);
        log.debug("Buscando agenda por id ({}) -> Registro encontrado:\n{}\n", id,
                JsonUtil.objetoParaJson(agenda.get()));
        return agenda.get();
    }
    @PostMapping("/{id}")
    public AgendaEntity atualizarAgenda(Long id, AgendaEntity agenda) {
        if (!agendaRepository.existsById(id)) {
            log.info("Alterando agenda com id ({}) -> Salvar: \n{}\n", id, JsonUtil.objetoParaJson(agenda));
            throw new NotFoundException("Agenda não encontrado com o ID: " + id);
        }
        agenda.setId(id);
        log.info("Alterando agenda -> Salvo com sucesso");
        log.debug("Alterando agenda -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(agenda));
        return agendaRepository.save(agenda);
    }
    @DeleteMapping("/{id}")
    public void excluirAgenda(Long id) {
        if (!agendaRepository.existsById(id)) {
            log.info("Excluindo agenda com id ({}) -> Excluindo", id);
            throw new NotFoundException("Agenda não encontrada com o ID: " + id);
        }
        agendaRepository.deleteById(id);
        log.info("Excluindo agenda com id ({}) -> Excluído com sucesso", id);
    }
    
}
