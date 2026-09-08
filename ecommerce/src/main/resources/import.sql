INSERT INTO categoria (nome, descricao) VALUES ('Tecnologia', 'Eventos relacionados a tecnologia');
INSERT INTO categoria (nome, descricao) VALUES ('Programacao', 'Eventos sobre desenvolvimento de software');
INSERT INTO categoria (nome, descricao) VALUES ('Empreendedorismo', 'Eventos sobre negocios');
INSERT INTO categoria (nome, descricao) VALUES ('Inovacao', 'Eventos sobre inovacao');
INSERT INTO categoria (nome, descricao) VALUES ('Educacao', 'Eventos relacionados a educacao');

INSERT INTO local (nome, endereco, capacidade) VALUES ('Auditorio Central', 'Rua Principal, 100', 300);
INSERT INTO local (nome, endereco, capacidade) VALUES ('Sala de Tecnologia', 'Rua Principal, 200', 100);
INSERT INTO local (nome, endereco, capacidade) VALUES ('Centro de Convencoes', 'Avenida Brasil, 500', 500);
INSERT INTO local (nome, endereco, capacidade) VALUES ('Laboratorio de Informatica', 'Rua da Universidade, 50', 80);
INSERT INTO local (nome, endereco, capacidade) VALUES ('Sala Multiuso', 'Rua das Flores, 80', 150);

INSERT INTO palestrante (nome, mini_bio, email) VALUES ('Carlos Almeida', 'Especialista em desenvolvimento de software', 'carlos@email.com');
INSERT INTO palestrante (nome, mini_bio, email) VALUES ('Mariana Souza', 'Profissional de tecnologia e inovacao', 'mariana@email.com');
INSERT INTO palestrante (nome, mini_bio, email) VALUES ('Rafael Oliveira', 'Empreendedor e consultor de negocios', 'rafael@email.com');
INSERT INTO palestrante (nome, mini_bio, email) VALUES ('Juliana Santos', 'Especialista em educacao e tecnologia', 'juliana@email.com');
INSERT INTO palestrante (nome, mini_bio, email) VALUES ('Fernando Costa', 'Desenvolvedor e professor de programacao', 'fernando@email.com');

INSERT INTO participante (nome, email, telefone) VALUES ('Joao Silva', 'joao@email.com', '14999990001');
INSERT INTO participante (nome, email, telefone) VALUES ('Maria Santos', 'maria@email.com', '14999990002');
INSERT INTO participante (nome, email, telefone) VALUES ('Pedro Oliveira', 'pedro@email.com', '14999990003');
INSERT INTO participante (nome, email, telefone) VALUES ('Ana Souza', 'ana@email.com', '14999990004');
INSERT INTO participante (nome, email, telefone) VALUES ('Lucas Costa', 'lucas@email.com', '14999990005');

INSERT INTO evento (nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id)
VALUES ('Semana de Tecnologia', 'Evento sobre tecnologia', '2026-10-01 19:00:00', '2026-10-01 22:00:00', 300, 'ATIVO', 1, 1, 1);

INSERT INTO evento (nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id)
VALUES ('Workshop de Java', 'Workshop de desenvolvimento Java', '2026-10-05 19:00:00', '2026-10-05 22:00:00', 100, 'ATIVO', 2, 2, 5);

INSERT INTO evento (nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id)
VALUES ('Empreendedorismo Digital', 'Palestra sobre negocios digitais', '2026-10-10 14:00:00', '2026-10-10 17:00:00', 500, 'ATIVO', 3, 3, 3);

INSERT INTO evento (nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id)
VALUES ('Inovacao e Futuro', 'Debate sobre inovacao e tecnologia', '2026-10-15 19:00:00', '2026-10-15 22:00:00', 80, 'ATIVO', 4, 4, 2);

INSERT INTO evento (nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id)
VALUES ('Tecnologia na Educacao', 'Tecnologia aplicada a educacao', '2026-10-20 19:00:00', '2026-10-20 21:00:00', 150, 'ATIVO', 5, 5, 4);

INSERT INTO inscricao (data_inscricao, status, evento_id, participante_id)
VALUES ('2026-09-01 10:00:00', 'CONFIRMADA', 1, 1);

INSERT INTO inscricao (data_inscricao, status, evento_id, participante_id)
VALUES ('2026-09-02 10:00:00', 'CONFIRMADA', 2, 2);

INSERT INTO inscricao (data_inscricao, status, evento_id, participante_id)
VALUES ('2026-09-03 10:00:00', 'PENDENTE', 3, 3);

INSERT INTO inscricao (data_inscricao, status, evento_id, participante_id)
VALUES ('2026-09-04 10:00:00', 'CONFIRMADA', 4, 4);

INSERT INTO inscricao (data_inscricao, status, evento_id, participante_id)
VALUES ('2026-09-05 10:00:00', 'CONFIRMADA', 5, 5);