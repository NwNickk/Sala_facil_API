package com.sala.aplicativop.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotBlank(message = "O email é obrigatório") String email,
        @NotBlank(message = "O telefone é obrigatório") String telefone,
        @NotBlank(message = "O cpf é obrigatório") String cpf
) {
}
