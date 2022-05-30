package br.com.barata.baragym.entity.converter;

import br.com.barata.baragym.entity.TurmaEntity;
import br.com.barata.baragym.infrastructure.stereotype.Converter;
import br.com.barata.baragym.model.Turma;
import org.springframework.data.domain.Page;

@Converter
public class TurmaConverter {

 public Turma convertToModel(TurmaEntity entity) {
  return Turma
		  .builder()
		  .id(entity.getId())
		  .capacidade(entity.getCapacidade())
		  .nome(entity.getNome())
		  .build();
 }

 public Page<Turma> convertToModel(Page<TurmaEntity> entityPage) {
  return entityPage.map(entity -> Turma
		  .builder()
		  .id(entity.getId())
		  .capacidade(entity.getCapacidade())
		  .nome(entity.getNome())
		  .build());
 }
}
