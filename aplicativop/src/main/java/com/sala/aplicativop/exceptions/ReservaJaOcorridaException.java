package com.sala.aplicativop.exceptions;

public class ReservaJaOcorridaException extends RuntimeException{

    public ReservaJaOcorridaException() {
        super("Não é possível atualizar uma reserva que já ocorreu.");
    }

    public ReservaJaOcorridaException(String message) {
        super(message);
    }
}
