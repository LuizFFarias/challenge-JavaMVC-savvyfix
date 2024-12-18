package br.com.fiap.savvyfix.model;

import java.time.LocalTime;
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
@Table(name = "Atividades")
public class Atividades {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ATIVIDADES")
    @SequenceGenerator(name = "SQ_ATIVIDADES", sequenceName = "SQ_ATIVIDADES", allocationSize = 1)
    @Column(name = "ID_ATIVIDADES")
    private Long id;

    @Column(name = "PRECO_VARIADO", nullable = false)
    @Positive(message = "{atividades.precoVariado.validar_positivo}")
    private Float precoVariado;

    @Column(name = "HORARIO_ATUAL")
    private LocalTime horarioAtual;

    @Column(name = "LOCALIZACAO_ATUAL", length = 50)
    @Size(min = 2, max = 50, message = "{atividades.localizacaoAtual.validar_tamanho}")
    private String localizacaoAtual;

    @Column(name = "CLIMA_ATUAL", length = 20)
    @Size(min = 2, max = 20, message = "{atividades.climaAtual.validar_tamanho}")
    private String climaAtual;

    @Column(name = "QNTD_PROCURA", nullable = false, length = 10)
    @Positive(message = "{atividades.qntdProcura.validar_positivo}")
    @NotNull(message = "{atividades.qntdProcura.validar_vazio}")
    private Integer qntdProcura;

    @Column(name = "DEMANDA_PRODUTO", nullable = false, length = 2)
    @Size(min = 2, max = 2, message = "{atividades.demanda.validar_tamanho}")
    @NotEmpty(message = "{atividades.demanda.validar_vazio}")
    private String demanda;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "ID_CLIENTE",
            referencedColumnName = "ID_CLIENTE",
            foreignKey = @ForeignKey(
                    name = "CLIENTE_ATIVIDADES_FK"
            )
    )
    @Valid
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "ID_PRODUTO",
            referencedColumnName = "ID_PROD",
            foreignKey = @ForeignKey(
                name = "PRODUTO_ATIVIDADES_FK"
            )
    )

    @Valid
    private Produto produto;
}
