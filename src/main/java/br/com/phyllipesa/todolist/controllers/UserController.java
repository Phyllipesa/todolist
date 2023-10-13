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

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private IUserRepository userRepository;

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
