package br.com.barata.baragym.service;

import br.com.barata.baragym.controller.usuario.request.UsuarioAtividadeRequest;
import br.com.barata.baragym.entity.*;
import br.com.barata.baragym.entity.converter.UsuarioAtividadeConverter;
import br.com.barata.baragym.exception.AtividadeNaoEncontradaException;
import br.com.barata.baragym.exception.DiaSemanaNaoEncontradoException;
import br.com.barata.baragym.exception.UsuarioNaoEncontradoException;
import br.com.barata.baragym.model.UsuarioAlocacaoAgendamento;
import br.com.barata.baragym.model.UsuarioAtividade;
import br.com.barata.baragym.repository.*;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioAtividadeService {

 @Autowired
 private UsuarioAtividadeRepository usuarioAtividadeRepository;

 @Autowired
 private UsuarioRepository usuarioRepository;

 @Autowired
 private AtividadeRepository atividadeRepository;

 @Autowired
 private AgendamentoRepository agendamentoRepository;

 @Autowired
 private AlocacaoRepository alocacaoRepository;

 @Autowired
 private UsuarioAtividadeConverter converter;

 @Transactional(propagation = Propagation.REQUIRES_NEW)
 public void criarUsuarioAtividade(UsuarioAtividadeRequest request, String matricula) {
  Optional<UsuarioEntity> optUsuarioEntity = usuarioRepository.findByMatricula(matricula);

  obtemEExcluiAtividadesDoUsuario(optUsuarioEntity.orElseThrow(DiaSemanaNaoEncontradoException::new));
  obtemEExcluiAgendamentosDoUsuario(optUsuarioEntity.orElseThrow(UsuarioNaoEncontradoException::new));
  
  Date vigenciaInicio = new Date();
  Date fimVigencia = DateUtils.addDays(vigenciaInicio, 30);

  request.getAtividades().forEach(atividadeContratada -> {
   Optional<AtividadeEntity> optAtividadeEntity = atividadeRepository.findById(atividadeContratada.getAtividadeId());


   UsuarioAtividadeEntity entity = UsuarioAtividadeEntity
		   .builder()
		   .atividade(optAtividadeEntity.orElseThrow(AtividadeNaoEncontradaException::new))
		   .usuario(optUsuarioEntity.orElseThrow(DiaSemanaNaoEncontradoException::new))
		   .vigenciaInicio(vigenciaInicio)
		   .vigenciaFim(fimVigencia)
		   .quantidadeSemana(atividadeContratada.getQuantidadeSemana())
		   .valorTotal(request.getValorTotal())
		   .build();

   usuarioAtividadeRepository.save(entity);
  });
 }

 @Transactional(propagation = Propagation.REQUIRES_NEW)
 private void obtemEExcluiAtividadesDoUsuario(UsuarioEntity usuario) {
  List<UsuarioAtividadeEntity> persistedEntityList = usuarioAtividadeRepository.findAllByUsuarioMatricula(usuario.getMatricula());
  usuarioAtividadeRepository.deleteAll(persistedEntityList);
 }

 @Transactional(propagation = Propagation.REQUIRES_NEW)
 private void obtemEExcluiAgendamentosDoUsuario(UsuarioEntity usuario) {
  List<AgendamentoEntity> persistedEntityList = agendamentoRepository.findAllByUsuarioMatricula(usuario.getMatricula());
  agendamentoRepository.deleteAll(persistedEntityList);
 }

 @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
 public List<UsuarioAtividade> listarUsuarioAtividade(String matricula) {
  List<UsuarioAtividadeEntity> entityList = usuarioAtividadeRepository.findAllByUsuarioMatricula(matricula);
  return converter.convertToModel(entityList);
 }

 @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
 public Page<UsuarioAlocacaoAgendamento> listarAlocacoesEAgendamentosPorUsuario(String matricula, Long usuarioAtividadeId, Pageable pageable) {
  Optional<UsuarioAtividadeEntity> optUsuarioAtividade = usuarioAtividadeRepository.findById(usuarioAtividadeId);

  Page<AlocacaoEntity> alocacaoEntityPage = alocacaoRepository.findByAtividadeId(
		  optUsuarioAtividade.orElseThrow(AtividadeNaoEncontradaException::new)
				  .getAtividade().getId(),
		  pageable);

  Page<AgendamentoEntity> agendamentoEntityPage = agendamentoRepository.findAllByUsuarioMatricula(matricula, pageable);
  return converter.convertToAlocacaoAgendamentoModel(alocacaoEntityPage, agendamentoEntityPage);
 }


}
