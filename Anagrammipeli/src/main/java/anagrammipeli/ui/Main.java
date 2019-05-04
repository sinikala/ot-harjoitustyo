package anagrammipeli.ui;

import anagrammipeli.logics.*;
import anagrammipeli.dao.*;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    GameService service;
    String currentWord;
    Pane picture;
    ImageView pic;

//    @Override
//    public void init() throws Exception {
//        
//    }
    @Override
    public void start(Stage window) throws Exception {
        GameService service = new GameService();

        // start layout base
        BorderPane startLayout = new BorderPane();
        startLayout.setPadding(new Insets(10));
        startLayout.setMinSize(400, 250);

        // 'old or new' components
        VBox oldOrNewBox = new VBox();
        oldOrNewBox.setPadding(new Insets(10));
        HBox oldOrNewButtonBox = new HBox();
        oldOrNewButtonBox.setPadding(new Insets(20));
        oldOrNewButtonBox.setSpacing(10);
        Label oldOrNewWelcome = new Label("Tervetuloa!");
        Button newGameButton = new Button("Uusi peli");
        Button oldGameButton = new Button("Jatka aiempaa peliä");

        oldOrNewButtonBox.getChildren().addAll(oldGameButton, newGameButton);
        oldOrNewBox.getChildren().addAll(oldOrNewWelcome, oldOrNewButtonBox);
        startLayout.setCenter(oldOrNewBox);

        // old player components
        VBox oldPlayerBox = new VBox();
        oldPlayerBox.setPadding(new Insets(20));
        oldPlayerBox.setSpacing(10);

        Label oldInstructions = new Label("Kirjoita nimimerkki, jolla pelasit\nviimeksi ja paina OK.");
        TextField oldNameField = new TextField();
        Button oldNameOk = new Button("OK");
        Button play = new Button("Pelaamaan!");
        Button backFromOld = new Button("Takaisin");

        oldPlayerBox.getChildren().addAll(oldInstructions, oldNameField, oldNameOk, backFromOld);

        //new player components
        VBox newPlayerBox = new VBox();
        newPlayerBox.setPadding(new Insets(20));
        newPlayerBox.setSpacing(10);

        Label newPlayerInstructions = new Label("Syötä haluamasi nimimerkki ja paina OK. ");
        Label nameAlreadyInUse = new Label(" ");
        TextField newUsername = new TextField();
        Button newUserOk = new Button("OK");
        Button backFromNew = new Button("Takaisin");

        newPlayerBox.getChildren().addAll(newPlayerInstructions, newUsername, newUserOk, backFromNew, nameAlreadyInUse);

        // game scene components
        BorderPane gameLayout = new BorderPane();
        gameLayout.setPadding(new Insets(10));
        VBox gameBox = new VBox();
        gameBox.setPadding(new Insets(40, 80, 40, 80));
        gameBox.setSpacing(10);

        Label gameInstructions = new Label("Tässä anagrammisi:");
        Label anagramm = new Label(" ");
        TextField userGuess = new TextField();
        HBox options = new HBox();
        options.setSpacing(10);
        Button checkButton = new Button("Tarkista");
        Button newWordButton = new Button("Uusi sana");
        Button scoresButton = new Button("Missä mennään?");

        options.getChildren().addAll(checkButton, newWordButton);
        Label feedback = new Label(" ");
        feedback.setMinSize(200, 50);

        gameBox.getChildren().addAll(gameInstructions, anagramm, userGuess, options, feedback);
        gameLayout.setTop(gameBox);
        gameLayout.setBottom(scoresButton);

        //picture element
        picture = new Pane();
        Image prints = new Image("file:prints.png");
        pic = new ImageView(prints);
        pic.setFitHeight(200);
        pic.setFitWidth(200);
        picture.getChildren().add(pic);
        //setPicture();

        //score components
        BorderPane scoreLayout = new BorderPane();
        scoreLayout.setPadding(new Insets(10));
        VBox scoreBox = new VBox();
        scoreBox.setPadding(new Insets(40, 80, 40, 80));
        scoreBox.setSpacing(10);

        Label scoreNow = new Label("Olet ratkaissut 0.0 % sanoista!");
        Button backToGame = new Button("Takaisin");
        scoreBox.getChildren().addAll(scoreNow, picture, backToGame);
        scoreLayout.setTop(scoreBox);

        //all done scene
        BorderPane doneLayout = new BorderPane();
        VBox doneBox = new VBox();
        doneBox.setPadding(new Insets(40, 80, 40, 80));
        Pane monstersPane = new Pane();
        Image monster = new Image("file:monster.png");
        ImageView monsterPic = new ImageView(monster);
        pic.setFitHeight(200);
        pic.setFitWidth(200);
        monstersPane.getChildren().add(monsterPic);
        Label congrats = new Label("Olet ratkaissut kaikki anagrammit! Hyvää työtä!");
        doneBox.getChildren().addAll(congrats, monstersPane);
        doneLayout.setTop(doneBox);

        Scene gameScene = new Scene(gameLayout);
        Scene welcomeScene = new Scene(startLayout);
        Scene allDone = new Scene(doneLayout);
        Scene scoreScene = new Scene(scoreLayout);

        oldGameButton.setOnAction((event) -> {
            startLayout.setCenter(oldPlayerBox);
        });

        newGameButton.setOnAction((event) -> {
            startLayout.setCenter(newPlayerBox);
        });

        oldNameOk.setOnAction((event) -> {
            try {
                String text = oldNameField.getText();
                if (service.getOldUser(text)) {
                    oldInstructions.setText("Tervetuloa takaisin " + oldNameField.getText() + "!");
                    oldPlayerBox.getChildren().removeAll(oldNameField, oldNameOk, backFromOld);
                    oldPlayerBox.getChildren().add(play);
                } else {
                    oldInstructions.setText("Kirjoititko nimen varmasti oikein?\n  ");
                    oldNameField.clear();
                    oldNameField.requestFocus();
                }
            } catch (Exception e) {
            }

        });

        play.setOnAction((event) -> {
            if (service.allDone()) {
                window.setScene(allDone);
            } else {
                window.setScene(gameScene);
                currentWord = service.getWord();
                anagramm.setText(currentWord);
            }
        });

        newUserOk.setOnAction((event) -> {
            try {
                String text = newUsername.getText();
                if (service.checkIfNameInUse(text)) {
                    nameAlreadyInUse.setText("Nimi on jo käytössä, kokeile jotain toista.");
                    newUsername.clear();
                } else {
                    service.createNewUser(text);
                    newPlayerBox.getChildren().removeAll(nameAlreadyInUse, newUsername, newUserOk, backFromNew);
                    newPlayerInstructions.setText("Tervetuloa pelaamaan " + text + "!");
                    newPlayerBox.getChildren().add(play);
                }

            } catch (Exception e) {

            }
        });
        
        backFromOld.setOnAction((event)-> {
            startLayout.setCenter(oldOrNewBox);
        });
        
         backFromNew.setOnAction((event)-> {
            startLayout.setCenter(oldOrNewBox);
        });

        userGuess.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    if (service.check(userGuess.getText())) {
                        service.setSolved();
                        feedback.setText("Oikein! " + service.getScore());
                        if (service.allDone()) {
                            window.setScene(allDone);
                        } else {
                            currentWord = service.getWord();
                            anagramm.setText(currentWord);
                        }
                    } else {
                        feedback.setText("Yritä uudelleen.");
                    }
                    userGuess.clear();
                    userGuess.requestFocus();
                } catch (Exception e) {
                }
            }
        });

        checkButton.setOnAction((event) -> {
            try {
                if (service.check(userGuess.getText())) {
                    service.setSolved();
                    feedback.setText("Oikein! " + service.getScore());
                    if (service.allDone()) {
                        window.setScene(allDone);
                    } else {
                        currentWord = service.getWord();
                        anagramm.setText(currentWord);
                    }
                } else {
                    feedback.setText("Yritä uudelleen.");
                }
                userGuess.clear();
                userGuess.requestFocus();
            } catch (Exception e) {
            }
        });

        newWordButton.setOnAction((event) -> {
            anagramm.setText(service.getWord());
            userGuess.clear();
        });

        scoresButton.setOnAction((event) -> {
            scoreNow.setText(service.getPercentage());
            Double percent = service.getPercentageforPicture();

            if (percent >= 33 && percent < 66) {
                picture.getChildren().remove(pic);
                Image egg = new Image("file:egg.jpeg");
                pic = new ImageView(egg);
                pic.setFitHeight(250);
                pic.setFitWidth(200);
                picture.getChildren().add(pic);
            } else if (percent >= 66 && percent < 100) {
                picture.getChildren().remove(pic);
                Image hatched = new Image("file:hatched.jpg");
                pic = new ImageView(hatched);
                pic.setFitHeight(200);
                pic.setFitWidth(250);
                picture.getChildren().add(pic);
            }

            window.setScene(scoreScene);

        });

        backToGame.setOnAction((event) -> {
            window.setScene(gameScene);
            feedback.setText(" ");
        });

        window.setScene(welcomeScene);
        window.setTitle("ANAGRAMMIPELI");
        window.show();
    }

    public static void main(String[] args) throws Exception {
        launch(Main.class);
    }

}
/*
picture.getChildren().remove(pic);
                Image monster = new Image("file:monster.png");
                pic = new ImageView(prints);
                pic.setFitHeight(200);
        pic.setFitWidth(200);
                picture.getChildren().add(pic);
 */
