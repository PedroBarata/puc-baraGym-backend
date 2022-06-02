package br.com.barata.baragym.commons.annotations;


import br.com.barata.baragym.commons.utils.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimeValidator implements ConstraintValidator<TimeValidation, String> {

 @Override
 public void initialize(TimeValidation contactNumber) {
 }

 @Override
 public boolean isValid(String timeField,
                        ConstraintValidatorContext cxt) {
  return timeField != null && !timeField.isEmpty() && StringUtils.validaHoraMinutoSegundo(timeField);
 }
}
