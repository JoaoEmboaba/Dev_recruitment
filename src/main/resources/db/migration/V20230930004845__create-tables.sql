CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE candidato(
    id_candidato uuid,
    nome varchar(255) not null,
    nacionalidade varchar(255) not null,
    data_nascimento DATE,
    contratado boolean,
    primary key(id_candidato)
);


CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tecnologia (
    id_tech uuid,
    nome varchar(255) not null,
    framework varchar(255) not null,
    area_aconselhavel varchar(255),
    descricao varchar(255) not null,
    primary key(id_tech)
);


CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE vaga(
    id_vaga uuid default uuid_generate_v4(),
    descricao varchar(255),
    necessidade_vaga varchar(255),
    primary key(id_vaga)
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE candidatura (
    id_candidatura uuid default uuid_generate_v4(),
    id_candidato uuid,
    id_vaga uuid,
    data_candidatura DATE DEFAULT NOW()::DATE,
    primary key(id_candidatura),
    foreign key (id_candidato) references candidato(id_candidato),
    foreign key (id_vaga) references vaga(id_vaga)
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE candidato_tecnologia(
    id_candidato uuid,
    id_tech uuid,
    foreign key (id_candidato) references candidato(id_candidato),
    foreign key (id_tech) references tecnologia(id_tech)
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE vaga_tecnologia(
    fk_id_vaga uuid,
    fk_id_tech uuid,
    foreign key (fk_id_vaga) references vaga(id_vaga),
    foreign key (fk_id_tech) references tecnologia(id_tech)
);

ALTER TABLE vaga RENAME COLUMN necessidade_vaga to necessidadeVaga;
ALTER TABLE tecnologia rename column area_aconselhavel to areaAconselhavel;

ALTER TABLE candidato ADD ativo boolean;
ALTER TABLE tecnologia ADD ativo boolean;
ALTER TABLE vaga ADD ativo boolean;

update candidato set ativo = true;
update tecnologia set ativo = true;
update vaga set ativo = true;