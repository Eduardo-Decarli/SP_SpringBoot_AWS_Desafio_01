# Library Manager

Esse é o sistema que faz a gerencia de uma biblioteca, onde o gestor do sistema pode cadastrar outros gestores, cadastrar livros, emprestimos e retornos de emprestimos. O sistema foi desenvolvido para ser trabalhado inteiramente pelo terminal, utilizando a tecnologia java e com associação ao banco de dados.

## Fluxo de Dados

1. O sistema solicita ao usuário se quer fazer login como Member (Utilizando o email como forma de acesso) ou se quer registrar outro um novo Member.

2. O sistema apresenta um menu de opções aonde o usuario pode escolher entre **registrar um livro**, **registrar um autor**, **registrar um Member**(Segundo método de registrar um Member), **registrar um emprestimo**, **registrar um retorno de emprestimo**, **gerar um relatório** e **sair do sistema**.

3. Ao selecionar registrar um livro, o usuario irá inserir os dados do livro e o sistema solicita se quer associar a um autor já cadastrado ou cadastrar um novo autor para aquele livro.

4. 

## Tecnologias utilizadas

- VSCode
- InteliJ
- Java 21
- Git
- GitHub
- MySQL
- DrawIo

## Funcionalidades Principais



## Funcionalidades do Banco de dados

- Classe AuthorDAO
    - Busca autor pelo id.
    - Busca autor pelo nome.
    - Busca todos os autores.
    - Insere um novo autor.

- Classe BookDAO 
    - Seleciona um livro pelo Isbn.

- Classe MemberDAO
    - Insere um membro no banco de dados
    - Busca um membro pelo email

## Lógica Preventiva

- Não permite cadastrar dois Member com o mesmo Email.

- Não permite enviar um email vazio para o banco de dados.

- ISBN de um livro é obrigatoriamente 8 digitos.

- Não permite cadastrar dois autores com o mesmo nome.