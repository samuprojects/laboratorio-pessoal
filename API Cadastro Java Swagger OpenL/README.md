
---

# API REST de Cadastro de Clientes

Esta API REST permite o cadastro, consulta, atualização e remoção de clientes em um banco de dados PostgreSQL. A aplicação foi desenvolvida utilizando Java 17, Open Liberty como servidor de aplicação, e Swagger para documentação.

## Tecnologias Utilizadas

- **Java 17** (JDK)
- **Open Liberty** (Servidor de aplicação)
- **Swagger** (Documentação da API)
- **PostgreSQL** (Banco de dados)
- **JDBC** (Conexão com banco de dados)
- **JAX-RS** (API para construção de serviços REST)

## Estrutura do Projeto

- `src/main/java/br/com/samudev/api/cadastro` - Pacote principal com a lógica da API.
  - **config** - Configuração da conexão com o banco de dados.
  - **controller** - Controladores responsáveis pelos endpoints da API.
  - **model** - Modelos de dados (entidades).
  - **repository** - Acesso direto ao banco de dados.
  - **service** - Lógica de negócio para manipulação dos dados.

## Endpoints da API

### 1. **POST** `/clientes`
Cria um novo cliente no banco de dados.

#### Requisição:
```json
{
  "nome": "Nome do Cliente",
  "dataNascimento": "YYYY-MM-DD",
  "email": "cliente@exemplo.com",
  "telefone": "11-99999-1234"
}
```

#### Resposta (201 Created):
```json
{
  "id": 1,
  "nome": "Nome do Cliente",
  "dataNascimento": "YYYY-MM-DD",
  "email": "cliente@exemplo.com",
  "telefone": "11-99999-1234"
}
```

### 2. **GET** `/clientes/{id}`
Busca um cliente pelo ID.

#### Exemplo de URL:
`GET /clientes/1`

#### Resposta (200 OK):
```json
{
  "id": 1,
  "nome": "Nome do Cliente",
  "dataNascimento": "YYYY-MM-DD",
  "email": "cliente@exemplo.com",
  "telefone": "11-99999-1234"
}
```

#### Resposta (404 Not Found):
```json
{
  "message": "Cliente não encontrado"
}
```

### 3. **GET** `/clientes`
Busca todos os clientes cadastrados.

#### Resposta (200 OK):
```json
[
  {
    "id": 1,
    "nome": "Nome do Cliente",
    "dataNascimento": "YYYY-MM-DD",
    "email": "cliente@exemplo.com",
    "telefone": "11-99999-1234"
  },
  {
    "id": 2,
    "nome": "Outro Cliente",
    "dataNascimento": "YYYY-MM-DD",
    "email": "outro@exemplo.com",
    "telefone": "11-98888-1234"
  }
]
```

### 4. **PUT** `/clientes/{id}`
Atualiza os dados de um cliente existente.

#### Requisição:
```json
{
  "nome": "Novo Nome",
  "dataNascimento": "YYYY-MM-DD",
  "email": "novocliente@exemplo.com",
  "telefone": "11-98765-4321"
}
```

#### Resposta (204 No Content):
Sem corpo de resposta.

### 5. **DELETE** `/clientes/{id}`
Deleta um cliente pelo ID.

#### Resposta (204 No Content):
Sem corpo de resposta.

---

## Instruções para Execução

1. **Clonando o repositório:**
   Clone o repositório para a sua máquina local:
   ```bash
   git clone https://github.com/samuprojects/laboratorio-pessoal.git
   cd cadastro
   ```

2. **Configuração do Banco de Dados:**
   Certifique-se de ter o PostgreSQL instalado e rodando. Crie o banco de dados `cadastro_db` e execute o script SQL fornecido para criar a tabela `clientes`.

   ```sql
   CREATE DATABASE cadastro_db;
   CREATE TABLE clientes (
       id SERIAL PRIMARY KEY,
       nome VARCHAR(100),
       data_nascimento DATE,
       email VARCHAR(100),
       telefone VARCHAR(15)
   );
   ```

3. **Compilando e Executando a API:**
   Utilize o Maven para compilar e rodar a aplicação:
   ```bash
   mvn clean install
   mvn liberty:run
   ```

4. **Acessando a API:**
   Após a execução da aplicação, a API estará disponível em `http://localhost:9080`. Você pode usar o Swagger para explorar a API em `http://localhost:9080/openapi`.

---

## Swagger

A documentação da API está disponível via Swagger, que é automaticamente gerado e integrado ao Open Liberty.

- Acesse a documentação da API no seguinte endpoint:  
  `http://localhost:9080/openapi`

---

## Licença

Este projeto está licenciado sob a Licença MIT.

---

