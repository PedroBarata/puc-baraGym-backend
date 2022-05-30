package br.com.barata.baragym.controller.turma.request;


import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Builder
public class TurmaRequest {

 private String nome;
 @PositiveOrZero
 private Integer capacidade;
}
