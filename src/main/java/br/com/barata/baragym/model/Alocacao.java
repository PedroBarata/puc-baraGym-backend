package br.com.barata.baragym.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Builder
@Getter
public class Alocacao {

 private Long id;
 private LocalTime horaInicio;
 private LocalTime horaFim;

}
