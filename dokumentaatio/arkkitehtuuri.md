# Arkkitehtuurikuvaus

## Rakenne

Sovelluksen arkkitehtuuri on kolmikerroksinen ja koodin pakkausrakenne on seuraava:

![Pakkausrakenne](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Pakettirakenne.png)

**Pakkausten vastuualueet:**
* Anagrammipeli.ui - Käyttöliittymä (JavaFx)
* Anagrammipeli.logics - Sovelluslogiikka
* Anagrammipeli.dao -Tietojen pysyväistalletus

## Käyttöliittymä
Käyttöliittymä on toteuttettu kokonaan luokassa anagrammipeli.ui.Main. Muita spvelluksen käyttämiä metodeja se pyytää kutsumalla GameService-luokan metodeja, joka huolehtii pääosin sovelluslogiikasta. Käyttöliittymä muokkaa itseään tapahtumakäsittelijöiden aktivoitumisen ja GameService-oliolta saamiensa palautusarvojen pohjalta. Vastaavia muokkauksia ovat esimerkiksi näkymästä toiseen siirtyminen ja arvauksen tarkistaminen.

Käyttöliittymä sisältää kuusi kappaletta yksi kerrallaan näkyvää, neljään eri Scene-olioon pohjautuvaa näkymää:
* aloitusvalikko ja valinnan mukaan sitä seuraa joko _Aloita uusi peli_ tai _Jatka vanhaa peliä_ -alinäkymä
* pelinäkymä
* tilastonäkymä
* loppunäkymä


## Sovelluslogiikka
Sovelluksen keskiössä ovat luokat User, joka kuvaa käyttäjää eli pelin pelaajaa ja tämän etenemistä pelissä sekä luokka  GameLibrary, johon on tallennettu pelin sanavarasto ja, joka toteuttaa lopullisen arvausten tarkastamisen.

Luokka GameService vastaa pelin toiminnallisuudesta ja toimii käyttöliittymän ja pelitietojen välikätenä.
GameService tarjoaa metodin kaikkiin käyttöliittymän tarpeisiin. Muutama keskeinen esimerkki:
* setUser(String name)
* String getWord()
* boolean check(String guess)
* void setSolved()

GameService pääsee käsiksi käyttäjiin joko Dao-rajapintaa toteuttavan UserDao-luokan kautta tai myöhemmin, kun kyseistä pelisessiota varten on luotu User-luokan olio, suoraan sitä kutsuvilla metodeilla. Tietojen pysyväistallennuksen ja noutamisen GameService toteuttaa aina UserDaon kautta.


Anagrammipeli-sovellusta kuvaava luokka/pakkauskaavio:

![Pakkauskaavio9(https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Pakkaus_luokkakaavio.jpg)

## Tietojen pysyväistalletus
Anagrammipeli.dao-paketin luokka UserDao huolehtii tietojen tallentamisesta tietokantaan. Rakenne mukailee Data Access Object -suunnittelumallia.

### Tallenettavat tiedot
Sovellus tallentaa tietoja sovelluksen mukana tulevaan *playerDatabase*-tietokantaan. Tietokanta koostuu kahdesta tietokantataulusta: *Player* ja *SolvedWords*.

![Relaatiokaavio](http://yuml.me/a6146445.jpg)

Tauluun *Player* tallennetaan pelaajan (ts. käyttäjän) nimi ja hänelle luodaan automaattisesti id-numero. *SolvedWords* listaa mitkä anagrammit pelaaja on jo ratkaissut.


### Päätoiminnallisuudet
Kuvataan seuraavaksi sekvenssikaaviona erään sovelluksen päätoiminnallisuuden toimintalogiikka.

#### arvauksen tarkistaminen
Kun käyttäjä on syöttänyt arvauskenttään veikauksen ja klikkaa "Tarkista"-painiketta.

![Tarkistus](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/Tarkistus-false.png)

Painikkeen painamiseen reagoiva tapahtumakäsittelijä kutsuu palveluluokkaa GameService, joka aloittaa arvauksen käsittelyn kustsumalla User-luokan metodia _check_ parametrinaan pelaajan arvaus. Pelaajaan liittyvä User-luokan olio pitää kirjaa nyt ratkaistavana olevan sanan indeksistä ja liittää sen veikkauksen mukana parametriksi kutsuessaan GameLibrary-luokan metodia _isCorrect_. GameLibrary vertaa onko sen sanalistassa annetussa indeksissä sama merkkijono kuin pelaajan arvauksessa. Tässä tapauksessa veikkaus on väärin, joten GameLibrary palauttaa arvon false. User puolestaan välittää false-arvon Gameserviselle, jolta tulos päätyy tapahtumakäsittelijälle, joka päivittää käyttöliittymää näyttämällä pelaajalle palautteen "Yritä uudestaan" ja tyhjentää arvauskentän. Nyt pelaajan voi halutessaan yrittää uudelleen.  
