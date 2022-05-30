package br.com.barata.baragym.controller.turma;

import br.com.barata.baragym.controller.turma.request.TurmaRequest;
import br.com.barata.baragym.controller.usuario.response.converter.UsuarioResponseConverter;
import br.com.barata.baragym.model.Turma;
import br.com.barata.baragym.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/turmas", produces = MediaType.APPLICATION_JSON_VALUE)
public class TurmaCriarController {

 @Autowired
 private TurmaService turmaService;

 @Autowired
 private UsuarioResponseConverter converter;

 @PostMapping
 @PreAuthorize("hasAnyRole('ADMIN')")
 public Turma criaTurma(@Valid @RequestBody TurmaRequest request) {
  return turmaService.criarTurma(request);
 }

}
