package com.sala.aplicativop.exceptions;

public class NomeSalaInvalidoException extends RuntimeException{

    public NomeSalaInvalidoException() {
        super("Nome inválido, já existe outra sala com esse nome");
    }

    public NomeSalaInvalidoException(String message) {
        super(message);
    }
}
