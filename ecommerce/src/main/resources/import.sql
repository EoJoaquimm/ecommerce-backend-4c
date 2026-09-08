-- CATEGORIAS

INSERT INTO categoria (nome, descricao)
VALUES ('Informatica', 'Produtos de Informatica');

INSERT INTO categoria (nome, descricao)
VALUES ('Livros', 'Livros Tecnicos');

INSERT INTO categoria (nome, descricao)
VALUES ('Eletronicos', 'Aparelhos Eletronicos');

INSERT INTO categoria (nome, descricao)
VALUES ('Games', 'Produtos para jogos');

INSERT INTO categoria (nome, descricao)
VALUES ('Acessorios', 'Acessorios de informatica');


-- PRODUTOS

INSERT INTO produto (nome, descricao, preco, estoque, categoria_id)
VALUES ('Codigo Limpo', 'Livro do Autor Robert C. Martin', 100.00, 20, 2);

INSERT INTO produto (nome, descricao, preco, estoque, categoria_id)
VALUES ('Notebook', 'Notebook para estudos', 3500.00, 10, 1);

INSERT INTO produto (nome, descricao, preco, estoque, categoria_id)
VALUES ('Smartphone', 'Smartphone Android', 1800.00, 15, 3);

INSERT INTO produto (nome, descricao, preco, estoque, categoria_id)
VALUES ('Teclado Mecanico', 'Teclado para computador', 250.00, 20, 4);

INSERT INTO produto (nome, descricao, preco, estoque, categoria_id)
VALUES ('Mouse', 'Mouse sem fio', 120.00, 25, 5);


-- CLIENTES

INSERT INTO cliente (nome, email, telefone)
VALUES ('Joao Silva', 'joao@email.com', '14999990001');

INSERT INTO cliente (nome, email, telefone)
VALUES ('Maria Santos', 'maria@email.com', '14999990002');

INSERT INTO cliente (nome, email, telefone)
VALUES ('Pedro Oliveira', 'pedro@email.com', '14999990003');

INSERT INTO cliente (nome, email, telefone)
VALUES ('Ana Souza', 'ana@email.com', '14999990004');

INSERT INTO cliente (nome, email, telefone)
VALUES ('Lucas Costa', 'lucas@email.com', '14999990005');


-- PEDIDOS

INSERT INTO pedido (data, status, valor_total, cliente_id)
VALUES ('2026-09-01 10:00:00', 'PAGO', 100.00, 1);

INSERT INTO pedido (data, status, valor_total, cliente_id)
VALUES ('2026-09-02 11:00:00', 'PAGO', 3500.00, 2);

INSERT INTO pedido (data, status, valor_total, cliente_id)
VALUES ('2026-09-03 12:00:00', 'PENDENTE', 1800.00, 3);

INSERT INTO pedido (data, status, valor_total, cliente_id)
VALUES ('2026-09-04 13:00:00', 'PAGO', 250.00, 4);

INSERT INTO pedido (data, status, valor_total, cliente_id)
VALUES ('2026-09-05 14:00:00', 'PENDENTE', 120.00, 5);


-- ITENS DOS PEDIDOS

INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id)
VALUES (1, 100.00, 1, 1);

INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id)
VALUES (1, 3500.00, 2, 2);

INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id)
VALUES (1, 1800.00, 3, 3);

INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id)
VALUES (1, 250.00, 4, 4);

INSERT INTO item_pedido (quantidade, valor_unitario, pedido_id, produto_id)
VALUES (1, 120.00, 5, 5);


-- PAGAMENTOS

INSERT INTO pagamento (valor, data, status, tipo, pedido_id)
VALUES (100.00, '2026-09-01 10:05:00', 'APROVADO', 'PIX', 1);

INSERT INTO pagamento (valor, data, status, tipo, pedido_id)
VALUES (3500.00, '2026-09-02 11:05:00', 'APROVADO', 'CARTAO', 2);

INSERT INTO pagamento (valor, data, status, tipo, pedido_id)
VALUES (1800.00, '2026-09-03 12:05:00', 'PENDENTE', 'PIX', 3);

INSERT INTO pagamento (valor, data, status, tipo, pedido_id)
VALUES (250.00, '2026-09-04 13:05:00', 'APROVADO', 'CARTAO', 4);

INSERT INTO pagamento (valor, data, status, tipo, pedido_id)
VALUES (120.00, '2026-09-05 14:05:00', 'PENDENTE', 'PIX', 5);