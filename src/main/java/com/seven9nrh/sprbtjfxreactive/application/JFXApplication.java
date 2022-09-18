package com.seven9nrh.sprbtjfxreactive.application;

import com.seven9nrh.sprbtjfxreactive.SprbtJfxReactiveApplication;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class JFXApplication extends Application {

  private Logger logger = LoggerFactory.getLogger(getClass());

  private ConfigurableApplicationContext applicationContext;

  @Override
  public void init() {
    logger.info("init");
    applicationContext =
      new SpringApplicationBuilder(SprbtJfxReactiveApplication.class).run();
  }

  @Override
  public void start(Stage arg0) throws Exception {
    applicationContext.publishEvent(new StageReadyEvent(arg0));
    // Exception Handler
    Thread
      .currentThread()
      .setUncaughtExceptionHandler(
        (t, e) -> {
          showErrorDialog(e);
        }
      );
  }

  protected void showErrorDialog(Throwable e) {
    Alert dialog = new Alert(Alert.AlertType.WARNING, e.getMessage());
    dialog.setTitle(e.getClass().getSimpleName());
    dialog.setHeaderText(null);
    dialog.showAndWait();
  }

  @Override
  public void stop() throws Exception {
    applicationContext.close();
  }

  static class StageReadyEvent extends ApplicationEvent {

    public StageReadyEvent(Stage stage) {
      super(stage);
    }

    public Stage getStage() {
      return ((Stage) getSource());
    }
  }
}
