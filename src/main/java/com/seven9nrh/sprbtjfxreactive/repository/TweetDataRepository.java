package com.seven9nrh.sprbtjfxreactive.repository;

import com.seven9nrh.twitter.tweets.model.TweetData;
import java.util.List;
import reactor.core.publisher.Flux;

public interface TweetDataRepository {
  Flux<TweetData> tweetsSerchRecent(String query);

  TweetData tweet(String id);

  List<TweetData> tweets(String[] ids);
}
