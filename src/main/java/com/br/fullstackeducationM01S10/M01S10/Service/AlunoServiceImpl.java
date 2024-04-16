package com.br.fullstackeducationM01S10.M01S10.Service;

import com.br.fullstackeducationM01S10.M01S10.Entity.AlunoEntity;
import com.br.fullstackeducationM01S10.M01S10.Exception.NotFoundException;
import com.br.fullstackeducationM01S10.M01S10.Repository.AlunoRepository;
import com.br.fullstackeducationM01S10.M01S10.util.JsonUtil;
import lombok.NoArgsConstructor;
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
public class AlunoServiceImpl {

    private final AlunoRepository alunoRepository;

    @PostMapping
    public AlunoEntity criarAluno(@NotNull AlunoEntity alunoNovo) {
        alunoNovo.setId(null);
        log.info("Criando aluno -> Salvar: \n{}\n", JsonUtil.objetoParaJson(alunoNovo));
        AlunoEntity aluno = alunoRepository.save(alunoNovo);
        log.info("Criando aluno -> Salvo com sucesso");
        log.debug("Criando aluno -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(alunoNovo));
        return aluno;
    }

    @GetMapping
    public List<AlunoEntity> listarAlunos() {
        log.info("Buscando todos os alunos");
        List<AlunoEntity> alunos = alunoRepository.findAll();
        log.info("Buscando todos os alunos -> {} alunos encontrados", alunoRepository.findAll().size());
        log.debug("Buscando todos os alunos -> Registros encontrados:\n{}\n",
                JsonUtil.objetoParaJson(alunoRepository));
        return alunos;
    }

    @GetMapping("/{id}")
    public AlunoEntity buscarAlunoPorId(Long id) {
        log.info("Buscando aluno por ID: {}", id);
        Optional<AlunoEntity> aluno = alunoRepository.findById(id);
        if (aluno.isEmpty()) {
            log.error("Buscando aluno por id {} -> NÃO Encontrado", id);
            return alunoRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Aluno não encontrado com o ID: " + id));
        }
        log.info("Buscando aluno por id ({}) -> Encontrado", id);
        log.debug("Buscando aluno por id ({}) -> Registro encontrado:\n{}\n", id,
                JsonUtil.objetoParaJson(aluno.get()));
        return aluno.get();
    }
    @PostMapping("/{id}")
    public AlunoEntity atualizarAluno(Long id, AlunoEntity aluno) {
        if (!alunoRepository.existsById(id)) {
            log.info("Alterando aluno com id ({}) -> Salvar: \n{}\n", id, JsonUtil.objetoParaJson(aluno));
            throw new NotFoundException("Aluno não encontrado com o ID: " + id);
        }
        aluno.setId(id);
        log.info("Alterando aluno -> Salvo com sucesso");
        log.debug("Alterando aluno -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(aluno));
        return alunoRepository.save(aluno);
    }
    @DeleteMapping("/{id}")
    public void excluirAluno(Long id) {
        if (!alunoRepository.existsById(id)) {
            log.info("Excluindo aluno com id ({}) -> Excluindo", id);
            throw new NotFoundException("Aluno não encontrada com o ID: " + id);
        }
        alunoRepository.deleteById(id);
        log.info("Excluindo aluno com id ({}) -> Excluído com sucesso", id);
    }

}
