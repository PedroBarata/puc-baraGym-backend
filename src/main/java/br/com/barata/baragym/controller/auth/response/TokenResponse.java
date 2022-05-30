package br.com.barata.baragym.controller.auth.response;

import lombok.Getter;

@Getter
public class TokenResponse {

 private String accessToken;

 public TokenResponse(String accessToken) {
  this.accessToken = "Bearer " + accessToken;
 }

}
