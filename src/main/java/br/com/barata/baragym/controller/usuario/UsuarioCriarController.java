package br.com.barata.baragym.controller.usuario;

import br.com.barata.baragym.controller.usuario.request.UsuarioRequest;
import br.com.barata.baragym.controller.usuario.response.UsuarioResponse;
import br.com.barata.baragym.controller.usuario.response.converter.UsuarioResponseConverter;
import br.com.barata.baragym.model.Usuario;
import br.com.barata.baragym.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioCriarController {

 @Autowired
 private UsuarioService usuarioService;

 @Autowired
 private UsuarioResponseConverter converter;

 @PostMapping
 public UsuarioResponse criaUsuario(@Valid @RequestBody UsuarioRequest request) {
  Usuario usuario = usuarioService.criarUsuario(request);
  return converter.convertToResponse(usuario);
 }

}
