package br.com.barata.baragym.commons.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StringUtils {

 @Autowired
 private PasswordEncoder passwordEncoder;

 public String encodePassword(String rawPassword) {
  return passwordEncoder.encode(rawPassword);
 }
}
