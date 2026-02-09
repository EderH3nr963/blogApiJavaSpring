# Blog API

API REST desenvolvida em Java Spring Boot para gerenciamento de um sistema de blog, com funcionalidades de autenticação, autorização e CRUD de posts e usuários.

## 📋 Descrição

Esta é uma API de blog desenvolvida para aprofundamento em Java Spring Boot, implementando conceitos de segurança com JWT, paginação, soft delete e controle de acesso baseado em roles (USER e ADMIN).

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 4.0.2**
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **MySQL** - Banco de dados
- **JWT (Auth0)** - Tokens de autenticação
- **Swagger/OpenAPI 3.0** - Documentação da API
- **Maven** - Gerenciamento de dependências
- **Jakarta Validation** - Validação de dados

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
```

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

**⚠️ Importante:** Em produção, use variáveis de ambiente ao invés de hardcoded secrets.

### 4. Execute o projeto

```bash
mvn spring-boot:run
```

Ou execute a classe `DemoApplication` diretamente na sua IDE.

A aplicação estará disponível em: `http://localhost:8080`

## 📚 Documentação da API

A documentação interativa da API está disponível através do Swagger UI:

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **OpenAPI JSON:** http://localhost:8080/v3/api-docs

## 🔐 Autenticação

A API utiliza autenticação baseada em JWT (JSON Web Tokens). Para acessar endpoints protegidos:

1. Faça login ou registro através dos endpoints de autenticação
2. Copie o token retornado na resposta
3. Inclua o token no header das requisições:

```
Authorization: Bearer <seu_token>
```

### Endpoints de Autenticação

- `POST /api/v1/auth/register` - Registrar novo usuário
- `POST /api/v1/auth/login` - Fazer login

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
├── mapper/              # Mappers para conversão de objetos
├── repository/          # Interfaces JPA Repository
├── security/            # Configurações de segurança e JWT
└── service/             # Lógica de negócio
```

## 🛣️ Endpoints Principais

### Autenticação

- `POST /api/v1/auth/register` - Registrar usuário
- `POST /api/v1/auth/login` - Login

### Posts

- `GET /api/v1/post` - Listar posts (paginação)
- `GET /api/v1/post/{postId}` - Buscar post por ID
- `GET /api/v1/post/author/{authorId}` - Listar posts por autor
- `POST /api/v1/post` - Criar post (autenticado)
- `PATCH /api/v1/post/{postId}/content` - Atualizar conteúdo (autor ou admin)
- `DELETE /api/v1/post/{postId}` - Deletar post (autor ou admin)

### Usuários

- `GET /api/v1/users/me` - Obter perfil do usuário logado
- `PATCH /api/v1/users/email` - Atualizar email
- `PATCH /api/v1/users/username` - Atualizar username
- `PATCH /api/v1/users/password` - Atualizar senha
- `DELETE /api/v1/users` - Deletar conta (soft delete)

### Admin (requer role ADMIN)

- `GET /api/v1/admin/**` - Endpoints administrativos

## 👥 Roles e Permissões

A API possui dois níveis de acesso:

- **USER**: Usuário padrão, pode criar e gerenciar seus próprios posts
- **ADMIN**: Administrador, possui acesso completo ao sistema

## 🔒 Segurança

- Senhas são codificadas usando BCrypt
- Tokens JWT com expiração de 48 horas
- Proteção CSRF desabilitada (configurar adequadamente em produção)
- CORS configurado
- Validação de dados de entrada

## 📝 Modelos de Dados

### Usuario

- `id` (UUID)
- `username` (único)
- `email` (único)
- `password` (hash)
- `role` (USER/ADMIN)
- `blocked` (boolean)
- `deleted` (boolean)

### Post

- `id` (UUID)
- `title` (String)
- `content` (String)
- `author` (Usuario)
- `createdAt` (Date)
- `updatedAt` (Date)
- `deleted` (boolean)


## 🔄 Próximos Passos

- [ ] Adicionar validação de força de senha
- [ ] Adicionar testes unitários e de integração
- [ ] Implementar filtros para soft delete nas queries de post
- [ ] Configurar variáveis de ambiente para produção

## 📄 Licença

Este projeto é um projeto de demonstração para fins educacionais.

## 👨‍💻 Autor

Desenvolvido para aprofundamento em Java Spring Boot.

---

**Versão:** 1.0.0  
**Última atualização:** 2026
