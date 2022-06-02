package br.com.barata.baragym.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Builder
@Table(name = "alocacao")
@NoArgsConstructor
@AllArgsConstructor
public class AlocacaoEntity {

 @ToString.Include
 @EqualsAndHashCode.Include
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Id
 @Column(name = "id")
 private Long id;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "turma_id")
 private TurmaEntity turma;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "dia_semana_id")
 private DiaSemanaEntity diaSemana;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "atividade_id")
 private AtividadeEntity atividade;

 @Column(name = "hora_inicio")
 private String horaInicio;

 @Column(name = "hora_fim")
 private String horaFim;

 @CreationTimestamp
 @Column(name = "created_at")
 private OffsetDateTime createdAt;

 @UpdateTimestamp
 @Column(name = "updated_at")
 private OffsetDateTime updatedAt;
}
