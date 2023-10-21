package br.com.phyllipesa.todolist.models;

import br.com.phyllipesa.todolist.exceptions.TitleTooLongException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Modelo de Tarefa.
 *
 * Esta classe representa uma tarefa na aplicação.
 * Ela inclui informações como ID, descrição, título, datas de início e término,
 * prioridade, ID do usuário e data de criação.
 */
@Entity(name = "tb_tasks")
public class TaskModel {
@Id
@GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;
    private UUID idUser;
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * Construtor padrão.
     */
    public TaskModel() {
    }

    /**
     * Construtor com todos os atributos.
     *
     * @param id O ID da tarefa.
     * @param description A descrição da tarefa.
     * @param title O título da tarefa.
     * @param startAt A data de início da tarefa.
     * @param endAt A data de término da tarefa.
     * @param priority A prioridade da tarefa.
     * @param idUser O ID do usuário associado à tarefa.
     * @param createdAt A data de criação da tarefa.
     */
    public TaskModel(
            UUID id,
            String description,
            String title,
            LocalDateTime startAt,
            LocalDateTime endAt,
            String priority,
            UUID idUser,
            LocalDateTime createdAt
        ) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
        this.priority = priority;
        this.idUser = idUser;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.length() > 50) {
            throw new TitleTooLongException();
        }
        this.title = title;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
