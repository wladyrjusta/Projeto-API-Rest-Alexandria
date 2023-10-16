# API Rest Alexandria

Projeto para uma API Rest com persistência de dados no Spring Data JPA, executando em um container com banco de dados MySQL e fornecendo operações CRUD para quatro tabelas: authors, books, publishers e book_details.

## Visão Geral

- **Authors**: Autores de livros.
- **Books**: Informações sobre livros.
- **Publishers**: Editoras de livros.
- **Book_Details**: Detalhes adicionais de cada livro.

Relacionamentos:
- Books e Book_Details: 1:1.
- Books e Authors: N:N.
- Books e Publishers: 1:N.

## Configuração e Execução

1. **Pré-requisitos**: Java configurado e Docker rodando.

2. **Clonando o Repositório**: Clone o projeto do GitHub.

3. **Configurando o Banco de Dados**: Configure as credenciais do MySQL em `application.properties`.

4. **Build e Execução**: Execute a aplicação Spring Boot. Acesse em `http://localhost:8080`.

## Endpoints da API

- `GET /authors`: Lista de autores.
- `GET /books`: Lista de livros.
- `GET /publishers`: Lista de editoras.
- `GET /book_details`: Lista de detalhes de livros.

- `POST /authors`: Criar autor.
- `POST /books`: Criar livro.
- `POST /publishers`: Criar editora.
- `POST /book_details`: Criar detalhe de livro.

- `PUT /authors/{id}`: Atualizar autor.
- `PUT /books/{id}`: Atualizar livro.
- `PUT /publishers/{id}`: Atualizar editora.
- `PUT /book_details/{id}`: Atualizar detalhe de livro.

- `DELETE /authors/{id}`: Excluir autor.
- `DELETE /books/{id}`: Excluir livro.
- `DELETE /publishers/{id}`: Excluir editora.
- `DELETE /book_details/{id}`: Excluir detalhe de livro.

