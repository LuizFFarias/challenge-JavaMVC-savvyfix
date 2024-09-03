package br.com.fiap.savvyfix.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequest (

        @NotNull(message = "O nome é obrigatório")
        String nome,

        @CPF(message = "CPF inválido")
        @NotNull(message = "O CPF é obrigatório")
        String cpf,

        @NotNull(message = "A senha é obrigatória")
        String senha,

        @Valid
        AbstractRequest endereco
) {}
