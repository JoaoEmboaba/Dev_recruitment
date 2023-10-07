/* Database: PostgreSql. Generation date: 2023-08-25 09:29:38:641 */
/* Entity Candidate */
create table candidate (
	id UUID NOT NULL,
	name VARCHAR(255) NOT NULL,
	candidate_nationality VARCHAR(20) NOT NULL /* candidateNationality */,
	birth_date DATE NOT NULL /* birthDate */,
	hired BOOLEAN NOT NULL,
	active BOOLEAN,
	ext JSONB
);


/* Creating index for customization column */
create index candidate_ext on candidate using gin (ext);

/* Entity Technology */
create table technology (
	id UUID NOT NULL,
	name VARCHAR(255) NOT NULL,
	framework VARCHAR(255) NOT NULL,
	recommended_area VARCHAR(255) /* recommendedArea */,
	active BOOLEAN,
	ext JSONB
);


/* Creating index for customization column */
create index technology_ext on technology using gin (ext);

/* Entity Vacancy */
create table vacancy (
	id UUID NOT NULL,
	description VARCHAR(100) NOT NULL,
	need_vacancy VARCHAR(255) NOT NULL /* needVacancy */,
	active BOOLEAN,
	ext JSONB
);


/* Creating index for customization column */
create index vacancy_ext on vacancy using gin (ext);

/* Entity Candidacy */
create table candidacy (
	id UUID NOT NULL,
	candidacy_date TIMESTAMP /* candidacyDate */,
	candidates UUID NOT NULL,
	vacancies UUID NOT NULL,
	ext JSONB
);


/* Creating index for customization column */
create index candidacy_ext on candidacy using gin (ext);

/* Join Tables */
/* master: Candidate as candidate, detail: Technology as technology */
create table candidate_technologies (
	candidate_id UUID NOT NULL,
	technologies_id UUID NOT NULL
);

/* master: Candidate as candidate, detail: Candidacy as candidacy */
create table candidate_candidacies (
	candidate_id UUID NOT NULL,
	candidacies_id UUID NOT NULL
);

/* master: Vacancy as vacancy, detail: Candidacy as candidacy */
create table vacancy_candidacies (
	vacancy_id UUID NOT NULL,
	candidacies_id UUID NOT NULL
);

/* Primary Key Constraints */
alter table candidate_technologies add constraint pk_candidate_technologies primary key(candidate_id, technologies_id);
alter table candidate_candidacies add constraint pk_candidate_candidacies primary key(candidate_id, candidacies_id);
alter table candidate add constraint pk_candidate_id primary key(id);
alter table technology add constraint pk_technology_id primary key(id);
alter table vacancy_candidacies add constraint pk_vacancy_candidacies primary key(vacancy_id, candidacies_id);
alter table vacancy add constraint pk_vacancy_id primary key(id);
alter table candidacy add constraint pk_candidacy_id primary key(id);

/* Foreign Key Constraints */
alter table candidate_technologies add constraint fk5lpj3c64kqexio9lqkpxtbvjxbfq foreign key (candidate_id) references candidate (id);
alter table candidate_technologies add constraint fkvnnixsvidyinxmfqqvd35xtpxosa foreign key (technologies_id) references technology (id);
alter table candidate_candidacies add constraint fkl45ruiua4hn4dh5bck5fktypu3sm foreign key (candidate_id) references candidate (id);
alter table candidate_candidacies add constraint fkctubxc89au4v5tpwm2yzqsotvrod foreign key (candidacies_id) references candidacy (id);
alter table vacancy_candidacies add constraint fkjqxglkgqxjux3bn86ah378glz9lx foreign key (vacancy_id) references vacancy (id);
alter table vacancy_candidacies add constraint fkvooicdwhemy7p1y1dcrwfpyereqa foreign key (candidacies_id) references candidacy (id);
alter table candidacy add constraint fkacrgdijuvhtipe5qii35dlvi6glm foreign key (candidates) references candidate (id);
alter table candidacy add constraint fky3fbkce6ly4hylmyc64vjglhnl1i foreign key (vacancies) references vacancy (id);

/* Unique Key Constraints */

/* Sequences for auto increment entity ids */
