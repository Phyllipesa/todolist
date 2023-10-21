package br.com.phyllipesa.todolist.repository;

import br.com.phyllipesa.todolist.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Interface de Repositório de Tarefas.
 *
 * Esta interface define operações de acesso a dados para as tarefas na aplicação.
 * Ela estende a interface JpaRepository, permitindo a busca e manipulação de objetos do tipo TaskModel
 * por meio de UUID, incluindo operações personalizadas, como a busca por ID de usuário.
 */
public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    /**
     * Obtém uma lista de tarefas por ID de usuário.
     *
     * Este método personalizado permite a recuperação de uma lista de tarefas associadas a um ID de
     * usuário específico.
     * É útil para buscar todas as tarefas pertencentes a um determinado usuário.
     *
     * @param idUser O ID do usuário para o qual as tarefas estão sendo obtidas.
     * @return Uma lista de objetos TaskModel associados ao usuário especificado.
     */
    List<TaskModel> findByIdUser(UUID idUser);
}
