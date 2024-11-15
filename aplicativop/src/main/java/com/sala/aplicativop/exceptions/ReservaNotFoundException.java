package com.sala.aplicativop.exceptions;

public class ReservaNotFoundException extends RuntimeException{

    public ReservaNotFoundException() {
        super("Reserva não encontrada");
    }

    public ReservaNotFoundException(String message) {
        super(message);
    }
}
