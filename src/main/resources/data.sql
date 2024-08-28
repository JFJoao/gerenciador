--DDL
-- Inserir dados na tabela pessoa
INSERT INTO pessoa (nome, departamento) VALUES ('João Pereira', 'TI');
INSERT INTO pessoa (nome, departamento) VALUES ('Ana Paula', 'Financeiro');
INSERT INTO pessoa (nome, departamento) VALUES ('Carlos Souza', 'RH');

-- Inserir dados na tabela tarefa
INSERT INTO tarefa (titulo, descricao, prazo, departamento, duracao, pessoa_alocada_id, finalizado)
VALUES ('Implementar API', 'Desenvolver endpoints REST', '2024-12-31', 'TI', 10, 1, FALSE);
INSERT INTO tarefa (titulo, descricao, prazo, departamento, duracao, pessoa_alocada_id, finalizado)
VALUES ('Revisar Documentos', 'Revisar todos os contratos', '2024-10-15', 'RH', 5, 2, TRUE);
INSERT INTO tarefa (titulo, descricao, prazo, departamento, duracao, pessoa_alocada_id, finalizado)
VALUES ('Preparar Relatório Financeiro', 'Criar o relatório financeiro anual', '2024-11-30', 'Financeiro', 15, 3, FALSE);


