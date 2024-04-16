package com.br.fullstackeducationM01S10.M01S10.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "materiais")
public class MaterialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private AgendaEntity agendaId;
    private String descricao;
    private String caminhoDoArquivo;
}
