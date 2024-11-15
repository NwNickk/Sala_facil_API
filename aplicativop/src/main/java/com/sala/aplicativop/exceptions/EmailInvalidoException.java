package com.sala.aplicativop.exceptions;

public class EmailInvalidoException extends RuntimeException{

    public EmailInvalidoException() {
        super("E-mail inválido, já existe outro usuário com esse e-mail");
    }

    public EmailInvalidoException(String message) {
        super(message);
    }
}
