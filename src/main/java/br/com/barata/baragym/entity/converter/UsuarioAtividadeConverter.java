package br.com.barata.baragym.entity.converter;

import br.com.barata.baragym.entity.UsuarioAtividadeEntity;
import br.com.barata.baragym.infrastructure.stereotype.Converter;
import br.com.barata.baragym.model.UsuarioAtividade;

import java.util.List;
import java.util.stream.Collectors;

@Converter
public class UsuarioAtividadeConverter {

 public UsuarioAtividade convertToModel(List<UsuarioAtividadeEntity> entityList) {
  if (entityList.isEmpty()) {
   return UsuarioAtividade.builder().build();
  }

  return UsuarioAtividade
          .builder()
          .nomeAtividades(entityList
                  .stream()
                  .map(entity -> entity.getAtividade().getNome())
                  .collect(Collectors.toList()))
          .vigenciaInicio(entityList.get(0).getVigenciaInicio())
          .vigenciaFim(entityList.get(0).getVigenciaFim())
          .build();
 }
}
