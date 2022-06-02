package br.com.barata.baragym.controller.alocacao.request;


import br.com.barata.baragym.commons.annotations.TimeValidation;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Builder
public class AlocacaoRequest {

 @NotNull
 private Long turmaId;
 @NotNull
 private Long diaSemanaId;
 @NotNull
 private Long atividadeId;

 @TimeValidation
 private String horaInicio;

 @TimeValidation
 private String horaFim;

 public boolean periodoEhValido() {
  int horaInicial = obtemHora(horaInicio);
  int minutoInicial = obtemMinuto(horaInicio);

  int horaFinal = obtemHora(horaFim);
  int minutoFinal = obtemMinuto(horaFim);

  LocalTime timeInicio = LocalTime.of(horaInicial, minutoInicial);
  LocalTime timeFinal = LocalTime.of(horaFinal, minutoFinal);

  return timeInicio.compareTo(timeFinal) < 0;
 }

 protected int obtemHora(String tempo) {
  return Integer.parseInt(tempo.substring(0, 2));
 }

 protected int obtemMinuto(String tempo) {
  return Integer.parseInt(tempo.substring(3, 5));
 }
}
