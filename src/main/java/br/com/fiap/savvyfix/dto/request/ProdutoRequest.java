package br.com.fiap.savvyfix.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoRequest (
        @Size(min = 2, max = 50, message = "O tamanho do nome deve ser de 2 a 50 caracteres")
        @NotNull(message = "O nome é obrigatório")
        String nome,

        @Size(min = 5, max = 50, message = "O tamanho da descrição deve ser de 2 a 50 caracteres")
        @NotNull(message = "A descrição do produto é obrigatório")
        String descricao,

        @Size(min = 2, max = 15, message = "O tamanho da marca ser de 2 a 15 caracteres")
        @NotNull(message = "A marca do produto é obrigatória")
        String marca,

        @NotNull(message = "O preço do produto é obrigatório")
        @Positive(message = "O preço deve ser positivo")
        Float precoFixo
) {}