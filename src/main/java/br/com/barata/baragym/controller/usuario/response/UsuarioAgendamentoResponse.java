package br.com.barata.baragym.controller.usuario.response;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class UsuarioAgendamentoResponse {


 private Long atividadeId;
 private String nomeAtividade;

 @Builder.Default
 private List<UsuarioAgendamento> agendamentos = new ArrayList<>();

 @Getter
 @Builder
 public static class UsuarioAgendamento {
  private Long id;
  private Long alocacaoId;
  private String nomeDiaSemana;
  private String nomeTurma;
  private String horaInicio;
  private String horaFim;
 }

}
