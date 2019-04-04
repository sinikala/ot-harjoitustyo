package anagrammipeli.library;

import anagrammipeli.library.GameLibrary;
import java.util.Random;

public class User {

    private String name;
    private boolean[] isSolved;
    private int solved;

    public User(String name, int solvableWords) {
        this.name = name;
        this.solved = 0;
        this.isSolved = new boolean[solvableWords];
    }

    public void setSolved(int idx) {
        isSolved[idx] = true;
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

    public int pickUnsolvedWordIndex() {
        if(solved==isSolved.length){return -1;}
        
        Random random = new Random();
        int index = -1;
        while (true) {
            index = random.nextInt(isSolved.length);
            if (isSolved[index] == false) {
                break;
            }
        }
        return index;
    }

}
