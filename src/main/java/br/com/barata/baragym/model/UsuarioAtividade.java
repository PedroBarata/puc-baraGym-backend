package br.com.barata.baragym.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class UsuarioAtividade {

 private Long id;
 private Long atividadeId;
 private String nomeAtividade;
 private Integer quantidadeSemana;
 private Date vigenciaInicio;
 private Date vigenciaFim;

}
