# Arkkitehtuurikuvaus

## Rakenne

Sovelluksen arkkitehtuuri on kolmikerroksinen ja koodin pakkausrakenne on seuraava:

![Pakkausrakenne](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Pakettirakenne.png)

**Pakkausten vastuualueet:**
* Anagrammipeli.ui - Käyttöliittymä (JavaFx)
* Anagrammipeli.logics - Sovelluslogiikka
* Anagrammipeli.dao -Tietojen pysyväistalletus

## Käyttöliittymä
Käyttöliittymä on toteuttettu kokonaan luokassa anagrammipeli.ui.Main. Muita sovelluksen toiminnallisuuksia Main-luokka  pyytää kutsumalla GameService-luokan metodeja. GameService huolehtii pääosin sovelluslogiikasta. Käyttöliittymä muokkaa itseään tapahtumakäsittelijöiden aktivoitumisen ja GameService-oliolta saamiensa palautusarvojen pohjalta. Tälläisia muokkauksia ovat esimerkiksi näkymästä toiseen siirtyminen ja arvauksen tarkistaminen.

Käyttöliittymä sisältää kuusi kappaletta yksi kerrallaan näkyviä, neljään eri Scene-olioon pohjautuvaa näkymää:
* aloitusvalikko ja valinnan mukaan sitä seuraa joko _Aloita uusi peli_ tai _Jatka vanhaa peliä_ -alinäkymä
* pelinäkymä
* tilastonäkymä
* loppunäkymä


## Sovelluslogiikka
Sovelluksen keskiössä ovat luokat _User_, joka kuvaa käyttäjää eli pelin pelaajaa ja tämän etenemistä pelissä sekä luokka  GameLibrary, johon on tallennettu pelin sanavarasto ja, joka toteuttaa lopullisen arvausten tarkastamisen.

Luokka GameService vastaa pelin toiminnallisuudesta ja toimii käyttöliittymän ja pelitietojen välikätenä.
GameService tarjoaa metodin kaikkiin käyttöliittymän tarpeisiin. Muutama keskeinen esimerkki:
* setUser(String name)
* String getWord()
* boolean check(String guess)
* void setSolved()

GameService pääsee käsiksi käyttäjiin joko Dao-rajapintaa toteuttavan UserDao-luokan kautta tai myöhemmin, kun kyseistä pelisessiota varten on luotu User-luokan olio, suoraan sitä kutsuvilla metodeilla. Tietojen pysyväistallennuksen ja noutamisen GameService toteuttaa aina UserDaon kautta.


Anagrammipeli-sovellusta kuvaava luokka/pakkauskaavio:

![Pakkauskaavio](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/luokkakaavio.png)

## Tietojen pysyväistalletus
anagrammipeli.dao-paketin luokka UserDao huolehtii tietojen tallentamisesta tietokantaan ja niiden noutamisesta. Rakenne mukailee Data Access Object -suunnittelumallia.

### Tallenettavat tiedot
Sovellus tallentaa tietoja sovelluksen luomaan *playerDatabase*-tietokantaan. Tietokanta koostuu kahdesta tietokantataulusta: *Player* ja *solvedWords*.

![Relaatiokaavio](http://yuml.me/a6146445.jpg)

Tauluun *Player* tallennetaan pelaajan (ts. käyttäjän) nimi ja hänelle luodaan automaattisesti id-numero. *SolvedWords* listaa mitkä anagrammit pelaaja on jo ratkaissut.


### Päätoiminnallisuudet
Kuvataan seuraavaksi sekvenssikaaviona sovelluksen kahden päätoiminnallisuuden toimintalogiikka.

#### vanhan pelaajan tietojen haku
Tässä tilanteessa pelaaja on valinnut jatkavansa aiempaa peliä ja syöttänyt tekstikenttään oikean käyttäjänimen.

![VanhanpelaajanValmistelu](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/vanhanPelaajanValmistelu.png)

Ensin UI pyytä GameServiceä tarkastamaan, että tälläinen pelaaja todella löytyy tietokannasta, parametrinaan pelaajan antama nimimerkki. UserDao huolehtii tietokannasta tarkistamisen. Koska UserDao löysi pelaajan, GameService kutsuu nyt pelaaja-oliota valmistelevia metodeja. UserDao luo pelaajaa vastaaavan User-luokan olion ja sen jälkeen tarkistaa tietokannasta _solvedWords_-tietokantataulusta mitkä sanat kyseinen pelaaja on mahdollisesti aiemmin ratkaissut ja tieto sijoitetaan User-olion muuttujiin. Lopuksi GameService palauttaa UI:lle _true_ onnistuneen vanhan pelaajan valmistelun merkiksi ja UI toivottaa käyttäjän tervetulleeksi.

Uuden pelaajan luominen noudattaa hyvin pitkälti samaa logiikkaa, pois lukien aiempien ratkaisujen hakeminen. Sen sijaan luonnin yhteydessä tarkistetaan tietokannasta, ettei haluttu uusi käyttäjänimi ole jo käytössä.

#### arvauksen tarkistaminen
Kun käyttäjä on syöttänyt arvauskenttään veikkauksen ja klikkaa "Tarkista"-painiketta.

![Tarkistus](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/tarkistus.png)

Painikkeen painamiseen reagoiva tapahtumakäsittelijä kutsuu palveluluokkaa GameService, joka aloittaa arvauksen käsittelyn kutsumalla User-luokan metodia _check_ parametrinaan pelaajan arvaus. Pelaajaan liittyvä User-luokan olio pitää kirjaa nyt ratkaistavana olevan sanan indeksistä ja liittää sen veikkauksen mukana parametriksi kutsuessaan GameLibrary-luokan metodia _isCorrect_. GameLibrary vertaa onko sen sanalistassa annetussa indeksissä sama merkkijono kuin pelaajan arvauksessa. Tässä tapauksessa veikkaus on oikein, joten GameLibrary palauttaa arvon true. User puolestaan välittää true-arvon GameServiselle. Nyt Nyt GameService kutsuu UserDaon metodia _setSolved_, joka huolehtii sanan merkitsemisestä ratkaistuksi. UserDao tallentaa ratkaistun sanan indeksin tietokannan tauluun solvedWords ja kutsuu myös User-luokan metodia _setSolved_, jolloin myös käyttäjää kuvastavalle luokalle tallentuu tieto siitä, että ko. sana on ratkaistu. Näiden tapahtumien jälkeen vuoro päätyy takaisin tapahtumakäsittelijälle, joka päivittää käyttöliittymää näyttämällä pelaajalle palautteen "Oikein!" Seuraavaksi UI pyytäisi GameServiceltä uutta anagrammia.
(Kaaviosta on lopusta jätetty pois varsinaisessa sovelluksessa palautteeseen liittyvän ratkaistujen sanojen lukumäärän noutaminen selkeyden takia)


## Ohjelman kehityskohdat

### käyttöliittymä
Graafinen käyttäliittymä on toteutettu täysin yhdessä luokassa ja sisältää paljon toisteisuutta. Näkymien luontia ja asettelua olisi hyvä yhdenmukaistaa sekä eritellä omiin luokkinsa tai metodeihinsa selkeyden vuoksi.

### DAO
UserDao-luokan toteutuksessa on hiottavaa parempien DAO-käytänteiden suuntaan. Lisäksi tietokantakutsut sisältävät paljon toisteisuutta yhteyden alustuksen muodossa, tätä olisi hyvä sieventää, mikäli mahdollista. Nyt se on toteutettu nykyisellä mallilla toiminnan takaamiseksi.
