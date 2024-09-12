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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "COMPRA")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COMPRA")
    @SequenceGenerator(name = "SQ_COMPRA", sequenceName = "SQ_COMPRA", allocationSize = 1)
    @Column(name = "ID_COMPRA")
    private Long id;

    @Column(name = "NM_PROD", nullable = false, length = 50)
    @NotEmpty(message = "{compra.nomeProd.validar_vazio}")
    @Size(min = 2, max = 50, message = "{compra.nomeProd.validar_tamanho}")
    private String nomeProd;

    @Column(name = "QNTD_PROD", nullable = false)
    @Positive(message = "{compra.qntdProd.validar_positivo}")
    @NotNull(message = "{compra.qntdProd.validar_vazio}")
    private Integer qntdProd;

    @Column(name = "VALOR_COMPRA", nullable = false)
    @Positive(message = "{compra.valorCompra.validar_positivo}")
    @NotNull(message = "{compra.valorCompra.validar_vazio}")
    private Float valorCompra;

    @Column(name = "ESPECIFICACAO_PROD", nullable = false, length = 30)
    @NotEmpty(message = "{compra.especificacoes.validar_vazio}")
    @Size(min = 2, max = 30, message = "{compra.especificacoes.validar_tamanho}")
    private String especificacoes;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "ID_PROD",
            referencedColumnName = "ID_PROD",
            foreignKey = @ForeignKey(
                    name = "COMPRA_PRODUTO_FK"
            )
    )
    @Valid
    private Produto produto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "ID_CLIENTE",
            referencedColumnName = "ID_CLIENTE",
            foreignKey = @ForeignKey(
                    name = "COMPRA_CLIENTE_FK"
            )
    )
    @Valid
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "PRECO_VARIADO",
            referencedColumnName = "ID_ATIVIDADES",
            foreignKey = @ForeignKey(
                    name = "COMPRA_ATIVIDADES_FK"
            )
    )
    @Valid
    private Atividades atividades;
}
