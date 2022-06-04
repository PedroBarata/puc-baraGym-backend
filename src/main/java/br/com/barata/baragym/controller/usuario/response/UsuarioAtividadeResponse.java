package br.com.barata.baragym.controller.usuario.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UsuarioAtividadeResponse {

 private Long id;
 private Long atividadeId;
 private String nomeAtividade;
 private String quantidadePorSemana;
}
