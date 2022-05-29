package br.com.barata.baragym.controller;

import br.com.barata.baragym.controller.request.UsuarioRequest;
import br.com.barata.baragym.controller.response.UsuarioResponse;
import br.com.barata.baragym.controller.response.converter.UsuarioResponseConverter;
import br.com.barata.baragym.model.Usuario;
import br.com.barata.baragym.security.annotation.CheckSecurity;
import br.com.barata.baragym.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
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
 public UsuarioResponse criaUsuario(@Valid @RequestBody UsuarioRequest request) {
  Usuario usuario = usuarioService.criarUsuario(request);
  return converter.convertToResponse(usuario);
 }

 @GetMapping
 @PreAuthorize("hasAnyRole('ADMIN')")
 public Page<UsuarioResponse> listaUsuarios(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

  Page<Usuario> usuarioPage = usuarioService.listarTodosUsuarios(PageRequest.of(page, size));
  return converter.convertToResponse(usuarioPage);
 }

}
