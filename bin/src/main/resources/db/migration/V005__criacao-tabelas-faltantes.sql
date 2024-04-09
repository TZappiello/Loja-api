create table item_pedido (
	id bigint not null auto_increment, 
    observacao varchar(120), 
    preco_total decimal(19,2) not null,
    preco_unitario decimal(19,2) not null, 
    quantidade integer not null, 
    pedido_id bigint not null, 
    produto_id bigint not null, 
    primary key (id)
);


create table pedido (
	id bigint not null auto_increment, 
    data_cancelamento datetime, 
    data_confirmacao datetime, 
    data_criacao datetime not null, 
    data_entrega datetime, 
    endereco_bairro varchar(120) not null, 
    endereco_cep varchar(15) not null, 
    endereco_complemento varchar(60), 
    endereco_logradouro varchar(120) not null, 
    endereco_numero varchar(20) not null, 
    status_pedido varchar(150) not null, 
    sub_total decimal(19,2) not null, 
    taxa_frete decimal(19,2) not null, 
    valor_total decimal(19,2) not null, 
    cliente_usuario bigint not null, 
    endereco_cidade_id bigint not null, 
    forma_pagamento bigint not null, 
    restaurante bigint not null, 
    primary key (id)
);

alter table item_pedido 
add constraint fk_item_pedido 
foreign key (pedido_id) 
references pedido (id);


alter table item_pedido 
add constraint fk_item_produto
foreign key (produto_id) 
references produtos (id);

alter table pedido 
add constraint fk_pedido_usuario 
foreign key (cliente_usuario) 
references usuario (id);

alter table pedido 
add constraint fk_pedido_cidade
foreign key (endereco_cidade_id) 
references cidade (id);

alter table pedido 
add constraint fk_pedido_forma_pagamento
foreign key (forma_pagamento) 
references forma_pagamento (id);

alter table pedido 
add constraint fk_pedido_restaurante
foreign key (restaurante) 
references restaurante (id);
