package br.com.barata.baragym.controller.usuario;

import br.com.barata.baragym.model.UsuarioAlocacaoAgendamento;
import br.com.barata.baragym.model.UsuarioAtividade;
import br.com.barata.baragym.security.annotation.CheckSecurity;
import br.com.barata.baragym.service.UsuarioAtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios/{matricula}/atividades", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioAtividadeListarController {

 @Autowired
 private UsuarioAtividadeService usuarioAtividadeService;

 @GetMapping
 @CheckSecurity.ValidaMatricula
 public List<UsuarioAtividade> listaUsuarioAtividade(@PathVariable("matricula") @NotBlank String matricula) {
  return usuarioAtividadeService.listarUsuarioAtividade(matricula);
 }

 @GetMapping(value = "{usuarioAtividadeId}/alocacoes")
 @CheckSecurity.ValidaMatricula
 public Page<UsuarioAlocacaoAgendamento> listaAlocacoesEAgendamentosPorUsuario(@PathVariable("matricula") @NotBlank String matricula,
                                                                               @PathVariable("usuarioAtividadeId") @NotBlank Long usuarioAtividadeId,
                                                                               @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                                               @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
  return usuarioAtividadeService.listarAlocacoesEAgendamentosPorUsuario(matricula, usuarioAtividadeId, PageRequest.of(page, size));
 }

}
