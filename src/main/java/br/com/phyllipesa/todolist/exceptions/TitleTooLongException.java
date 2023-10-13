package br.com.phyllipesa.todolist.exceptions;

public class TitleTooLongException extends RuntimeException {
    public TitleTooLongException() {
        super("O campo title deve conter no máximo 50 caracteres");
    }
}
