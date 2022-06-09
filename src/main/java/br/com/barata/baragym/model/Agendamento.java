package br.com.barata.baragym.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Agendamento {

 private Long id;
 private Long alocacaoId;
 private Long atividadeId;
 private String nomeAtividade;
 private String nomeDiaSemana;
 private String nomeTurma;
 private String horaInicio;
 private String horaFim;

}
