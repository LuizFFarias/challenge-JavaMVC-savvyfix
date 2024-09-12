package br.com.fiap.savvyfix.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "CLIENTE", uniqueConstraints = {
        @UniqueConstraint(name = "UK_CPF_CLIENTE", columnNames = "CPF_CLIE")
})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CLIENTE")
    @SequenceGenerator(name = "SQ_CLIENTE", sequenceName = "SQ_CLIENTE", allocationSize = 1)
    @Column(name = "ID_CLIENTE")
    private Long id;

    @Column(name = "NM_CLIE", nullable = false, length = 50)
    @NotEmpty(message = "{cliente.nome.validar_vazio}")
    @Size(min = 3, max = 50, message = "{cliente.nome.validar_tamanho}")
    private String nome;

    @CPF(message = "{cliente.cpf.validar}")
    @NotEmpty(message = "{cliente.cpf.validar_vazio}")
    @Column(name = "CPF_CLIE", nullable = false, length = 11)
    private String cpf;

    @Column(name = "SENHA_CLIE", nullable = false, length = 50 )
    @NotEmpty(message = "{cliente.senha.validar_vazio}")
    private  String senha;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "ID_ENDERECO",
            referencedColumnName = "ID_ENDERECO",
            foreignKey = @ForeignKey(
                    name = "ID_ENDERECO_FK"
            )
    )
    @Valid
    private Endereco endereco;
}