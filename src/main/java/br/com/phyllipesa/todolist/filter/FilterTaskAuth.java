package br.com.phyllipesa.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.phyllipesa.todolist.models.UserModel;
import br.com.phyllipesa.todolist.repository.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

/**
 * Filtro de Autenticação de Tarefa.
 *
 * Esta classe atua como um filtro para autenticar solicitações relacionadas a tarefas.
 * Ela verifica as informações de autenticação na solicitação, valida o usuário e a senha e,
 * se bem-sucedido, permite que a solicitação prossiga.
 * Caso contrário, ela retorna um erro de autenticação (401 Unauthorized).
 */
@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    /**
     * Recebe um bean do tipo IUserRepository por injeção de dependência.
     */
    @Autowired
    private IUserRepository userRepository;

    /**
     * Implementação do filtro de autenticação.
     *
     * Este método é chamado para cada solicitação e executa a lógica de autenticação,
     * validando as credenciais do usuário. Se as credenciais forem válidas,
     * a solicitação continuará com o atributo "idUser" definido como o ID do usuário autenticado.
     * Caso contrário, uma resposta de erro 401 Unauthorized será retornada.
     *
     * @param request A solicitação HTTP recebida.
     * @param response A resposta HTTP a ser enviada.
     * @param filterChain O encadeamento de filtros a ser executado.
     * @throws ServletException Se ocorrer uma exceção de servlet.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks/")) {

            // Pegar a autenticação (usuario e senha)
            String authorization = request.getHeader("Authorization");

            String authEncoded = authorization.substring("Basic".length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            String authString = new String(authDecode);

            //["valento", "racionais"]
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            // Validar usuario
            UserModel user = this.userRepository.findByUsername(username);

            if (user == null) {
                response.sendError(401);
            } else {
                // Validar senha
                BCrypt.Result passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    // Segue viagem
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }

            }

        } else {
            filterChain.doFilter(request, response);
        }
    }
}
