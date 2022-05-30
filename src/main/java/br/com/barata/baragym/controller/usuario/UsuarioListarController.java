package br.com.barata.baragym.controller.usuario;

import br.com.barata.baragym.controller.usuario.response.UsuarioResponse;
import br.com.barata.baragym.controller.usuario.response.converter.UsuarioResponseConverter;
import br.com.barata.baragym.model.Usuario;
import br.com.barata.baragym.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioListarController {

 @Autowired
 private UsuarioService usuarioService;

 @Autowired
 private UsuarioResponseConverter converter;

 @GetMapping
 @PreAuthorize("hasAnyRole('ADMIN')")
 public Page<UsuarioResponse> listaUsuarios(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

  Page<Usuario> usuarioPage = usuarioService.listarTodosUsuarios(PageRequest.of(page, size));
  return converter.convertToResponse(usuarioPage);
 }

}
