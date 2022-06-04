package br.com.barata.baragym.controller.usuario.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoRequest {

 @NotNull
 private Long alocacaoId;
}
