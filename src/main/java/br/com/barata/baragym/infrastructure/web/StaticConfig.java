package br.com.barata.baragym.infrastructure.web;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class StaticConfig implements WebMvcConfigurer {

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
