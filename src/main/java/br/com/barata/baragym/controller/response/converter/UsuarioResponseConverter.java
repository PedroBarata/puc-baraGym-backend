package br.com.barata.baragym.controller.response.converter;

import br.com.barata.baragym.controller.response.UsuarioResponse;
import br.com.barata.baragym.infrastructure.stereotype.Converter;
import br.com.barata.baragym.model.Usuario;

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
}
