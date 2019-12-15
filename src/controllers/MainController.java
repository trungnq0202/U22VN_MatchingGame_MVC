package controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Sound;

public class MainController {
    @FXML private Label gameScore;
    @FXML private GridPane mainView;
    @FXML private Label gameLevel;
    @FXML private ImageView soundImage;
    @FXML private Button soundBtn;
    @FXML private CountdownTimerController countdownTimerController;
    @FXML private PlayerCardListController playerCardListController;

    private Sound backgroundMusic;
    private Sound btnSound;
    private boolean enableSound;
    private Image soundOnImg;
    private Image soundOffImg;
    private Background bgSoundOnImg;
    private Background bgSoundOffImg;

    @FXML public void initialize(){
        System.out.println("Maincontroller.initialize////////////");
        injectChildrenControllers();
        createSoundEnableButton();
        getGameLevel();
        playerCardListController.createNewCardListView();

        Button btnNewGame = (Button)mainView.lookup("#btnNewGame");
        btnNewGame.setOnMouseClicked((MouseEvent e) -> {
            getGameLevel();
            playerCardListController.createNewCardListView();
        });

    }

    public MainController(){
        System.out.println("Maincontroller.constructor");
        enableSound = true;
        soundOnImg = new Image("file:src/resources/images/sound_on.jpg");
        soundOffImg = new Image("file:src/resources/images/sound_off.jpg");
        backgroundMusic = new Sound("BACKGROUND_MUSIC");
        btnSound = new Sound("BUTTON_SOUND");
    }

    public void injectChildrenControllers(){
        countdownTimerController.injectMainController(this);
        playerCardListController.injectMainController(this);
    }

    public void createSoundEnableButton(){
        bgSoundOnImg = new Background(new BackgroundImage(soundOnImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(soundBtn.getWidth(), soundBtn.getHeight(), true, true, true, false)));
        bgSoundOffImg = new Background(new BackgroundImage(soundOffImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(soundBtn.getWidth(), soundBtn.getHeight(), true, true, true, false)));
        soundBtn.setBackground(bgSoundOnImg);
    }

    public boolean getEnableSound(){
        return enableSound;
    }

    public void musicBtnHandle(MouseEvent mouseEvent) {
        btnSound.makeSound();
        if (enableSound){
            enableSound = false;
            backgroundMusic.pauseBackgroundMusic();
            soundBtn.setBackground(bgSoundOffImg);
        }else{
            enableSound = true;
            backgroundMusic.resumeBackgroundMusic();
            soundBtn.setBackground(bgSoundOnImg);
        }
    }

    public void setGameLevel(int gameLevel){
        this.gameLevel.setText(Integer.toString(gameLevel));
    }

    public void getGameLevel(){
        Stage startWindow = new Stage();
        startWindow.initModality(Modality.APPLICATION_MODAL);
        startWindow.setTitle("U22 Vietnam The Matching");
        Label levelChoosingMessage = new Label("Please choose the game level");
        Label levelLabel = new Label("Level: ");
        HBox levelChoosingHbox = new HBox(10);
        levelChoosingHbox.setAlignment(Pos.CENTER);
        ComboBox<Integer> levelBox = new ComboBox<Integer>();
        levelBox.getItems().addAll(1,2,3);
        levelBox.setValue(1);
        levelChoosingHbox.getChildren().addAll(levelLabel, levelBox);
        Button finishBtn = new Button("Done!");
        finishBtn.setOnAction(e -> {
            setGameLevel(levelBox.getValue());
            startWindow.close();
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(levelChoosingMessage ,levelChoosingHbox, finishBtn);
        layout.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout, 300, 250);
        startWindow.setScene(scene1);
        startWindow.showAndWait();
    }

//    public void setGameScore(int score){
//        int currentScore = gam
//    }
}
