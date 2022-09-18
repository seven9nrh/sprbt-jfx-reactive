package com.seven9nrh.sprbtjfxreactive.repository.impl;

import com.seven9nrh.sprbtjfxreactive.model.ApiKey;
import com.seven9nrh.sprbtjfxreactive.repository.SettingsRepository;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class SettingsRepositoryImpl implements SettingsRepository {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Value("${sprbtjfxreactive.settings.file}")
  private String settingsFilePath;

  private static final String PROP_API_KEY_BEARER_TOKEN = "apiKey.bearerToken";

  @Override
  public ApiKey getApiKey() {
    Properties properties = new Properties();
    try (
      FileInputStream fileInputStream = new FileInputStream(settingsFilePath);
    ) {
      properties.load(fileInputStream);
      var apiKey = new ApiKey(
        properties.getProperty(PROP_API_KEY_BEARER_TOKEN)
      );
      return apiKey;
    } catch (IOException e) {
      logger.warn("Properties load failure.", e);
    }
    return new ApiKey.NoApiKey();
  }

  @Override
  public void saveApiKey(ApiKey apiKey) {
    Properties properties = new Properties();
    try (InputStream inputStream = new FileInputStream(settingsFilePath);) {
      properties.load(inputStream);
    } catch (IOException e) {
      logger.warn("Properties load failure.", e);
    }
    properties.setProperty(PROP_API_KEY_BEARER_TOKEN, apiKey.getBearerToken());
    try (
      FileOutputStream fileOutputStream = new FileOutputStream(
        settingsFilePath
      );
    ) {
      properties.store(fileOutputStream, null);
    } catch (IOException e) {
      logger.warn("Properties store failure.", e);
    }
  }
}
