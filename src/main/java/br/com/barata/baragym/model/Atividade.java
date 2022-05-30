package br.com.barata.baragym.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class Atividade {

 private Long id;
 private String nome;
 private String descricao;
 private BigDecimal valorDia;
}
