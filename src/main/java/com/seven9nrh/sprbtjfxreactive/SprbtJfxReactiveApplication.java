package com.seven9nrh.sprbtjfxreactive;

import com.seven9nrh.sprbtjfxreactive.application.JFXApplication;
import com.seven9nrh.twitter.TwitterApiClientConfiguration;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(TwitterApiClientConfiguration.class)
public class SprbtJfxReactiveApplication {

  public static void main(String[] args) {
    Application.launch(JFXApplication.class, args);
  }
}
