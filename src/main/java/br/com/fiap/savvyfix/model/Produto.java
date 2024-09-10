package br.com.fiap.savvyfix.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
    @Size(min = 2, max = 50, message = "O tamanho do nome deve ser de 2 a 50 caracteres")
    @NotNull(message = "O nome é obrigatório")
    private  String nome;

    @Column(name = "DESC_PROD", nullable = false, length = 50)
    @Size(min = 5, max = 50, message = "O tamanho da descrição deve ser de 2 a 50 caracteres")
    @NotNull(message = "A descrição do produto é obrigatório")
    private String descricao;

    @Column(name = "MARCA_PROD", nullable = false, length = 15)
    @Size(min = 2, max = 15, message = "O tamanho da marca ser de 2 a 15 caracteres")
    @NotNull(message = "A marca do produto é obrigatória")
    private String marca;

    @Column(name = "PRECO_FIXO", nullable = false)
    @NotNull(message = "O preço do produto é obrigatório")
    @Positive(message = "O preço deve ser positivo")
    private Float precoFixo;

    @Column(name = "IMG_PROD", nullable = false)
    private String imgProduto;

}