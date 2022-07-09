package br.com.barata.baragym.controller.turma;

import br.com.barata.baragym.controller.turma.request.TurmaRequest;
import br.com.barata.baragym.controller.usuario.response.converter.UsuarioResponseConverter;
import br.com.barata.baragym.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/turmas", produces = MediaType.APPLICATION_JSON_VALUE)
public class TurmaAtualizarController {

 @Autowired
 private TurmaService turmaService;

 @Autowired
 private UsuarioResponseConverter converter;

 @PutMapping("/{turmaId}")
 @PreAuthorize("hasAnyRole('ADMIN')")
 public void atualizaTurma(@Valid @RequestBody TurmaRequest request,
                           @PathVariable("turmaId") @NotBlank Long turmaId) {
  turmaService.atualizarTurma(request, turmaId);
 }

}
