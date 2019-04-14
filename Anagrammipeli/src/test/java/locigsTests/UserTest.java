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

public class UserTest {
    User user;
    
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
        user = new User("Tes T. User ");
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


}
