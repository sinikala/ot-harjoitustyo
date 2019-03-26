# Vaativuusmäärittely

## Sovelluksen tarkoitus
Palapeli: Sovelluksella voidaan ratkoa palalejä ja pitää kirjaa nopeimmista
ratkaisijoista.

## Käyttäjäroolit
Erillisiä käyttäjärooleja ei ole.

## Perusversion suunnitellut toiminallisuudet
Sovelluksessa on viisi näkymää; aloitusnäkymä, pelinäkymä, ratkaistu-näkymä, 
ratkaistu ennätysajassa -näkymä sekä high score -näkymä.
*Alkunäkymä: Pelaaja voi valita vaihtoehdot "Aloita uusi peli" 
	ja "Näytä high score". Kumpikin toiminto johtaa uuteen näkymään.

*Pelinäkymä: Valitsemalla uuden pelin, pelaaja pääsee ratkaisemaan palapeliä.
	Näkymä alkaa valmiilla palapelillä. Painamalla "Sekoita!"-nappia, 
	palat sekoittuvat ja ajastin alkaa. Pelaaja voi nyt ratkaista hiiren avulla
	palapelin. Kun peli valmistuu, pelaaja pääse menestyksestään riippuen 
	jompaankumpaan kahdesta ratkaisunäkymästä.

*Ratkaistu-näkymä: Näkymässä onnitellaan pelaajaa ratkaisusta pelistä, näytetään
	kulunut aika ja annetaan vaihtoehdot "Pelaa uusi peli", joka johtaa takaisin 
	pelinäkymään ja uuteen peliin tai "Katso ennätykset", jota painamalla pelaaja
	pääse high score -näkymään.

*Ratkaistu ennätysajassa -näkymä: Mikäli palapelin ratkaisuaika oli tarpeeksi 
	nopea päästäkseen enäätyslistalle, kysytään käyttäjältä nimimerkkiä, 
	joka liitetään ennätysaikaan. Muuten samat toiminnot kuin Ratkaisu-näkymässä.

*High score -näkymä: Pelaaja näkee listauksen parhaista ratkaisuajoista ja ne 
	tehneiden pelaajien nimimerkeistä. Myös tässä näkymässä nappi "Aloita 
	uusi peli".

## Laajentavat jatkokehitysideat
Näitä toteutetaan mahdollisuuksien mukaan:
*Palapelille voi valita vaikeustason
*Eri vaikeustasoille omat high score -listansa.
*Pelaajja voi valita saman pelin heti uudelleen (replay)
