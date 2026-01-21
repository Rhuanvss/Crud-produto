# Projeto Spring Boot API - Gerenciamento de Produtos e Usuários

Este é uma API RESTful desenvolvida com Spring Boot, focada no gerenciamento de produtos e autenticação de usuários segura utilizando JWT (JSON Web Token). O projeto utiliza PostgreSQL como banco de dados, rodando em um container Docker.

## 🚀 Tecnologias Utilizadas

*   **Java**: Linguagem de programação principal.
*   **Spring Boot**: Framework para facilitar a criação da aplicação.
    *   **Spring Data JPA**: Para persistência de dados e interação com o banco de dados.
    *   **Spring Security**: Para autenticação e controle de acesso.
    *   **Spring Web**: Para criação dos controladores REST.
*   **JWT (JSON Web Token)**: Para autenticação *stateless* segura.
*   **PostgreSQL**: Banco de dados relacional.
*   **Docker & Docker Compose**: Para containerização do banco de dados.
*   **Maven**: Gerenciador de dependências e build.

## 🏗️ Padrões de Projeto

### 🔄 Data Transfer Objects (DTO)
O projeto adota o padrão **DTO** para a transferência de dados entre a camada de controle e o cliente.
*   **Segurança:** Previne a exposição acidental de dados sensíveis das entidades JPA.
*   **Desacoplamento:** Separa a estrutura interna do banco de dados da API pública.
*   **Java Records:** Utiliza a feature de Records do Java para criar DTOs imutáveis e concisos (ex: `ProdutoDTO`).

## ⚙️ Funcionalidades

### 🔐 Autenticação e Segurança
*   Registro e Login de usuários.
*   Criptografia de senhas (BCrypt).
*   Geração e validação de Tokens JWT.
*   Proteção de rotas (apenas usuários autenticados podem gerenciar produtos).

### 📦 Gerenciamento de Produtos
*   **CRUD** completo:
    *   Criar Produto (`POST`)
    *   Listar Produtos (`GET`)
    *   Buscar Produto por ID (`GET`)
    *   Atualizar Produto (`PUT`)
    *   Deletar Produto (`DELETE`)
*   Tratamento de exceções personalizado (ex: `RecursoNaoEncontradoException`) com respostas HTTP adequadas (`ResponseEntity`).

## 🛠️ Como Rodar o Projeto

### Pré-requisitos
*   Java JDK 17+ instalado.
*   Maven instalado (ou usar o wrapper `mvnw` incluso).
*   Docker e Docker Compose instalados.

### Passo 1: Configurar o Banco de Dados
O projeto utiliza um arquivo `docker-compose.yml` para subir o PostgreSQL. Execute o comando na raiz do projeto:

```bash
docker-compose up -d
```

Isso iniciará o banco de dados na porta `5432` com as configurações definidas no arquivo compose.

### Passo 2: Configurar Variáveis (Opcional)
Verifique o arquivo `src/main/resources/application.properties`. Por padrão, ele espera conectar no localhost. Se necessário, ajuste as credenciais se você alterou o `docker-compose.yml`.

### Passo 3: Executar a Aplicação
Você pode rodar a aplicação via linha de comando:

```bash
# Linux/Mac
./mvnw spring-boot:run

# Windows
.\mvnw.cmd spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

## 📚 Documentação da API (Endpoints)

### Autenticação (`/auth`)
*   `POST /auth/login`: Autentica o usuário e retorna o Token JWT.
    *   Body: `{ "username": "...", "password": "..." }`

### Produtos (`/produtos`)
> **Nota:** É necessário enviar o token JWT no Header `Authorization` como `Bearer <seu_token>` para as requisições abaixo.

*   `GET /produtos`: Lista todos os produtos.
*   `GET /produtos/{id}`: Retorna detalhes de um produto específico.
*   `POST /produtos`: Cria um novo produto.
*   `PUT /produtos/{id}`: Atualiza um produto existente.
*   `DELETE /produtos/{id}`: Remove um produto.

## ⚠️ Tratamento de Erros
A aplicação possui um `GlobalExceptionHandler` que intercepta erros comuns e retorna JSONs formatados, facilitando o entendimento do erro pelo cliente da API.

---
Desenvolvido como parte de estudos em Spring Boot.
