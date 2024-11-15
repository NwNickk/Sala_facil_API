package com.sala.aplicativop.exceptions;

public class UsuarioNotFoundException extends RuntimeException{

    public UsuarioNotFoundException() {
        super("Usuário não encontrado");
    }

    public UsuarioNotFoundException(String message) {
        super(message);
    }
}
