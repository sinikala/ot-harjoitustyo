# Käyttöohje

Lataa tiedosto [Anagrammipeli.jar](https://github.com/sinikala/ot-harjoitustyo/releases/tag/v1.2) sekä samasta listasta löytyvät neljä kuvatiedostoa.

![kuvaohje](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kuvaohje.png)


## Konfigurointi
Varmista, että jar-tiedosto sijaitsee samassa kansiossa lataamiesi kuvatiedostojen kanssa.

![kansioesimerkki](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/esimerkkikansio.png)


## Ohjelman käynnistäminen
Ohjelma käynnistetään komennolla
````
java -jar Anagrammipeli.jar
````

## Kirjautuminen

Alussa valitaan halutaanko aloittaa uusi peli vai jatkaa aiempaa

![uusiVaiVanha](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot%20from%202019-05-05%2017-06-53.png)

Mikäli valitaan uusi peli, käyttäjä voi syöttää haluamansa käyttäjänimen. Mikäli nimi on varattu, ilmoittaa peli siitä ja on pelaajan keksittävä uusi.

![uusi](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot%20from%202019-05-05%2017-07-58.png)

Mikäli halutaan jatkaa vanhaa, kysyy peli aiempaa käyttänimeä

![vanha](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot%20from%202019-05-05%2017-07-34.png)


## Anagrammin ratkaiseminen
Pelaaja saa näytölle anagrammin, joka hänen tulee ratkaista. Hän voi syöttää arvauksensa tekstikenttään ja tarkastaa sen painamalla _Enter_ tai _Tarkista_-nappia. Mikäli vastaus on oikein, pelaaja saa siitä tiedon sekä näkee siihen asti ratkaisemiensa anagrammien kokonaismäärän. Peli näyttää hänelle automaattisesti uuden ratkaistavan anagrammin, mikäli niitä on vielä jäljellä. Mikäli vastaus on väärin, pelaaja saa yrittää arvata sitä loputtoman monta kertaa uudelleen. Mikä hän haluaisi kokeilla mielummin jotain toista anagrammia, painamalla *Uusi sana* -nappia, arpoo peli hänelle uuden sanan ratakaistavaksi. 

![peli](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Screenshot%20from%202019-05-05%2017-09-00.png)

## Etenemisen seuranta
Pelaaja voi kesken pelin painaa *Missä mennään?* -näppäintä, jolla hän pääsee tilanteen seuranta -näkymään, jossa hänelle kerrotaan montako prosenttia kaikista anagrammeista hän on jo ratkaissut. Etenemistä havainnollistaa myös tässä näkymässä näkyvä kuvasarjan kuva. Sarjassa etdetään sitä mukaa, kun pelaaja ratkoo anagrammeja.

## Pelin päättyminen
Kun pelaaja on ratkaissut kaikki tarjolla olevat anagrammit, siirtyy hän loppunäkymään, jossa häntä onnitellaan saavutuksesta. Peli on kyseisen pelaajan osalta päättynyt.
