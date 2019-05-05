# Testausdokumentti

Sovellusta on testattu käsinkirjoitetuilla JUnit yksikkötesteillä

Testauskattavuus on rivikkattavuudeltaan 98 % ja haarakattavuudeltaan 70 %. Testikkattavuuden puutteet selittyvät kyseinten haarojen testaamisen monimutkaisuudella, sillä ne useimmiten edellyttävät "ihmisen toimintaa" kuten sanan oikein arvaamista.
Käyytäliittymän toteuttava paketti on jötetty pois laiskuista testikattavuuden osalta.

Eritoten DAO-paketin toiminnan testaamiseksi testien yhteydessä luodaan valekäyttäjä, jota hyödynnetään testauksessa.
Testien päätteksi valekäytäjän tiedot poistetaan tietokannasta.
![testikattavuus](https://github.com/sinikala/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/testikattavuus.png)

# Järjestelmätestaus
Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.
Sovellus on haettu ja todettu toimivaksi Linux- ja Windows-ympäristöissä

Määrittelydokumentin kuvaamat toiminnallisuudet on todettu toimiviksi ja ohjelmaa suunnitellessa pyritty estämään mahdolliset virhetilanteet mahdollisimman laajasti.
