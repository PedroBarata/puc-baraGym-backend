package br.com.barata.baragym.repository;

import br.com.barata.baragym.entity.AgendamentoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long>, PagingAndSortingRepository<AgendamentoEntity, Long> {

 @EntityGraph(attributePaths = {
		 "alocacao",
		 "alocacao.turma",
		 "alocacao.atividade",
		 "alocacao.diaSemana"})
 Page<AgendamentoEntity> findAllByUsuarioMatricula(String matricula, Pageable pageable);

 @EntityGraph(attributePaths = {"alocacao"})
 List<AgendamentoEntity> findAllByUsuarioMatriculaAndAlocacaoAtividadeId(String matricula, Long alocacaoId);

 @EntityGraph(attributePaths = {
		 "alocacao",
		 "alocacao.turma",
		 "alocacao.atividade",
		 "alocacao.diaSemana"})
 Optional<AgendamentoEntity> findById(Long id);

 Optional<AgendamentoEntity> findByUsuarioMatriculaAndAlocacaoId(String matricula, Long alocacaoId);

 List<AgendamentoEntity> findAllByUsuarioMatricula(String matricula);

 List<AgendamentoEntity> findByAlocacaoId(Long alocacaoId);

}
