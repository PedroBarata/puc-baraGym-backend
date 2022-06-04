package br.com.barata.baragym.controller.alocacao;

import br.com.barata.baragym.controller.alocacao.request.AlocacaoRequest;
import br.com.barata.baragym.service.AlocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/alocacoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlocacaoCriarController {

 @Autowired
 private AlocacaoService alocacaoService;

 @PostMapping
 @PreAuthorize("hasAnyRole('ADMIN')")
 public void criaAlocacao(@Valid @RequestBody AlocacaoRequest request) {
  alocacaoService.criarAlocacao(request);
 }

}
