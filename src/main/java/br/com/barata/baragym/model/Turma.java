package br.com.barata.baragym.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Turma {

 private Long id;
 private String nome;
 private Integer capacidade;

}
