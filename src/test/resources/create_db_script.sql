/* Usuario */
CREATE TABLE usuario (
  id SERIAL UNIQUE,
  nome VARCHAR ( 140 ) NOT NULL,
  matricula VARCHAR ( 36 ) UNIQUE NOT NULL,
  email VARCHAR ( 140 ) UNIQUE NOT NULL,
  senha text NOT NULL,
  role VARCHAR ( 140 ) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

comment on column usuario.id is 'id do usuario';
comment on column usuario.nome is 'nome do usuario';
comment on column usuario.matricula is 'matricula do usuario';
comment on column usuario.email is 'email do usuario';
comment on column usuario.senha is 'senha do usuario';
comment on column usuario.role is 'role do usuario: ROLE_ADMIN, ROLE_USER';
comment on column usuario.created_at is 'data de criação da tupla';
comment on column usuario.updated_at is 'data de atualização da tupla';

/* Turma */

CREATE TABLE turma (
  id SERIAL UNIQUE,
  nome VARCHAR ( 140 ) NOT NULL,
  capacidade INTEGER CHECK (capacidade >= 0) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

comment on column turma.id is 'id da turma';
comment on column turma.nome is 'nome da turma';
comment on column turma.capacidade is 'capacidade da turma, maior ou igual a zero';
comment on column turma.created_at is 'data de criação da tupla';
comment on column turma.updated_at is 'data de atualização da tupla';

/* DiaSemana */

CREATE TABLE dia_semana (
  id SERIAL UNIQUE,
  nome_dia VARCHAR ( 140 ) NOT NULL
);

comment on column dia_semana.id is 'id do dia da semana';
comment on column dia_semana.nome_dia is 'nome do dia da semana';

/* Atividade */

CREATE TABLE atividade (
  id SERIAL UNIQUE,
  nome VARCHAR ( 140 ) NOT NULL,
  descricao VARCHAR ( 255 ) NOT NULL,
  valor_dia DECIMAL(12,2) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

comment on column atividade.id is 'id da atividade';
comment on column atividade.nome is 'nome da atividade';
comment on column atividade.descricao is 'descricao da atividade';
comment on column atividade.valor_dia is 'valor do dia da atividade';
comment on column atividade.created_at is 'data de criação da tupla';
comment on column atividade.updated_at is 'data de atualização da tupla';


/* INSERT DIA DA SEMANA */

INSERT INTO dia_semana (nome_dia) VALUES('Segunda-feira');
INSERT INTO dia_semana (nome_dia) VALUES('Terça-feira');
INSERT INTO dia_semana (nome_dia) VALUES('Quarta-feira');
INSERT INTO dia_semana (nome_dia) VALUES('Quinta-feira');
INSERT INTO dia_semana (nome_dia) VALUES('Sexta-feira');
INSERT INTO dia_semana (nome_dia) VALUES('Sábado');
INSERT INTO dia_semana (nome_dia) VALUES('Domingo');
