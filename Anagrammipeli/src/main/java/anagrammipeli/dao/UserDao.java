package anagrammipeli.dao;

import java.sql.*;
import java.util.*;
import anagrammipeli.logics.User;
import anagrammipeli.logics.GameLibrary;

public class UserDao implements Dao {

    private User user;
    private int playerId;

    public UserDao() throws Exception {
    }
    
    public void setOldPlayerSolvedList() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:playerDatabase.db");
        PreparedStatement st = connection.prepareStatement("SELECT * FROM solvedWords WHERE player_id = ?");
        st.setInt(1, playerId);

        ResultSet rs = st.executeQuery();
        
        while(rs.next()){
            user.setPreviouslySolvedWords(rs.getInt("word_index"));
        }
        
        st.close();
        rs.close();
        connection.close();
    }

    @Override
    public void addSolvedWord(int wordIndex) throws Exception {

    }

    @Override
    public int countSolvedWords() throws Exception {
        return -1;
    }

    public boolean checkIfOldUser(String text) throws Exception {
        //tarkista löytyykö tietokannasta

        // !!! Useamman samannimisen estämäminen!!! 
        
        
        Connection connection = DriverManager.getConnection("jdbc:sqlite:playerDatabase.db");
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM Player WHERE name = ?");
        statement.setString(1, text);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            playerId = rs.getInt("id");
            statement.close();
            rs.close();
            connection.close();
            return true;
        }
        statement.close();
        rs.close();
        connection.close();
        return false;
    }

    @Override
    public User getOldUser(String username) throws Exception {
        // aseta vanhan pelaajan tiedot user-olioon
        Connection connection = DriverManager.getConnection("jdbc:sqlite:playerDatabase.db");
        PreparedStatement st = connection.prepareStatement("SELECT * FROM Player WHERE id = ?");
        st.setInt(1, playerId);

        ResultSet rs = st.executeQuery();
        rs.next();

        user = new User(rs.getString("name"), rs.getInt("id"));

        st.close();
        rs.close();
        connection.close();

        return user;
    }

    @Override
    public User create(String text) throws Exception {
        //luo tietokantaan uusi käyttäjä
        System.out.println("create");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:playerDatabase.db");
        PreparedStatement st = connection.prepareStatement("INSERT INTO Player (name) VALUES (?)");
        
        st.setString(1, text);
        st.executeUpdate();
        st.close();

        st = connection.prepareStatement("SELECT id FROM Player WHERE name = ?");
        st.setString(1, text);

        ResultSet rs = st.executeQuery();
        rs.next();

        user = new User(text, rs.getInt("id"));

        st.close();
        rs.close();
        connection.close();

        return user;
    }

}
