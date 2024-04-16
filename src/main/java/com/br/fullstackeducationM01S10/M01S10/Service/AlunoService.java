package com.br.fullstackeducationM01S10.M01S10.Service;

import com.br.fullstackeducationM01S10.M01S10.Entity.AlunoEntity;

import java.util.List;

public interface AlunoService {

    AlunoEntity criarAluno(AlunoEntity aluno);
    List<AlunoEntity> listarAlunos();

    AlunoEntity buscarAlunoPorId(Long id);

    AlunoEntity atualizarAluno(Long id, AlunoEntity aluno);

    void excluirALuno(Long id);

}
