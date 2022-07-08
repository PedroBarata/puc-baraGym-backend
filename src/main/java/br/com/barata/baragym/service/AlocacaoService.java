package br.com.barata.baragym.service;

import br.com.barata.baragym.controller.alocacao.request.AlocacaoRequest;
import br.com.barata.baragym.entity.AlocacaoEntity;
import br.com.barata.baragym.entity.AtividadeEntity;
import br.com.barata.baragym.entity.DiaSemanaEntity;
import br.com.barata.baragym.entity.TurmaEntity;
import br.com.barata.baragym.entity.converter.AlocacaoConverter;
import br.com.barata.baragym.exception.AtividadeNaoEncontradaException;
import br.com.barata.baragym.exception.DiaSemanaNaoEncontradoException;
import br.com.barata.baragym.exception.PeriodoDeTempoInvalidoException;
import br.com.barata.baragym.exception.TurmaNaoEncontradaException;
import br.com.barata.baragym.model.Alocacao;
import br.com.barata.baragym.repository.AlocacaoRepository;
import br.com.barata.baragym.repository.AtividadeRepository;
import br.com.barata.baragym.repository.DiaSemanaRepository;
import br.com.barata.baragym.repository.TurmaRepository;
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
public class AlocacaoService {

 @Autowired
 private AlocacaoRepository alocacaoRepository;

 @Autowired
 private TurmaRepository turmaRepository;

 @Autowired
 private DiaSemanaRepository diaSemanaRepository;

 @Autowired
 private AtividadeRepository atividadeRepository;

 @Autowired
 private AgendamentoService agendamentoService;

 @Autowired
 private AlocacaoConverter converter;

 @Transactional(propagation = Propagation.REQUIRES_NEW)
 public void criarAlocacao(AlocacaoRequest request) {

  if (!request.periodoEhValido()) {
   throw new PeriodoDeTempoInvalidoException();
  }

  Optional<TurmaEntity> optTurmaEntity = turmaRepository.findById(request.getTurmaId());
  Optional<AtividadeEntity> optAtividadeEntity = atividadeRepository.findById(request.getAtividadeId());
  Optional<DiaSemanaEntity> optDiaSemanaEntity = diaSemanaRepository.findById(request.getDiaSemanaId());

  AlocacaoEntity entity = AlocacaoEntity
          .builder()
          .atividade(optAtividadeEntity.orElseThrow(AtividadeNaoEncontradaException::new))
          .diaSemana(optDiaSemanaEntity.orElseThrow(DiaSemanaNaoEncontradoException::new))
          .turma(optTurmaEntity.orElseThrow(TurmaNaoEncontradaException::new))
          .horaInicio(request.getHoraInicio())
          .horaFim(request.getHoraFim())
          .build();

  alocacaoRepository.save(entity);
 }

 @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
 public Page<Alocacao> listarTodasAlocacoes(Pageable pageable) {
  Page<AlocacaoEntity> alocacaoEntityPage = alocacaoRepository.findAll(pageable);
  return converter.convertToModel(alocacaoEntityPage);
 }

 @Transactional(propagation = Propagation.REQUIRES_NEW)
 public void deletarAlocacao(Long alocacaoId) {
  agendamentoService.deletaAgendamentosPorAlocacaoId(alocacaoId);
  alocacaoRepository.deleteById(alocacaoId);
 }

 @Transactional(propagation = Propagation.REQUIRES_NEW)
 public void deletarAlocacaoPorAtividadeId(Long atividadeId) {
  List<Long> alocacaoIds = alocacaoRepository.findByAtividadeId(atividadeId).stream().map(AlocacaoEntity::getId).collect(Collectors.toList());
  alocacaoIds.forEach(this::deletarAlocacao);
 }

 @Transactional(propagation = Propagation.REQUIRES_NEW)
 public void deletarAlocacaoPorTurmaId(Long turmaId) {
  List<Long> alocacaoIds = alocacaoRepository.findByTurmaId(turmaId).stream().map(AlocacaoEntity::getId).collect(Collectors.toList());
  alocacaoIds.forEach(this::deletarAlocacao);
 }
}
