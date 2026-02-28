package foro.hub.apiRest.infra.GestorErrores;

import foro.hub.apiRest.domain.DTO.ApiResponse;
import foro.hub.apiRest.domain.response.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
}
