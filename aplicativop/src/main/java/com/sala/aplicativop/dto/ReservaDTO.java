package com.sala.aplicativop.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservaDTO(
        @NotNull(message = "A data da reserva é obrigatória") LocalDateTime dataReserva,
        @NotNull(message = "O status é obrigatório") Boolean status,
        @NotNull(message = "O id da sala é obrigatório") Long salaId,
        @NotNull(message = "O id do usuário é obrigatório") Long usuarioId
) {}
