package med.voll.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidacionException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorErrores {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream()
                .map(dataErrorsValidation::new).toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404() {
        return ResponseEntity.notFound().build();
    }

    public record dataErrorsValidation(
            String campo,
            String mensaje
    ) {
        public dataErrorsValidation(FieldError error) {
            this(
                    error.getField(),
                    error.getDefaultMessage()
            );
        }
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity tratarErrorValidacion(ValidacionException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
