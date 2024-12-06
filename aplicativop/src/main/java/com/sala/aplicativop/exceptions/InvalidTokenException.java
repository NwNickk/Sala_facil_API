package com.sala.aplicativop.exceptions;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException() {
        super("Token inválido ou expirado");
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
