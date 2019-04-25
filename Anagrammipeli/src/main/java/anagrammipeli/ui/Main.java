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

    User user;
    double percentage;
    GameService service;
    String currentWord;
   

//    @Override
//    public void init() throws Exception {
//        
//    }

    @Override
    public void start(Stage window) throws Exception {
        GameService service = new GameService();
        User user = null;
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
        scoreBox.getChildren().addAll(scoreNow, backToGame);
        scoreLayout.setTop(scoreBox);

        Scene gameScene = new Scene(gameLayout);
        Scene welcomeScene = new Scene(welcomeLayout);
        Scene allDone = new Scene(doneLayout);
        Scene scoreScene = new Scene(scoreLayout);
        
        nameOk.setOnAction((event) -> {
            try {
                String text = nameField.getText();
                service.setUser(text);    
                instructions.setText("Tervetuloa " + nameField.getText() + "!");
                box.getChildren().removeAll(nameField, nameOk);
                box.getChildren().add(play);
            } catch (Exception e) {
            }

        });

        play.setOnAction((event) -> {
            window.setScene(gameScene);
            currentWord =service.getWord();
            anagramm.setText(currentWord);
        });

//        //Miten poistaa toisteisuus??
////        userGuess.setOnKeyPressed((event) -> {
////            if(event.getCode() == KeyCode.ENTER){
////                if (user.check(userGuess.getText())) {
////                user.setSolved();
////                feedback.setText("Oikein! " + user.getScore());
////                if (user.pickWordToSolve().equals("X")) {
////                    window.setScene(allDone);
////                }
////                anagramm.setText(user.pickWordToSolve());
////            } else {
////                feedback.setText("Yritä uudelleen.");
////               
////            }
////            userGuess.clear();
////            userGuess.requestFocus();
////            }
////        });

        check.setOnAction((event) -> {
//            if (user.check(userGuess.getText())) {
//                user.setSolved();
//                feedback.setText("Oikein! " + user.getScore());
//                if (user.pickWordToSolve().equals("X")) {
//                    window.setScene(allDone);
//                }
//                anagramm.setText(user.pickWordToSolve());
            if (service.check(userGuess.getText())){
            } else {
                feedback.setText("Yritä uudelleen.");

            }
            userGuess.clear();
            userGuess.requestFocus();
        });
        
//        newWord.setOnAction((event) -> {
//            anagramm.setText(user.pickWordToSolve());
//            userGuess.clear();
//        });
//
//        scores.setOnAction((event) -> {
//            setPercentage(scoreNow);
//            window.setScene(scoreScene);
//        });
//
//        backToGame.setOnAction((event) -> {
//            window.setScene(gameScene);
//            feedback.setText(" ");
//        });

        window.setScene(welcomeScene);
        window.setTitle("ANAGRAMMIPELI");
        window.show();
    }

    public static void main(String[] args) throws Exception {
        launch(Main.class);
    }

//    private void setPercentage(Label scoreNow) {
//        double percentage = user.getPercentage();
//        scoreNow.setText("Olet ratkaissut " + percentage + " % sanoista!");
//    }

}
