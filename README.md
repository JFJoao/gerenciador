# Gerenciador

Este projeto consiste em uma API REST desenvolvida em Java utilizando o framework Spring Boot, com o objetivo de gerenciar tarefas e alocar pessoas.

## 📚 Índice

- [Descrição](#descrição)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Instalação](#instalação)
- [Configuração do Banco de Dados](#configuração-do-banco-de-dados)
- [Executando o Projeto](#executando-o-projeto)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Endpoints da API](#endpoints-da-api)
- [Testes](#testes)
- [Contribuições](#contribuições)
- [Licença](#licença)

## 📄 Descrição

O Gerenciador de Tarefas é uma API REST que permite o gerenciamento de tarefas e a alocação de pessoas a essas tarefas. Cada tarefa possui um título, descrição, prazo, duração, status de finalização, entre outros atributos. A API também permite consultar informações sobre as tarefas e as pessoas envolvidas, bem como verificar o tempo gasto em cada tarefa.

## 💻 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **JUnit 5**
- **Maven**

## Instalação

1. **Clone o repositório:**
   ```console
   git clone https://github.com/JFJoao/gerenciador
   cd gerenciador
   ```
2. **Instale as dependências:**
   ```console
   mvn install
   ```
## 🛠 Configuração do Banco de Dados
Antes de rodar a aplicação, configure o banco de dados PostgreSQL. No arquivo src/main/resources/application.properties, insira as credenciais do seu banco de dados:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco_de_dados
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```
## 🗃 Scripts de População do Banco de Dados
Para popular o banco de dados automaticamente com dados iniciais, utilize o arquivo data.sql localizado em src/main/resources:

```console
--DDL
-- Inserir dados na tabela pessoa
INSERT INTO pessoa (id, nome, departamento) VALUES (1,'João Pereira', 'TI') ON CONFLICT (id) DO NOTHING;
INSERT INTO pessoa (id, nome, departamento) VALUES (2,'Ana Paula', 'Financeiro') ON CONFLICT (id) DO NOTHING;
INSERT INTO pessoa (id, nome, departamento) VALUES (3,'Carlos Souza', 'RH') ON CONFLICT (id) DO NOTHING;

-- Inserir dados na tabela tarefa
INSERT INTO tarefa (id, titulo, descricao, prazo, departamento, duracao, pessoa_alocada_id, finalizado)
VALUES (1, 'Implementar API', 'Desenvolver endpoints REST', '2024-12-31', 'TI', 10, 1, FALSE) ON CONFLICT (id) DO NOTHING;
INSERT INTO tarefa (id, titulo, descricao, prazo, departamento, duracao, pessoa_alocada_id, finalizado)
VALUES (2, 'Revisar Documentos', 'Revisar todos os contratos', '2024-10-15', 'RH', 5, 2, TRUE) ON CONFLICT (id) DO NOTHING;
INSERT INTO tarefa (id, titulo, descricao, prazo, departamento, duracao, pessoa_alocada_id, finalizado)
VALUES (3, 'Preparar Relatório Financeiro', 'Criar o relatório financeiro anual', '2024-11-30', 'Financeiro', 15, 3, FALSE) ON CONFLICT (id) DO NOTHING;

```
Para criar as tabelas, utilize o arquivo schema.sql localizado em src/main/resources:

```console
-- DML
CREATE TABLE IF NOT EXISTS pessoa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    departamento VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS tarefa (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    prazo DATE NOT NULL,
    departamento VARCHAR(100) NOT NULL,
    duracao INTEGER NOT NULL,
    finalizado BOOLEAN DEFAULT FALSE,
    pessoa_alocada_id INTEGER REFERENCES pessoa(id),
    CONSTRAINT fk_pessoa FOREIGN KEY (pessoa_alocada_id) REFERENCES pessoa(id)
);
```
## 📂 Estrutura do Projeto

```console
src/
├── main/
│   ├── java/
│   │   └── br/
│   │       └── com/
│   │           └── perinity/
│   │               └── gerenciador/
│   │                   ├── controller/       # Controladores da API
│   │                   ├── exception/        # Tratamento de exceções
│   │                   ├── model/            # Entidades do banco de dados
│   │                   ├── repository/       # Repositórios JPA
│   │                   └── service/          # Serviços para lógica de negócio
│   └── resources/
│       ├── application.properties            # Configurações da aplicação
│       └── data.sql                          # Script para popular o banco de dados
└── test/
    └── java/
        └── br/
            └── com/
                └── perinity/
                    └── gerenciador/
                        ├── PessoaRepositoryTest.java  # Testes do repositório de Pessoa
                        └── TarefaRepositoryTest.java  # Testes do repositório de Tarefa


```

## 🌐 Endpoints da API
**Pessoas**
- **POST /pessoas**: Adiciona uma nova pessoa.
- **PUT /pessoas/{id}**: Altera os dados de uma pessoa.
- **DELETE /pessoas/{id}**: Remove uma pessoa.
- **GET /pessoas**: Lista todas as pessoas com o total de horas gastas em tarefas.
- **Tarefas**
- **POST /tarefas**: Adiciona uma nova tarefa.
- **PUT /tarefas/alocar/{id}**: Aloca uma pessoa em uma tarefa, se for do mesmo departamento.
- **PUT /tarefas/finalizar/{id}**: Finaliza uma tarefa.
- **GET /tarefas/pendentes**: Lista as 3 tarefas mais antigas que estão sem pessoa alocada.
- **GET /pessoas/indice**: Busca pessoas por nome e período, retornando a média de horas gastas por tarefa.
- **Departamentos**
- **GET /departamentos**: Lista os departamentos com a quantidade de pessoas e tarefas.

## ✅ Testes
Para rodar os testes, use o comando:
 ```console
  mvn test
```
## 🤝 Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## 📜 Licença
Este projeto é licenciado sob a MIT License.










   
