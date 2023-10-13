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

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

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

    @GetMapping("/")
    public ResponseEntity<List<TaskModel>> getTasksById(HttpServletRequest request) {
        List<TaskModel> tasks = taskRepository.findByIdUser((UUID) request.getAttribute("idUser"));
        return ResponseEntity.ok(tasks);
    }

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
