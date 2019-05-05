package anagrammipeli.dao;

import java.sql.*;
import java.util.*;
import anagrammipeli.logics.User;
import anagrammipeli.logics.GameLibrary;

public class UserDao implements Dao {

    private int playerId;

    /**
     * Alustetaan UserDao-olio ja tarkistetaan löytyykö pelin toimintaan
     * tarvittava tietokanta, jos ei, kutsutaan metodia, joka luo sen.
     *
     * @throws Exception
     */
    public UserDao() throws Exception {
        if (checkDatabaseConnection() == false) {
            initializeDatabase();
        }
    }

    /**
     * Haetaan vanhan, peliä jatkavan käyttäjän tilastot tietokannasta, eli
     * mitkä sanat on jo ratkaistu ja tallenetaan tiedot pelaajaa edustavaan
     * User-luokan olioon muuttujiin.
     *
     * @param user nyt pelaava käyttäjä
     * @throws Exception
     */
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

    /**
     * Tallennetaan tietokantaan pelaajan ratkaiseman anagrammin indeksi ja
     * päivitetään ratkaistuksi User-olion sanalistaan.
     *
     * @param user nyt pelaava käyttäjä
     * @throws Exception
     */
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

    /**
     * Tarkistetaan löytyykö annattu käyttäjänimi jo tietokannasta
     *
     * @param text käyttäjän syöttämä nimi
     * @return true, jos annetulla nimellä löytyy käyttäjä tietokannasta, muuten
     * false
     * @throws Exception
     */
    @Override
    public boolean checkIfOldUser(String text) throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:playerDatabase.db");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Player WHERE name = ?");
        statement.setString(1, text);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
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

    /**
     * Haetaan vanhan käyttäjän tiedot tietokannasta ja asetetaan ne häntä
     * edustavan User-olion muuttujiin.
     *
     * @param name käyttäjänimi jonka tiedot haetaan
     * @return User-olio
     * @throws Exception
     */
    @Override
    public User getOldUser(String name) throws Exception {
        // aseta vanhan pelaajan tiedot user-olioon
        Connection connection = DriverManager.getConnection("jdbc:sqlite:playerDatabase.db");
        PreparedStatement st = connection.prepareStatement("SELECT * FROM Player WHERE name = ?");
        st.setString(1, name);

        ResultSet rs = st.executeQuery();

        User user = new User(rs.getString("name"), rs.getInt("id"));
        playerId = rs.getInt("id");

        st.close();
        rs.close();
        connection.close();

        return user;
    }

    /**
     * Lisätään tietokantaan uuden pelaajan tiedot ja luodaan häntä edustava
     * User-luokan olio.
     *
     * @param text pelajaan syöttämä käyttäjänimi
     * @return User-olio
     * @throws Exception
     */
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

    /**
     * Tarkistetaan, onko tietokanta playerDatabase olemassa ja toimiiko yhteys.
     *
     * @return true, jos tietokanta löytyy, muutoin false
     * @throws Exception
     */
    private boolean checkDatabaseConnection() throws Exception {
        boolean connectionWorks = false;
        Connection connection = DriverManager.getConnection("jdbc:sqlite:playerDatabase.db");

        ResultSet resultSet = connection.getMetaData().getCatalogs();
        if (resultSet.next()) {
            connectionWorks = true;
        }

        resultSet.close();
        connection.close();

        return connectionWorks;

    }

    /**
     * Alustetaan pelin toiminnan edellyttämä tietokanta ja tietokantataulut
     */
    private void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:playerDatabase.db", "sa", "")) {

            PreparedStatement statement = conn.prepareStatement("CREATE TABLE Player(id integer primary key autoincrement, name varchar(45))");
            statement.executeUpdate();
            statement.close();

            statement = conn.prepareStatement("CREATE TABLE solvedWords(player_id integer, word_index integer, foreign key (player_id) references Player(id))");
            statement.executeUpdate();
            statement.close();

            conn.close();
        } catch (SQLException ex) {
        }
    }

}
