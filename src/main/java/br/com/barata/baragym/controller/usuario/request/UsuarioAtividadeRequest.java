package br.com.barata.baragym.controller.usuario.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class UsuarioAtividadeRequest {

 @NotEmpty
 private List<AtividadeContratada> atividades;
 private BigDecimal valorTotal;

 @Getter
 public static class AtividadeContratada {
  private Long atividadeId;
  private Integer quantidadeSemana;
 }
}
