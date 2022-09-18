package com.seven9nrh.sprbtjfxreactive.model;

import org.apache.commons.lang3.Validate;

public class ApiKey {

  private String bearerToken;

  public ApiKey(String bearerToken) {
    Validate.notBlank(bearerToken, "bearerToekn is blank");
    Validate.isTrue(bearerToken.length() > 64, "bearerToken is too short");
    this.bearerToken = bearerToken;
  }

  public String getBearerToken() {
    return bearerToken;
  }

  public static class NoApiKey extends ApiKey {

    public NoApiKey() {
      super("0".repeat(100));
    }
  }
}
