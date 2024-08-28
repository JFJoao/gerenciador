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

