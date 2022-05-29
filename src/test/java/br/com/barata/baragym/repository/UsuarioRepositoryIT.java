package br.com.barata.baragym.repository;

import br.com.barata.baragym.entity.UsuarioEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioRepositoryIT {

 @Autowired
 private UsuarioRepository repository;

 @Test
 @Sql({"/delete_usuarios.sql", "/insert_usuarios.sql"})
 void testFindAllReturnsName() {
  List<UsuarioEntity> persons = repository.findAll();
  Assertions.assertThat(persons.size()).isOne();
 }
}