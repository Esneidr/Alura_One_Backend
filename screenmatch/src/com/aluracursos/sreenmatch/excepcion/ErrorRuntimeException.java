package com.aluracursos.sreenmatch.excepcion;

public class ErrorRuntimeException extends RuntimeException {
    private String message;

    public ErrorRuntimeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
