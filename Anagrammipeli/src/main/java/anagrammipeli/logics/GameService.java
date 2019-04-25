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
            userDao.setOldPlayerSolvedList();
        } else {
            user = userDao.create(text);
        }

        return;
    }

    public String getWord() {
        return user.pickWordToSolve();
    }
    
    public boolean check(String guess){
        
        
        
        
        return false;
    }

    

}
