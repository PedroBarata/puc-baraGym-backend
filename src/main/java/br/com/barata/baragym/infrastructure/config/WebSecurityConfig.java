package br.com.barata.baragym.infrastructure.config;

import br.com.barata.baragym.security.enums.RoleEnum;
import br.com.barata.baragym.security.jwt.JwtAuthenticationEntryPoint;
import br.com.barata.baragym.security.jwt.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/* Habilita a configuração do security no projeto */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

 private static final String[] GROUP_ACTUATOR_HEALTH = {RoleEnum.ROLE_ADMIN.name()};

 @Autowired
 private JwtAuthenticationEntryPoint unauthorizedHandler;

 @Autowired
 private UserDetailsService userDetailsService;

 @Autowired
 public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
  authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
 }

 @Bean
 public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
  return new JwtAuthenticationTokenFilter();
 }

 @Bean
 @Override
 protected AuthenticationManager authenticationManager() throws Exception {
  return super.authenticationManager();
 }

 @Override
 protected void configure(HttpSecurity http) throws Exception {
  http.cors().and()
		  .authorizeRequests()
		  .antMatchers(HttpMethod.GET, "/actuator/health").hasAnyAuthority(GROUP_ACTUATOR_HEALTH)
		  .antMatchers(HttpMethod.GET, "/actuator/health/liveness").hasAnyAuthority(GROUP_ACTUATOR_HEALTH)
		  .antMatchers(HttpMethod.GET, "/actuator/health/readiness").hasAnyAuthority(GROUP_ACTUATOR_HEALTH)
		  .antMatchers(HttpMethod.GET, "/actuator/**").hasAnyAuthority(GROUP_ACTUATOR_HEALTH)
		  .antMatchers("/login/**").permitAll()
		  .antMatchers(HttpMethod.GET, "/public/**").permitAll() //Swagger

		  .anyRequest().authenticated()

		  .and()
		  .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

		  .and()
		  .headers()
		  .and()
		  .csrf().disable();

  http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
 }

}
