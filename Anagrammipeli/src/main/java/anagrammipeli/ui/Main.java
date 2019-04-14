package anagrammipeli.ui;

import anagrammipeli.logics.GameLibrary;
import anagrammipeli.logics.User;
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

    User user;
    double percentage;

    @Override
    public void start(Stage window) throws Exception {
        BorderPane welcomeLayout = new BorderPane();
        VBox box = new VBox();
        box.setPadding(new Insets(50, 100, 50, 100));
        box.setSpacing(10);

        Label instructions = new Label("Kirjoita nimesi ja paina OK.");
        TextField nameField = new TextField();
        Button nameOk = new Button("OK");
        Button play = new Button("Pelaamaan!");

        box.getChildren().addAll(instructions, nameField, nameOk);
        welcomeLayout.setTop(box);

        nameOk.setOnAction((event) -> {
            instructions.setText("Tervetuloa " + nameField.getText() + "!");
            user = new User(nameField.getText());
            box.getChildren().removeAll(nameField, nameOk);
            box.getChildren().addAll(play);
            
        });

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
        Button check = new Button("Tarkista");
        Button newWord = new Button("Uusi sana");
        Button scores = new Button("Missä mennään?");
        options.getChildren().addAll(check, newWord);
        Label feedback = new Label(" ");
        feedback.setMinSize(200, 50);

        gameBox.getChildren().addAll(gameInstructions, anagramm, userGuess, options, feedback);
        gameLayout.setTop(gameBox);
        gameLayout.setBottom(scores);

        BorderPane doneLayout = new BorderPane();
        VBox doneBox = new VBox();
        doneBox.setPadding(new Insets(40, 80, 40, 80));
        Label congrats = new Label("Olet ratkaissut kaikki anagrammit! Hyvää työtä!");
        doneBox.getChildren().addAll(congrats);
        doneLayout.setTop(doneBox);



        BorderPane scoreLayout = new BorderPane();
        scoreLayout.setPadding(new Insets(10));
        VBox scoreBox = new VBox();
        scoreBox.setPadding(new Insets(40, 80, 40, 80));
        scoreBox.setSpacing(10);
        Label scoreNow = new Label("Olet ratkaissut 0.0 % sanoista!");
        Button backToGame = new Button("Takaisin");
//        Image thumbsUp = new Image("thumbsup.png");
//        ImageView imageView = new ImageView();
//        imageView.setImage(thumbsUp);
        scoreBox.getChildren().addAll(scoreNow, backToGame);
        scoreLayout.setTop(scoreBox);

        Scene gameScene = new Scene(gameLayout);
        Scene welcomeScene = new Scene(welcomeLayout);
        Scene allDone = new Scene(doneLayout);
        Scene scoreScene = new Scene(scoreLayout);

        play.setOnAction((event) -> {
            window.setScene(gameScene);
            anagramm.setText(user.pickWordToSolve());
        });

        //Miten poistaa toisteisuus??
//        userGuess.setOnKeyPressed((event) -> {
//            if(event.getCode() == KeyCode.ENTER){
//                if (user.check(userGuess.getText())) {
//                user.setSolved();
//                feedback.setText("Oikein! " + user.getScore());
//                if (user.pickWordToSolve().equals("X")) {
//                    window.setScene(allDone);
//                }
//                anagramm.setText(user.pickWordToSolve());
//            } else {
//                feedback.setText("Yritä uudelleen.");
//               
//            }
//            userGuess.clear();
//            userGuess.requestFocus();
//            }
//        });
        check.setOnAction((event) -> {
            if (user.check(userGuess.getText())) {
                user.setSolved();
                feedback.setText("Oikein! " + user.getScore());
                if (user.pickWordToSolve().equals("X")) {
                    window.setScene(allDone);
                }
                anagramm.setText(user.pickWordToSolve());
            } else {
                feedback.setText("Yritä uudelleen.");

            }
            userGuess.clear();
            userGuess.requestFocus();
        });
        newWord.setOnAction((event) -> {
            anagramm.setText(user.pickWordToSolve());
            userGuess.clear();
        });

        scores.setOnAction((event) -> {
            setPercentage(scoreNow);
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

    public static void main(String[] args) {
        launch(Main.class);
    }

    private void setPercentage(Label scoreNow) {
        double percentage = user.getPercentage();
        scoreNow.setText("Olet ratkaissut " + percentage + " % sanoista!");
    }

}
