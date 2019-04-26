package anagrammipeli.logics;

import anagrammipeli.logics.User;

import java.util.Random;

public class GameLibrary {

    private static final String[] WORDLIST = {
        "kissa", "mies", "palomies", "kattila"};

    private static final String[] SCRAMBLEDWORDS = {
        "saksi", "esim", "lompaies", "tilkata"};

    /**
     *Palauttaa sanan oikeiden vastausten listasta
     * @param idx   halutun sanan indeksi
     * @return  haluttu oikea vastaus
     */
    public String getWord(int idx) {
        return WORDLIST[idx];
    }

    /**
     *HAetaan anagrammi-listasta
     * @param idx   halutun angrammin indeksi
     * @return  haluttu anagrammi
     */
    public String getScrambledWord(int idx) {
        return SCRAMBLEDWORDS[idx];
    }

    /**
     *Tarkistetaan onko veikkaus oikein
     * @param idx   arvatun anagrammin indeksi
     * @param guess käyttäjän arvaus
     * @return true, jos anagrammin indeksillä oikeiden vastausten listasta 
     * löytyvä sana vastaa arvausta, muuten false
     */
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
