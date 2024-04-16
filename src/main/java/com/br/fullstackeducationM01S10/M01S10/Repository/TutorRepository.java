package com.br.fullstackeducationM01S10.M01S10.Repository;

import com.br.fullstackeducationM01S10.M01S10.Entity.TutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<TutorEntity, Long>{

}
