package anagrammipeli.logics;

import anagrammipeli.logics.GameLibrary;
import java.util.Random;

/**
 * Pelin pelaajaa edustava luokka
 */
public class User {

    private GameLibrary library;
    private String name;
    private int playerId;
    private boolean[] isSolved;
    private int solved;
    private int currentWordIndex;

    /**
     *Alustetaan User-olio
     * @param name  pelaajan käyttäjänimi
     * @param playerId  tietokannan generoima pelaajan yksilöivä id-tunnus
     */
    public User(String name, int playerId) {
        this.library = new GameLibrary();
        this.playerId = playerId;
        this.name = name;
        this.solved = 0;
        this.isSolved = new boolean[library.getWordListSize()];
        this.currentWordIndex = 0;

    }

    /**
     * Asettaa nykyisessä indeksissä (wordIndex) olevan anagrammin ratkaistuksi
     * Kerryttää ratkaisujen lukumäärää yhdellä
     */
    public void setSolved() {
        isSolved[currentWordIndex] = true;
        solved++;
    }

    /**
     * Asettaa parametrinä annatussa indeksissä olevan anagrammin ratkaistuksi
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
        return currentWordIndex;
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
     * @return tilanteen kertova merkkijono
     */
    public String getScore() {
        return "Olet nyt ratkaissut " + "\n" + solved + "/" + isSolved.length + " sanaa.";
    }

    /**
     * Ilmoittaa prosentteina, kuinka suuri osa anagrammeista on ratkaistu
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
     * @param guess käyttäjän arvaus
     * @return true, jos arvaus on oikein, muuten false
     */
    public boolean check(String guess) {
        return library.isCorrect(currentWordIndex, guess);
    }

    /**
     * Valitsee uuden, kyseisen käyttäjän aiemmin ratkaisemattoman anagrammin
     * @return X, jos kaikki anagrammit on jo ratkaistu, muuten uusi anagrammi
     */
    public String pickWordToSolve() {
        if (solved == isSolved.length) {
            return "X";
        }

        Random random = new Random();

        while (true) {
            currentWordIndex = random.nextInt(isSolved.length);
            if (isSolved[currentWordIndex] == false) {
                break;
            }
        }
        String word = library.getScrambledWord(currentWordIndex);
        return word;
    }

    public String getName() {
        return name;
    }

    /**
     * tarkastetaan onko pelaaja ratkaissut jo annetussa indeksissä olevan anagrammin
     * @param idx kysytty indeksi
     * @return true, jos on ratkaistu, muuten false
     */
    public boolean checkIfSolved(int idx) {
        return isSolved[idx];
    }

}
