package br.com.fiap.savvyfix.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EnderecoRequest (

        @Size(min = 8, max = 8, message = "O CEP deve conter 8 caracteres")
        @NotNull(message = "O CEP é obrigatório")
        String cep,

        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String pais
) {}
