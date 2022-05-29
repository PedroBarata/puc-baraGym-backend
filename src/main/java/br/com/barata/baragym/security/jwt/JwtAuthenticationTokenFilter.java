package br.com.barata.baragym.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* Filtro responsável por verificar o acesso a cada requisição, se existe no HEADER, um token válido */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

 @Autowired
 private UserDetailsService userDetailsService;

 @Autowired
 private JwtTokenUtil jwtTokenUtil;

 @Override
 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		 throws ServletException, IOException {
  String authToken = request.getHeader("Authorization");
  String sub = jwtTokenUtil.obtemSubPorToken(authToken);

  if (sub != null && SecurityContextHolder.getContext().getAuthentication() == null) { //Verifica se está autenticado

   UserDetails userDetails = this.userDetailsService.loadUserByUsername(sub); //Verifica se existe este usuario

   if (jwtTokenUtil.validaToken(authToken, userDetails)) { //Valida o token
	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
			userDetails, null, userDetails.getAuthorities());
	authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	logger.info("authenticated user" + sub + ", setting security context");
	SecurityContextHolder.getContext().setAuthentication(authentication);
   }

  }
  chain.doFilter(request, response);
 }

}
