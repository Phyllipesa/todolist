package br.com.phyllipesa.todolist.exceptions;

/**
 * TitleTooLongException - Exceção de Título Muito Longo.
 *
 * Esta exceção é lançada quando um título de entidade (por exemplo, uma tarefa ou um usuário)
 * excede o limite de 50 caracteres.
 * Ela sinaliza que o título fornecido é muito longo e não atende aos requisitos de comprimento.
 */
public class TitleTooLongException extends RuntimeException {
    public TitleTooLongException() {
        super("O campo title deve conter no máximo 50 caracteres");
    }
}
