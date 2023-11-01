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

## Executando a Aplicação com Docker e Docker Compose

Para executar a aplicação com Docker e Docker Compose, siga as etapas abaixo:

1. **Clone o repositório**:

```shell
  git clone git@github.com:wladyrjusta/projeto-api-localizador-de-museus-java.git
```


  # Navegue até o diretório do projeto:
  ```shell
  cd projeto-api-localizador-de-museus-java
```

Crie uma imagem Docker:

```shell
docker build -t localizador-de-museus .
```

Inicie um contêiner Docker com a imagem criada e o banco de dados MySQL usando o Docker Compose:

```shell
docker-compose up -d
```

Isso iniciará um contêiner MySQL com as configurações especificadas no arquivo docker-compose.yml e o contêiner da aplicação Spring Boot. Certifique-se de que ambos os contêineres estejam em execução antes de acessar a aplicação.

Aplicação disponivél na rota:

```shell
http://localhost:8080/
```

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

