package br.com.fiap.savvyfix.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ROLE")
    @SequenceGenerator(name = "SQ_ROLE", sequenceName = "SQ_ROLE", allocationSize = 1)
    @Column(name = "ID_ROLE")
    private Long id;

    @Column(name = "NOME_ROLE")
    private String nome;
}
