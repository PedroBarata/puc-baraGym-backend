package br.com.barata.baragym.controller.usuario;

import br.com.barata.baragym.controller.usuario.request.AgendamentoRequest;
import br.com.barata.baragym.model.Agendamento;
import br.com.barata.baragym.security.annotation.CheckSecurity;
import br.com.barata.baragym.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/usuarios/{matricula}/agendamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioAgendamentoCriarController {

 @Autowired
 private AgendamentoService agendamentoService;

 @PostMapping
 @CheckSecurity.ValidaMatricula
 public Agendamento criaAgendamento(
         @PathVariable("matricula") @NotBlank String matricula,
         @Valid @RequestBody AgendamentoRequest request) {
  return agendamentoService.criarAgendamento(request, matricula);
 }

}
