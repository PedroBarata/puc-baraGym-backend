package br.com.barata.baragym.security.service;

import br.com.barata.baragym.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckAccessService {

 @Autowired
 private JwtTokenUtil jwtTokenUtil;

 public boolean validaMatricula(String matricula) {

  if (jwtTokenUtil.usuarioEhAdmin()) {
   return true;
  }

  String matriculaLogada = jwtTokenUtil.obtemMatriculaUsuarioLogado();

  return matricula.equals(matriculaLogada);
 }
}
