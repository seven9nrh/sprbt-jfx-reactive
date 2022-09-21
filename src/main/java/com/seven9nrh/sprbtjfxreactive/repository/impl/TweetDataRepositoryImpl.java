package com.seven9nrh.sprbtjfxreactive.repository.impl;

import com.seven9nrh.sprbtjfxreactive.repository.SettingsRepository;
import com.seven9nrh.sprbtjfxreactive.repository.TweetDataRepository;
import com.seven9nrh.twitter.model.TwitterCredentials;
import com.seven9nrh.twitter.tweets.SearchTweetsApi;
import com.seven9nrh.twitter.tweets.TweetsLookupApi;
import com.seven9nrh.twitter.tweets.model.TweetData;
import java.util.List;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class TweetDataRepositoryImpl implements TweetDataRepository {

  private SearchTweetsApi searchTweetsApi;

  private SettingsRepository settingsRepository;

  private TweetsLookupApi tweetsLookupApi;

  public TweetDataRepositoryImpl(
    SearchTweetsApi searchTweetsApi,
    SettingsRepository settingsRepository,
    TweetsLookupApi tweetsLookupApi
  ) {
    this.searchTweetsApi = searchTweetsApi;
    this.settingsRepository = settingsRepository;
    this.tweetsLookupApi = tweetsLookupApi;
  }

  @Override
  public Flux<TweetData> tweetsSerchRecent(String query) {
    var apiKey = settingsRepository.getApiKey();
    var credentials = new TwitterCredentials(apiKey.getBearerToken());
    return searchTweetsApi.tweetsSearchRecentFlux(query, credentials);
  }

  @Override
  public TweetData tweet(String id) {
    var apiKey = settingsRepository.getApiKey();
    var credentials = new TwitterCredentials(apiKey.getBearerToken());
    return tweetsLookupApi.tweet(id, credentials);
  }

  @Override
  public List<TweetData> tweets(String[] ids) {
    var apiKey = settingsRepository.getApiKey();
    var credentials = new TwitterCredentials(apiKey.getBearerToken());
    return tweetsLookupApi.tweets(ids, credentials);
  }
}
