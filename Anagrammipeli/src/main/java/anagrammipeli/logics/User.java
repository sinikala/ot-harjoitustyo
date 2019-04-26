package anagrammipeli.logics;

import anagrammipeli.logics.GameLibrary;
import java.util.Random;

/**
 * Pelin pelaajaa edustava luokka
 */
public class User {

    private String name;
    private boolean[] isSolved;
    private int solved;
    private GameLibrary library;
    private int wordIndex;
    private int playerId;

    /**
     *
     * @param name
     * @param playerId
     */
    public User(String name, int playerId) {
        this.library = new GameLibrary();
        this.name = name;
        this.solved = 0;
        this.isSolved = new boolean[library.getWordListSize()];
        this.wordIndex = 0;
        this.playerId = playerId;

    }

    /**
     * Asettaa nykyisessä indeksissä (wordIndex) olevan anagrammin ratkaistuksi
     * Kerryttää ratkaisujen lukumäärää yhdellä
     */
    public void setSolved() {
        isSolved[wordIndex] = true;
        solved++;
    }

    /**
     * Asettaa parametrinä annatussa indeksissä olevan anagrammin ratkaistuksi
     *
     * @param index
     */
    public void setPreviouslySolvedWord(int index) {
        isSolved[index] = true;
    }

    public int getNumberOfSolvedWords() {
        return solved;
    }

    /**
     * @return Nykyisen arvattavana olevan anagrammin indeksi
     */
    public int getWordIndex() {
        return wordIndex;
    }

    /**
     * Asetetaan aiemmin ratkaistujen lukmäärä ratkaistuihin
     *
     * @param solvedBefore tietokannasta löydettyjen aiemmin ratkaistujen
     * anagrammien lukumäärä
     */
    public void setAmountOfSolved(int solvedBefore) {
        solved = solvedBefore;
    }

    /**
     * Ilmoittaa montako anagrammia maksimista on ratkaistu
     *
     * @return tilanteen kertova merkkijono
     */
    public String getScore() {
        return "Olet nyt ratkaissut " + "\n" + solved + "/" + isSolved.length + " sanaa.";
    }

    /**
     * Ilmoittaa prosentteina, kuinka suuri osa anagrammeista on ratkaistu
     *
     * @return prosentuaalinen tilanne merkkijonona
     */
    public double getPercentage() {
        if (solved == 0) {
            return 0;
        }
        return (solved * 1.0) / isSolved.length * 100;

    }

    /**
     * Tarkistaa, onko käyttäjän arvaus oikein
     *
     * @param guess käyttäjän arvaus
     * @return true, jos arvaus on oikein, muuten false
     */
    public boolean check(String guess) {
        return library.isCorrect(wordIndex, guess);
    }

    /**
     * Valitsee uuden, kyseisen käyttäjän aiemmin ratkaisemattoman anagrammin
     *
     * @return X, jos kaikki anagrammit on jo ratkaistu, muuten uuden anagrammin
     */
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

    public String getName() {
        return name;
    }

    /**
     * tarkastetaan onko ratkaissut jo annetussa indeksissä olevan anagrammin
     * @param idx kysytty indeksi
     * @return true, jos on ratkaistu, muuten false
     */
    public boolean checkIfSolved(int idx) {
        return isSolved[idx];
    }

}
