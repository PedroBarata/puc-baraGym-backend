/* Usuario */
CREATE TABLE ${ph_dbname}.usuario (
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

CREATE TABLE ${ph_dbname}.turma (
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

/* Alocação */

--CREATE TABLE ${ph_dbname}.alocacao (
--  id SERIAL UNIQUE,
--  turma_id int NOT NULL,
--  dia_semana_id int NOT NULL,
--  hora_inicio TIME NOT NULL,
--  hora_fim TIME NOT NULL,
--  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
--  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
--
--  FOREIGN KEY (turma_id) REFERENCES turma (id),
--  FOREIGN KEY (dia_semana_id) REFERENCES dia_semana (id)
--);
--
--comment on column alocacao.id is 'id da alocacao';
--comment on column alocacao.turma_id is 'id da turma';
--comment on column alocacao.dia_semana_id is 'id do dia da semana';
--
--comment on column alocacao.hora_inicio is 'horário de inicio da alocacao';
--comment on column alocacao.hora_fim is 'horário de término da alocacao';
--comment on column alocacao.created_at is 'data de criação da tupla';
--comment on column alocacao.updated_at is 'data de atualização da tupla';

/* DiaSemana */

CREATE TABLE ${ph_dbname}.dia_semana (
  id SERIAL UNIQUE,
  nome_dia VARCHAR ( 140 ) NOT NULL
);

comment on column dia_semana.id is 'id do dia da semana';
comment on column dia_semana.nome_dia is 'nome do dia da semana';

/* Atividade */

CREATE TABLE ${ph_dbname}.atividade (
  id SERIAL UNIQUE,
  nome VARCHAR ( 140 ) NOT NULL,
  descricao VARCHAR ( 255 ),
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