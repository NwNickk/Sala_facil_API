package com.sala.aplicativop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SalaDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotBlank(message = "O departamento é obrigatório") String departamento,
        String descricao,
        @NotNull(message = "O status é obrigatório") Boolean status
) {}
