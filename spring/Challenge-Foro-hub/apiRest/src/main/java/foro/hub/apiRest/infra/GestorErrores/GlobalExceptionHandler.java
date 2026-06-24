package foro.hub.apiRest.infra.GestorErrores;

import foro.hub.apiRest.domain.DTO.ApiResponse;
import foro.hub.apiRest.domain.response.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    //Control Errores de Enum
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> manejarEnumInvalido() {
        return ResponseEntity.badRequest()
                .body(ResponseBuilder.error("El curso enviado no es válido."));
    }

    //Control Errores en los servicios
    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<ApiResponse<?>> handleBusiness(ValidacionException ex) {

        return ResponseEntity.badRequest()
                .body(ResponseBuilder.error(ex.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthentication(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ResponseBuilder.error("Debes iniciar sesión para acceder a este recurso."));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ResponseBuilder.error("No tienes permisos para acceder a este recurso."));
    }
}
