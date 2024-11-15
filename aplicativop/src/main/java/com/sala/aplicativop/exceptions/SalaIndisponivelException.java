package com.sala.aplicativop.exceptions;

public class SalaIndisponivelException extends RuntimeException{

    public SalaIndisponivelException() {
        super("A sala não está disponível para reserva.");
    }

    public SalaIndisponivelException(String message) {
        super(message);
    }
}
