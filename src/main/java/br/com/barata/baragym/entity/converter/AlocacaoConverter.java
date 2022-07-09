package br.com.barata.baragym.entity.converter;

import br.com.barata.baragym.entity.AlocacaoEntity;
import br.com.barata.baragym.infrastructure.stereotype.Converter;
import br.com.barata.baragym.model.Alocacao;
import br.com.barata.baragym.model.GenericIdAndNome;
import org.springframework.data.domain.Page;

@Converter
public class AlocacaoConverter {

 public Alocacao convertToModel(AlocacaoEntity entity) {
  GenericIdAndNome atividade = createGeneric(entity.getAtividade().getId(), entity.getAtividade().getNome());
  GenericIdAndNome diaSemana = createGeneric(entity.getDiaSemana().getId(), entity.getDiaSemana().getNomeDia());
  GenericIdAndNome turma = createGeneric(entity.getTurma().getId(), entity.getTurma().getNome());

  return Alocacao
		  .builder()
		  .id(entity.getId())
		  .atividade(atividade)
		  .diaSemana(diaSemana)
		  .turma(turma)
		  .horaInicio(entity.getHoraInicio())
		  .horaFim(entity.getHoraFim())
		  .build();
 }

 public Page<Alocacao> convertToModel(Page<AlocacaoEntity> entityPage) {
  return entityPage.map(this::convertToModel);
 }

 protected GenericIdAndNome createGeneric(Long id, String nome) {
  return GenericIdAndNome.builder()
		  .id(id)
		  .nome(nome)
		  .build();
 }
}
