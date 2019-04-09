package anagrammipeli.logics;

import anagrammipeli.logics.User;

import java.util.Random;

public class GameLibrary {

    private static final String[] WORDLIST = {
        "kissa", "mies", "palomies", "kattila"};

    private static final String[] SCRAMBLEDWORDS = {
        "saksi", "esim", "lompaies", "tilkata"};

    public String getWord(int idx) {
        return WORDLIST[idx];
    }

    public String getScrambledWord(int idx) {
        return SCRAMBLEDWORDS[idx];
    }

    public boolean isCorrect(int idx, String guess) {
        return guess.equals(getWord(idx));
    }

    public int getWordListSize() {
        return WORDLIST.length;
    }

    public int getScrambledWordListSize() {
        return SCRAMBLEDWORDS.length;
    }

}
