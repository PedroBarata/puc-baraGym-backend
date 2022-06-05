package br.com.barata.baragym.security.service;

import br.com.barata.baragym.entity.UsuarioEntity;
import br.com.barata.baragym.repository.UsuarioRepository;
import br.com.barata.baragym.security.jwt.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/* Serviço para manipular a classe UserDetails */
@Service
public class JwtUserDetailsService implements UserDetailsService {

 @Autowired
 private UsuarioRepository usuarioRepository;

 @Override
 public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
  Optional<UsuarioEntity> optionalUsuarioEntity = this.usuarioRepository.findByEmail(email);
  return JwtUserFactory.create(optionalUsuarioEntity.orElseThrow());
 }



}
