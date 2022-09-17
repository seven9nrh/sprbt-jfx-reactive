package com.seven9nrh.sprbtjfxreactive.repository.impl;

import com.seven9nrh.sprbtjfxreactive.repository.TweetDataRepository;
import com.seven9nrh.twitter.TwitterApiClient;
import com.seven9nrh.twitter.model.TweetData;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class TweetDataRepositoryImpl implements TweetDataRepository {

  private TwitterApiClient twitterApiClient;

  public TweetDataRepositoryImpl(TwitterApiClient twitterApiClient) {
    this.twitterApiClient = twitterApiClient;
  }

  public Flux<TweetData> tweetsSerchRecent(String query) {
    return twitterApiClient.tweetsSerchRecentFlux(query);
  }
}
