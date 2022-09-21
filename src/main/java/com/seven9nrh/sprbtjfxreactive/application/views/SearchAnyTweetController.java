package com.seven9nrh.sprbtjfxreactive.application.views;

import com.seven9nrh.sprbtjfxreactive.repository.TweetDataRepository;
import com.seven9nrh.twitter.tweets.model.TweetData;
import java.io.IOException;
import java.time.LocalDateTime;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

@Component
public class SearchAnyTweetController {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  TweetDataRepository tweetDataRepository;

  @Autowired
  TweetFormController tweetFormController;

  @FXML
  private Button btnSearch;

  @FXML
  private Button btnClear;

  @FXML
  private TableView<TweetData> lstMain;

  @FXML
  private TextField txtQuery;

  @FXML
  private TableColumn<TweetData, String> colId;

  @FXML
  private TableColumn<TweetData, String> colText;

  @FXML
  private TableColumn<TweetData, String> colUsername;

  @FXML
  private TableColumn<TweetData, LocalDateTime> createdAt;

  @FXML
  private TableColumn<TweetData, String> authorId;

  @FXML
  private TableColumn<TweetData, Boolean> possiblysensitive;

  @FXML
  private TableColumn<TweetData, Integer> retweetCount;

  @FXML
  private TableColumn<TweetData, Integer> replyCount;

  @FXML
  private TableColumn<TweetData, Integer> likeCount;

  @FXML
  private TableColumn<TweetData, Integer> quoteCount;

  @FXML
  private MenuItem menuSettings;

  @FXML
  private MenuItem menuExit;

  @FXML
  private MenuItem menuCopy;

  @Autowired
  private ApplicationContext applicationContext;

  @FXML
  protected void handleBtnSearch(ActionEvent event) {
    lstMain.getItems().clear();

    tweetDataRepository
      .tweetsSerchRecent(txtQuery.getText())
      .subscribeOn(Schedulers.newSingle("sub"))
      .subscribe(
        tweetData -> {
          // UI thread
          Platform.runLater(
            () -> {
              lstMain.getItems().add(tweetData);
            }
          );
        }, // onNext
        error -> {
          // UI thread
          Platform.runLater(
            () -> {
              logger.error("Error: " + error.getMessage());
            }
          );
        }, // onError
        () -> {
          // UI thread
          Platform.runLater(
            () -> {
              lstMain.scrollTo(lstMain.getItems().size() - 1);
            }
          );
        } // onComplete
      );
  }

  @FXML
  protected void handleBtnClear(ActionEvent event) {
    lstMain.getItems().clear();
    txtQuery.setText("");
  }

  @FXML
  protected void initialize() {
    colId.setVisible(false);
    authorId.setVisible(false);
    possiblysensitive.setVisible(false);

    colId.setCellValueFactory(new PropertyValueFactory<>("id"));
    colText.setCellValueFactory(new PropertyValueFactory<>("text"));
    colUsername.setCellValueFactory(
      celldata ->
        new SimpleStringProperty(
          celldata.getValue().getUserData().getUsername()
        )
    );
    createdAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
    authorId.setCellValueFactory(new PropertyValueFactory<>("authorId"));
    possiblysensitive.setCellValueFactory(
      new PropertyValueFactory<>("possiblySensitive")
    );
    retweetCount.setCellValueFactory(
      new PropertyValueFactory<>("retweetCount")
    );
    replyCount.setCellValueFactory(new PropertyValueFactory<>("replyCount"));
    likeCount.setCellValueFactory(new PropertyValueFactory<>("likeCount"));
    quoteCount.setCellValueFactory(new PropertyValueFactory<>("quoteCount"));

    menuSettings.setAccelerator(
      javafx.scene.input.KeyCombination.keyCombination("Ctrl+,")
    );
    menuExit.setAccelerator(
      javafx.scene.input.KeyCombination.keyCombination("Ctrl+Q")
    );
    menuCopy.setAccelerator(
      javafx.scene.input.KeyCombination.keyCombination("Ctrl+C")
    );
  }

  @FXML
  protected void handleMenuSettings(ActionEvent event) throws IOException {
    ClassPathResource fxml = new ClassPathResource("views/Settings.fxml");
    var fxmlLoader = new FXMLLoader(fxml.getURL());
    fxmlLoader.setControllerFactory(this.applicationContext::getBean);
    Parent window = fxmlLoader.load();
    var scene = new Scene(window);
    var stage = new Stage();

    stage.setTitle("Settings");
    Image logoTitle = new Image(
      getClass().getResourceAsStream("/assets/img/logo-title.png")
    );
    stage.getIcons().add(logoTitle);
    stage.setScene(scene);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.show();
  }

  @FXML
  protected void handleMenuExit(ActionEvent event) {
    System.exit(0);
  }

  @FXML
  protected void handleMenuCopy(ActionEvent event) {
    var tweetData = lstMain.getSelectionModel().getSelectedItem();
    if (tweetData != null) {
      var clipboard = javafx.scene.input.Clipboard.getSystemClipboard();
      var content = new javafx.scene.input.ClipboardContent();
      content.putString(tweetData.getText());
      clipboard.setContent(content);
    }
  }

  @FXML
  void handleSelectedRow(MouseEvent event) {
    // double click
    if (event.getClickCount() == 2) {
      var tweetData = lstMain.getSelectionModel().getSelectedItem();
      if (tweetData != null) {
        tweetFormController.initScreen(tweetData);
      } else {
        throw new IllegalArgumentException("tweetData is null");
      }
    }
  }
}
