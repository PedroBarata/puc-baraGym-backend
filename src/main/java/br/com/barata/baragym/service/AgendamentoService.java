package br.com.barata.baragym.service;

import br.com.barata.baragym.controller.usuario.request.AgendamentoRequest;
import br.com.barata.baragym.entity.AgendamentoEntity;
import br.com.barata.baragym.entity.AlocacaoEntity;
import br.com.barata.baragym.entity.UsuarioAtividadeEntity;
import br.com.barata.baragym.entity.UsuarioEntity;
import br.com.barata.baragym.entity.converter.AgendamentoConverter;
import br.com.barata.baragym.exception.*;
import br.com.barata.baragym.model.Agendamento;
import br.com.barata.baragym.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

 @Autowired
 private AlocacaoRepository alocacaoRepository;

 @Autowired
 private AgendamentoRepository agendamentoRepository;

 @Autowired
 private UsuarioRepository usuarioRepository;

 @Autowired
 private AtividadeRepository atividadeRepository;

 @Autowired
 private DiaSemanaRepository diaSemanaRepository;

 @Autowired
 private TurmaRepository turmaRepository;

 @Autowired
 private UsuarioAtividadeRepository usuarioAtividadeRepository;

 @Autowired
 private AgendamentoConverter converter;

 @Transactional(propagation = Propagation.REQUIRES_NEW)
 public Agendamento criarAgendamento(AgendamentoRequest request, String matricula) {

  if (jaPossuiUmAgendamento(matricula, request.getAlocacaoId())) {
   throw new AgendamentoJaExistenteException();
  }
  Optional<AlocacaoEntity> optAlocacaoEntity = alocacaoRepository.findById(request.getAlocacaoId());

  if (ultrapassouLimiteMaximoDeVezesPorSemana(matricula, optAlocacaoEntity.orElseThrow(AlocacaoNaoEncontradaException::new).getAtividade().getId())) {
   throw new LimiteMaximoDeAgendamentosUltrapassadoException();
  }

  Optional<UsuarioEntity> optUsarioEntity = usuarioRepository.findByMatricula(matricula);

  AgendamentoEntity entity = AgendamentoEntity
          .builder()
          .alocacao(optAlocacaoEntity.orElseThrow(AlocacaoNaoEncontradaException::new))
          .usuario(optUsarioEntity.orElseThrow(UsuarioNaoEncontradoException::new))
          .build();

  AgendamentoEntity persistedEntity = agendamentoRepository.save(entity);

  return converter.convertToModel(agendamentoRepository.findById(persistedEntity.getId()).get());
 }

 @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
 public Page<Agendamento> listarTodosAgendamentosPorMatricula(String matricula, Pageable pageable) {
  Page<AgendamentoEntity> agendamentoEntityPage = agendamentoRepository.findAllByUsuarioMatricula(matricula, pageable);
  return converter.convertToModel(agendamentoEntityPage);
 }

 public boolean jaPossuiUmAgendamento(String matricula, Long alocacaoId) {
  return agendamentoRepository.findByUsuarioMatriculaAndAlocacaoId(matricula, alocacaoId).isPresent();
 }

 @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
 public boolean ultrapassouLimiteMaximoDeVezesPorSemana(String matricula, Long atividadeId) {
  Integer agendamentos = agendamentoRepository.findAllByUsuarioMatriculaAndAlocacaoAtividadeId(matricula, atividadeId).size();
  UsuarioAtividadeEntity usuarioAtividadeEntity = usuarioAtividadeRepository.findByUsuarioMatriculaAndAtividadeId(matricula, atividadeId);

  if (usuarioAtividadeEntity == null) {
   throw new UsuarioAgendamentoNaoEncontradoException();
  }

  return agendamentos.equals(usuarioAtividadeEntity.getQuantidadeSemana());
 }

 public void deletarAgendamento(Long agendamentoId) {
  agendamentoRepository.deleteById(agendamentoId);
 }

 public void deletaAgendamentosPorAlocacaoId(Long alocacaoId) {
  List<Long> agendamentos = agendamentoRepository.findByAlocacaoId(alocacaoId).stream().map(AgendamentoEntity::getId).collect(Collectors.toList());
  agendamentoRepository.deleteAllById(agendamentos);
 }

}
