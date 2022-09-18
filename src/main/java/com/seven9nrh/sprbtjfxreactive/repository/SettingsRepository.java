package com.seven9nrh.sprbtjfxreactive.repository;

import com.seven9nrh.sprbtjfxreactive.model.ApiKey;

public interface SettingsRepository {
  public ApiKey getApiKey();

  public void saveApiKey(ApiKey apiKey);
}
