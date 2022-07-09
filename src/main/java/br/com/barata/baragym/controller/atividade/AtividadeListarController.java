package br.com.barata.baragym.controller.atividade;

import br.com.barata.baragym.model.Alocacao;
import br.com.barata.baragym.model.Atividade;
import br.com.barata.baragym.security.annotation.CheckSecurity;
import br.com.barata.baragym.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/atividades", produces = MediaType.APPLICATION_JSON_VALUE)
public class AtividadeListarController {

 @Autowired
 private AtividadeService atividadeService;


 @GetMapping
 @CheckSecurity.ValidaSeEstaLogado
 public Page<Atividade> listaAtividades(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
										@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

  return atividadeService.listarTodasAtividades(PageRequest.of(page, size));
 }

 @GetMapping(value = "{atividadeId}/alocacoes")
 @CheckSecurity.ValidaSeEstaLogado
 public Page<Alocacao> listaAlocacoesPorAtividade(@PathVariable("atividadeId") @NotBlank Long atividadeId,
												  @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
												  @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

  return atividadeService.listarAlocacoesPorAtividade(atividadeId, PageRequest.of(page, size));
 }

 @GetMapping("/{atividadeId}")
 @PreAuthorize("hasAnyRole('ADMIN')")
 public Atividade obtemAtividade(@PathVariable("atividadeId") @NotBlank Long atividadeId) {
  return atividadeService.obterAtividade(atividadeId);
 }


}
