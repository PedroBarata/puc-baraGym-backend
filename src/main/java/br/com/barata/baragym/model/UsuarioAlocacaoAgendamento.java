package br.com.barata.baragym.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UsuarioAlocacaoAgendamento {

 private Long agendamentoId;
 private Long alocacaoId;

 private String nomeAtividade;
 private String nomeDiaSemana;
 private String nomeTurma;
 private String horaInicio;
 private String horaFim;

 @Builder.Default
 private Boolean estaAgendado = false;

}
