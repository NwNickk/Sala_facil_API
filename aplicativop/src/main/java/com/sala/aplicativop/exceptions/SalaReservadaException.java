package com.sala.aplicativop.exceptions;

public class SalaReservadaException extends RuntimeException{

    public SalaReservadaException() {
        super("A sala já está reservada para esse dia e horário.");
    }

    public SalaReservadaException(String message) {
        super(message);
    }
}
