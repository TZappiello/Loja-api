create table forma_pagamento (
	id bigint not null auto_increment, 
    descricao varchar(60), 
    primary key (id)
);


create table grupo (
	id bigint not null auto_increment, 
    nome varchar(80), 
    primary key (id)
);

create table grupo_permissao (
	grupo_id bigint not null, 
    permissao_id bigint not null
);

create table grupos_usuarios (
	usuario_id bigint not null, 
    grupo_id bigint not null
);

create table permissao (
	id bigint not null auto_increment, 
    descricao varchar(80), 
    nome varchar(60), 
    primary key (id)
);

create table produtos (
	id bigint not null auto_increment, 
    ativo bit not null, 
    descricao varchar(120), 
    nome varchar(80), 
    preco decimal(19,2), 
    restaurante_id bigint, 
    primary key (id)
);

create table restaurante (
	id bigint not null auto_increment, 
    data_atualizacao datetime not null, 
    data_cadastro datetime not null, 
    endereco_bairro varchar(150), 
    endereco_cep varchar(15), 
    endereco_complemento varchar(150), 
    endereco_logradouro varchar(255), 
    endereco_numero varchar(20), 
    nome varchar(120), 
    taxa_frete decimal(19,2), 
    cozinha_id bigint, 
    endereco_cidade_id bigint, 
    primary key (id)
);

create table restaurante_forma_pagamento (
	restaurante_id bigint not null, 
    forma_pagamento_id bigint not null
);

create table usuario (
	id bigint not null auto_increment, 
    data_cadastro datetime not null, 
    email varchar(255), 
    nome varchar(255), 
    senha varchar(255), 
    primary key (id)
);

alter table grupo_permissao 
add constraint fk_grupo_permissao 
foreign key (permissao_id) 
references permissao (id);

alter table grupo_permissao 
add constraint fk_grupo_p 
foreign key (grupo_id) 
references grupo (id);

alter table grupos_usuarios 
add constraint fk_grupo_usuario
foreign key (grupo_id) 
references grupo (id);

alter table grupos_usuarios 
add constraint fk_grupo_u 
foreign key (usuario_id) 
references usuario (id);

alter table produtos 
add constraint fk_produtos_restaurante
foreign key (restaurante_id) 
references restaurante (id);

alter table restaurante
add constraint fk_restaurante_cozinha
foreign key (cozinha_id) 
references cozinha (id);

alter table restaurante 
add constraint fk_restaurante_cidade 
foreign key (endereco_cidade_id) 
references cidade (id);

alter table restaurante_forma_pagamento 
add constraint fk_restaurante_forma_pagamento
foreign key (forma_pagamento_id) 
references forma_pagamento (id);

alter table restaurante_forma_pagamento 
add constraint fk_forma_pagamento_restaurante
foreign key (restaurante_id) 
references restaurante (id);

