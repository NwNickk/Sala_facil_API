package com.sala.aplicativop.exceptions;

public class SalaNotFoundException extends RuntimeException{

    public SalaNotFoundException() {
        super("Sala não encontrada");
    }

    public SalaNotFoundException(String message) {
        super(message);
    }
}
