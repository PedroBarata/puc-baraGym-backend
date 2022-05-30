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
