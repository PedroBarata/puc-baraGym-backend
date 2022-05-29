package br.com.barata.baragym.infrastructure.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticConfig implements WebMvcConfigurer {

 @Override
 public void addCorsMappings(CorsRegistry registry) {
  registry.addMapping("/**")
          .allowedMethods("GET", "POST", "PUT", "DELETE")
          .allowedOrigins("*")
          .allowedHeaders("*");
 }

 @Override
 public void addResourceHandlers(ResourceHandlerRegistry registry) {
  registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
 }

 @Override
 public void addViewControllers(ViewControllerRegistry registry) {
  registry.addViewController("/public").setViewName("redirect:/public/");
  registry.addViewController("/public/").setViewName("redirect:/public/index.html");
 }
}
