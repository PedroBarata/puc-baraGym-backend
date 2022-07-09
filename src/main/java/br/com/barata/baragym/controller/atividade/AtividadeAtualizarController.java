package br.com.barata.baragym.controller.atividade;

import br.com.barata.baragym.controller.atividade.request.AtividadeRequest;
import br.com.barata.baragym.controller.usuario.response.converter.UsuarioResponseConverter;
import br.com.barata.baragym.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/atividades", produces = MediaType.APPLICATION_JSON_VALUE)
public class AtividadeAtualizarController {

 @Autowired
 private AtividadeService atividadeService;

 @Autowired
 private UsuarioResponseConverter converter;

 @PutMapping("/{atividadeId}")
 @PreAuthorize("hasAnyRole('ADMIN')")
 public void atualizaAtividade(@Valid @RequestBody AtividadeRequest request,
							   @PathVariable("atividadeId") @NotBlank Long atividadeId) {
  atividadeService.atualizarAtividade(request, atividadeId);
 }
}
