package com.sala.aplicativop.exceptions;

public class CpfInvalidoException extends RuntimeException{

    public CpfInvalidoException() {
        super("Cpf inválido, já existe outro usuário com esse cpf");
    }

    public CpfInvalidoException(String message) {
        super(message);
    }
}
