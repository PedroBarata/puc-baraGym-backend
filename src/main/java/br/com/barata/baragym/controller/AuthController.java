package br.com.barata.baragym.controller;

import br.com.barata.baragym.controller.request.JwtAuthenticationRequest;
import br.com.barata.baragym.model.Usuario;
import br.com.barata.baragym.security.jwt.JwtTokenUtil;
import br.com.barata.baragym.security.model.UsuarioLogado;
import br.com.barata.baragym.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping(value="/api/auth") //No webSecurityConfig autorizamos o acesso a esse endpoint, para solicitar o token para fazer as proximas requisições
	public UsuarioLogado geraToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authenticationRequest.getEmail(), 
						authenticationRequest.getPassword()
						)
				);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		String token = jwtTokenUtil.generateToken(userDetails);

		Usuario usuario = usuarioService.buscarPorEmail(authenticationRequest.getEmail());
		return new UsuarioLogado(token, usuario);
	}
	
	/*@PostMapping(value="/api/refresh") //Dá um refresh no token
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		String username = jwtTokenUtil.getUsernameFromToken(token);
		final User user = userService.findByEmail(username);
		
		if(jwtTokenUtil.canTokenBeRefreshed(token)) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new CurrentUser(refreshedToken, user));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}*/
	
}
