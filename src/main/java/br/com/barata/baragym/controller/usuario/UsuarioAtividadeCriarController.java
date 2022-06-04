package br.com.barata.baragym.controller.usuario;

import br.com.barata.baragym.controller.usuario.request.UsuarioAtividadeRequest;
import br.com.barata.baragym.security.annotation.CheckSecurity;
import br.com.barata.baragym.service.UsuarioAtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/usuarios/{matricula}/atividades", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioAtividadeCriarController {

 @Autowired
 private UsuarioAtividadeService usuarioAtividadeService;

 @PostMapping
 @CheckSecurity.ValidaMatricula
 public void criaUsuarioAtividade(
         @PathVariable("matricula") @NotBlank String matricula,
         @Valid @RequestBody UsuarioAtividadeRequest request) {
  usuarioAtividadeService.criarUsuarioAtividade(request, matricula);
 }

}
