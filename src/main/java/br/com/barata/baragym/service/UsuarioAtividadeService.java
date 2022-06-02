package br.com.barata.baragym.service;

import br.com.barata.baragym.controller.usuario.request.UsuarioAtividadeRequest;
import br.com.barata.baragym.entity.AtividadeEntity;
import br.com.barata.baragym.entity.UsuarioAtividadeEntity;
import br.com.barata.baragym.entity.UsuarioEntity;
import br.com.barata.baragym.entity.converter.UsuarioAtividadeConverter;
import br.com.barata.baragym.exception.AtividadeNaoEncontradaException;
import br.com.barata.baragym.exception.DiaSemanaNaoEncontradoException;
import br.com.barata.baragym.model.UsuarioAtividade;
import br.com.barata.baragym.repository.AtividadeRepository;
import br.com.barata.baragym.repository.UsuarioAtividadeRepository;
import br.com.barata.baragym.repository.UsuarioRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 private UsuarioAtividadeConverter converter;

 public UsuarioAtividade criarUsuarioAtividade(UsuarioAtividadeRequest request, String matricula) {
  Optional<UsuarioEntity> optUsuarioEntity = usuarioRepository.findByMatricula(matricula);

  obtemEExcluiAtividadesDoUsuario(optUsuarioEntity.orElseThrow(DiaSemanaNaoEncontradoException::new));

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
  List<UsuarioAtividadeEntity> entityList = usuarioAtividadeRepository.findByUsuarioMatriculaAndVigenciaFimGreaterThanEqual(matricula, new Date());

  return converter.convertToModel(entityList);
 }

 private void obtemEExcluiAtividadesDoUsuario(UsuarioEntity usuario) {
  List<UsuarioAtividadeEntity> persistedEntityList = usuarioAtividadeRepository.findAllByUsuarioMatricula(usuario.getMatricula());
  usuarioAtividadeRepository.deleteAll(persistedEntityList);
 }
}
