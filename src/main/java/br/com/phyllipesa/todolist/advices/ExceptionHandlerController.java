package br.com.phyllipesa.todolist.advices;

import br.com.phyllipesa.todolist.exceptions.TitleTooLongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ExceptionHandlerController - Controlador de Exceções.
 *
 * Esta classe atua como um controlador de exceções global para lidar com exceções específicas
 * que podem ser lançadas em toda a aplicação.
 * Ela permite o tratamento apropriado das exceções personalizadas, como a exceção `TitleTooLongException`.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    /**
     * handlerHttpMessageNotReadableException - Manipula a exceção `TitleTooLongException`.
     *
     * Este método é acionado quando a exceção `TitleTooLongException` é lançada em qualquer parte da aplicação.
     * Ele retorna uma resposta de erro com status HTTP BAD_REQUEST (400) e uma mensagem de erro que descreve a natureza da exceção.
     *
     * @param e A exceção `TitleTooLongException` lançada.
     * @return Um ResponseEntity com status HTTP BAD_REQUEST (400) e a mensagem de erro associada à exceção.
     */
    @ExceptionHandler(TitleTooLongException.class)
    public ResponseEntity<String> handlerHttpMessageNotReadableException(TitleTooLongException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
