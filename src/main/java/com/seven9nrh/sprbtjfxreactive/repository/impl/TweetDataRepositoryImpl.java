package com.seven9nrh.sprbtjfxreactive.repository.impl;

import com.seven9nrh.sprbtjfxreactive.model.ApiKey;
import com.seven9nrh.sprbtjfxreactive.repository.SettingsRepository;
import com.seven9nrh.sprbtjfxreactive.repository.TweetDataRepository;
import com.seven9nrh.twitter.TwitterApiClient;
import com.seven9nrh.twitter.model.TweetData;
import com.seven9nrh.twitter.model.TwitterCredentials;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class TweetDataRepositoryImpl implements TweetDataRepository {

  private TwitterApiClient twitterApiClient;

  private SettingsRepository settingsRepository;

  public TweetDataRepositoryImpl(
    TwitterApiClient twitterApiClient,
    SettingsRepository settingsRepository
  ) {
    this.twitterApiClient = twitterApiClient;
    this.settingsRepository = settingsRepository;
  }

  @Override
  public Flux<TweetData> tweetsSerchRecent(String query) {
    var apiKey = settingsRepository.getApiKey();
    var credentials = new TwitterCredentials(apiKey.getBearerToken());
    return twitterApiClient.tweetsSerchRecentFlux(query, credentials);
  }
}
