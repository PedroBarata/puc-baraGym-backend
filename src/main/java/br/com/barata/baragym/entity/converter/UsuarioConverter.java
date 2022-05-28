package br.com.barata.baragym.entity.converter;

import br.com.barata.baragym.entity.UsuarioEntity;
import br.com.barata.baragym.infrastructure.stereotype.Converter;
import br.com.barata.baragym.model.Usuario;
import org.springframework.stereotype.Component;

@Converter
public class UsuarioConverter {

 public Usuario convertToModel(UsuarioEntity entity) {
  return Usuario.builder().build();
 }

 public UsuarioEntity convertToEntity(Usuario usuario) {
  return UsuarioEntity.builder().build();
 }
}
