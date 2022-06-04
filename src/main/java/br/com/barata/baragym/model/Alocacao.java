package br.com.barata.baragym.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Alocacao {

 private Long id;

 private String nomeAtividade;
 private String nomeDiaSemana;
 private String nomeTurma;
 private String horaInicio;
 private String horaFim;

}
