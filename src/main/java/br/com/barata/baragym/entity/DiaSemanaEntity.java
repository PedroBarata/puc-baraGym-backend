package br.com.barata.baragym.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Builder
@Table(name = "dia_semana")
@NoArgsConstructor
@AllArgsConstructor
public class DiaSemanaEntity {

 @ToString.Include
 @EqualsAndHashCode.Include
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Id
 @Column(name = "id")
 private Long id;

 @Column(name = "nome_dia")
 private String nomeDia;
}
