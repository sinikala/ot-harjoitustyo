package anagrammipeli.ui;

import anagrammipeli.library.GameLibrary;
import anagrammipeli.library.User;
import java.util.*;

public class Main {

    GameLibrary library;
    int wordIdx;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameLibrary library = new GameLibrary();

        System.out.println("");
        System.out.println("Hei! Kuka pelaa!");
        String name = scanner.nextLine();

        User test = new User(name, library.getWordListSize());
        System.out.println("Mukava nähdä, " + test.getName());

        System.out.println("");
        System.out.println("Tervetuloa Anagrammipeliin!");
        System.out.println("   ********************");
        System.out.println("");
        System.out.println("Kun haluat lopettaa, syötä x");

        String guess = "";
        int wordIdx = test.pickUnsolvedWordIndex();

        while (!guess.equals("x")) {
            if(wordIdx==-1){
                System.out.println("Tässä kaikki tältä erää.");
                break;
            }
            System.out.println("Tässä anagrammisi:");
            System.out.println(library.getScrambledWord(wordIdx));
            guess = scanner.nextLine();
            
            if (library.isCorrect(wordIdx, guess)) {
                System.out.println("**Oikein!**");
                test.setSolved(wordIdx);
                wordIdx=test.pickUnsolvedWordIndex();
            } else {
                System.out.println("Väärin, yritä uudelleen.");
                System.out.println(library.getScrambledWord(wordIdx));
            }
        }
        System.out.println("");
        System.out.println(test.getScore());
        System.out.println("Kiitos pelistä!");
    }

}
