insert into cozinha (id, nome) values (1, 'Chinesa');
insert into cozinha (id, nome) values (2, 'Mexicana');
insert into cozinha (id, nome) values (3, 'Tailandesa');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Mestre cuca', 14, 1) ;
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Du Chefe', 17, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Gula', 17, 3);

insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('Cartão Crédito');
insert into forma_pagamento (descricao) values ('Cartão Débito');

insert into permissao (descricao, nome) values ('Pedido Telefone', 'Joao ');

insert into estados (nome) values ('São Paulo');

insert into cidades (nome, estado_id) values ('Mogi das Cruzes', 1);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (1,3),(2,1),(2,2),(3,1);