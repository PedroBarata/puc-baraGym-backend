CREATE TABLE ${ph_dbname}.usuario (
  id serial PRIMARY KEY,
  nome VARCHAR ( 140 ) NOT NULL,
  matricula VARCHAR ( 36 ) UNIQUE NOT NULL,
  email VARCHAR ( 140 ) UNIQUE NOT NULL,
  senha text NOT NULL,
  role VARCHAR ( 140 ) NOT NULL,
  dt_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  dt_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

comment on column usuario.id is 'id do usuario';
comment on column usuario.nome is 'nome do usuario';
comment on column usuario.matricula is 'matricula do usuario';
comment on column usuario.email is 'email do usuario';
comment on column usuario.senha is 'senha do usuario';
comment on column usuario.role is 'role do usuario: ROLE_ADMIN, ROLE_USER';
comment on column usuario.dt_created is 'data de criação da tupla';
comment on column usuario.dt_updated is 'data de atualização da tupla';