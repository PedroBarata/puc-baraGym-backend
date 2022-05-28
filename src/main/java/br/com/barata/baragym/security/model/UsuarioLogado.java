package br.com.barata.baragym.security.model;

import br.com.barata.baragym.model.Usuario;
import lombok.Getter;

@Getter
public class UsuarioLogado {

	private String token;
	private Usuario usuario;
	
	public UsuarioLogado(String token, Usuario usuario) {
		this.token = token;
		this.usuario = usuario;
	}

}
