package com.sala.aplicativop.exceptions;

public class DataPassadaException extends RuntimeException{

    public DataPassadaException() {
        super("Não é possível reservar em uma data passada.");
    }

    public DataPassadaException(String message) {
        super(message);
    }
}
