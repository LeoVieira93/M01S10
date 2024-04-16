package com.br.fullstackeducationM01S10.M01S10.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "agendas")
public class AgendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private AlunoEntity alunoId;
    private TutorEntity tutorId;
    private LocalDate data;
    private String status;
    private String tema;
    private String descricao;


}
