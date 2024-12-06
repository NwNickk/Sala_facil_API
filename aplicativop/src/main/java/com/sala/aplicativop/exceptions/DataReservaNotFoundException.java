package com.sala.aplicativop.exceptions;

public class DataReservaNotFoundException extends RuntimeException {

    public DataReservaNotFoundException() {
        super("Não há nenhuma reserva pra essa data");
    }

    public DataReservaNotFoundException(String message) {
        super(message);
    }
}
