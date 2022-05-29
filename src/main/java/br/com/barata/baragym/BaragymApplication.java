package br.com.barata.baragym;

import br.com.barata.baragym.commons.utils.StringUtils;
import br.com.barata.baragym.entity.UsuarioEntity;
import br.com.barata.baragym.model.Usuario;
import br.com.barata.baragym.repository.UsuarioRepository;
import br.com.barata.baragym.security.enums.RoleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class BaragymApplication {

 public static void main(String[] args) {
  SpringApplication.run(BaragymApplication.class, args);
 }

 @Bean
 CommandLineRunner init(UsuarioRepository userRepository, PasswordEncoder encoder) {
  return args -> {
   initUsers(userRepository, encoder);
  };
 }

 private void initUsers(UsuarioRepository userRepository, PasswordEncoder encoder) {
  StringUtils stringUtils = new StringUtils();

  Optional<UsuarioEntity> optionalUsuarioEntity = userRepository.findByEmail("admin@helpdesk.com");
  if (optionalUsuarioEntity.isEmpty()) {
   userRepository.save(UsuarioEntity
		   .builder()
		   .nome("admin")
		   .email("admin@helpdesk.com")
		   .senha(encoder.encode("123456"))
		   .role(RoleEnum.ROLE_ADMIN.name())
		   .build());
  }
 }

}
