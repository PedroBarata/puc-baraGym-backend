package br.com.barata.baragym.controller.usuario;

import br.com.barata.baragym.security.annotation.CheckSecurity;
import br.com.barata.baragym.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/usuarios/{matricula}/agendamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioAgendamentoDeletarController {

 @Autowired
 private AgendamentoService agendamentoService;

 @DeleteMapping(value = "/{agendamentoId}")
 @CheckSecurity.ValidaMatricula
 public void deletaAgendamento(@PathVariable("matricula") @NotBlank String matricula,
                               @PathVariable("agendamentoId") @NotBlank Long agendamentoId) {
  agendamentoService.deletarAgendamento(agendamentoId);
 }

}
