package com.br.fullstackeducationM01S10.M01S10.Repository;

import com.br.fullstackeducationM01S10.M01S10.Entity.AgendaEntity;
import com.br.fullstackeducationM01S10.M01S10.Entity.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {
}
