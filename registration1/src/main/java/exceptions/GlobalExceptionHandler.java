package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExistsException
                                                                       exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).
                body(exception.getMessage());

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleArgumentInvalid(MethodArgumentNotValidException exception){
        List <String>errors=new ArrayList<>();
        List <FieldError> fieldErrors=exception.getBindingResult().getFieldErrors();
        for(FieldError error: fieldErrors){
                errors.add(error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(errors);
    }
}
