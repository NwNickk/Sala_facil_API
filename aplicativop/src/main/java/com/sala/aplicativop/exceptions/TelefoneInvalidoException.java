package com.sala.aplicativop.exceptions;

public class TelefoneInvalidoException extends RuntimeException{

    public TelefoneInvalidoException() {
        super("Telefone inválido,  já existe outro usuário com esse telefone");
    }

    public TelefoneInvalidoException(String message) {
        super(message);
    }
}
