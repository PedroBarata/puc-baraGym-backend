package br.com.barata.baragym.controller.alocacao;

import br.com.barata.baragym.controller.alocacao.request.AlocacaoRequest;
import br.com.barata.baragym.service.AlocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/alocacoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlocacaoAtualizarController {

 @Autowired
 private AlocacaoService alocacaoService;

 @PutMapping("/{alocacaoId}")
 @PreAuthorize("hasAnyRole('ADMIN')")
 public void atualizaAlocacao(@Valid @RequestBody AlocacaoRequest request,
                              @PathVariable("alocacaoId") @NotBlank Long alocacaoId) {
  alocacaoService.atualizarAlocacao(request, alocacaoId);
 }

}
