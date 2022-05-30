package br.com.barata.baragym.repository;

import br.com.barata.baragym.entity.AtividadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends JpaRepository<AtividadeEntity, Long>, PagingAndSortingRepository<AtividadeEntity, Long> {
}
