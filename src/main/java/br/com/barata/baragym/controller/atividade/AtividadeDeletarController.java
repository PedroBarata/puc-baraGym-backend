package br.com.barata.baragym.controller.atividade;

import br.com.barata.baragym.controller.usuario.response.converter.UsuarioResponseConverter;
import br.com.barata.baragym.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/atividades", produces = MediaType.APPLICATION_JSON_VALUE)
public class AtividadeDeletarController {

 @Autowired
 private AtividadeService atividadeService;

 @Autowired
 private UsuarioResponseConverter converter;

 @DeleteMapping("/{atividadeId}")
 @PreAuthorize("hasAnyRole('ADMIN')")
 public void deletaAtividade(@PathVariable("atividadeId") @NotBlank Long atividadeId) {
  atividadeService.deletarAtividade(atividadeId);
 }
}
