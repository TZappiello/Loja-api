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
delete from pedido;
delete from item_pedido;
delete from foto_produto;
delete from oauth_client_details;

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
alter table pedido auto_increment = 1;
alter table item_pedido auto_increment = 1;

insert into cozinha (id, nome) values (1, 'Chinesa');
insert into cozinha (id, nome) values (2, 'Mexicana');
insert into cozinha (id, nome) values (3, 'Tailandesa');
insert into cozinha (id, nome) values (4, 'Brasileira');
insert into cozinha (id, nome) values (5, 'Japonesa');
insert into cozinha (id, nome) values (6, 'Arabe');

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

insert into forma_pagamento (descricao, data_atualizacao) values ('Dinheiro', utc_timestamp);
insert into forma_pagamento (descricao, data_atualizacao) values ('Cartão Crédito', utc_timestamp);
insert into forma_pagamento (descricao, data_atualizacao) values ('Cartão Débito', utc_timestamp);

insert into permissao (id, nome, descricao) values (1, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_FORMAS_PAGAMENTO', 'Permite criar ou editar formas de pagamento');
insert into permissao (id, nome, descricao) values (3, 'EDITAR_CIDADES', 'Permite criar ou editar cidades');
insert into permissao (id, nome, descricao) values (4, 'EDITAR_ESTADOS', 'Permite criar ou editar estados');
insert into permissao (id, nome, descricao) values (5, 'CONSULTAR_USUARIOS', 'Permite consultar usuários');
insert into permissao (id, nome, descricao) values (6, 'EDITAR_USUARIOS', 'Permite criar ou editar usuários');
insert into permissao (id, nome, descricao) values (7, 'EDITAR_RESTAURANTES', 'Permite criar, editar ou gerenciar restaurantes');
insert into permissao (id, nome, descricao) values (8, 'CONSULTAR_PEDIDOS', 'Permite consultar pedidos');
insert into permissao (id, nome, descricao) values (9, 'GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos');
insert into permissao (id, nome, descricao) values (10, 'GERAR_RELATORIOS', 'Permite gerar relatórios');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (1,3),(2,1),(2,2),(3,1);

insert into produtos(ativo, descricao, nome, preco, restaurante_id) values (true, "Virado a Paulista", "Prato da Casa", 22, 1);
insert into produtos(ativo, descricao, nome, preco, restaurante_id) values (true, "Bisteca", "Opção", 17, 2);
insert into produtos(ativo, descricao, nome, preco, restaurante_id) values (false, "Churrasco", "Especial", 26, 1);

insert into grupo(id, nome) values (1, 'Gerente'), (2, 'Vendedor'), (3, 'Secretária'), (4, 'Cadastrador');

# Adiciona todas as permissoes no grupo do gerente
insert into grupo_permissao (grupo_id, permissao_id)
select 1, id from permissao;

# Adiciona permissoes no grupo do vendedor
insert into grupo_permissao (grupo_id, permissao_id)
select 2, id from permissao where nome like 'CONSULTAR_%';

# Adiciona permissoes no grupo do auxiliar
insert into grupo_permissao (grupo_id, permissao_id)
select 3, id from permissao where nome like 'CONSULTAR_%';

# Adiciona permissoes no grupo cadastrador
insert into grupo_permissao (grupo_id, permissao_id)
select 4, id from permissao where nome like '%_RESTAURANTES' or nome like '%_PRODUTOS';

insert into usuario(data_cadastro, email, nome, senha) values(utc_timestamp, "zappi.thiago.ger@gmail.com", "Thiago Teste Quarto", "$2a$12$lSyQfqx48h1E2ytRZ0HSKu4si6iyEBZ75Z00i0vBslYqqWptOMS/y");
insert into usuario(data_cadastro, email, nome, senha) values(utc_timestamp, "zappi.thiago+luana.ven@gmail.com", "Luana Teste Quinto", "$2a$12$lSyQfqx48h1E2ytRZ0HSKu4si6iyEBZ75Z00i0vBslYqqWptOMS/y");
insert into usuario(data_cadastro, email, nome, senha) values(utc_timestamp, "bob_teste.sec@mail.com", "Bob Teste Primeiro", "$2a$12$lSyQfqx48h1E2ytRZ0HSKu4si6iyEBZ75Z00i0vBslYqqWptOMS/y");
insert into usuario(data_cadastro, email, nome, senha) values(utc_timestamp, "ana_teste.cad@mail.com", "Ana Teste Segundo", "$2a$12$lSyQfqx48h1E2ytRZ0HSKu4si6iyEBZ75Z00i0vBslYqqWptOMS/y");
insert into usuario(data_cadastro, email, nome, senha) values(utc_timestamp, "joao_teste@mail.com", "Joao Teste Terceiro", "$2a$12$lSyQfqx48h1E2ytRZ0HSKu4si6iyEBZ75Z00i0vBslYqqWptOMS/y");

insert into grupos_usuarios(usuario_id, grupo_id) values (1,1), (1,2), (2,2), (3,3), (4,4); 

insert into restaurante_usuario_responsavel(restaurante_id, usuario_id) values (1,1),(1,2),(2,3);

insert into pedido (id, codigo, restaurante, cliente_usuario, forma_pagamento, endereco_cidade_id, endereco_cep, 
			    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
			    status_pedido,  data_criacao, sub_total, taxa_frete, valor_total)
    values (1, '45e415ac-8f8e-4193-bacd-b7b19a6c070f', 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil', 'CRIADO',  '2023-05-28 18:12:42', 298.90, 10, 308.90);
				
insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)values (1, 1, 1, 1, 78.9, 78.9, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');

insert into pedido (id, codigo, restaurante, cliente_usuario, forma_pagamento, endereco_cidade_id, endereco_cep, 
		        endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
		        status_pedido, data_criacao, sub_total, taxa_frete, valor_total)
	values (2, '9251d342-f179-407f-888f-0fda8ed5d932', 3, 2, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro','CRIADO', '2023-05-04 23:00:42', 79, 0, 79);
	
insert into pedido (id, codigo, restaurante, cliente_usuario, forma_pagamento, endereco_cidade_id, endereco_cep, 
		        endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
		        status_pedido, data_criacao, sub_total, taxa_frete, valor_total)
	values (3, 'b5ab0856-0508-4c14-b4db-262c5511d502', 1, 2, 3, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro','ENTREGUE', '2023-05-05 02:00:42', 79, 0, 79);

insert into pedido (id, codigo, restaurante, cliente_usuario, forma_pagamento, endereco_cidade_id, endereco_cep, 
		        endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
		        status_pedido, data_criacao, sub_total, taxa_frete, valor_total)
	values (4, 'b5ab0336-0508-4c14-b4db-262c5511d502', 1, 2, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro','CONFIRMADO', '2023-05-28 18:12:42', 89, 10, 79);


insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)values (3, 2, 3, 1, 79, 79, 'Ao ponto');



insert into oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
values (
  'loja-api-web', null, '$2a$12$laqXp155./Ra/8qJLR1x6u2AOuo/XcIxksm34qR6NSRnrW/v/fFZC',
  'LEITURA,ESCRITA', 'password', null, null,
  60 * 60 * 6, 60 * 24 * 60 * 60, null
);

insert into oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
values (
  'lojaanalista', null, '$2a$12$laqXp155./Ra/8qJLR1x6u2AOuo/XcIxksm34qR6NSRnrW/v/fFZC',
  'LEITURA,ESCRITA', 'authorization_code', 'http://www.lojaalanista.local:8082', null,
  null, null, null
);

insert into oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
values (
  'faturamento', null, '$2a$12$laqXp155./Ra/8qJLR1x6u2AOuo/XcIxksm34qR6NSRnrW/v/fFZC',
  'LEITURA,ESCRITA', 'client_credentials', null, 'CONSULTAR_PEDIDOS,GERAR_RELATORIOS',
  null, null, null
);
