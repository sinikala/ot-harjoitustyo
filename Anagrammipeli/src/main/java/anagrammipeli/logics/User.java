package anagrammipeli.logics;

import anagrammipeli.logics.GameLibrary;
import java.util.Random;

public class User {

    private String name;
    private boolean[] isSolved;
    private int solved;
    GameLibrary library;
    int wordIndex;
    int playerIndex;

    public User(String name, int playerIndex) {
        this.library = new GameLibrary();
        this.name = name;
        this.solved = 0;
        this.isSolved = new boolean[library.getWordListSize()];
        this.wordIndex = 0;
        this.playerIndex = playerIndex;

    }

    public void setSolved() {
        isSolved[wordIndex] = true;
        solved++;
    }
    
    public void setPreviouslySolvedWords(int index){
        isSolved[index]=true;
    }

    public int getNumberOfSolvedWords() {
        return solved;
    }
    
    public int getWordIndex(){
        return wordIndex;
    }
    
    public void setSolved(int solvedBefore){
        solved += solvedBefore;
    }

    public boolean checkIfSolved(int idx) {
        return isSolved[idx];
    }

    public String getScore() {
        return "Olet nyt ratkaissut " + "\n" + solved + "/" + isSolved.length + " sanaa.";
    }

    public double getPercentage() {
        if (solved == 0) {
            return 0;
        }
        return (solved * 1.0) / isSolved.length * 100;
    }

    public String getName() {
        return name;
    }

    public boolean check(String guess) {
        return library.isCorrect(wordIndex, guess);
    }

    public String pickWordToSolve() {
        if (solved == isSolved.length) {
            return "X";
        }

        Random random = new Random();

        while (true) {
            wordIndex = random.nextInt(isSolved.length);
            if (isSolved[wordIndex] == false) {
                break;
            }
        }
        String word = library.getScrambledWord(wordIndex);
        return word;
    }

}
