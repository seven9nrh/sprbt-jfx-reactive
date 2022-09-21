package com.seven9nrh.sprbtjfxreactive.application.views;

import com.seven9nrh.sprbtjfxreactive.repository.TweetDataRepository;
import com.seven9nrh.twitter.tweets.model.TweetData;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TweetFormController {

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private TweetDataRepository tweetDataRepository;

  @FXML
  private TextFlow txtDescription1;

  @FXML
  private Text txtAuthorId;

  @FXML
  private Text txtName;

  @FXML
  private Text txtAuthorId1;

  @FXML
  private Text txtQuoteCount1;

  @FXML
  private Text likeCount;

  @FXML
  private Text txtUserId1;

  @FXML
  private VBox vboxRoot;

  @FXML
  private Text likeCount1;

  @FXML
  private Text txtReplyCount;

  @FXML
  private Text txtRetweetCount;

  @FXML
  private TextFlow txtText;

  @FXML
  private TextFlow txtText1;

  @FXML
  private Button btnClose;

  @FXML
  private Text txtPossiblysensitive;

  @FXML
  private Text txtUserId;

  @FXML
  private Text txtCreateAt1;

  @FXML
  private TextFlow txtDescription;

  @FXML
  private Text txtId1;

  @FXML
  private Text txtRetweetCount1;

  @FXML
  private Text txtName1;

  @FXML
  private Text txtId;

  @FXML
  private Text txtReplyCount1;

  @FXML
  private Text txtUsername;

  @FXML
  private Text txtCreateAt;

  @FXML
  private Text txtUsername1;

  @FXML
  private Text txtPossiblysensitive1;

  @FXML
  private Text txtQuoteCount;

  @FXML
  protected void initialize() {}

  void initScreen(TweetData tweetData) {
    // stage show TweetForm.fxml
    FXMLLoader fxmlLoader = new FXMLLoader(
      getClass().getResource("/views/TweetForm.fxml")
    );
    fxmlLoader.setControllerFactory(this.applicationContext::getBean);
    Parent window = null;
    try {
      window = fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    var scene = new Scene(window);
    var stage = new Stage();
    stage.setTitle("Tweet");
    Image logoTitle = new Image(
      getClass().getResourceAsStream("/assets/img/logo-title.png")
    );
    stage.getIcons().add(logoTitle);
    stage.setScene(scene);
    stage.initModality(Modality.APPLICATION_MODAL);

    // set data
    txtId.setText(tweetData.getId());
    txtText.getChildren().add(new Text(tweetData.getText()));
    txtUsername.setText(tweetData.getUserData().getUsername());
    txtCreateAt.setText(String.valueOf(tweetData.getCreatedAt()));
    txtAuthorId.setText(tweetData.getAuthorId());
    txtPossiblysensitive.setText(
      String.valueOf(tweetData.getPossiblysensitive())
    );
    txtUserId.setText(tweetData.getUserData().getId());
    txtDescription
      .getChildren()
      .add(new Text(tweetData.getUserData().getDescription()));
    txtName.setText(tweetData.getUserData().getName());
    txtReplyCount.setText(String.valueOf(tweetData.getReplyCount()));
    txtRetweetCount.setText(String.valueOf(tweetData.getRetweetCount()));
    likeCount.setText(String.valueOf(tweetData.getLikeCount()));
    txtQuoteCount.setText(String.valueOf(tweetData.getQuoteCount()));

    TweetData tweetData2 = tweetDataRepository.tweet(tweetData.getId());
    txtId1.setText(tweetData2.getId());
    txtText1.getChildren().add(new Text(tweetData2.getText()));
    txtUsername1.setText(tweetData2.getUserData().getUsername());
    txtCreateAt1.setText(String.valueOf(tweetData2.getCreatedAt()));
    txtAuthorId1.setText(tweetData2.getAuthorId());
    txtPossiblysensitive1.setText(
      String.valueOf(tweetData2.getPossiblysensitive())
    );
    txtUserId1.setText(tweetData2.getUserData().getId());
    txtDescription1
      .getChildren()
      .add(new Text(tweetData2.getUserData().getDescription()));
    txtName1.setText(tweetData2.getUserData().getName());
    txtReplyCount1.setText(String.valueOf(tweetData2.getReplyCount()));
    txtRetweetCount1.setText(String.valueOf(tweetData2.getRetweetCount()));
    likeCount1.setText(String.valueOf(tweetData2.getLikeCount()));
    txtQuoteCount1.setText(String.valueOf(tweetData2.getQuoteCount()));

    stage.show();
  }

  @FXML
  void handleBtnClose(ActionEvent event) {
    Stage stage = (Stage) vboxRoot.getScene().getWindow();
    stage.close();
  }
}
