package com.seven9nrh.sprbtjfxreactive.repository;

public interface SettingsRepository {
  public String getApiKey();

  public void saveApiKey(String apiKey);
}
