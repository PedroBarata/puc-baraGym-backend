package br.com.barata.baragym.controller;

import br.com.barata.baragym.controller.request.UsuarioRequest;
import br.com.barata.baragym.controller.response.UsuarioResponse;
import br.com.barata.baragym.controller.response.converter.UsuarioResponseConverter;
import br.com.barata.baragym.model.Usuario;
import br.com.barata.baragym.security.annotation.CheckSecurity;
import br.com.barata.baragym.security.jwt.JwtTokenUtil;
import br.com.barata.baragym.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

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

 @PostMapping
 //@PreAuthorize("hasAnyRole('ADMIN')")
 public void criaUsuario(@Valid @RequestBody UsuarioRequest request) {
  usuarioService.criarUsuario(request);
 }

}
