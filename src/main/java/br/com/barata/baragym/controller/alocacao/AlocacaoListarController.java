package br.com.barata.baragym.controller.alocacao;

import br.com.barata.baragym.model.Alocacao;
import br.com.barata.baragym.security.annotation.CheckSecurity;
import br.com.barata.baragym.service.AlocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/alocacoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlocacaoListarController {


 @Autowired
 private AlocacaoService alocacaoService;

 @GetMapping
 @CheckSecurity.ValidaSeEstaLogado
 public Page<Alocacao> listarAlocacoes(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                       @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

  return alocacaoService.listarTodasAlocacoes(PageRequest.of(page, size));
 }

 @GetMapping("/{alocacaoId}")
 @PreAuthorize("hasAnyRole('ADMIN')")
 public Alocacao obtemAlocacao(@PathVariable("alocacaoId") @NotBlank Long alocacaoId) {
  return alocacaoService.obterAlocacao(alocacaoId);
 }

}
