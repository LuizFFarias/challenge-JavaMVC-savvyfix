package br.com.fiap.savvyfix.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AbstractRequest (
        @NotNull(message = "O ID é obrigatório")
        @Positive
        Long id
) {}