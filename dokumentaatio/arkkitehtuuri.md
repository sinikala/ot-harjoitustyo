# Arkkitehtuurikuvaus

## Rakenne

Anagrammipeli-sovellusta kuvaava luokka/pakkauskaavio:

![Luokka/pakkauskaavio](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/Pakkaus_luokkakaavio.jpg)

## Sovelluslogiikka
### Päätoiminnallisuudet
Kuvataan seuraavaksi sekvenssikaaviona erään sovelluksen päätoiminnallisuuden toimintalogiikka.

#### arvauksen tarkistaminen
Kun käyttäjä on syöttänyt arvauskenttään veikauksen ja klikkaa "Tarkista"-painiketta.

![Tarkistus-false](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/Tarkistus-false.png)

Painikkeen painamiseen reagoiva tapahtumakäsittelijä kutsuu User-luokan metodia _check_ parametrinaan pelaajan arvaus. Pelaajaan liittyvä User-luokan olio pitää kirjaa nyt ratkaistavana olevan sanan indeksistä ja liittää sen veikkauksen mukana parametriksi kutsuessaan GameLibrary-luokan metodia _isCorrect_. GameLibrary vertaa onko sen sanalistassa annetussa indeksissä sama merkkijono kuin pelaajan arvauksessa. Tässä tapauksessa veikkaus on väärin, joten GameLibrary palauttaa arvon false. User puolestaan välittää false-arvon tapahtumakäsittelijälle, joka päivittää käyttöliittymää näyttämällä pelaajalle palautteen "Yritä uudestaan" ja tyhjentää arvauskentän. Nyt pelaajan voi halutessaan yrittää uudelleen.  
