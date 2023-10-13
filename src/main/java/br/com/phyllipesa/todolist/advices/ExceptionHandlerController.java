package br.com.phyllipesa.todolist.advices;

import br.com.phyllipesa.todolist.exceptions.TitleTooLongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ControllerAdvice - Faz o gerenciamento de erros da aplicação.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    /**
     * TimeoutException - Tratamento de erro caso title exceder 50 caracteres.
     */
    @ExceptionHandler(TitleTooLongException.class)
    public ResponseEntity<String> handlerHttpMessageNotReadableException(TitleTooLongException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
