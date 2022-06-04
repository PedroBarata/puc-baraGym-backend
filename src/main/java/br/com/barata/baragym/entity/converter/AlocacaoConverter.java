package br.com.barata.baragym.entity.converter;

import br.com.barata.baragym.entity.AlocacaoEntity;
import br.com.barata.baragym.infrastructure.stereotype.Converter;
import br.com.barata.baragym.model.Alocacao;
import org.springframework.data.domain.Page;

@Converter
public class AlocacaoConverter {

 public Alocacao convertToModel(AlocacaoEntity entity) {
  return Alocacao
		  .builder()
		  .id(entity.getId())
		  .nomeAtividade(entity.getAtividade().getNome())
		  .nomeDiaSemana(entity.getDiaSemana().getNomeDia())
		  .nomeTurma(entity.getTurma().getNome())
		  .horaInicio(entity.getHoraInicio())
		  .horaFim(entity.getHoraFim())
		  .build();
 }

 public Page<Alocacao> convertToModel(Page<AlocacaoEntity> entityPage) {
  return entityPage.map(this::convertToModel);
 }
}
