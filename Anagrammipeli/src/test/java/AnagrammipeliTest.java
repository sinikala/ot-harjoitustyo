/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import anagrammipeli.library.GameLibrary;
import anagrammipeli.library.User;
import java.util.*;

/**
 *
 * @author sinikala
 */
public class AnagrammipeliTest {
    GameLibrary library;
    User user;
    
    public AnagrammipeliTest() {
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
       user = new User("Tes T. User " ,5);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void wordListSizeEqualsScrambledWordListSize(){
        assertEquals(library.getWordListSize(), library.getScrambledWordListSize());
    }
    
    @Test
    public void wordAndScrambledWordHaveSameIndex(){
        char[] word = library.getWord(1).toCharArray();
        char[] scrambled = library.getScrambledWord(1).toCharArray();
        Arrays.sort(word);
        Arrays.sort(scrambled);
        
        assertTrue(Arrays.equals(word, scrambled));
        
    }
    
    @Test
    public void userStartsWithZeroSolvedWords(){
        assertEquals(0, user.getNumberOfSolvedWords());
    }
    
    @Test
    public void userStartsWithAllFalseInIsSolved(){
        assertFalse(user.checkIfSolved(0));
    }
}
