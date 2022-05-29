package br.com.barata.baragym.service;

import br.com.barata.baragym.commons.utils.StringUtils;
import br.com.barata.baragym.controller.request.UsuarioRequest;
import br.com.barata.baragym.entity.UsuarioEntity;
import br.com.barata.baragym.entity.converter.UsuarioConverter;
import br.com.barata.baragym.exception.UsuarioNaoEncontradoException;
import br.com.barata.baragym.model.Usuario;
import br.com.barata.baragym.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

 @Autowired
 private UsuarioRepository repository;

 @Autowired
 private UsuarioConverter converter;

 @Autowired
 private StringUtils stringUtils;

 public Usuario buscarPorEmail(String email) {
  Optional<UsuarioEntity> optEntity = repository.findByEmail(email);
  return converter.convertToModel(optEntity.orElseThrow(UsuarioNaoEncontradoException::new));
 }

 public Usuario buscarPorMatricula(String matricula) {
  Optional<UsuarioEntity> optEntity = repository.findByMatricula(matricula);
  return converter.convertToModel(optEntity.orElseThrow(UsuarioNaoEncontradoException::new));
 }

 public Usuario criarUsuario(UsuarioRequest request) {
  UsuarioEntity entity = UsuarioEntity
		  .builder()
		  .nome(request.getNome())
		  .email(request.getEmail())
		  .senha(stringUtils.encodePassword(request.getSenha()))
		  .build();

  UsuarioEntity persistedEntity = repository.save(entity);

  return converter.convertToModel(persistedEntity);
 }

 public Page<Usuario> listarTodosUsuarios(Pageable pageable) {

  Page<UsuarioEntity> usuarioEntityPage = repository.findAll(pageable);

  return converter.convertToModel(usuarioEntityPage);
 }
}
