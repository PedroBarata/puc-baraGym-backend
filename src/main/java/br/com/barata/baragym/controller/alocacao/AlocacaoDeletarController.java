package br.com.barata.baragym.controller.alocacao;

import br.com.barata.baragym.service.AlocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/alocacoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlocacaoDeletarController {

 @Autowired
 private AlocacaoService alocacaoService;

 @DeleteMapping("/{alocacaoId}")
 @PreAuthorize("hasAnyRole('ADMIN')")
 public void deletaAlocacao(@PathVariable("alocacaoId") @NotBlank Long alocacaoId) {
  alocacaoService.deletarAlocacao(alocacaoId);
 }

}
