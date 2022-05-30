package br.com.barata.baragym.controller.usuario.request;


import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
@Builder
public class UsuarioRequest {

 @Email
 private String email;

 private String nome;
 private String senha;
}
