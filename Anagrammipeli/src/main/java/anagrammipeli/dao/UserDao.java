package anagrammipeli.dao;

import java.sql.*;
import java.util.*;
import anagrammipeli.logics.User;
import anagrammipeli.logics.GameLibrary;

public class UserDao implements Dao {

    private int playerId;

    public UserDao() throws Exception {
    }

    public void setOldPlayerSolvedList(User user) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:playerDatabase.db");
        PreparedStatement st = connection.prepareStatement("SELECT * FROM solvedWords WHERE player_id = ?");
        st.setInt(1, playerId);

        ResultSet rs = st.executeQuery();

        int wordCounter = 0;
        while (rs.next()) {
            user.setPreviouslySolvedWord(rs.getInt("word_index"));
            wordCounter++;
        }

        st.close();
        rs.close();
        connection.close();
        user.setAmountOfSolved(wordCounter);
    }

    @Override
    public void addSolvedWord(User user) throws Exception {
        int solvedWordIndex = user.getWordIndex();

        Connection connection = DriverManager.getConnection("jdbc:sqlite:playerDatabase.db");
        PreparedStatement st = connection.prepareStatement("INSERT INTO solvedWords (player_id, word_index) VALUES (?, ?)");

        st.setInt(1, playerId);
        st.setInt(2, solvedWordIndex);
        st.executeUpdate();
        st.close();

        user.setSolved();
    }

    @Override
    public int countSolvedWords() throws Exception {
        return -1;
    }

    public boolean checkIfOldUser(String text) throws Exception {
        //tarkista löytyykö tietokannasta

        // !!! Useamman samannimisen estäminen!!! 
        Connection connection = DriverManager.getConnection("jdbc:sqlite:playerDatabase.db");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Player WHERE name = ?");
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
    public User getOldUser() throws Exception {
        // aseta vanhan pelaajan tiedot user-olioon
        Connection connection = DriverManager.getConnection("jdbc:sqlite:playerDatabase.db");
        PreparedStatement st = connection.prepareStatement("SELECT * FROM Player WHERE id = ?");
        st.setInt(1, playerId);

        ResultSet rs = st.executeQuery();

        User user = new User(rs.getString("name"), rs.getInt("id"));

        st.close();
        rs.close();
        connection.close();

        return user;
    }

    @Override
    public User create(String text) throws Exception {
        //luo tietokantaan uusi käyttäjä
        Connection connection = DriverManager.getConnection("jdbc:sqlite:playerDatabase.db");
        PreparedStatement st = connection.prepareStatement("INSERT INTO Player (name) VALUES (?)");

        st.setString(1, text);
        st.executeUpdate();
        st.close();

        st = connection.prepareStatement("SELECT id FROM Player WHERE name = ?");
        st.setString(1, text);

        ResultSet rs = st.executeQuery();

        User user = new User(text, rs.getInt("id"));
        playerId = rs.getInt("id");

        st.close();
        rs.close();
        connection.close();

        return user;
    }

}
