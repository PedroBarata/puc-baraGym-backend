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
@Table(name = "turma")
@NoArgsConstructor
@AllArgsConstructor
public class TurmaEntity {

 @ToString.Include
 @EqualsAndHashCode.Include
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Id
 @Column(name = "id")
 private Long id;

 @Column(name = "nome")
 private String nome;

 @Column(name = "capacidade")
 private Integer capacidade;

 @CreationTimestamp
 @Column(name = "created_at")
 private OffsetDateTime createdAt;

 @UpdateTimestamp
 @Column(name = "updated_at")
 private OffsetDateTime updatedAt;

}
