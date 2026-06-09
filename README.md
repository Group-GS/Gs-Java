#  Projeto Agro

API REST desenvolvida para gerenciamento agrícola inteligente, permitindo o controle de usuários, sensores, leituras ambientais, cultivos, culturas, biomas, alertas e localizações de monitoramento.

---

##  Sobre o projeto

O **Projeto Agro** foi desenvolvido com o objetivo de simular um sistema backend para monitoramento agrícola e apoio à tomada de decisões no campo, aplicando conceitos de:

- Arquitetura em Camadas
- API REST
- Persistência com JPA
- DTOs e Projections
- Paginação
- Cache
- Documentação com Swagger/OpenAPI
- Banco de Dados H2
- Autenticação JWT
- Spring Security

---

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Spring Validation
- Spring Security
- JWT (JSON Web Token)
- Spring Cache
- H2 Database
- Lombok
- Swagger / OpenAPI
- Maven

---

## Estrutura do projeto

```bash
src/main/java/br/com/gs/projeto_agro/
│
├── config/         # Configurações (Swagger, Cache, etc.)
├── control/        # Controllers da API
├── dto/            # Objetos de transferência de dados
├── model/          # Entidades do sistema
├── projection/     # Projeções
├── repository/     # Repositórios JPA
├── security/       # Configurações JWT e Spring Security
├── service/        # Regras de negócio
└── validations/    # Validações customizadas
```

---

##  Entidades principais

O sistema possui as seguintes entidades:

- Usuário
- Sensor
- Leitura
- Cultivo
- Cultura
- Bioma
- Alerta
- Local

---

## Configuração do projeto

### 1. Clonar o repositório

```bash
git clone <url-do-repositorio>
cd projeto-agro
```

---

### 2. Rodar a aplicação

Usando Maven:

```bash
./mvnw spring-boot:run
```

ou no Windows:

```bash
mvnw.cmd spring-boot:run
```

---

##  Porta da aplicação

A aplicação roda em:

```bash
http://localhost:8083
```

---

##  Banco de dados H2

Acesso ao console:

```bash
http://localhost:8083/h2-console
```

## Configuração do Banco de Dados

Para executar o projeto corretamente, cada integrante deverá configurar o banco de dados em seu próprio ambiente.

O projeto não utiliza um banco compartilhado. Portanto, é necessário criar e utilizar uma instância de banco de dados vinculada à sua própria conta.

### Passos para configuração

1. Crie ou utilize uma instância de banco de dados em sua própria conta.
2. Atualize as configurações do arquivo `application.properties` com as credenciais do seu banco.
3. Certifique-se de que o usuário possui permissões para criar e manipular tabelas.
4. Execute a aplicação para que o Spring Boot crie automaticamente as estruturas necessárias.

### Exemplo de configuração Oracle

```properties
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
##  Swagger

Documentação interativa disponível em:

```bash
http://localhost:8080/swagger-ui.html
```

ou

```bash
http://localhost:8080/swagger-ui/index.html
```

---

##  Autenticação JWT

A API utiliza autenticação baseada em JWT (JSON Web Token).

### Login

```http
POST /autenticacao/login
```

### Parâmetros

```txt
usuario
senha
duracao
```
### Exemplo de retorno

```txt
eyJhbGciOiJIUzI1NiJ9...
```

---
### Utilização do Token

Após realizar o login:

1. Copie o token retornado.
2. Clique em **Authorize** no Swagger.
3. Informe:

```txt
Bearer seu_token_aqui
```

4. Clique em **Authorize** e depois em **Close**.

Agora será possível acessar todos os endpoints protegidos da API.

---

## Funcionalidades

Cadastro de usuários

Gerenciamento de sensores

Registro de leituras ambientais

Controle de cultivos

Gerenciamento de culturas agrícolas

Cadastro de biomas

Sistema de alertas

Gestão de localizações

Paginação de resultados

Cache para otimização

Documentação interativa com Swagger

Autenticação e autorização com JWT

---

## 👥 Integrantes do Grupo

Aluno: Lucas Rafael Solimene / RM: 565194

Aluno: Samyr Couto Oliveira / RM: 565562

Aluno: Henrique Teixeira Cesar / RM: 563088

