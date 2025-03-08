# API REST em Java (sem frameworks)

Esta é uma API REST simples desenvolvida em Java 17 sem o uso de frameworks como Spring Boot ou Quarkus como parte da entrega do desafio da comunidade [Forjando Devs](https://www.linkedin.com/newsletters/forjando-devs-7151045841976262656/).

A aplicação é executada no servidor Open Liberty e conecta-se a um banco de dados PostgreSQL usando JDBC. A API expõe endpoints para realizar operações CRUD (Criar, Ler, Atualizar e Deletar) sobre clientes e está completamente documentada utilizando Swagger para facilitar a interação com os endpoints.

## Funcionalidades

A API oferece os seguintes endpoints:

- **POST /api/clientes**: Cria um novo cliente.
- **GET /api/clientes/{id}**: Busca um cliente pelo ID.
- **GET /api/clientes**: Lista todos os clientes.
- **PUT /api/clientes/{id}**: Atualiza os dados de um cliente existente.
- **DELETE /api/clientes/{id}**: Deleta um cliente.

## Estrutura do Projeto

A aplicação está organizada de acordo com as seguintes camadas:

- **Config**: Configuração de conexão com o banco de dados PostgreSQL.
- **Controller**: Controladores que definem os endpoints REST.
- **Service**: Lógica de negócios e interação com os repositórios.
- **Repository**: Camada responsável pelas operações de banco de dados.
- **Model**: Modelo de dados da aplicação (entidade Cliente).
- **Exception**: Tratamento de exceções personalizadas.

## Tecnologias Utilizadas

- **Java 17**
- **Open Liberty** (servidor de aplicação)
- **JDBC** (para conexão com o banco de dados PostgreSQL)
- **Swagger** (para documentação da API)
- **PostgreSQL** (banco de dados relacional)
- **MicroProfile OpenAPI** (para gerar a documentação da API)

## Como Executar

1. **Clonar o repositório**

   ```bash
   git clone https://github.com/samuprojects/laboratorio-pessoal.git
   cd repository
   ```

2. **Configurar o banco de dados PostgreSQL**

   Certifique-se de que o banco de dados PostgreSQL esteja configurado corretamente e com a tabela `clientes` criada. 
   O script SQL utilizado para criar o banco e a tabela são:
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
   A conexão pode ser feita usando as credenciais definidas no código (`postgres:admin`), apenas para fins didáticos.

   Os códigos que podem ser utilizados para popular o banco com dados fictícios estão na pasta sql na raiz do projeto.

3. **Compilar e empacotar a aplicação**

   Utilize o Maven para compilar e empacotar a aplicação em um arquivo WAR.

   ```bash
   mvn clean package
   ```

4. **Executar no Open Liberty**

   O arquivo WAR gerado pode ser colocado no servidor Open Liberty para execução. Certifique-se de ter o servidor Open Liberty configurado corretamente com as dependências necessárias (como Java EE 8, JAX-RS, e MicroProfile OpenAPI).

   ```bash
   mvn liberty:run
   ```

5. **Testar a API**

   A API estará disponível no seguinte endpoint:

   - **http://localhost:9080/api/clientes**

   Você pode usar o Swagger UI para interagir com os endpoints da API em:

   - **http://localhost:9080/api/openapi/ui**

## Swagger

A documentação da API é gerada automaticamente pelo **Swagger** e pode ser acessada em:

- **http://localhost:9080/api/openapi/ui**

## Dependências

- **Jackson** (para serialização e desserialização de objetos JSON)
- **JAX-RS** (para construção dos endpoints REST)
- **MicroProfile OpenAPI** (para documentação OpenAPI)
- **PostgreSQL JDBC Driver**

## Exemplo de Requisição

### Criar Cliente

```bash
POST /api/clientes
Content-Type: application/json

{
  "nome": "João da Silva",
  "dataNascimento": "1990-01-01",
  "email": "joao.silva@email.com",
  "telefone": "11-99999-9999"
}
```

### Buscar Cliente por ID

```bash
GET /api/clientes/1
```

### Atualizar Cliente

```bash
PUT /api/clientes/1
Content-Type: application/json

{
  "nome": "João da Silva Atualizado",
  "dataNascimento": "1990-01-01",
  "email": "joao.silva.updated@email.com",
  "telefone": "11-99999-8888"
}
```

### Deletar Cliente

```bash
DELETE /api/clientes/1
```

## Contribuindo

Sinta-se à vontade para abrir *issues* ou fazer *forks* deste repositório. Toda contribuição será bem-vinda.

---

## Licença

Este projeto está licenciado sob a Licença MIT.

---

**Desenvolvido por [samudev](https://github.com/samuprojects)**
