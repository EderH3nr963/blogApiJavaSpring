# Blog API

API REST desenvolvida em Java Spring Boot para gerenciamento de um sistema de blog, com funcionalidades de autenticação, autorização e CRUD de posts e usuários.

## 📋 Descrição

Esta é uma API de blog desenvolvida para aprofundamento em Java Spring Boot, implementando conceitos de segurança com JWT, paginação, **soft delete lógico (flag `deleted`)** e controle de acesso baseado em roles (**USER** e **ADMIN**).

O projeto segue boas práticas de arquitetura REST, separação de responsabilidades e uso de DTOs para evitar a exposição direta das entidades.

## 🧪 Status do Projeto

🚧 Em desenvolvimento para fins de estudo e evolução contínua.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 4.0.2**
- **Spring Security** – Autenticação e autorização
- **Spring Data JPA** – Persistência de dados
- **MySQL** – Banco de dados relacional
- **JWT (Auth0)** – Tokens de autenticação
- **Swagger / OpenAPI 3.0** – Documentação da API
- **Maven** – Gerenciamento de dependências
- **Jakarta Validation** – Validação de dados

## 📦 Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- Java 17 ou superior
- Maven 3.6+
- MySQL 8.0+
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)

## ⚙️ Configuração

### 1. Clone o repositório

```bash
git clone <url-do-repositório>
cd demo
````

### 2. Configure o banco de dados

Crie um banco de dados MySQL:

```sql
CREATE DATABASE blogApi;
```

### 3. Configure as variáveis de ambiente

Edite o arquivo `src/main/resources/application.properties` e configure:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blogApi
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Configure uma chave secreta forte para JWT
api.security.token.secret=sua_chave_secreta_aqui
```

**⚠️ Importante:** em ambientes de produção, utilize variáveis de ambiente para armazenar credenciais e secrets sensíveis.

### 4. Execute o projeto

```bash
mvn spring-boot:run
```

Ou execute a classe principal diretamente pela IDE.

A aplicação estará disponível em:
`http://localhost:8080`

## 📚 Documentação da API

A documentação interativa da API está disponível via Swagger:

* **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## 🔐 Autenticação

A API utiliza autenticação baseada em JWT (JSON Web Token). Para acessar endpoints protegidos:

1. Realize o registro ou login
2. Copie o token retornado na resposta
3. Envie o token no header das requisições:

```http
Authorization: Bearer <seu_token>
```

### Endpoints de Autenticação

* `POST /api/v1/auth/register` – Registrar novo usuário
* `POST /api/v1/auth/login` – Autenticar usuário

## 📁 Estrutura do Projeto

```
src/main/java/com/blog/demo/
├── config/              # Configurações (Security, CORS, Swagger)
├── controller/          # Controllers REST
├── domain/              # Entidades JPA
│   ├── post/
│   └── usuario/
├── dto/                 # Data Transfer Objects
│   ├── request/
│   └── response/
├── exception/           # Exceções customizadas
├── mapper/              # Conversão entre entidades e DTOs
├── repository/          # Interfaces JPA Repository
├── security/            # Configuração de segurança e JWT
└── service/             # Regras de negócio
```

## 🛣️ Endpoints Principais

### 🔑 Autenticação

* `POST /api/v1/auth/register` – Registrar usuário
* `POST /api/v1/auth/login` – Login

### 📝 Posts

* `GET /api/v1/post` – Listar posts (paginação)
* `GET /api/v1/post/{postId}` – Buscar post por ID
* `GET /api/v1/post/author/{authorId}` – Listar posts por autor
* `POST /api/v1/post` – Criar post (usuário autenticado)
* `PATCH /api/v1/post/{postId}/content` – Atualizar conteúdo (autor ou admin)
* `DELETE /api/v1/post/{postId}` – Remover post (soft delete)

### 👤 Usuários

* `GET /api/v1/users/me` – Obter perfil do usuário autenticado
* `PATCH /api/v1/users/email` – Atualizar email
* `PATCH /api/v1/users/username` – Atualizar username
* `PATCH /api/v1/users/password` – Atualizar senha
* `DELETE /api/v1/users` – Remover conta (soft delete)

### 🛠️ Administração (role ADMIN)

* `GET /api/v1/admin/users` – Listar usuários
* `GET /api/v1/admin/users/{id}` – Buscar usuário por ID
* `PATCH /api/v1/admin/users/{id}` – Atualizar dados do usuário
* `DELETE /api/v1/admin/users/{id}` – Remover usuário

## 👥 Roles e Permissões

* **USER**

    * Criar e gerenciar seus próprios posts
    * Gerenciar seus dados pessoais

* **ADMIN**

    * Acesso completo aos usuários
    * Moderação de posts e comentários

## 🔒 Segurança

* Senhas armazenadas com **BCrypt**
* Autenticação stateless com JWT
* Tokens com expiração configurada
* Proteção CSRF desabilitada (adequado para APIs REST)
* Configuração de CORS
* Validação de dados com Jakarta Validation

## 📝 Modelos de Dados

### Usuário

* `id` (UUID)
* `username` (único)
* `email` (único)
* `password` (hash)
* `role` (USER / ADMIN)
* `blocked` (boolean)
* `deleted` (boolean)

### Post

* `id` (UUID)
* `title`
* `content`
* `author` (Usuário)
* `createdAt`
* `updatedAt`
* `deleted` (boolean)

## 🏗️ Decisões de Arquitetura

* Uso de DTOs para evitar exposição de entidades
* Separação em camadas (Controller, Service, Repository)
* Autenticação baseada em JWT
* Controle de acesso por roles
* Soft delete para preservar histórico de dados

## 📄 Licença

Projeto desenvolvido para fins educacionais.

## 👨‍💻 Autor

Eder
Estudante de Análise e Desenvolvimento de Sistemas
Focado em Java, Spring Boot e APIs REST

---

**Versão:** 1.0.0
**Última atualização:** 2026

