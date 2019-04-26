package anagrammipeli.logics;

import anagrammipeli.dao.*;
import anagrammipeli.ui.*;
import java.util.*;

public class GameService {

    private UserDao userDao;
    private User user;

    public GameService() throws Exception {
        this.userDao = new UserDao();
        this.user = null;
    }

    public void setUser(String text) throws Exception {
        if (userDao.checkIfOldUser(text)) {
            user = userDao.getOldUser(text);
            userDao.setOldPlayerSolvedList(user);
        } else {
            user = userDao.create(text);
        }

        return;
    }

    public String getWord() {
        return user.pickWordToSolve();
    }

    public boolean check(String guess) {
        return user.check(guess);
    }

    public boolean allDone() {
       if(user.pickWordToSolve().equals("X") ){
           return true;
       }
        return false;
    }

    public void setSolved() throws Exception{
        userDao.addSolvedWord(user);
       
    }

    public String getScore() {
        return user.getScore();
    }

    public String getPercentage() {
        double percentage = user.getPercentage();
        
        return "Olet ratkaissut " + percentage + " % sanoista!";
    }

}
