package com.br.fullstackeducationM01S10.M01S10.Repository;

import com.br.fullstackeducationM01S10.M01S10.Entity.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<MaterialEntity, Long> {
}
