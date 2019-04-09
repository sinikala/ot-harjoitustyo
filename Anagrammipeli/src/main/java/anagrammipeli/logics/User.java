package anagrammipeli.logics;

import anagrammipeli.logics.GameLibrary;
import java.util.Random;

public class User {

    private String name;
    private boolean[] isSolved;
    private int solved;
    GameLibrary library;
    int index;

    public User(String name) {
        this.library = new GameLibrary();
        this.name = name;
        this.solved = 0;
        this.isSolved = new boolean[library.getWordListSize()];
    }

    public void setSolved() {
        isSolved[index] = true;
        solved++;
    }

    public int getNumberOfSolvedWords() {
        return solved;
    }

    public boolean checkIfSolved(int idx) {
        return isSolved[idx];
    }

    public String getScore() {
        return "Olet nyt ratkaissut " + solved + "/" + isSolved.length + "sanaa.";
    }

    public String getName() {
        return name;
    }
    
    public boolean check(String guess){
        return library.isCorrect(index, guess);
    }

    public String pickWordToSolve() {
        if(solved==isSolved.length){return "X";}
        
        Random random = new Random();
        index = -1;
        while (true) {
            index = random.nextInt(isSolved.length);
            if (isSolved[index] == false) {
                break;
            }
        }
        String word = library.getScrambledWord(index);
        return word;
    }

}
