package logicsTests;

import anagrammipeli.dao.Dao;
import anagrammipeli.logics.User;
import java.util.*;

public class FakeUserDao implements Dao {
    ArrayList<User> testUsers;

    public FakeUserDao() {
        testUsers = new ArrayList<>();
        testUsers.add(new User("Tes. T User", -1));
    }

    @Override
    public User create(String text) throws Exception {
        return null;
    }

    @Override
    public User getOldUser(String name) throws Exception {
        return null;
    }

    @Override
    public void addSolvedWord(User user) throws Exception {
    }

    @Override
    public boolean checkIfOldUser(String text) throws Exception {
        for( User user : testUsers){
            if(user.getName().equals("Tes. T User")){
                return true;
            }
        }
        return false;
    }

}
