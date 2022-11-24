insert into cozinha (id, nome) values (1, 'Chinesa');
insert into cozinha (id, nome) values (2, 'Mexicana');
insert into cozinha (id, nome) values (3, 'Tailandesa');

insert into estados (nome) values ('São Paulo');
insert into estados (nome) values ('Rio de Janeiro');
insert into estados (nome) values ('Minas Gerais');

insert into cidades (nome, estado_id) values ('Mogi das Cruzes', 1);
insert into cidades (nome, estado_id) values ('Suzano', 1);
insert into cidades (nome, estado_id) values ('Barra da Tijuca', 2);
insert into cidades (nome, estado_id) values ('Barbacena', 3);

insert into restaurante ( endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id ) values ( 'Cj Bosque', '08743-000', 'Casa fundos', 'Rua Shozo Sakay', '1000',  'Mestre cuca', 14, 1, 1 );
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Du Chefe', 17, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Gula', 17, 3);

insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('Cartão Crédito');
insert into forma_pagamento (descricao) values ('Cartão Débito');

insert into permissao (descricao, nome) values ('Pedido Telefone', 'Joao ');


insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (1,3),(2,1),(2,2),(3,1);