package locigsTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import anagrammipeli.logics.GameLibrary;
import anagrammipeli.logics.User;
import java.util.*;

public class GameLibraryTest {

    GameLibrary library;

    public GameLibraryTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        library = new GameLibrary();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void wordListSizeEqualsScrambledWordListSize() {
        assertEquals(library.getWordListSize(), library.getScrambledWordListSize());
    }

    @Test
    public void wordAndScrambledWordHaveSameIndex() {
        for (int i = 0; i < library.getWordListSize(); i++) {
            char[] word = library.getWord(i).toCharArray();
            char[] scrambled = library.getScrambledWord(i).toCharArray();
            Arrays.sort(word);
            Arrays.sort(scrambled);

            assertTrue(Arrays.equals(word, scrambled));
        }
    }

    @Test
    public void wordListIsNotEmpty() {
        assertTrue(library.getWordListSize() != 0);
    }

    @Test
    public void scrambledWordListIsNotEmpty() {
        assertTrue(library.getScrambledWordListSize() != 0);
    }
}
