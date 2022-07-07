package br.com.barata.baragym.controller.turma;

import br.com.barata.baragym.controller.usuario.response.converter.UsuarioResponseConverter;
import br.com.barata.baragym.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/turmas", produces = MediaType.APPLICATION_JSON_VALUE)
public class TurmaDeletarController {

 @Autowired
 private TurmaService turmaService;

 @Autowired
 private UsuarioResponseConverter converter;

 @DeleteMapping("/{turmaId}")
 @PreAuthorize("hasAnyRole('ADMIN')")
 public void deletaTurma(@PathVariable("turmaId") @NotBlank Long turmaId) {
  turmaService.deletarTurma(turmaId);
 }
}
