package br.com.barata.baragym.repository;

import br.com.barata.baragym.entity.AlocacaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlocacaoRepository extends JpaRepository<AlocacaoEntity, Long>, PagingAndSortingRepository<AlocacaoEntity, Long> {

 Page<AlocacaoEntity> findByAtividadeId(Long atividadeId, Pageable pageable);
}
