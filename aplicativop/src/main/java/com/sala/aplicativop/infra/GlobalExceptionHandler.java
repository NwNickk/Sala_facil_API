package com.sala.aplicativop.infra;

import com.sala.aplicativop.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleUsuarioNotFound(UsuarioNotFoundException e) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SalaNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleSalaNotFound(SalaNotFoundException e) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReservaNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handlerReservanotFound(ReservaNotFoundException e) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataPassadaException.class)
    public ResponseEntity<RestErrorMessage> handleDataPassada(DataPassadaException e) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReservaJaOcorridaException.class)
    public ResponseEntity<RestErrorMessage> handlerReservaJaOcorrida(ReservaJaOcorridaException e) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JaTemReservaException.class)
    public ResponseEntity<RestErrorMessage> handleJaTemReserva(JaTemReservaException e) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MuitaAntecedenciaException.class)
    public ResponseEntity<RestErrorMessage> handleMuitaAntecedencia(MuitaAntecedenciaException e) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SalaIndisponivelException.class)
    public ResponseEntity<RestErrorMessage> handleSalaIndisponivel(SalaIndisponivelException e) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SalaReservadaException.class)
    public ResponseEntity<RestErrorMessage> handleSalaReservada(SalaReservadaException e) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NomeSalaInvalidoException.class)
    public ResponseEntity<RestErrorMessage> handleNomeSalaException(NomeSalaInvalidoException e) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NomeUsuarioInvalidoException.class)
    public ResponseEntity<RestErrorMessage> handleNomeUsuarioInvalido(NomeUsuarioInvalidoException e) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailInvalidoException.class)
    public ResponseEntity<RestErrorMessage> handleEmailInvalido(EmailInvalidoException e) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TelefoneInvalidoException.class)
    public ResponseEntity<RestErrorMessage> handleTelefoneInvalido(TelefoneInvalidoException e) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<RestErrorMessage> handleCpfInvalido(CpfInvalidoException e) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
