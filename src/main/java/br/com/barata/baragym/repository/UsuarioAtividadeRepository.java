package br.com.barata.baragym.repository;

import br.com.barata.baragym.entity.UsuarioAtividadeEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UsuarioAtividadeRepository extends JpaRepository<UsuarioAtividadeEntity, Long>, PagingAndSortingRepository<UsuarioAtividadeEntity, Long> {

 @EntityGraph(attributePaths = {"atividade"})
 List<UsuarioAtividadeEntity> findByUsuarioMatriculaAndVigenciaFimGreaterThanEqual(String matricula, Date data);

 @EntityGraph(attributePaths = {"atividade"})
 List<UsuarioAtividadeEntity> findAllByUsuarioMatricula(String matricula);

 UsuarioAtividadeEntity findByUsuarioMatriculaAndAtividadeId(String matricula, Long atividadeId);
}
