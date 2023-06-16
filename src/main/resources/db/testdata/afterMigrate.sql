set foreign_key_checks = 0;
SET SQL_SAFE_UPDATES = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from grupos_usuarios;
delete from permissao;
delete from produtos;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from restaurante_usuario_responsavel;

set foreign_key_checks = 1;
SET SQL_SAFE_UPDATES = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table grupo_permissao auto_increment = 1;
alter table grupos_usuarios auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produtos auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table restaurante_forma_pagamento auto_increment = 1;
alter table usuario auto_increment = 1;
alter table restaurante_usuario_responsavel auto_increment = 1;

insert into cozinha (id, nome) values (1, 'Chinesa');
insert into cozinha (id, nome) values (2, 'Mexicana');
insert into cozinha (id, nome) values (3, 'Tailandesa');

insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Rio de Janeiro');
insert into estado (nome) values ('Minas Gerais');

insert into cidade (nome, estado_id) values ('Mogi das Cruzes', 1);
insert into cidade (nome, estado_id) values ('Suzano', 1);
insert into cidade (nome, estado_id) values ('Barra da Tijuca', 2);
insert into cidade (nome, estado_id) values ('Barbacena', 3);

insert into restaurante ( endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id, data_atualizacao, data_cadastro, ativo, aberto) values ( 'Cj Bosque', '08743-000', 'Casa fundos', 'Rua Shozo Sakay', '1000',  'Mestre cuca', 14, 1, 1, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_atualizacao, data_cadastro, ativo, aberto) values ('Du Chefe', 17, 2, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_atualizacao, data_cadastro, ativo, aberto) values ('Gula', 17, 3, utc_timestamp, utc_timestamp, true, false);

insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('Cartão Crédito');
insert into forma_pagamento (descricao) values ('Cartão Débito');

insert into permissao (descricao, nome) values ('Pedido Telefone', 'Joao');
insert into permissao (descricao, nome) values ('Permitir editar atributos', 'CONSULTAR_ATRIBUTOS');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (1,3),(2,1),(2,2),(3,1);

insert into produtos(ativo, descricao, nome, preco, restaurante_id) values (true, "Virado a Paulista", "Prato da Casa", 22, 1);
insert into produtos(ativo, descricao, nome, preco, restaurante_id) values (true, "Bisteca", "Opção", 17, 2);
insert into produtos(ativo, descricao, nome, preco, restaurante_id) values (false, "Churrasco", "Especial", 26, 1);

insert into grupo(nome) values ("Permitido");
insert into grupo(nome) values ("Não permitido");
insert into grupo(nome) values ("Avaliado");

insert into grupo_permissao(grupo_id, permissao_id) values (1,1),(1,2),(2,2);

insert into usuario(data_cadastro, email, nome, senha) values(utc_timestamp, "bob_teste@mail.com", "Bob Teste Primeiro", "123");
insert into usuario(data_cadastro, email, nome, senha) values(utc_timestamp, "ana_teste@mail.com", "Ana Teste Segundo", "123");
insert into usuario(data_cadastro, email, nome, senha) values(utc_timestamp, "joao_teste@mail.com", "Joao Teste Terceiro", "123");

insert into grupos_usuarios(usuario_id, grupo_id) values(1,1),(1,2),(2,1);

insert into restaurante_usuario_responsavel(restaurante_id, usuario_id) values (1,1),(1,2),(2,3);

insert into pedido (id, restaurante, cliente_usuario, forma_pagamento, endereco_cidade_id, endereco_cep, 
			    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
			    status_pedido,  data_criacao, sub_total, taxa_frete, valor_total)
    values (1, 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil', 'CRIADO',  utc_timestamp, 298.90, 10, 308.90);
				
insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)values (1, 1, 1, 1, 78.9, 78.9, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');

insert into pedido (id, restaurante, cliente_usuario, forma_pagamento, endereco_cidade_id, endereco_cep, 
		        endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
		        status_pedido, data_criacao, sub_total, taxa_frete, valor_total)
	values (2, 3, 2, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro','CRIADO', utc_timestamp, 79, 0, 79);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)values (3, 2, 3, 1, 79, 79, 'Ao ponto');
