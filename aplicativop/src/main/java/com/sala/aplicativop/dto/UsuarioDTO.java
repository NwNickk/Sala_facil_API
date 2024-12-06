package com.sala.aplicativop.dto;

import com.sala.aplicativop.entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UsuarioDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotBlank(message = "O login é obrigatório") String login,
        @NotBlank(message = "O e-mail não pode estar em branco")
        @Email(message = "E-mail deve ser no formato: nome@dominio.com") String email,
        @NotBlank(message = "A senha é obrigatória") String senha,
        @NotBlank(message = "O telefone não pode estar em branco")
        @Pattern(regexp = "\\d{2}\\s?\\d{9}", message = "O telefone deve estar no formato XX XXXXXXXXX ou XXXXXXXXXXX")
        String telefone,
        @NotBlank(message = "O cpf é obrigatório") String cpf,
        @NotNull(message = "O role do usuário é obrigatório") UserRole role
        ) {
}
