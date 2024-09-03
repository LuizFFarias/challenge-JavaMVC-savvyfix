package br.com.fiap.savvyfix.dto.response;

import lombok.Builder;

@Builder
public record CompraResponse (

        Long id,
        String nomeProd,
        Integer qntdProd,
        Float valorCompra,
        String especificacoes,
        ClienteResponse cliente,
        ProdutoResponse produto,
        AtividadesResponse atividades
) {}