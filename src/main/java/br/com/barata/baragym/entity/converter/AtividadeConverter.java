package br.com.barata.baragym.entity.converter;

import br.com.barata.baragym.entity.AtividadeEntity;
import br.com.barata.baragym.infrastructure.stereotype.Converter;
import br.com.barata.baragym.model.Atividade;
import org.springframework.data.domain.Page;

@Converter
public class AtividadeConverter {

 public Atividade convertToModel(AtividadeEntity entity) {
  return Atividade
		  .builder()
		  .id(entity.getId())
		  .descricao(entity.getDescricao())
		  .nome(entity.getNome())
		  .valorDia(entity.getValorDia())
		  .build();
 }

 public Page<Atividade> convertToModel(Page<AtividadeEntity> entityPage) {
  return entityPage.map(entity -> Atividade
		  .builder()
		  .id(entity.getId())
		  .descricao(entity.getDescricao())
		  .nome(entity.getNome())
		  .valorDia(entity.getValorDia())
		  .build());
 }
}
