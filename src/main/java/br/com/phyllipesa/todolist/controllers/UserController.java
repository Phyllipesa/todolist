package br.com.phyllipesa.todolist.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.phyllipesa.todolist.models.UserModel;
import br.com.phyllipesa.todolist.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de Usuários.
 *
 * Esta classe atua como um controlador para manipular operações relacionadas a usuários na aplicação.
 * Ela é mapeada para o caminho "/users" e fornece endpoint para criar usuários.
 */
@RestController
@RequestMapping("/users")
public class UserController {
  /**
   * Recebe um bean do tipo IUserRepository por injeção de dependência.
   */
  @Autowired
  private IUserRepository userRepository;

  /**
   * create - Cria um novo usuário.
   *
   * Este endpoint permite a criação de um novo usuário com base nas informações fornecidas.
   * O endpoint verifica se já existe um usuário com o mesmo nome de usuário (username) e,
   * em caso afirmativo, retorna um erro. Caso contrário, o usuário é criado com sucesso.
   *
   * @param userModel Um objeto contendo as informações do novo usuário, incluindo nome de usuário e senha.
   * @return Um ResponseEntity com status HTTP OK (200) e o objeto UserModel criado.
   * Em caso de erro, pode retornar um status HTTP 400 com uma mensagem de erro apropriada.
   */
  @PostMapping("/")
  public ResponseEntity create(@RequestBody UserModel userModel) {
    UserModel user = this.userRepository.findByUsername(userModel.getUsername());

    if (user != null) {
      return ResponseEntity.status(400).body("Usuário já existe");
    }

    String passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
    userModel.setPassword(passwordHashred);

    UserModel userCreated = this.userRepository.save(userModel);
    return ResponseEntity.ok(userCreated);
  }
}
