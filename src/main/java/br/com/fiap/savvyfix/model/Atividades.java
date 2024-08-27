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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "Atividades", uniqueConstraints = {
        @UniqueConstraint(name = "UK_CLIENTE_ATIVIDADES", columnNames = "ID_CLIENTE")
})
public class Atividades {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ATIVIDADES")
    @SequenceGenerator(name = "SQ_ATIVIDADES", sequenceName = "SQ_ATIVIDADES", allocationSize = 1)
    @Column(name = "ID_ATIVIDADES")
    private Long id;

    @Column(name = "PRECO_VARIADO", nullable = false)
    private Float precoVariado;

    @Column(name = "HORARIO_ATUAL")
    private LocalTime horarioAtual;

    @Column(name = "LOCALIZACAO_ATUAL", length = 50)
    private String localizacaoAtual;

    @Column(name = "CLIMA_ATUAL", length = 20)
    private String climaAtual;

    @Column(name = "QNTD_PROCURA", nullable = false, length = 10)
    private Integer qntdProcura;

    @Column(name = "DEMANDA_PRODUTO", nullable = false, length = 2)
    private String demanda;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "ID_CLIENTE",
            referencedColumnName = "ID_CLIENTE",
            foreignKey = @ForeignKey(
                    name = "CLIENTE_ATIVIDADES_FK"
            )
    )
    private Cliente cliente;

}