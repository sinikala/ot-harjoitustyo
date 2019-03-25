package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoOikeinAlussa() {
        assertTrue(kortti.saldo()==10);      
    }
    
    @Test
    public void kortinLataaminenToimii() {
        kortti.lataaRahaa(200);
        assertTrue(kortti.saldo()==210);      
    }
    
    @Test
    public void saldoVaheneeOikeinRahaaOtettaessa() {
        kortti.otaRahaa(5);
        assertTrue(kortti.saldo()==5);      
    }
    
     @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(15);
        assertTrue(kortti.saldo()==10);      
    }
    
     @Test
    public void otaRahaaPalauttaaTrueJosSaldoRiittaa() {
        assertTrue(kortti.otaRahaa(5));      
    }
    
    @Test
    public void otaRahaaPalauttaaFalseJosSaldoEiRiita() {
        assertFalse(kortti.otaRahaa(15));      
    }
}
