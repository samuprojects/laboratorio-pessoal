# API REST de Cadastro de Clientes

Esta é uma API REST simples e funcional, construída em **Java 17** sem o uso de frameworks, JPA ou servidores externos. A aplicação utiliza **JDBC** para interagir diretamente com um banco de dados PostgreSQL e oferece endpoints para realizar operações CRUD em um cadastro de clientes.

## Tecnologias

- **Java 17**
- **JDBC** (para interação com o banco de dados PostgreSQL)
- **PostgreSQL** (banco de dados)
- **Jackson** (para conversão de objetos Java para JSON)

## Funcionalidades

A API oferece os seguintes endpoints:

1. **POST /clientes**: Cria um novo cliente.
2. **GET /clientes**: Lista todos os clientes cadastrados.
3. **GET /clientes/{id}**: Obtém um cliente pelo ID.
4. **PUT /clientes/{id}**: Atualiza os dados de um cliente existente.
5. **DELETE /clientes/{id}**: Deleta um cliente pelo ID.

## Pré-requisitos

1. **JDK 17**: A aplicação foi desenvolvida utilizando o JDK 17.
2. **PostgreSQL**: A API utiliza o banco de dados PostgreSQL. Você precisará de uma instância configurada com o banco `cadastro_db` e a tabela `clientes`.

### Script para criar o banco de dados e a tabela de clientes:

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

## Configuração do Banco de Dados

- **URL do Banco**: `jdbc:postgresql://localhost:5432/cadastro_db`
- **Usuário**: `postgres`
- **Senha**: `admin`

A configuração do banco de dados está no arquivo `DatabaseConfig.java`.

## Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/samuprojects/laboratorio-pessoal.git
   cd cadastro
   ```

2. Configure o banco de dados PostgreSQL com as tabelas e dados de exemplo fornecidos acima.

3. Compile o projeto com Maven:
   ```bash
   mvn clean install
   ```

4. Execute a aplicação:
   ```bash
   java -jar target/cadastro-1.0-SNAPSHOT.jar
   ```

A aplicação estará rodando localmente, ouvindo na porta `8080`.

## Testando a API

Você pode testar os endpoints utilizando ferramentas como **Postman** ou **cURL**.

### Exemplo de Requisição para Criar um Cliente (POST):

**URL**: `http://localhost:8080/clientes`

**Corpo da Requisição**:
```json
{
  "nome": "Peter Parker",
  "dataNascimento": "1996-08-10",
  "email": "peter.parker@exemplo.com",
  "telefone": "11-99999-1234"
}
```

### Exemplo de Requisição para Listar Todos os Clientes (GET):

**URL**: `http://localhost:8080/clientes`

### Exemplo de Requisição para Obter um Cliente (GET):

**URL**: `http://localhost:8080/clientes/{id}`

Substitua `{id}` pelo ID do cliente.

### Exemplo de Requisição para Atualizar um Cliente (PUT):

**URL**: `http://localhost:8080/clientes/{id}`

**Corpo da Requisição**:
```json
{
  "nome": "Peter Parker",
  "dataNascimento": "1996-08-10",
  "email": "peter.parker@novoemail.com",
  "telefone": "11-99999-5678"
}
```

### Exemplo de Requisição para Deletar um Cliente (DELETE):

**URL**: `http://localhost:8080/clientes/{id}`

Substitua `{id}` pelo ID do cliente a ser deletado.

## Estrutura do Projeto

```
src/
│
├── br/com/samudev/api/cadastro/
│   ├── config/
│   │   └── DatabaseConfig.java  # Configuração do banco de dados
│   ├── controller/
│   │   └── ClienteController.java  # Lógica dos endpoints da API
│   ├── exception/
│   │   └── ApiException.java  # Exceções personalizadas
│   ├── model/
│   │   └── Cliente.java  # Modelo de dados do cliente
│   ├── repository/
│   │   └── ClienteRepository.java  # Acesso ao banco de dados
│   └── service/
│       └── ClienteService.java  # Lógica de negócio para clientes
```

## Como Funciona

### Conexão com o Banco de Dados
A classe `DatabaseConfig` é responsável por criar e fornecer a conexão com o banco de dados PostgreSQL utilizando **JDBC**.

### Endpoints
- O controlador `ClienteController` mapeia os endpoints HTTP e direciona as requisições para o serviço correspondente.
- O serviço `ClienteService` contém a lógica de negócio para as operações com clientes, delegando as interações com o banco de dados para o repositório `ClienteRepository`.

### Processamento de Erros
Em caso de erro, a API retorna mensagens detalhadas com os códigos de status HTTP apropriados (por exemplo, 400 para requisições malformadas, 404 para recursos não encontrados, e 500 para erros internos do servidor).

## Considerações Finais

Este projeto foi criado para fins educacionais e demonstra como construir uma API REST simples em Java puro com JDBC. Para uso em produção, seria necessário adicionar camadas de segurança, validações e outras funcionalidades, além de ajustar a escalabilidade e a robustez.

## Licença

Este projeto é licenciado sob a MIT License - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

```

Esse README aborda como rodar a aplicação, os requisitos, e os exemplos de uso. Fique à vontade para ajustar conforme necessário!