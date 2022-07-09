package br.com.barata.baragym.service;

import br.com.barata.baragym.controller.turma.request.TurmaRequest;
import br.com.barata.baragym.entity.TurmaEntity;
import br.com.barata.baragym.entity.converter.TurmaConverter;
import br.com.barata.baragym.exception.TurmaNaoEncontradaException;
import br.com.barata.baragym.model.Turma;
import br.com.barata.baragym.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurmaService {

 @Autowired
 private TurmaRepository repository;

 @Autowired
 private TurmaConverter converter;

 @Autowired
 private AlocacaoService alocacaoService;

 @Transactional(propagation = Propagation.REQUIRES_NEW)
 public Turma criarTurma(TurmaRequest request) {
  TurmaEntity entity = TurmaEntity
          .builder()
          .nome(request.getNome())
          .capacidade(request.getCapacidade())
          .build();

  TurmaEntity persistedEntity = repository.save(entity);

  return converter.convertToModel(persistedEntity);
 }

 @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
 public Page<Turma> listarTodasTurmas(Pageable pageable) {
  Page<TurmaEntity> usuarioEntityPage = repository.findAll(pageable);
  return converter.convertToModel(usuarioEntityPage);
 }

 @Transactional(propagation = Propagation.REQUIRES_NEW)
 public void deletarTurma(Long turmaId) {
  alocacaoService.deletarAlocacaoPorTurmaId(turmaId);
  repository.deleteById(turmaId);
 }

 @Transactional(propagation = Propagation.REQUIRES_NEW)
 public void atualizarTurma(TurmaRequest request, Long turmaId) {
  TurmaEntity entity = repository.findById(turmaId).orElseThrow(TurmaNaoEncontradaException::new);

  entity.setNome(request.getNome());
  entity.setCapacidade(request.getCapacidade());

  repository.save(entity);
 }

 @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
 public Turma obterTurma(Long turmaId) {
  TurmaEntity entity = repository.findById(turmaId).orElseThrow(TurmaNaoEncontradaException::new);

  return converter.convertToModel(entity);
 }

}
