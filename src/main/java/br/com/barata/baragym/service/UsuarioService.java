package br.com.barata.baragym.service;

import br.com.barata.baragym.entity.UsuarioEntity;
import br.com.barata.baragym.entity.converter.UsuarioConverter;
import br.com.barata.baragym.model.Usuario;
import br.com.barata.baragym.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

 @Autowired
 private UsuarioRepository repository;

 @Autowired
 private UsuarioConverter converter;

 public Usuario buscarPorEmail(String email) {
  Optional<UsuarioEntity> optEntity = repository.findByEmail(email);

  return converter.convertToModel(optEntity.orElseThrow());
 }
}
