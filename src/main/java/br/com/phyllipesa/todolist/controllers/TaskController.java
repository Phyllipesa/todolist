package br.com.phyllipesa.todolist.controllers;

import br.com.phyllipesa.todolist.models.TaskModel;
import br.com.phyllipesa.todolist.repository.ITaskRepository;
import br.com.phyllipesa.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


/**
 * Controlador de Tarefas.
 *
 * Esta classe atua como um controlador para manipular operações relacionadas a tarefas
 * na aplicação. Ela é mapeada para o caminho "/tasks" e fornece endpoints para criar, buscar e atualizar tarefas.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    /**
     * Recebe um bean do tipo ITaskRepository por injeção de dependência.
     */
    @Autowired
    private ITaskRepository taskRepository;
//    Crie um ReadMe do github para um projeto web de TodoList em java usando maven,

    /**
     * create - Registra uma nova tarefa.
     *
     * Este endpoint permite que um usuário registre uma nova tarefa.
     * O usuário deve fornecer informações sobre a tarefa, como título, descrição, data de início e data de término.
     *
     * @param taskModel Um objeto contendo informações da tarefa a ser registrada.
     * @param request O ID do usuário que está registrando a tarefa.
     * @return Um ResponseEntity com status HTTP CREATED (201) e o objeto TaskModel recém-criado.
     */
    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        taskModel.setIdUser((UUID) request.getAttribute("idUser"));
        LocalDateTime currentDate = LocalDateTime.now();
        //Verifica se a dada informada é maior ou igual a data atual.
        // 10/11/2023 - current
        // 10/10/2023 - startAt
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de início/termino deve ser maior que a data atual");
        }

        //Verifica se a dada inicial informada é maior do que a data término.
        // 12/10/2023 - startAt
        // 10/11/2023 - endAt
        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de início deve ser menor do que a data de término");
        }

        TaskModel taskCreated = this.taskRepository.save(taskModel);
        return ResponseEntity.ok(taskCreated);
    }

    /**
     * getTasksById - Obtém as tarefas de um usuário específico.
     *
     * Este endpoint retorna uma lista de tarefas pertencentes a um usuário com base no ID do usuário fornecido na requisição.
     *
     * @param request O ID do usuário para o qual as tarefas estão sendo obtidas.
     * @return Um ResponseEntity com status HTTP OK (200) e uma lista de objetos TaskModel associados ao usuário.
     */
    @GetMapping("/")
    public ResponseEntity<List<TaskModel>> getTasksById(HttpServletRequest request) {
        List<TaskModel> tasks = taskRepository.findByIdUser((UUID) request.getAttribute("idUser"));
        return ResponseEntity.ok(tasks);
    }

    /**
     * update - Atualiza uma tarefa existente.
     *
     * Este endpoint permite que um usuário atualize uma tarefa existente com
     * base no ID da tarefa fornecido na URL. A tarefa a ser atualizada é identificada
     * por seu ID e deve pertencer ao usuário que está fazendo a atualização.
     *
     * @param taskModel Um objeto contendo as informações atualizadas da tarefa.
     * @param request O ID do usuário que está solicitando a atualização.
     * @param id O ID da tarefa a ser atualizada.
     * @return Um ResponseEntity com status HTTP OK (200) e o objeto TaskModel atualizado.
     * Em caso de erro, pode retornar um status HTTP BAD_REQUEST (400) com uma mensagem de erro apropriada.
     */
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {
        TaskModel task = this.taskRepository.findById(id).orElse(null);

        if (task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tarefa não encontrada.");
        }

        Object idUser = request.getAttribute("idUser");

        if (!task.getIdUser().equals(idUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuário não tem permissão para alterar essa tarefa");
        }

        Utils.copyNonNullProperties(taskModel, task);
        TaskModel taskUpdated = this.taskRepository.save(task);
        return ResponseEntity.ok(taskUpdated);
    }
}
