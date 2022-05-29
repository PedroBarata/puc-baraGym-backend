package br.com.barata.baragym.security.jwt;

import br.com.barata.baragym.model.Usuario;
import br.com.barata.baragym.security.enums.RoleEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Classe para manipular o token
@Component
public class JwtTokenUtil implements Serializable {

 static final String CLAIM_KEY_USERNAME = "sub";
 static final String CLAIM_KEY_MATRICULA = "matricula";
 static final String CLAIM_KEY_NOME = "nome";

 static final String CLAIM_KEY_CREATED = "created";
 static final String CLAIM_KEY_EXPIRED = "exp";

 static final String BEARER = "Bearer ";

 /* Valores que vem do application.properties */
 @Value("${jwt.secret}")
 private String secret;

 @Value("${jwt.expiration}")
 private Long expiration;

 protected Authentication getAuthentication() {
  return SecurityContextHolder.getContext().getAuthentication();
 }

 public String obtemSubPorToken(String token) {
  String username;
  try {
   final Claims claims = obtemClaimsDoToken(token);
   username = claims.getSubject();
  } catch (Exception e) {
   username = null;
  }
  return username;
 }

 public boolean usuarioEhAdmin() {
  return hasAnyAuthority(RoleEnum.ROLE_ADMIN);
 }

 protected boolean hasAnyAuthority(RoleEnum... authorities) {

  for (RoleEnum appAuthority : authorities) {
   if (hasAuthority(appAuthority.name())) {
	return true;
   }
  }

  return false;
 }

 protected boolean hasAuthority(String authorityName) {
  return getAuthentication().getAuthorities().stream()
		  .anyMatch(authority -> authority.getAuthority().equals(authorityName));
 }

 public String obtemMatriculaUsuarioLogado() {
  JwtUser usuarioLogado = (JwtUser) getAuthentication().getPrincipal();
  return usuarioLogado.getMatricula();
 }

 public Date obtemDataDeExpiracaoDoToken(String token) { // Obtem a data de expiração de um token
  Date expiration;
  try {
   final Claims claims = obtemClaimsDoToken(token);
   expiration = claims.getExpiration();
  } catch (Exception e) {
   expiration = null;
  }
  return expiration;
 }

 private Claims obtemClaimsDoToken(String token) { // Realiza o parser do token, para extrair as informações contidas
  // em seu corpo
  Claims claims;
  token = StringUtils.remove(token, BEARER);
  try {
   claims = Jwts
           .parser()
           .setSigningKey(secret)
           .parseClaimsJws(token)
           .getBody();
  } catch (Exception e) {
   claims = null;
  }
  return claims;
 }

 private Boolean validaTokenExpirado(String token) { // Verifica se o token está expirado
  final Date expiration = obtemDataDeExpiracaoDoToken(token);
  return expiration.before(new Date());
 }

 public String geraToken(Usuario usuario) { // Responsável por gerar o token
  Map<String, Object> claims = new HashMap<>();

  claims.put(CLAIM_KEY_USERNAME, usuario.getEmail());
  claims.put(CLAIM_KEY_MATRICULA, usuario.getMatricula());
  claims.put(CLAIM_KEY_NOME, usuario.getNome());

  final Date createdDate = new Date();
  claims.put(CLAIM_KEY_CREATED, createdDate);

  return mapClaims(claims);
 }

 protected String mapClaims(Map<String, Object> claims) { // Método auxiliar
  final Date createdDate = (Date) claims.get(CLAIM_KEY_CREATED);
  final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
  return Jwts
		  .builder()
		  .setClaims(claims)
		  .setExpiration(expirationDate)
		  .signWith(SignatureAlgorithm.HS256, secret)
		  .compact();
 }

 public Boolean podeSerAtualizado(String token) { // Verifica se o token pode ser atualizado
  return !validaTokenExpirado(token);
 }

 public String refreshToken(String token) { // Atualiza o token
  String refreshedToken;
  try {
   final Claims claims = obtemClaimsDoToken(token);
   claims.put(CLAIM_KEY_CREATED, new Date());
   refreshedToken = mapClaims(claims);
  } catch (Exception e) {
   refreshedToken = null;
  }
  return refreshedToken;
 }

 public Boolean validaToken(String token, UserDetails userDetails) { // Verifica se o token está valido
  JwtUser user = (JwtUser) userDetails;
  final String username = obtemSubPorToken(token);
  return (
		  username.equals(user.getUsername())
				  && !validaTokenExpirado(token));
 }

}
