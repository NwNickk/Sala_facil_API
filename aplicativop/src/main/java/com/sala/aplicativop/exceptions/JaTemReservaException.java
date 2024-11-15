package com.sala.aplicativop.exceptions;

public class JaTemReservaException extends RuntimeException {

    public JaTemReservaException() {
        super("O usuário já possui uma reserva para o mesmo horário.");
    }

    public JaTemReservaException(String message) {
        super(message);
    }
}
