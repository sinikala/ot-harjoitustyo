package anagrammipeli.logics;

import anagrammipeli.dao.*;
import anagrammipeli.ui.*;
import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Sovelluslogiikasta vastaava luokka
 */
public class GameService {

    private UserDao userDao;
    private User user;

    /**
     *
     * @throws Exception
     */
    public GameService() throws Exception {
        this.userDao = new UserDao();
        this.user = null;
    }

    /**
     * Tarkistaa kutsumalla userDaon metodeja, onko pelin käynnistäjä vanha vai
     * uusi käyttäjä ja tuloksen mukaan joko kutsuu uuden käyttäjän luovaa tai
     * vanhan käyttäjän tiedot hakevia metodeja.
     *
     * @param text
     * @throws Exception
     * @see anagrammipeli.dao.UserDao#checkIfOldUser(String)
     * @see anagrammipeli.dao.UserDao#getOldUser(String)
     * @see anagrammipeli.dao.UserDao#setOldPlayerSolvedList(User)
     * @see anagrammipeli.dao.UserDao#create(String)
     */
    public void getOldUser(String name) throws Exception {
        if (userDao.checkIfOldUser(name)) {
            user = userDao.getOldUser(name);
            userDao.setOldPlayerSolvedList(user);
        } else {
            return;
        }
    }

    public void createNewUser(String name) throws Exception {
        user = userDao.create(name);
    }

    public boolean checkIfNameInUse(String name) throws Exception {
        return userDao.checkIfOldUser(name);
    }

    /**
     * Ratkaistavan anagrammin hakeminen
     *
     * @return ratkaistava anagrammi
     */
    public String getWord() {
        return user.pickWordToSolve();
    }

    /**
     * Käyttäjän arvauksen tarkistus
     *
     * @param guess käyttäjän arvaus
     * @return true, jos arvaus oli oikein, muuten false
     */
    public boolean check(String guess) {
        return user.check(guess);
    }

    /**
     * Tarkistetaan, onko ratkaistavia anagrammeja vielä jäljellä
     *
     * @return true, jos kaikki anagrammit on ratkaistu, muuten false
     * @see anagrammipeli.logics.User#pickWordToSolve()
     */
    public boolean allDone() {
        if (user.pickWordToSolve().equals("X")) {
            return true;
        }
        return false;
    }

    /**
     * Merkitään okein arvattu anagrammi ratkaistuksi
     *
     * @throws Exception
     */
    public void setSolved() throws Exception {
        userDao.addSolvedWord(user);

    }

    /**
     * montako anagrammia on tähän mennessä ratkaistu
     *
     * @return tämän hetkisen tilanteen kertova merkkijono
     */
    public String getScore() {
        return user.getScore();
    }

    /**
     * montako prosenttia anagrammeista on ratkaistu
     *
     * @return tämän hetkisen prosenttitilanteen kertova merkkijono
     */
    public String getPercentage() {
        double percentage = user.getPercentage();

        return "Olet ratkaissut " + percentage + " % sanoista!";
    }

    public Double getPercentageforPicture() {
        if (user == null) {
            return 0.0;
        } else {
            return user.getPercentage();
        }
    }

}
