package br.com.fiap.savvyfix.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ENDERECO")
    @SequenceGenerator(name = "SQ_ENDERECO", sequenceName = "SQ_ENDERECO", allocationSize = 1)
    @Column(name = "ID_ENDERECO")
    private Long id;

    @Column(name = "CEP_ENDERECO", nullable = false, length = 8)
    private String cep;

    @Column(name = "RUA_ENDERECO", nullable = false, length = 50)
    private String rua;

    @Column(name = "NUM_ENDERECO", nullable = false, length = 20)
    private String numero;

    @Column(name = "BAIRRO_ENDERECO", nullable = false, length = 50)
    private String bairro;

    @Column(name = "CIDADE_ENDERECO", nullable = false, length = 50)
    private String cidade;

    @Column(name = "ESTADO_ENDERECO", nullable = false, length = 2)
    private String estado;

    @Column(name = "PAIS", nullable = false)
    private String pais;
}