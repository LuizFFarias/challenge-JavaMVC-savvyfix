package br.com.fiap.savvyfix.dto.response;

import lombok.Builder;

@Builder
public record ProdutoResponse (

        Long id,
        String nome,
        String descricao,
        String marca,
        Float precoFixo
) {}