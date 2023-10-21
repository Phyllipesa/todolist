package br.com.phyllipesa.todolist.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Modelo de Usuário.
 *
 * Esta classe representa um usuário na aplicação.
 * Ela inclui informações como ID, nome de usuário, nome, senha e data de criação.
 * Os usuários são armazenados na tabela "tb_users".
 */
@Entity(name="tb_users")
public class UserModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @Column(unique = true)
  private String username;
  private String name;
  private String password;

  @CreationTimestamp
  private LocalDateTime createdAt;

  /**
   * Construtor padrão.
   */
  public UserModel() {
  }

  /**
   * Construtor com todos os atributos.
   *
   * @param id O ID do usuário.
   * @param username O nome de usuário exclusivo.
   * @param name O nome do usuário.
   * @param password A senha do usuário.
   * @param createdAt A data de criação do usuário.
   */
  public UserModel(UUID id, String username, String name, String password, LocalDateTime createdAt) {
    this.id = id;
    this.username = username;
    this.name = name;
    this.password = password;
    this.createdAt = createdAt;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
