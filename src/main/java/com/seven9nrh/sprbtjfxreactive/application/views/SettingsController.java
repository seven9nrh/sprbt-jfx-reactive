package com.seven9nrh.sprbtjfxreactive.application.views;

import com.seven9nrh.sprbtjfxreactive.repository.SettingsRepository;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SettingsController {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private SettingsRepository settingsRepository;

  @FXML
  private Button btnCancel;

  @FXML
  private Button btnOk;

  @FXML
  private TextField textApiKey;

  @FXML
  private GridPane paneRoot;

  @FXML
  protected void handleBtnCancel(ActionEvent event) {
    ((Node) (event.getSource())).getScene().getWindow().hide();
  }

  @FXML
  protected void handleBtnOk(ActionEvent event) throws IOException {
    settingsRepository.saveApiKey(textApiKey.getText());
    ((Node) (event.getSource())).getScene().getWindow().hide();
  }

  @FXML
  protected void initialize() {
    logger.info("initialize");
    textApiKey.setText(settingsRepository.getApiKey());
  }
}
