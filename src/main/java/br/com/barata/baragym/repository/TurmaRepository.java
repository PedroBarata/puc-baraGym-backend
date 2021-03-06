package br.com.barata.baragym.repository;

import br.com.barata.baragym.entity.TurmaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<TurmaEntity, Long>, PagingAndSortingRepository<TurmaEntity, Long> {
}
