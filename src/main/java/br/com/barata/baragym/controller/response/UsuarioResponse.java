package br.com.barata.baragym.controller.response;

import lombok.Builder;

@Builder
public class UsuarioResponse {

 private String nome;
 private String matricula;
 private String email;
}
