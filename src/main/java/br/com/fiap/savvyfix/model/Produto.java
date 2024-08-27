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
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(name = "SQ_PRODUTO", sequenceName = "SQ_PRODUTO", allocationSize = 1)
    @Column(name = "ID_PROD")
    private Long id;

    @Column(name = "NM_PROD", nullable = false, length = 50)
    private  String nome;

    @Column(name = "DESC_PROD", nullable = false, length = 50)
    private String descricao;

    @Column(name = "MARCA_PROD", nullable = false, length = 15)
    private String marca;

    @Column(name = "PRECO_FIXO", nullable = false)
    private Float precoFixo;

    @Column(name = "IMG_PROD", nullable = false)
    private String imgProduto;

}