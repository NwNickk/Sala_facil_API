package com.sala.aplicativop.exceptions;

public class LoginAlreadyExistsException extends RuntimeException {

    public LoginAlreadyExistsException() {
        super("Login inválido, esse login já está em uso");
    }

    public LoginAlreadyExistsException(String message) {
        super(message);
    }
}
