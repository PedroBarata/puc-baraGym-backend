package br.com.barata.baragym.repository;

import br.com.barata.baragym.entity.DiaSemanaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaSemanaRepository extends JpaRepository<DiaSemanaEntity, Long> {
}
