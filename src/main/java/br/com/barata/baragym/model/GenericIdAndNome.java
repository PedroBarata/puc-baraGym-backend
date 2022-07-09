package br.com.barata.baragym.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GenericIdAndNome {

 private Long id;
 private String nome;
}
