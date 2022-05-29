package br.com.barata.baragym.controller.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UsuarioResponse {

 private String nome;
 private String matricula;
 private String email;
}
