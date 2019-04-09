package anagrammipeli.logics;
import anagrammipeli.logics.User;

import java.util.Random;

public class GameLibrary {

    private static final String[] wordList = {
        "kissa", "mies", "palomies", "kattila"};
    
    private static final String [] scrambledWords = {
        "saksi", "esim", "lompaies", "tilkata"};
    
    public String getWord(int idx){
        return wordList[idx];
    }
    
    public String getScrambledWord(int idx){
        return scrambledWords[idx];
    }
    
    public boolean isCorrect(int idx, String guess){
        return guess.equals(getWord(idx));
    }
    
    public int getWordListSize(){
        return wordList.length;
    }
    public int getScrambledWordListSize(){
        return scrambledWords.length;
    }

}
