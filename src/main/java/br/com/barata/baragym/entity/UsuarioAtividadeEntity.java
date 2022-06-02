package br.com.barata.baragym.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Builder
@Table(name = "usuario_atividade")
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAtividadeEntity {

 @ToString.Include
 @EqualsAndHashCode.Include
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Id
 @Column(name = "id")
 private Long id;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "usuario_id")
 private UsuarioEntity usuario;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "atividade_id")
 private AtividadeEntity atividade;

 @Column(name = "valor_total")
 private BigDecimal valorTotal;

 @Column(name = "quantidade_semana")
 private Integer quantidadeSemana;

 @Column(name = "vigencia_inicio")
 private Date vigenciaInicio;

 @Column(name = "vigencia_fim")
 private Date vigenciaFim;

 @CreationTimestamp
 @Column(name = "created_at")
 private OffsetDateTime createdAt;

 @UpdateTimestamp
 @Column(name = "updated_at")
 private OffsetDateTime updatedAt;
}
