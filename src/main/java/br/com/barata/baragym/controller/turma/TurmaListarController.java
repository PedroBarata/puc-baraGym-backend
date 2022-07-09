package br.com.barata.baragym.controller.turma;

import br.com.barata.baragym.model.Turma;
import br.com.barata.baragym.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/turmas", produces = MediaType.APPLICATION_JSON_VALUE)
public class TurmaListarController {

 @Autowired
 private TurmaService turmaService;


 @GetMapping
 @PreAuthorize("hasAnyRole('ADMIN')")
 public Page<Turma> listaTurmas(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
								@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

  return turmaService.listarTodasTurmas(PageRequest.of(page, size));
 }

 @GetMapping("/{turmaId}")
 @PreAuthorize("hasAnyRole('ADMIN')")
 public Turma obtemTurma(@PathVariable("turmaId") @NotBlank Long turmaId) {
  return turmaService.obterTurma(turmaId);
 }

}
