package logicsTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import anagrammipeli.logics.GameLibrary;
import anagrammipeli.logics.User;
import java.util.*;

public class UserTest {

    User user;
    GameLibrary library;
    private static final double DELTA = 0.01;

    public UserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        user = new User("Tes T. User", -1);
        library = new GameLibrary();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void userStartsWithZeroSolvedWords() {
        assertEquals(0, user.getNumberOfSolvedWords());
    }

    @Test
    public void userStartsWithAllFalseInIsSolved() {
        assertFalse(user.checkIfSolved(0));
    }

    @Test
    public void userStartsWithZeroPercentage() {
        assertEquals(0.0, user.getPercentage(), DELTA);
    }

    @Test
    public void startingScoreReporstZero() {
        assertEquals("Olet nyt ratkaissut " + "\n" + "0/" + library.getWordListSize() + " sanaa.", user.getScore());
    }

    @Test
    public void userNameIsCorrect() {
        assertEquals("Tes T. User", user.getName());
    }

    @Test
    public void setSolvedWorks() {
        user.setSolved();
        assertEquals(1, user.getNumberOfSolvedWords());
    }

    @Test
    public void solvingWordsRaisesPercentage() {
        double expected = 1.0 / library.getWordListSize() * 100;
        user.setSolved();
        assertEquals(expected, user.getPercentage(), DELTA);
    }

    @Test
    public void pickWordToSolveReturnsWord() {
        String stringReceived = null;
        stringReceived = user.pickWordToSolve();
        assertFalse(stringReceived == null);
    }

    @Test
    public void pickWordToSolveReturnsXWhenAllSolved() {
        user.setAmountOfSolved(library.getWordListSize());
        assertEquals("X", user.pickWordToSolve());

    }

    @Test
    public void userStartsWithWordIndexZero() {
        assertEquals(0, user.getWordIndex());
    }

    @Test
    public void setAmountOfSolvedChangesSolvedCounter() {
        user.setAmountOfSolved(5);
        assertFalse(user.getNumberOfSolvedWords() == 0);
    }

    @Test
    public void setPreviouslySolvedWordChangesWordToSolvedStatus() {
        user.setPreviouslySolvedWord(2);
        assertEquals(true, user.checkIfSolved(2));
    }

}
