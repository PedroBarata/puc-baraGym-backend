package br.com.barata.baragym.security.jwt;

import br.com.barata.baragym.entity.UsuarioEntity;
import br.com.barata.baragym.security.enums.RoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/* Classe para converter o usuario (entity) em um usuario reconhecido pelo spring security */

@Component
public class JwtUserFactory {

	public static JwtUser create(UsuarioEntity usuarioEntity) { //Gera um JWTUser com base nos dados de um usuario
		return new JwtUser(
				usuarioEntity.getEmail(),
				usuarioEntity.getMatricula(),
				usuarioEntity.getSenha(),
				mapToGrantedAuthorities(RoleEnum.valueOf(usuarioEntity.getRole()))
				);
	}
	private static List<GrantedAuthority> mapToGrantedAuthorities(RoleEnum roleEnum) { //Converte o perfil do usuário para o formato utilizado no Spring Security
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(roleEnum.toString()));
		return authorities;
	}
}
