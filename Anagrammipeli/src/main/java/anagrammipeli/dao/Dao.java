package anagrammipeli.dao;

import anagrammipeli.logics.*;
import java.util.*;
import java.sql.*;

public interface Dao {

    User create(String text) throws Exception;

    User getOldUser(String name) throws Exception;

    void addSolvedWord(User user) throws Exception;

    boolean checkIfOldUser(String text) throws Exception;
}
