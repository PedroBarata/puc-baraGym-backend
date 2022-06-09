package br.com.barata.baragym.controller.usuario.response.converter;

import br.com.barata.baragym.controller.usuario.response.UsuarioAgendamentoResponse;
import br.com.barata.baragym.infrastructure.stereotype.Converter;
import br.com.barata.baragym.model.Agendamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Converter
public class UsuarioAgendamentoResponseConverter {

 public UsuarioAgendamentoResponse convertToResponse(Agendamento agendamento) {

  UsuarioAgendamentoResponse response = UsuarioAgendamentoResponse
		  .builder()
		  .nomeAtividade(agendamento.getNomeAtividade())
		  .atividadeId(agendamento.getAtividadeId())
		  .build();

  response.getAgendamentos().add(mapAgendamentos(agendamento));

  return response;
 }

 public Page<UsuarioAgendamentoResponse> convertToResponse(Page<Agendamento> agendamentoPage) {
  List<UsuarioAgendamentoResponse> responseList = new ArrayList<>();

  agendamentoPage.getContent().forEach(agendamento -> {
   Optional<UsuarioAgendamentoResponse> optAtividade = responseList
		   .stream()
		   .filter(agendamentoResponse -> agendamentoResponse.getAtividadeId().equals(agendamento.getAtividadeId()))
		   .findAny();

   if (optAtividade.isPresent()) {
	responseList.get(responseList.indexOf(optAtividade.get()))
			.getAgendamentos()
			.add(mapAgendamentos(agendamento));
   } else {
	responseList.add(convertToResponse(agendamento));
   }
  });

  return new PageImpl<>(responseList);
 }

 protected UsuarioAgendamentoResponse.UsuarioAgendamento mapAgendamentos(Agendamento agendamento) {
  return UsuarioAgendamentoResponse
		  .UsuarioAgendamento
		  .builder()
		  .alocacaoId(agendamento.getAlocacaoId())
		  .id(agendamento.getId())
		  .nomeDiaSemana(agendamento.getNomeDiaSemana())
		  .horaInicio(agendamento.getHoraInicio())
		  .horaFim(agendamento.getHoraFim())
		  .nomeTurma(agendamento.getNomeTurma())
		  .build();
 }
}
