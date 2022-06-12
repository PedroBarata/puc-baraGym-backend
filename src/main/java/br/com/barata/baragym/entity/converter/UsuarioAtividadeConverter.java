package br.com.barata.baragym.entity.converter;

import br.com.barata.baragym.entity.AgendamentoEntity;
import br.com.barata.baragym.entity.AlocacaoEntity;
import br.com.barata.baragym.entity.AtividadeEntity;
import br.com.barata.baragym.entity.UsuarioAtividadeEntity;
import br.com.barata.baragym.infrastructure.stereotype.Converter;
import br.com.barata.baragym.model.Atividade;
import br.com.barata.baragym.model.UsuarioAlocacaoAgendamento;
import br.com.barata.baragym.model.UsuarioAtividade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter
public class UsuarioAtividadeConverter {
/*
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
 }*/

 public Page<UsuarioAlocacaoAgendamento> convertToAlocacaoAgendamentoModel(Page<AlocacaoEntity> alocacaoEntityPage, Page<AgendamentoEntity> agendamentoEntityPage, Pageable pageable) {

  List<UsuarioAlocacaoAgendamento> usuarioAlocacaoAgendamentos = alocacaoEntityPage.getContent().stream().map(alocacaoEntity -> {

   Optional<AgendamentoEntity> agendamentoEntity = agendamentoEntityPage.getContent()
		   .stream()
		   .filter(agendamento -> agendamento.getAlocacao().getId().equals(alocacaoEntity.getId()))
		   .findAny();

   UsuarioAlocacaoAgendamento.UsuarioAlocacaoAgendamentoBuilder usuarioAlocacaoAgendamentoBuilder = UsuarioAlocacaoAgendamento.builder();

   usuarioAlocacaoAgendamentoBuilder
		   .alocacaoId(alocacaoEntity.getId())
		   .horaInicio(alocacaoEntity.getHoraInicio())
		   .horaFim(alocacaoEntity.getHoraFim())
		   .nomeAtividade(alocacaoEntity.getAtividade().getNome())
		   .nomeTurma(alocacaoEntity.getTurma().getNome())
		   .nomeDiaSemana(alocacaoEntity.getDiaSemana().getNomeDia());

   agendamentoEntity.ifPresent(entity -> {
	usuarioAlocacaoAgendamentoBuilder.agendamentoId(entity.getId());
	usuarioAlocacaoAgendamentoBuilder.estaAgendado(true);
   });

   return usuarioAlocacaoAgendamentoBuilder.build();
  }).collect(Collectors.toList());

  return new PageImpl<>(usuarioAlocacaoAgendamentos, pageable, alocacaoEntityPage.getTotalPages());
 }

 public List<UsuarioAtividade> convertToModel(List<UsuarioAtividadeEntity> entityList) {
  if (entityList.isEmpty()) {
   return new ArrayList<>();
  }
  return entityList
		  .stream()
		  .map(entity -> {
		   AtividadeEntity atividadeEntity = entity.getAtividade();
		   return UsuarioAtividade.builder()
				   .id(entity.getId())
				   .atividadeId(atividadeEntity.getId())
				   .nomeAtividade(atividadeEntity.getNome())
				   .quantidadeSemana(entity.getQuantidadeSemana())
				   .vigenciaInicio(entity.getVigenciaInicio())
				   .vigenciaFim(entity.getVigenciaFim())
				   .valorTotal(entity.getValorTotal())
				   .build();
		  })
		  .collect(Collectors.toList());
 }

 public List<Atividade> convertToAtividadeModel(List<UsuarioAtividadeEntity> entityList) {
  if (entityList.isEmpty()) {
   return new ArrayList<>();
  }

  return entityList
		  .stream()
		  .map(entity -> {
		   AtividadeEntity atividadeEntity = entity.getAtividade();
		   return Atividade.builder()
				   .id(entity.getId())
				   .descricao(atividadeEntity.getDescricao())
				   .nome(atividadeEntity.getNome())
				   .valorDia(atividadeEntity.getValorDia())
				   .build();
		  })
		  .collect(Collectors.toList());
 }
}
