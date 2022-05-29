package br.com.barata.baragym.security.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

 @PreAuthorize("@checkAccessService.validaMatricula(#matricula)")
 @Retention(RUNTIME)
 @Target(METHOD)
 public @interface ValidaMatricula {

 }


}

