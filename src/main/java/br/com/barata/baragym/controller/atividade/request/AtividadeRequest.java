package br.com.barata.baragym.controller.atividade.request;


import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Builder
public class AtividadeRequest {

 private String nome;
 private String descricao;

 @PositiveOrZero
 private BigDecimal valorDia;
}
