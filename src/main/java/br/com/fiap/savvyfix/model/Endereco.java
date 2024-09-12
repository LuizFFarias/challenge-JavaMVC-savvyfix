package br.com.fiap.savvyfix.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
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
    @Pattern(regexp = "\\d{8}", message = "{endereco.cep.validar_formato}")
    @Size(min = 8, max = 8, message = "{endereco.cep.validar_tamanho}")
    @NotEmpty(message = "{endereco.cep.validar_vazio}")
    private String cep;

    @Column(name = "RUA_ENDERECO", nullable = false, length = 50)
    @Size(min = 2, max = 50, message = "{endereco.rua.validar_tamanho}")
    private String rua;

    @Column(name = "NUM_ENDERECO", nullable = false, length = 20)
    @Size(min = 1, max = 20, message = "{endereco.numero.validar_tamanho}")
    private String numero;

    @Column(name = "BAIRRO_ENDERECO", nullable = false, length = 50)
    @Size(min = 2, max = 50, message = "{endereco.bairro.validar_tamanho}")
    private String bairro;

    @Column(name = "CIDADE_ENDERECO", nullable = false, length = 50)
    @Size(min = 2, max = 50, message = "{endereco.cidade.validar_tamanho}")
    private String cidade;

    @Column(name = "ESTADO_ENDERECO", nullable = false, length = 2)
    @Size(min = 2, max = 2, message = "{endereco.estado.validar_tamanho}")
    private String estado;

    @Column(name = "PAIS", nullable = false, length = 50)
    @Size(min = 2, max = 50, message = "{endereco.pais.validar_tamanho}")
    private String pais;
}
