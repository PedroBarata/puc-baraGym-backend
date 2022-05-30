package br.com.barata.baragym.service;

import br.com.barata.baragym.controller.atividade.request.AtividadeRequest;
import br.com.barata.baragym.entity.AtividadeEntity;
import br.com.barata.baragym.entity.converter.AtividadeConverter;
import br.com.barata.baragym.model.Atividade;
import br.com.barata.baragym.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AtividadeService {

 @Autowired
 private AtividadeRepository repository;

 @Autowired
 private AtividadeConverter converter;

 public Atividade criarAtividade(AtividadeRequest request) {
  AtividadeEntity entity = AtividadeEntity
		  .builder()
		  .descricao(request.getDescricao())
		  .nome(request.getNome())
		  .valorDia(request.getValorDia())
		  .build();

  AtividadeEntity persistedEntity = repository.save(entity);

  return converter.convertToModel(persistedEntity);
 }

 public Page<Atividade> listarTodasAtividades(Pageable pageable) {
  Page<AtividadeEntity> atividadeEntityPage = repository.findAll(pageable);
  return converter.convertToModel(atividadeEntityPage);
 }
}
