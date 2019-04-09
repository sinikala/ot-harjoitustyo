# Ohjelmistotekniikka, harjoitustyö

## Anagrammipeli

Sovellus on toteutettu Ohjelmistotekniikka-kurssin harjoitustyönä. Sovelluksella pelaaja pääse ratkomaan [anagrammeja](https://fi.wikipedia.org/wiki/Anagrammi). Useampi pelaaja voi pelata peliä omalla käyttäjänimellään ja seurata edistymistään matkallaan anagrammien mestaruuteen.

### Dokumentaatio

[Määrittelydokumentti](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentointi/m%C3%A4%C3%A4rittelydokumentti.md)

[Työaikakirjanpito](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentointi/ty%C3%B6aikakirjanpito.md)


### Komentorivikomennot

#### Testaus
Testit suoritetaan komennolla
````
mvn test
````

Testikattavuusraportti luodaan komennolla
````
mvn jacoco:report
````

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

#### Checkstyle
Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla
````
mvn jxr:jxr checkstyle:checkstyle
````
Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
