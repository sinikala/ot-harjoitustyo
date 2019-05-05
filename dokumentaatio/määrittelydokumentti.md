# Vaativuusmäärittely

## Sovelluksen tarkoitus
Anagrammipeli: Sovelluksella voidaan ratkoa [anagrammeja](https://fi.wikipedia.org/wiki/Anagrammi) ja pitää kirjaa jo ratkaistuistujen anagrammien määrästä. 

## Käyttäjäroolit
Erillisiä käyttäjärooleja ei ole.

## Sovelluksen tarjoamat toiminallisuudet
* Uusi pelaaja voi aloittaa pelin valitsemalla _Uusi peli_ ja luomalla itselleen käyttäjänimen
* Vanha pelaaja voi jatkaa vanhaa suoritustaan valitsemalla _Jatka aiempaa peliä_ 
* Peli pitää kirjaa kunkin pelaajan jo ratkaistuista anagrammeista, jotta peli ei tarjoa jo ratkaistua sanaa uudelleen
* Pelaaja voi tarkistaa kuinka ison osan sanoista hän on jo ratkaissut
* Etenemisnäkymän yhteydessä pelaajalle näytetään kuva, joka kuvastaa hänen etenemistään pelissä
* Pelissä pelaaja voi veikata saamansa anagrammin vastausta tai
* Pelaaja voi myös pyytää uuden anagrammin, vaikka ei olisi keksinyt nykyistä
* Jokaisen oikean vastauksen jälkeen peli kertoo montako sanaa pelaaja on nyt ratkaissut
* Pelaajan edetessä pelissä, eli ratkoessa lisää anagrammeja, etenemisruudun kuvasarja etenee

### Näkymät
* Aloitusnäkymä: Pelaaja valitsee haluaako jatkaa vanhaa peliä vai aloittaa uuden
* Uusi peli -näkymä: Pelaaja luo uuden käyttäjänimen, jos nimi on hyväksyttävä pääsee hän ilmestyneellä _Pelaamaan!_-napilla peliin (mahdollisuus päästä myös takaisin aloitusnäkymään)
* Vanha peli -näkymä: Pelaaja syöttää aiemman käyttäjäimensä ja pääsee ilmestyneellä _Pelaamaan!_-napilla peliin (mahdollisuus päästä myös takaisin aloitusnäkymään)
* Pelinäkymä: Pelaajalle näytetään ratkaistava anagrammi, pelaaja syöttää arvauksensa ja tarkistaa sen tarkistus-napilla,	vaihtoehtoisesti hän voi pyytää uuden sanan "yritä toista"-napilla. Näkymässä myös "katso tilasto"-nappi, josta 	pelaaja pääse tilastonäkymään.
* Tilastonäkymä: Pelaaja näkee kuinka suuren osan sanoista hän on ratkaissut havainnekuvan, joka heijastaa etenemistä hänen etenemistään pelissä.
* Loppunäkymä: Kun pelaaja on ratkaissut kaikki anagrammit hänet siirretään loppunäkymään, jossa onnitellaan pelin läpäisemisestä. 


## Laajentavat jatkokehitysideat
* Pelaaja voi tarkastella määrän lisäksi myös ratkaistuja sanoja
* Pelaaja saa vihjeen halutessaan (tietyn kirjaimen sijainti)
