package br.com.barata.baragym.entity.converter;

import br.com.barata.baragym.entity.AgendamentoEntity;
import br.com.barata.baragym.entity.AlocacaoEntity;
import br.com.barata.baragym.infrastructure.stereotype.Converter;
import br.com.barata.baragym.model.Agendamento;
import org.springframework.data.domain.Page;

@Converter
public class AgendamentoConverter {

 public Agendamento convertToModel(AgendamentoEntity entity) {
  AlocacaoEntity alocacaoEntity = entity.getAlocacao();

  return Agendamento
		  .builder()
		  .id(entity.getId())
		  .alocacaoId(alocacaoEntity.getId())
		  .atividadeId(alocacaoEntity.getAtividade().getId())
		  .horaFim(alocacaoEntity.getHoraFim())
		  .nomeTurma(alocacaoEntity.getTurma().getNome())
		  .nomeAtividade(alocacaoEntity.getAtividade().getNome())
		  .horaInicio(alocacaoEntity.getHoraInicio())
		  .nomeDiaSemana(alocacaoEntity.getDiaSemana().getNomeDia())
		  .build();
 }

 public Page<Agendamento> convertToModel(Page<AgendamentoEntity> entityPage) {
  return entityPage.map(this::convertToModel);
 }
}
