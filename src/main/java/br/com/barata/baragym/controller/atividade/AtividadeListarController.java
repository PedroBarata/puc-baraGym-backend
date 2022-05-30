package br.com.barata.baragym.controller.atividade;

import br.com.barata.baragym.model.Atividade;
import br.com.barata.baragym.service.AtividadeService;
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
@RequestMapping(value = "/atividades", produces = MediaType.APPLICATION_JSON_VALUE)
public class AtividadeListarController {

 @Autowired
 private AtividadeService turmaService;


 @GetMapping
 @PreAuthorize("hasAnyRole('ADMIN')")
 public Page<Atividade> listaAtividades(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                        @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

  return turmaService.listarTodasAtividades(PageRequest.of(page, size));
 }

}
