package br.com.barata.baragym.commons.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TimeValidator.class)
public @interface TimeValidation {
 //error message
 public String message() default "Hora inválida. Deve ser no formato HH:mm.";

 //represents group of constraints
 public Class<?>[] groups() default {};

 //represents additional information about annotation
 public Class<? extends Payload>[] payload() default {};
}