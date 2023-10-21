package br.com.phyllipesa.todolist.repository;

import br.com.phyllipesa.todolist.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Interface de Repositório de Usuários.
 *
 * Esta interface define operações de acesso a dados para os usuários na aplicação.
 * Ela estende a interface JpaRepository, permitindo a busca e manipulação de objetos do
 * tipo UserModel por meio de UUID. Além disso, fornece um método personalizado para
 * buscar um usuário com base no nome de usuário.
 */
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
  /**
   * Encontra um usuário por nome de usuário.
   *
   * Este método personalizado permite a recuperação de um usuário com base no nome de usuário fornecido.
   * É útil para verificar a existência de um usuário com um determinado nome de usuário.
   *
   * @param username O nome de usuário a ser pesquisado.
   * @return O objeto UserModel associado ao nome de usuário especificado, ou null se nenhum usuário for encontrado.
   */
  UserModel findByUsername(String username);
}
