package br.com.barata.baragym.repository;

import br.com.barata.baragym.entity.UsuarioEntity;
import br.com.barata.infrastructure.stereotype.IntegrationTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

@IntegrationTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({
        "/db/testdata/usuarios/deleta_todos_usuarios.sql",
        "/db/testdata/usuarios/insere_admin.sql"
})
class UsuarioRepositoryIT {

 @Autowired
 private UsuarioRepository repository;

 @Test
 void obterPorEmail_quandoExistir_deveRetonarRegistro() {
  //cenario
  String email = "admin@teste.com";
  //acao
  Optional<UsuarioEntity> usuario = repository.findByEmail(email);

  //validacao
  Assertions.assertThat(usuario).isNotEmpty();
  Assertions.assertThat(usuario).get().hasNoNullFieldsOrProperties();
  Assertions.assertThat(usuario.get().getEmail()).isEqualTo(email);
 }

 @Test
 void obterPorMatricula_quandoExistir_deveRetonarRegistro() {
  //cenario
  String matricula = "1";
  //acao
  Optional<UsuarioEntity> usuario = repository.findByMatricula(matricula);

  //validacao
  Assertions.assertThat(usuario).isNotEmpty();
  Assertions.assertThat(usuario).get().hasNoNullFieldsOrProperties();
  Assertions.assertThat(usuario.get().getMatricula()).isEqualTo(matricula);
 }

}