package br.com.barata.baragym.repository;


import br.com.barata.baragym.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

 Optional<UsuarioEntity> findByEmail(String email);

 Optional<UsuarioEntity> findByMatricula(String matricula);

}
