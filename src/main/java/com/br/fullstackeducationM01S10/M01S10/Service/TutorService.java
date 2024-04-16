package com.br.fullstackeducationM01S10.M01S10.Service;

import com.br.fullstackeducationM01S10.M01S10.Entity.TutorEntity;
import com.br.fullstackeducationM01S10.M01S10.Entity.TutorEntity;

import java.util.List;

public interface TutorService {

    TutorEntity criarTutor(TutorEntity aluno);
    List<TutorEntity> listarTutores();

    TutorEntity buscarTutorPorId(Long id);

    TutorEntity atualizarTutor(Long id, TutorEntity aluno);

    void excluirTutor(Long id);
    
}
