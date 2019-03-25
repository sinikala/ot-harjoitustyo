package com.mycompany.unicafe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;
    Maksukortti vajaaKortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(500);
        vajaaKortti = new Maksukortti(100);
    }

    @Test
    public void alussaSaldoOikein() {
        assertTrue(kassa.kassassaRahaa() == 100000);
    }

    @Test
    public void alussaMyydytLounaatNolla() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void edullinenLounasKasvattaaKassanSaldoa() {
        kassa.syoEdullisesti(240);
        assertTrue(kassa.kassassaRahaa() == 100240);
    }

    @Test
    public void maukasLounasKasvattaaKassanSaldoa() {
        kassa.syoMaukkaasti(400);
        assertTrue(kassa.kassassaRahaa() == 100400);
    }

    @Test
    public void syoEdullisestiPalauttaaOikeanVaihtorahan() {
        int vaihtoraha = kassa.syoEdullisesti(300);
        assertTrue(vaihtoraha == 60);
    }

    @Test
    public void syoMaukkaastiPalauttaaOikeanVaihtorahan() {
        int vaihtoraha = kassa.syoMaukkaasti(460);
        assertTrue(vaihtoraha == 60);
    }

    @Test
    public void syoEdullisestiKasvattaaMyytyjenLounaidenMaaraa() {
        kassa.syoEdullisesti(300);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
    }

    @Test
    public void syoMaukkaastiKasvattaaMyytyjenLounaidenMaaraa() {
        kassa.syoMaukkaasti(600);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }

    @Test
    public void riittamatonRahaEiKasvataKassanSaldoaEdullisissaLounaissa() {
        kassa.syoEdullisesti(140);
        assertTrue(kassa.kassassaRahaa() == 100000);
    }

    @Test
    public void riittamatonRahaEiKasvataKassanSaldoaMaukkkaissaLounaissa() {
        kassa.syoMaukkaasti(100);
        assertTrue(kassa.kassassaRahaa() == 100000);
    }

    @Test
    public void syoEdullisestiPalauttaaKokoRahanKunRahaEiRiita() {
        int vaihtoraha = kassa.syoEdullisesti(200);
        assertTrue(vaihtoraha == 200);
    }

    @Test
    public void syoMaukkaastiPalauttaaKokoRahanKunRahaEiRiita() {
        int vaihtoraha = kassa.syoMaukkaasti(60);
        assertTrue(vaihtoraha == 60);
    }

    @Test
    public void riittamatonRahaEiKasvataMyytyjenEdullistenMaaraa() {
        kassa.syoEdullisesti(200);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
    }

    @Test
    public void riittamatonRahaEiKasvataMyytyjenMaukkaidenMaaraa() {
        kassa.syoMaukkaasti(60);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void JosKortillaTarpeeksiRahaaVeloitetaanEdullisenHintaKortilta() {
        kassa.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 260);
    }

    @Test
    public void JosKortillaTarpeeksiRahaaSyoEdullisestiPalauttaaTrue() {

        assertTrue(kassa.syoEdullisesti(kortti));
    }

    @Test
    public void JosKortillaTarpeeksiRahaaVeloitetaanMaukkaanHintaKortilta() {

        kassa.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo() == 100);
    }

    @Test
    public void JosKortillaTarpeeksiRahaaSyoMaukkaastiPalauttaaTrue() {
        assertTrue(kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void JosKortillaTarpeeksiRahaaMyytyjenEdullistenMaaraKasvaa() {
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void JosKortillaTarpeeksiRahaaMyytyjenMaukkaidenMaaraKasvaa() {
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }
    

     @Test
    public void JosKortillaEiOleTarpeeksiRahaaEiVeloitetaEdullisenHintaaKortilta() {
        kassa.syoEdullisesti(vajaaKortti);
        assertTrue(vajaaKortti.saldo() == 100);
    }

    @Test
    public void JosKortillaEiOleTarpeeksiRahaaSyoEdullisestiPalauttaaFalse() {
        assertFalse(kassa.syoEdullisesti(vajaaKortti));
    }

    @Test
    public void JosKortillaEiOleTarpeeksiRahaaEiVeloitetaMaukkaanHintaaKortilta() {
        kassa.syoMaukkaasti(vajaaKortti);
        assertTrue(vajaaKortti.saldo() == 100);
    }

     @Test
    public void JosKortillaEiOleTarpeeksiRahaaSyoMaukkaastiPalauttaaFalse() {
        assertFalse(kassa.syoMaukkaasti(vajaaKortti));
    }
    
    @Test
    public void syoEdullisestiKortillaEiKasvataKassanSaldoa(){
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.kassassaRahaa()==100000);
    }

    @Test
    public void syoMaukkaastiKortillaEiKasvataKassanSaldoa(){
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.kassassaRahaa()==100000);
    }
    
    @Test
    public void LataaRahaaKortilleKasvattaaKortinSaldoa(){
        kassa.lataaRahaaKortille(kortti, 50);
        assertTrue(kortti.saldo()==550);
    }
    
    @Test
    public void LataaRahaaKortilleKasvattaaKassanSaldoa(){
        kassa.lataaRahaaKortille(kortti, 50);
        assertTrue(kassa.kassassaRahaa()==100050);
    }
    @Test
    public void LataaRahaaKortilleEiMuutaKortinSaldoaJosLadattavaArvoOnNegatiivinen(){
        kassa.lataaRahaaKortille(kortti, -50);
        assertTrue(kortti.saldo()==500);
    }
}
