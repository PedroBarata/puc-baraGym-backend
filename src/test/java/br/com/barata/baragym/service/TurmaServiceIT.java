package br.com.barata.baragym.service;

import br.com.barata.baragym.controller.turma.request.TurmaRequest;
import br.com.barata.baragym.model.Turma;
import br.com.barata.infrastructure.stereotype.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({
		"/db/testdata/turmas/deleta_todas_turmas.sql",
		"/db/testdata/turmas/insere_turmas.sql"
})
class TurmaServiceIT {

 @Autowired
 private TurmaService service;

 @Test
 void criarTurma_deveCriarUmaNovaTurma() {
//cenario
  Integer capacidade = 400;
  String nome = "Sala teste";
  TurmaRequest request = TurmaRequest
		  .builder()
		  .nome(nome)
		  .capacidade(capacidade)
		  .build();

  //acao
  Turma turma = service.criarTurma(request);

  //validacao
  assertThat(turma).hasNoNullFieldsOrProperties();
  assertThat(turma.getNome()).isEqualTo(nome);
  assertThat(turma.getCapacidade()).isEqualTo(capacidade);
  assertThat(turma.getId()).isNotNull();
 }

 @Test
 void listarTodasTurmas_deveListarTurmas() {
  //acao
  Page<Turma> turmaPage = service.listarTodasTurmas(PageRequest.of(0, 10));

  //validacao - paginacao
  assertThat(turmaPage.getTotalPages()).isEqualTo(1);
  assertThat(turmaPage.getTotalElements()).isEqualTo(3);

  //validacao - conteudo do elemento - sala 1
  assertThat(turmaPage.getContent().get(0).getId()).isEqualTo(1);
 }

}