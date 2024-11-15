package com.sala.aplicativop.exceptions;

public class MuitaAntecedenciaException extends RuntimeException{

    public MuitaAntecedenciaException() {
        super("Não é permitido reservar com mais de 30 dias de antecedência.");
    }

    public MuitaAntecedenciaException(String message) {
        super(message);
    }
}
