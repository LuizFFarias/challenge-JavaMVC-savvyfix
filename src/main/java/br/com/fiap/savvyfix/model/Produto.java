package br.com.fiap.savvyfix.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(name = "SQ_PRODUTO", sequenceName = "SQ_PRODUTO", allocationSize = 1)
    @Column(name = "ID_PROD")
    private Long id;

    @Column(name = "NM_PROD", nullable = false, length = 50)
    @Size(min = 2, max = 50, message = "{produto.nome.validar_tamanho}")
    @NotEmpty(message = "{produto.nome.validar_vazio}")
    private  String nome;

    @Column(name = "DESC_PROD", nullable = false, length = 50)
    @Size(min = 5, max = 50, message = "{produto.descricao.validar_tamanho}")
    @NotEmpty(message = "{produto.descricao.validar_vazio}")
    private String descricao;

    @Column(name = "MARCA_PROD", nullable = false, length = 15)
    @Size(min = 2, max = 15, message = "{produto.marca.validar_tamanho}")
    @NotEmpty(message = "{produto.marca.validar_vazio}")
    private String marca;

    @Column(name = "PRECO_FIXO", nullable = false)
    @NotNull(message = "{produto.preco_fixo.validar_vazio}")
    @Positive(message = "{produto.preco_fixo.validar_positivo}")
    private Float precoFixo;

    @Column(name = "IMG_PROD", nullable = false)
    @NotEmpty(message = "{produto.img.validar_vazio}")
    private String imgProduto;

}