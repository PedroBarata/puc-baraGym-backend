package br.com.barata.baragym.service;

import br.com.barata.baragym.controller.turma.request.TurmaRequest;
import br.com.barata.baragym.entity.TurmaEntity;
import br.com.barata.baragym.entity.converter.TurmaConverter;
import br.com.barata.baragym.model.Turma;
import br.com.barata.baragym.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {

 @Autowired
 private TurmaRepository repository;

 @Autowired
 private TurmaConverter converter;

 public Turma criarTurma(TurmaRequest request) {
  TurmaEntity entity = TurmaEntity
		  .builder()
		  .nome(request.getNome())
		  .capacidade(request.getCapacidade())
		  .build();

  TurmaEntity persistedEntity = repository.save(entity);

  return converter.convertToModel(persistedEntity);
 }

 public Page<Turma> listarTodasTurmas(Pageable pageable) {
  Page<TurmaEntity> usuarioEntityPage = repository.findAll(pageable);
  return converter.convertToModel(usuarioEntityPage);
 }
}
