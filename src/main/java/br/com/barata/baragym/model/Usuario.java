package br.com.barata.baragym.model;

import br.com.barata.baragym.security.enums.RoleEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Usuario {

 private String matricula;
 private String nome;
 private String email;
 private String senha;
}
