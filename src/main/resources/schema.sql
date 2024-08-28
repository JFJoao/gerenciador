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
