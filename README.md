# TodoList Web Application

Este é um projeto de exemplo de uma aplicação web de TodoList em Java, desenvolvido com o uso do Apache Maven. O projeto inclui classes que representam tarefas, usuários e utilitários para manipulação de dados.

## Descrição do Projeto

Este projeto tem como objetivo demonstrar o desenvolvimento de uma aplicação web simples de gerenciamento de tarefas (TodoList) em Java. Ele utiliza as seguintes classes principais:

- `TaskModel`: Uma classe que representa uma tarefa, incluindo informações como ID, descrição, título, datas de início e término, prioridade, ID do usuário e data de criação.

- `UserModel`: Uma classe que representa um usuário, incluindo informações como ID, nome de usuário, nome, senha e data de criação.

- `Utils`: Uma classe utilitária que fornece métodos para copiar propriedades não nulas de um objeto para outro e para obter os nomes de propriedades nulas em um objeto.

Além disso, o projeto inclui controladores (`TaskController` e `UserController`) para lidar com operações relacionadas a tarefas e usuários.

## Executar Localmente

Para executar este projeto, você precisará de uma instalação do Apache Maven. Depois de configurar o ambiente, siga estas etapas:

1. Clone o repositório para o seu ambiente local.
   ```bash
    git clone git@github.com:Phyllipesa/todolist.git

2. Navegue até o diretório raiz do projeto.
    ```bash
    cd todolist/

3. Execute o seguinte comando para compilar e executar o projeto:

   ```bash
   mvn spring-boot:run
   

## Tecnologias Utilizadas

- Java
- Apache

  
## Autores

    @phyllipesa - Desenvolvimento do projeto
    @Rocketseat - Commit inicial

