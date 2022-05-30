package br.com.barata.baragym;

import br.com.barata.baragym.entity.UsuarioEntity;
import br.com.barata.baragym.repository.UsuarioRepository;
import br.com.barata.baragym.security.enums.RoleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class BaragymApplication {

 public static void main(String[] args) {
  SpringApplication.run(BaragymApplication.class, args);
 }

 @Bean
 CommandLineRunner init(UsuarioRepository userRepository) {
  return args -> {
   initUsers(userRepository);
  };
 }

 @Bean
 BCryptPasswordEncoder bCryptPasswordEncoder() {
  return new BCryptPasswordEncoder();
 }

 private void initUsers(UsuarioRepository userRepository) {

  Optional<UsuarioEntity> optionalUsuarioEntity = userRepository.findByEmail("admin@teste.com");

  if (optionalUsuarioEntity.isEmpty()) {
   userRepository.save(UsuarioEntity
		   .builder()
		   .nome("admin")
		   .email("admin@teste.com")
		   .senha(bCryptPasswordEncoder().encode("123456"))
		   .role(RoleEnum.ROLE_ADMIN.name())
		   .build());
  }
 }

}
