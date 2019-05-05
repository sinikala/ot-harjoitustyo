# Anagrammipeli

Sovellus on toteutettu Ohjelmistotekniikka-kurssin harjoitustyönä. Sovelluksella pelaaja pääse ratkomaan [anagrammeja](https://fi.wikipedia.org/wiki/Anagrammi). Useampi pelaaja voi pelata peliä omalla käyttäjänimellään ja seurata edistymistään matkallaan anagrammien mestaruuteen.

## Dokumentaatio
[Arkkitehtuurikuvaus](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Määrittelydokumentti](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/m%C3%A4%C3%A4rittelydokumentti.md)

[Työaikakirjanpito](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/ty%C3%B6aikakirjanpito.md)
[Testausdokumentti](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)


## Releaset
[Viikko 5](https://github.com/sinikala/ot-harjoitustyo/releases)

[Viikko 6](https://github.com/sinikala/ot-harjoitustyo/releases/tag/v0.2beta)


## Komentorivikomennot


### Testaus
Testit suoritetaan komennolla
````
mvn test
````

Testikattavuusraportti luodaan komennolla
````
mvn jacoco:report
````

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_.

### Suoritettavan jarin generointi
Komento
````
mvn package
````
generoi hakemistoon _target_ suoritettavan jar-tiedoston _Anagrammipeli-1.0-SNAPSHOT.jar_.

### JavaDoc
JavaDoc generoidaan komennolla:
````
mvn javadoc:javadoc
````
JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_.



### Checkstyle
Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla
````
mvn jxr:jxr checkstyle:checkstyle
````
Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
