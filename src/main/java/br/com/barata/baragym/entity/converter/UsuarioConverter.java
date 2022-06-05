package br.com.barata.baragym.entity.converter;

import br.com.barata.baragym.entity.UsuarioEntity;
import br.com.barata.baragym.infrastructure.stereotype.Converter;
import br.com.barata.baragym.model.Usuario;
import br.com.barata.baragym.security.enums.RoleEnum;
import org.springframework.data.domain.Page;

@Converter
public class UsuarioConverter {

 public Usuario convertToModel(UsuarioEntity entity) {
  return Usuario
          .builder()
          .email(entity.getEmail())
          .nome(entity.getNome())
          .role(RoleEnum.valueOf(entity.getRole()))
          .matricula(entity.getMatricula())
          .build();
 }

 public Page<Usuario> convertToModel(Page<UsuarioEntity> entityPage) {
  return entityPage.map(this::convertToModel);
 }

}
