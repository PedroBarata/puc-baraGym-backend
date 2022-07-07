package br.com.barata.baragym.controller.atividade;

import br.com.barata.baragym.controller.atividade.request.AtividadeRequest;
import br.com.barata.baragym.controller.usuario.response.converter.UsuarioResponseConverter;
import br.com.barata.baragym.model.Atividade;
import br.com.barata.baragym.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/atividades", produces = MediaType.APPLICATION_JSON_VALUE)
public class AtividadeDeletarController {

 @Autowired
 private AtividadeService atividadeService;

 @Autowired
 private UsuarioResponseConverter converter;

 @PostMapping
 @PreAuthorize("hasAnyRole('ADMIN')")
 public Atividade criaAtividade(@Valid @RequestBody AtividadeRequest request) {
  return atividadeService.criarAtividade(request);
 }

 @DeleteMapping("/{atividadeId}")
 @PreAuthorize("hasAnyRole('ADMIN')")
 public void deletaAtividade(@PathVariable("atividadeId") @NotBlank Long atividadeId) {
  atividadeService.deletarAtividade(atividadeId);
 }
}
