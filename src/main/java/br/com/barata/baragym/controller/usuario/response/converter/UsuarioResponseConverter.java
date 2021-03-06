package br.com.barata.baragym.controller.usuario.response.converter;

import br.com.barata.baragym.controller.usuario.response.UsuarioResponse;
import br.com.barata.baragym.infrastructure.stereotype.Converter;
import br.com.barata.baragym.model.Usuario;
import org.springframework.data.domain.Page;

@Converter
public class UsuarioResponseConverter {

 public UsuarioResponse convertToResponse(Usuario usuario) {
  return UsuarioResponse
		  .builder()
		  .email(usuario.getEmail())
		  .matricula(usuario.getMatricula())
		  .nome(usuario.getNome())
		  .build();
 }

 public Page<UsuarioResponse> convertToResponse(Page<Usuario> usuarioPage) {
  return usuarioPage.map(usuario -> UsuarioResponse
		  .builder()
		  .email(usuario.getEmail())
		  .matricula(usuario.getMatricula())
		  .nome(usuario.getNome())
		  .build());
 }
}
