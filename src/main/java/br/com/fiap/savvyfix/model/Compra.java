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
    private String nomeProd;

    @Column(name = "QNTD_PROD", nullable = false, length = 3)
    private Integer qntdProd;

    @Column(name = "VALOR_COMPRA", nullable = false)
    private Float valorCompra;

    @Column(name = "ESPECIFICACAO_PROD", nullable = false, length = 30)
    private  String especificacoes;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "ID_PROD",
            referencedColumnName = "ID_PROD",
            foreignKey = @ForeignKey(
                    name = "COMPRA_PRODUTO_FK"
            )
    )

    private Produto produto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "ID_CLIENTE",
            referencedColumnName = "ID_CLIENTE",
            foreignKey = @ForeignKey(
                    name = "COMPRA_CLIENTE_FK"
            )
    )

    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "PRECO_VARIADO",
            referencedColumnName = "ID_ATIVIDADES",
            foreignKey = @ForeignKey(
                    name = "COMPRA_ATIVIDADES_FK"
            )
    )

    private Atividades atividades;
}