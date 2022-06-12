package br.com.barata.baragym.controller.usuario;

import br.com.barata.baragym.controller.usuario.response.UsuarioAgendamentoResponse;
import br.com.barata.baragym.controller.usuario.response.converter.UsuarioAgendamentoResponseConverter;
import br.com.barata.baragym.model.Agendamento;
import br.com.barata.baragym.security.annotation.CheckSecurity;
import br.com.barata.baragym.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/usuarios/{matricula}/agendamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioAgendamentoListarController {

 @Autowired
 private AgendamentoService agendamentoService;

 @Autowired
 private UsuarioAgendamentoResponseConverter converter;


 @GetMapping
 @CheckSecurity.ValidaMatricula
 public Page<UsuarioAgendamentoResponse> listaAgendamento(@PathVariable("matricula") @NotBlank String matricula,
                                                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                          @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
  Page<Agendamento> agendamentoPage = agendamentoService.listarTodosAgendamentosPorMatricula(matricula, PageRequest.of(page, size));

  return converter.convertToResponse(agendamentoPage, PageRequest.of(page, size));
 }

}
