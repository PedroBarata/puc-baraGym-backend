package br.com.barata.baragym.controller.request;


import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class UsuarioRequest {

 @Email
 private String email;

 private String nome;
 private String senha;
}
