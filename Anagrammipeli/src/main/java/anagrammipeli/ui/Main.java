package anagrammipeli.ui;

import anagrammipeli.logics.GameLibrary;
import anagrammipeli.logics.User;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    User user;

    @Override
    public void start(Stage window) throws Exception {
        BorderPane welcomeLayout = new BorderPane();
        VBox box = new VBox();
        box.setPadding(new Insets(40, 80, 40, 80));
        box.setSpacing(10);

        Label instructions = new Label("Kirjoita nimesi ja paina OK.");
        TextField nameField = new TextField();
        Button nameOk = new Button("OK");
        Label welcomeText = new Label(" ");
        Button play = new Button("Uusi peli");

        box.getChildren().addAll(instructions, nameField, nameOk, welcomeText);
        welcomeLayout.setTop(box);

        nameOk.setOnAction((event) -> {
            welcomeText.setText("Tervetuloa " + nameField.getText() + "!");
            user = new User(nameField.getText());
            box.getChildren().addAll(play);
        });

        BorderPane gameLayout = new BorderPane();
        VBox gameBox = new VBox();
        gameBox.setPadding(new Insets(40, 80, 40, 80));
        gameBox.setSpacing(10);
        Label gameInstructions = new Label("Tässä anagrammisi:");
        Label anagramm = new Label(" ");
        TextField guess = new TextField();
        Button check = new Button("Tarkista");
        Label feedback = new Label(" ");
        Button newWord = new Button("Uusi sana");

        gameBox.getChildren().addAll(gameInstructions, anagramm, guess, check, feedback);
        gameLayout.setTop(gameBox);

        BorderPane doneLayout = new BorderPane();
        VBox doneBox = new VBox();
        doneBox.setPadding(new Insets(40, 80, 40, 80));
        Label congrats = new Label("Olet ratkaissut kaikki anagrammit! Hyvää työtä!");
        doneBox.getChildren().addAll(congrats);
        doneLayout.setTop(doneBox);

        Scene gameScene = new Scene(gameLayout);
        Scene welcomeScene = new Scene(welcomeLayout);
        Scene allDone = new Scene(doneLayout);

        play.setOnAction((event) -> {
            window.setScene(gameScene);
            anagramm.setText(user.pickWordToSolve());
        });
        check.setOnAction((event) -> {
            if (user.check(guess.getText())) {
                feedback.setText("Oikein!");
                user.setSolved();
                if (user.pickWordToSolve().equals("X")) {
                    window.setScene(allDone);
                }
                anagramm.setText(user.pickWordToSolve());
                guess.clear();
            } else {
                feedback.setText("Yritä uudelleen.");
                guess.clear();
            }
        });

        window.setScene(welcomeScene);
        window.setTitle("ANAGRAMMIPELI");
        window.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }

}
