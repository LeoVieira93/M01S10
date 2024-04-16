package com.br.fullstackeducationM01S10.M01S10.Service;

import com.br.fullstackeducationM01S10.M01S10.Entity.TutorEntity;
import com.br.fullstackeducationM01S10.M01S10.Exception.NotFoundException;
import com.br.fullstackeducationM01S10.M01S10.Repository.TutorRepository;
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
public class TutorServiceImpl {

    private final TutorRepository tutorRepository;

    @PostMapping
    public TutorEntity criarTutor(@NotNull TutorEntity tutorNovo) {
        tutorNovo.setId(null);
        log.info("Criando tutor -> Salvar: \n{}\n", JsonUtil.objetoParaJson(tutorNovo));
        TutorEntity tutor = tutorRepository.save(tutorNovo);
        log.info("Criando tutor -> Salvo com sucesso");
        log.debug("Criando tutor -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(tutorNovo));
        return tutor;
    }

    @GetMapping
    public List<TutorEntity> listarTutores() {
        log.info("Buscando todos os tutors");
        List<TutorEntity> tutors = tutorRepository.findAll();
        log.info("Buscando todos os tutors -> {} tutors encontrados", tutorRepository.findAll().size());
        log.debug("Buscando todos os tutors -> Registros encontrados:\n{}\n",
                JsonUtil.objetoParaJson(tutorRepository));
        return tutors;
    }

    @GetMapping("/{id}")
    public TutorEntity buscarTutorPorId(Long id) {
        log.info("Buscando tutor por ID: {}", id);
        Optional<TutorEntity> tutor = tutorRepository.findById(id);
        if (tutor.isEmpty()) {
            log.error("Buscando tutor por id {} -> NÃO Encontrado", id);
            return tutorRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Tutor não encontrado com o ID: " + id));
        }
        log.info("Buscando tutor por id ({}) -> Encontrado", id);
        log.debug("Buscando tutor por id ({}) -> Registro encontrado:\n{}\n", id,
                JsonUtil.objetoParaJson(tutor.get()));
        return tutor.get();
    }
    @PostMapping("/{id}")
    public TutorEntity atualizarTutor(Long id, TutorEntity tutor) {
        if (!tutorRepository.existsById(id)) {
            log.info("Alterando tutor com id ({}) -> Salvar: \n{}\n", id, JsonUtil.objetoParaJson(tutor));
            throw new NotFoundException("Tutor não encontrado com o ID: " + id);
        }
        tutor.setId(id);
        log.info("Alterando tutor -> Salvo com sucesso");
        log.debug("Alterando tutor -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(tutor));
        return tutorRepository.save(tutor);
    }
    @DeleteMapping("/{id}")
    public void excluirTutor(Long id) {
        if (!tutorRepository.existsById(id)) {
            log.info("Excluindo tutor com id ({}) -> Excluindo", id);
            throw new NotFoundException("Tutor não encontrada com o ID: " + id);
        }
        tutorRepository.deleteById(id);
        log.info("Excluindo tutor com id ({}) -> Excluído com sucesso", id);
    }

}
