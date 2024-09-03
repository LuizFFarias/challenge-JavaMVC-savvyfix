package br.com.fiap.savvyfix.dto.request;

import jakarta.validation.Valid;

import java.time.LocalTime;

public record AtividadesRequest (
        Float precoVariado,

        LocalTime horarioAtual,

        String localizacaoAtual,
        String  climaAtual,
        Integer qntdProcura,
        String demanda,

        @Valid
        AbstractRequest cliente

) {}