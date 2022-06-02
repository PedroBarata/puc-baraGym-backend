package br.com.barata.baragym.commons.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StringUtils {

 private static final String TIME_REGEX = "^([0-1]?\\d|2[0-3])(?::([0-5]?\\d))?$";

 @Autowired
 private PasswordEncoder passwordEncoder;

 public String encodePassword(String rawPassword) {
  return passwordEncoder.encode(rawPassword);
 }

 public static boolean validaHoraMinutoSegundo(String time) {
  return time.matches(TIME_REGEX);
 }
}
