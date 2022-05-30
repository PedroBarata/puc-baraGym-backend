package br.com.barata.baragym.controller.usuario;

import br.com.barata.baragym.controller.usuario.response.UsuarioResponse;
import br.com.barata.baragym.controller.usuario.response.converter.UsuarioResponseConverter;
import br.com.barata.baragym.model.Usuario;
import br.com.barata.baragym.security.annotation.CheckSecurity;
import br.com.barata.baragym.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioPorMatriculaController {

 @Autowired
 private UsuarioService usuarioService;

 @Autowired
 private UsuarioResponseConverter converter;

 @GetMapping("/{matricula}")
 @CheckSecurity.ValidaMatricula
 public UsuarioResponse obtemUsuarioPorMatricula(@PathVariable("matricula") @NotBlank String matricula) {
  Usuario usuario = usuarioService.buscarPorMatricula(matricula);
  return converter.convertToResponse(usuario);
 }

}
