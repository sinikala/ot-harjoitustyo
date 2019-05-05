package logicsTests;

import anagrammipeli.dao.UserDao;
import anagrammipeli.logics.GameLibrary;

import anagrammipeli.logics.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameServiceTest {

    GameService service;
    UserDao testDao;
    User user;
    GameLibrary library;
    private static final double DELTA = 0.01;

    public GameServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        service = new GameService();
        library = new GameLibrary();
        testDao = new UserDao();
        service.createNewUser("Tes T. User");
        user = new User("Tes T. User", -1);
    }

    @After
    public void tearDown() throws Exception {
        //testDao.removeTestUser("Tes T. User");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:playerDatabase.db");
        PreparedStatement st = connection.prepareStatement("SELECT id FROM Player WHERE name = ?");
        st.setString(1, "Tes T. User");

        ResultSet rs = st.executeQuery();
        int testUserId = rs.getInt("id");
        st.close();
        rs.close();

        st = connection.prepareStatement("DELETE FROM Player WHERE name = ?");
        st.setString(1, "Tes T. User");
        st.executeUpdate();
        st.close();

        st = connection.prepareStatement("DELETE FROM solvedWords WHERE player_id = ?");
        st.setInt(1, testUserId);
        st.executeUpdate();
        st.close();
        connection.close();
    }

    @Test
    public void GetOldServiceReturnsTrueForOldUser() throws Exception {
        assertEquals(true, service.getOldUser("Tes T. User"));
    }

    @Test
    public void getOldServiceReturnsFalseForNonExcistantUser() throws Exception {
        assertEquals(false, service.getOldUser("Ghost!#¤%"));
    }

    @Test
    public void checkIfNameInUseReturnsTrueCorrectly() throws Exception {
        assertEquals(true, service.checkIfNameInUse("Tes T. User"));
    }

    @Test
    public void checkIfNameInUseReturnsFalseCorrectly() throws Exception {
        assertEquals(false, service.checkIfNameInUse("Ghost&%¤#"));
    }

    @Test
    public void getWordReturnsAWord() {
        String wordReceived = service.getWord();
        assertTrue(wordReceived != null);
    }

    @Test
    public void checkReturnsFalseForScrambledWordItself() {
        String wordReceived = service.getWord();
        assertEquals(false, service.check(wordReceived));
    }

    @Test
    public void allDoneReturnsFalseWhenStarting() {
        assertEquals(false, service.allDone());
    }

    @Test
    public void getPercentageStartsFromZero() {
        assertEquals("Olet ratkaissut 0.0 % sanoista!", service.getPercentage());
    }

    @Test
    public void setSolvedAffectsPercentage() throws Exception {
        service.getWord();
        service.setSolved();
        assertNotEquals("Olet ratkaissut 0.0 % sanoista!", service.getPercentage());
    }

    @Test
    public void getScoreStartsFromZero() {
        assertEquals("Olet nyt ratkaissut " + "\n" + "0/" + library.getWordListSize() + " sanaa.", service.getScore());
    }

    @Test
    public void setSolvedAffectsScore() throws Exception {
        service.getWord();
        service.setSolved();
        assertNotEquals("Olet nyt ratkaissut " + "\n" + "0/" + library.getWordListSize() + " sanaa.", service.getScore());
    }

    @Test
    public void getpercentageForPictureStartsWithZero() {
        assertEquals(0.0, service.getPercentageforPicture(), DELTA);
    }

    @Test
    public void getPercentageForPictureRChangesWithSetSolved() throws Exception {
        service.getWord();
        service.setSolved();
        assertNotEquals(0.0, service.getPercentageforPicture(), DELTA);
    }

}
