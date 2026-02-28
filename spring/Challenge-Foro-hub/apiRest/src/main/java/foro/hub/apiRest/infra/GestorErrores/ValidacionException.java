package foro.hub.apiRest.infra.GestorErrores;

public class ValidacionException extends RuntimeException {
    public ValidacionException(String message) {
        super(message);
    }
}
