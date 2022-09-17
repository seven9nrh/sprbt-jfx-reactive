package com.seven9nrh.sprbtjfxreactive.application;

import com.seven9nrh.sprbtjfxreactive.application.JFXApplication.StageReadyEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private ApplicationContext applicationContext;

  @Override
  public void onApplicationEvent(StageReadyEvent event) {
    Stage stage = event.getStage();
    ClassPathResource fxml = new ClassPathResource(
      "/views/SearchAnyTweet.fxml"
    );
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(fxml.getURL());
      fxmlLoader.setControllerFactory(this.applicationContext::getBean);
      Parent root = fxmlLoader.load();
      Scene scene = new Scene(root);
      event.getStage().setTitle("Search Any Tweet");
      Image logoTitle = new Image(
        getClass().getResourceAsStream("/assets/img/logo-title.png")
      );
      event.getStage().getIcons().add(logoTitle);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
      logger.error("fxml load failer", e);
    }
  }
}
