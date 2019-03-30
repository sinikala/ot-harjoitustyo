# Vaativuusmäärittely

Huom! Projektin aihetta vaihdettu realiteettisyistä 30.3.
Vaatimuusmäärittelyt uudellenkirjoitettu vastaamaan muuttunutta aihetta 30.3.

## Sovelluksen tarkoitus
Anagrammipeli: Sovelluksella voidaan ratkoa [anagarammeja](https://fi.wikipedia.org/wiki/Anagrammi) ja pitää kirjaa jo ratkaistuistujen anagrammien määrästä. 

## Käyttäjäroolit
Erillisiä käyttäjärooleja ei ole.

## Perusversion suunnitellut toiminallisuudet
* Pelissä pelaaja voi tallentaa käyttäjänimensä. 
* Pelaaja voi kirjatua peliin käyttäjänimellään
* Uusi pelaaja voi luoda käyttäjännimen
* Peli pitää kirjaa kunkin pelaajan jo ratkaistuista anagrammeista, jotta peli ei tarjoa jo ratkaistua sanaa uudelleen.
* Pelaaja voi tarkistaa montako sanaa hän on jo ratkaissut
* Pelissä pelaaja voi veikata saamansa anagrammin vastausta tai
* Pelaaja voi myös pyytää uuden anagrammin, vaikka ei olisi keksinyt nykyistä
* Jokaisen oikean vastauksen jälkeen peli kertoo montako sanaa pelaaja on nyt ratkaissut

### Suunnitellut näkymät
* Kirjautumisnäkymä: Pelaaja joko luo uuden käyttäjänimen tai syöttää jo olemassaolevan
* Pelinäkymä: Pelaajalle näytetään ratkaistava anagrammi, pelaaja syöttää arvauksensa ja tarkistaa sen tarkistus-napilla, 		vaihtoehtoisesti hän voi pyytää uuden sanan "yritä toista"-napilla. Näkymässä myös "katso tilasto"-nappi, josta 		pelaaja pääse tilastonäkymään.
* Tilastonäkymä: Pelaaja näkee kuinka suuren osan sanoista hän on ratkaissut. Näkymään tulee mahdollisesti jokin visuaalinen 			havainnekuva, joka heijastaa etenemistä (mittari, kasvava kuva tms).
* Loppunäkymä: Kun pelaaja on ratkaissut kaikki anagrammit hänet siirretään loppunäkymään, jossa onnitellaan pelin 			läpäisemisestä. 


## Laajentavat jatkokehitysideat
Näitä toteutetaan mahdollisuuksien mukaan:
* Pelaaja voi tarkastella määrän lisäksi myös ratkaistuja sanoja
* Pelaaja saa vihjeen halutessaan (tietyn kirjaimen sijainti)
