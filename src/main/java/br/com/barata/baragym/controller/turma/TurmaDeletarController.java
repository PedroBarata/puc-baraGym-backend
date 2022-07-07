package br.com.barata.baragym.controller.turma;

import br.com.barata.baragym.controller.turma.request.TurmaRequest;
import br.com.barata.baragym.controller.usuario.response.converter.UsuarioResponseConverter;
import br.com.barata.baragym.model.Turma;
import br.com.barata.baragym.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/turmas", produces = MediaType.APPLICATION_JSON_VALUE)
public class TurmaDeletarController {

 @Autowired
 private TurmaService turmaService;

 @Autowired
 private UsuarioResponseConverter converter;

 @PostMapping
 @PreAuthorize("hasAnyRole('ADMIN')")
 public Turma criaTurma(@Valid @RequestBody TurmaRequest request) {
  return turmaService.criarTurma(request);
 }

 @DeleteMapping("/{turmaId}")
 @PreAuthorize("hasAnyRole('ADMIN')")
 public void deletaTurma(@PathVariable("turmaId") @NotBlank Long turmaId) {
  turmaService.deletarTurma(turmaId);
 }
}
