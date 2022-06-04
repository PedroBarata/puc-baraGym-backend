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
@Table(name = "agendamento")
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoEntity {

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
 @JoinColumn(name = "alocacao_id")
 private AlocacaoEntity alocacao;

 @CreationTimestamp
 @Column(name = "created_at")
 private OffsetDateTime createdAt;

 @UpdateTimestamp
 @Column(name = "updated_at")
 private OffsetDateTime updatedAt;
}
