package com.sala.aplicativop.exceptions;

public class NomeUsuarioInvalidoException extends RuntimeException{

    public NomeUsuarioInvalidoException() {
        super("Nome inválido, já existe outro usuário com esse nome");
    }

    public NomeUsuarioInvalidoException(String message) {
        super(message);
    }
}
