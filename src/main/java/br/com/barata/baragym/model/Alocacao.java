package br.com.barata.baragym.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Alocacao {

 private Long id;

 private GenericIdAndNome atividade;
 private GenericIdAndNome diaSemana;
 private GenericIdAndNome turma;

 private String horaInicio;
 private String horaFim;

}
