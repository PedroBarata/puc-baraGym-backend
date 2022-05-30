package br.com.barata.baragym.controller.auth;

import br.com.barata.baragym.controller.auth.request.AuthRequest;
import br.com.barata.baragym.controller.auth.response.TokenResponse;
import br.com.barata.baragym.model.Usuario;
import br.com.barata.baragym.security.annotation.CheckSecurity;
import br.com.barata.baragym.security.exception.TokenNaoPodeSerAtualizadoException;
import br.com.barata.baragym.security.jwt.JwtTokenUtil;
import br.com.barata.baragym.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

@RestController
public class AuthController {

 @Autowired
 private AuthenticationManager authenticationManager;

 @Autowired
 private JwtTokenUtil jwtTokenUtil;

 @Autowired
 private UsuarioService usuarioService;

 @PostMapping(value = "/login")
 public TokenResponse geraToken(@RequestBody AuthRequest authenticationRequest) throws AuthenticationException {

  Authentication authentication = authenticationManager.authenticate(
		  new UsernamePasswordAuthenticationToken(
				  authenticationRequest.getEmail(),
				  authenticationRequest.getSenha()
		  )
  );

  SecurityContextHolder.getContext().setAuthentication(authentication);
  Usuario usuario = usuarioService.buscarPorEmail(authenticationRequest.getEmail());
  String token = jwtTokenUtil.geraToken(usuario);

  return new TokenResponse(token);
 }

 @PostMapping(value = "/refresh")
 public TokenResponse atualizaToken(HttpServletRequest request) {
  String token = request.getHeader("Authorization");

  if (jwtTokenUtil.podeSerAtualizado(token)) {
   String refreshToken = jwtTokenUtil.refreshToken(token);
   return new TokenResponse(refreshToken);
  }
  throw new TokenNaoPodeSerAtualizadoException();
 }

 @GetMapping(value = "{matricula}/teste")
 @CheckSecurity.ValidaMatricula
 public void teste(@PathVariable("matricula") @NotBlank String matricula) {
 }

}
