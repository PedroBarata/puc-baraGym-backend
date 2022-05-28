package br.com.barata.baragym.entity;

import br.com.barata.baragym.security.enums.RoleEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Random;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Builder
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

 @ToString.Include
 @EqualsAndHashCode.Include
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Id
 @Column(name = "id")
 private Long id;

 @Column(name = "matricula")
 private String matricula;

 @Column(name = "nome")
 private String nome;
/*

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "customer_id")
 private CustomerEntity customer;
*/

 @Column(name = "email")
 private String email;

 @Column(name = "senha")
 private String senha;

 @Column(name = "role")
 private String role;

 @CreationTimestamp
 @Column(name = "dt_created")
 private OffsetDateTime createdAt;

 @UpdateTimestamp
 @Column(name = "dt_updated")
 private OffsetDateTime updatedAt;

 @PrePersist
 public void geraMatricula() {
  Random rand = new Random();
  // Generating random integers in range 0 to 99999
  this.matricula = Integer.toString(rand.nextInt(100000));
 }
}
