package br.com.barata.baragym.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/* O spring security depende de uma interface que implemente o userDetail,
 * é através desses dados que ele controlará quem está logado no sistema */

public class JwtUser implements UserDetails {

 private static final long serialVersionUID = -427534579035911926L;

 private final String email;
 private final String password;
 private final Collection<? extends GrantedAuthority> authorities;
 @Getter
 private final String matricula;


 public JwtUser(String email, String matricula, String password, Collection<? extends GrantedAuthority> authorities) {
  this.email = email;
  this.matricula = matricula;
  this.password = password;
  this.authorities = authorities;
 }

 @Override
 public Collection<? extends GrantedAuthority> getAuthorities() {
  return authorities;
 }

 @Override
 public String getPassword() {
  return this.password;
 }

 @Override
 public String getUsername() {
  return this.email;
 }

 @JsonIgnore
 @Override
 public boolean isAccountNonExpired() {
  return true;
 }

 @JsonIgnore
 @Override
 public boolean isAccountNonLocked() {
  return true;
 }

 @JsonIgnore
 @Override
 public boolean isCredentialsNonExpired() {
  return true;
 }

 @Override
 public boolean isEnabled() {
  return true;
 }


}
