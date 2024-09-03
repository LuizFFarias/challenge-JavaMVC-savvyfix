package br.com.fiap.savvyfix.dto.response;

import lombok.Builder;

import java.time.LocalTime;

@Builder
public record AtividadesResponse (

        Long id,
        Float precoVariado,
        LocalTime horarioAtual,
        String localizacaoAtual,
        String  climaAtual,
        Integer qntdProcura,
        String demanda,
        ClienteResponse cliente
) {}
