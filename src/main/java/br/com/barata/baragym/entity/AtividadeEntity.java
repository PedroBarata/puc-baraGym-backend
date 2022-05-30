package br.com.barata.baragym.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Builder
@Table(name = "atividade")
@NoArgsConstructor
@AllArgsConstructor
public class AtividadeEntity {

 @ToString.Include
 @EqualsAndHashCode.Include
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Id
 @Column(name = "id")
 private Long id;

 @Column(name = "nome")
 private String nome;

 @Column(name = "descricao")
 private String descricao;

 @Column(name = "valor_dia")
 private BigDecimal valorDia;

 @CreationTimestamp
 @Column(name = "created_at")
 private OffsetDateTime createdAt;

 @UpdateTimestamp
 @Column(name = "updated_at")
 private OffsetDateTime updatedAt;


}
