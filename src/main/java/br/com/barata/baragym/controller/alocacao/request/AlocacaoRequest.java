package br.com.barata.baragym.controller.alocacao.request;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class AlocacaoRequest {

 private Long turmaId;
 private Long diaSemanaId;
 private Long atividadeId;

 //Se nao rolar, passar string
 private LocalTime horaInicio;
 private LocalTime horaFim;
}
