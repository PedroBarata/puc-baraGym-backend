package br.com.barata.baragym.service;

import br.com.barata.baragym.controller.atividade.request.AtividadeRequest;
import br.com.barata.baragym.entity.AlocacaoEntity;
import br.com.barata.baragym.entity.AtividadeEntity;
import br.com.barata.baragym.entity.converter.AlocacaoConverter;
import br.com.barata.baragym.entity.converter.AtividadeConverter;
import br.com.barata.baragym.model.Alocacao;
import br.com.barata.baragym.model.Atividade;
import br.com.barata.baragym.repository.AlocacaoRepository;
import br.com.barata.baragym.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtividadeService {

 @Autowired
 private AtividadeRepository atividadeRepo;

 @Autowired
 private AlocacaoRepository alocacaoRepo;

 @Autowired
 private AtividadeConverter atividadeConverter;

 @Autowired
 private AlocacaoConverter alocacaoConverter;

 @Transactional(propagation = Propagation.REQUIRES_NEW)
 public Atividade criarAtividade(AtividadeRequest request) {
  AtividadeEntity entity = AtividadeEntity
          .builder()
          .descricao(request.getDescricao())
          .nome(request.getNome())
          .valorDia(request.getValorDia())
          .build();

  AtividadeEntity persistedEntity = atividadeRepo.save(entity);

  return atividadeConverter.convertToModel(persistedEntity);
 }

 @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
 public Page<Atividade> listarTodasAtividades(Pageable pageable) {
  Page<AtividadeEntity> atividadeEntityPage = atividadeRepo.findAll(pageable);
  return atividadeConverter.convertToModel(atividadeEntityPage);
 }

 @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
 public Page<Alocacao> listarAlocacoesPorAtividade(Long atividadeId, Pageable pageable) {
  Page<AlocacaoEntity> alocacaoEntityPage = alocacaoRepo.findByAtividadeId(atividadeId, pageable);
  return alocacaoConverter.convertToModel(alocacaoEntityPage);
 }
}
