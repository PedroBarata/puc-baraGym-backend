package br.com.barata.baragym.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Builder
@Getter
public class UsuarioAtividade {

 private List<String> nomeAtividades;
 private Date vigenciaInicio;
 private Date vigenciaFim;
}
