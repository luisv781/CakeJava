package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    Stage window;
    Scene launcher, game;

    AudioClip shopMusic = new AudioClip(Objects.requireNonNull(Main.class.getResource("../assets/sounds/music/shop.wav")).toString());
    AudioClip mainMusic = new AudioClip(Objects.requireNonNull(Main.class.getResource("../assets/sounds/music/main.wav")).toString());
    AudioClip oofSound = new AudioClip(Objects.requireNonNull(Main.class.getResource("../assets/sounds/misc/oof.mp3")).toString());

    public void openLauncher(Stage primaryStage) {

        window = primaryStage;

        //Top
        HBox topBox = new HBox();//30
        topBox.setStyle("-fx-background-color: #323232;");
        topBox.setPrefHeight(350);

        //Left Side Bar
        VBox sideBar = new VBox();
        //VBox.setMargin(sideBar, new Insets(0, 0, 0, 0));
        sideBar.setStyle("-fx-background-color: #414141;");
        sideBar.setPrefWidth(150);

        //Side Bar Buttons
        Button homeButton = new Button("Home");
        homeButton.prefHeight(25);
        homeButton.setPrefWidth(150);
        homeButton.setStyle("-fx-border-radius: 0;");
        Button settingsButton = new Button("Settings");
        settingsButton.prefHeight(25);
        settingsButton.setPrefWidth(150);
        settingsButton.setStyle("-fx-border-radius: 0;");

        sideBar.getChildren().addAll(homeButton, settingsButton);

        //Middle Screen
        StackPane middleFrame = new StackPane();
        middleFrame.setStyle("-fx-background-color: #323232;");

        //Home Screen
        //Thumbnail Image
        StackPane Home = new StackPane();
        Image ThumbImg = new Image("assets/images/PlayBckg.png");
        ImageView ThumbnailImage = new ImageView(ThumbImg);
        ThumbnailImage.setFitWidth(750);

        Home.getChildren().addAll(ThumbnailImage);
        Home.setVisible(true);

        //Settings Screen
        StackPane Settings = new StackPane();
        Settings.getChildren().addAll();
        Settings.setVisible(false);

        middleFrame.getChildren().addAll(Home);

        topBox.getChildren().addAll(sideBar, middleFrame);


        //Bottom
        HBox bottomBox = new HBox();//20
        bottomBox.setPadding(new Insets(15, 12, 15, 12));
        bottomBox.setStyle("-fx-background-color: #464646;");
        bottomBox.setMaxHeight(150);
        VBox.setVgrow(bottomBox, Priority.ALWAYS);
        //VBox.setMargin(bottomBox, new Insets(20, 20, 20, 20));

        //Title
        Label title = new Label("Cake Java Edition");
        title.setFont(new Font("Franklin Gothic Demi", 20));
        title.setTextFill(Color.rgb(200, 200, 200));
        HBox.setMargin(title, new Insets(5, 50, 0, 50));
        title.setAlignment(Pos.TOP_CENTER);

        //Play Button
        Button playButton = new Button();
        Image img = new Image("assets/images/playbtn.png");
        ImageView buttonImg = new ImageView(img);
        playButton.setGraphic(buttonImg);
        playButton.setPadding(Insets.EMPTY);
        playButton.setStyle("-fx-background-color: transparent;");
        playButton.setOnAction(e ->{
            AlertBox.display("Warning", "This game is not finished.", "Continue");
            window.setScene(game);
            //window.setFullScreen(true);
            //shopMusic.setCycleCount(9999);
            oofSound.play();
        });

        bottomBox.getChildren().addAll(title, playButton);

        //Exit Button
        /* Button exitButton = new Button();
        Image exitImg = new Image("assets/images/exitbtn.png");
        ImageView exitButtonImg = new ImageView(exitImg);
        exitButton.setGraphic(exitButtonImg);
        exitButton.setPadding(Insets.EMPTY);
        exitButton.setStyle("-fx-background-color: transparent;");
        exitButton.setOnAction(e -> closeProgram());*/

        //Launcher Scene
        VBox launcherRoot = new VBox(topBox, bottomBox);
        launcherRoot.prefHeightProperty().bind(window.heightProperty());
        VBox.setVgrow(launcherRoot, Priority.ALWAYS);
        //launcherRoot.getChildren().addAll(bottomBox);
        launcher = new Scene(launcherRoot, 850, 500);
        //launcher.getStylesheets().add(getClass().getClassLoader().getResource("sample/GameLauncher.css").toExternalForm());

        //Game Scene
        StackPane gameRoot = new StackPane();

        Image gameimg = new Image("assets/images/Image1.png");
        Image switcherimg = new Image("assets/images/switcher.png");
        ImageView gamebackimg = new ImageView(gameimg);
        Button switcher = new Button();
        switcher.setGraphic(new ImageView(switcherimg));
        switcher.setPadding(Insets.EMPTY);
        switcher.setStyle("-fx-background-color: transparent;");

        //Exit Button
        /*
        Button btn2 = new Button("Leave Game");
        btn2.setOnAction(e ->{
            closeGame();
            //player.stop();
        });*/

        gameRoot.getChildren().addAll(gamebackimg, switcher);
        game = new Scene(gameRoot, 1280, 720);

        //Preparing Stage
        window.setScene(launcher);
        window.setTitle("Caka Java Edition");
        window.getIcons().add(new Image("assets/images/GameIcon.png"));
        //window.setResizable(false);
        if (window.getScene() == game) {

        }
        //window.setOnCloseRequest(e -> e.consume() });

        window.show();

    }

    private void closeGame() {
        boolean answer = ConfirmBox.display("Exit", "Are you sure you want to exit?", "Yes", "No");

        if (answer) {
            InfoBox.display("Saving", "Saving data...");
            InfoBox.stopDisplay();
            window.setScene(launcher);
            window.setFullScreen(false);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception{
        openLauncher(primaryStage);
    }

}
