package com.org.NutikasRestoran_service.laud;

import org.springframework.stereotype.Component;

@Component
public class LaudFactory {

    private final LaudService laudService;

    public LaudFactory(LaudService laudService) {
        this.laudService = laudService;
    }

    /**
     * Random number generator
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * laudade genemise loogika töötab erinevate laudade arvude puhul.
     */
    public void looLauad(int peaSaaliLaudu, int terrassiLaudu, int privaatseidLaudu) {
        looPeasaal(peaSaaliLaudu);
        looTerrass(terrassiLaudu);
        looPrivaatsedLauad(privaatseidLaudu);
    }

    /**
     * Genereerime peasaali lauad, struktuuriga 3xN.
     * Reeglid:
     * Saali vasakus servas on aknakohaga lauad,
     * Privaatsust peasaalis pole,
     * Peasaali lauad mida ümbritsevad teised lauad on halvasti ligipääsetavad,
     * Inimeste mahtuvus laua puhul genereeritakse juhuslikult (2-4)
     *
     */
    public void looPeasaal(int peaSaaliLaudu) {

        boolean aknaKoht;
        boolean ligipääseatav;

        for (long i = 0; i < peaSaaliLaudu; i++) {

            if (((double) i + 1.0) % 3.0 == 1) // peasaali aknakohad on saali vasakus servas
                aknaKoht = true;
            else
                aknaKoht = false;

            if (((double) i + 1.0) % 3 == 2)  // ainult lauad mis on ümbritsetud teiste laudade poolt, ei ole ligipääsetavad
                if (i == 1 || i + 1 >= peaSaaliLaudu - 3) // saali üleval esimene ja saali all viimane rida laudasid on hästi ligipääsetavad
                    ligipääseatav = true;
                else
                    ligipääseatav = false;
            else
                ligipääseatav = true;

            this.laudService.save(new Laud(0, null, false, aknaKoht, ligipääseatav, getRandomNumber(2, 5), "saal"));
        }
    }

    /**
     * Genereerime terassi lauad, struktuuriga 3xN.
     * Reeglid:
     * Aknakohaga laudu pole,
     * privaatsust terassil pole,
     * Ligipääsetavad lauad on peasaali küljega kohakuti,
     * Inimeste mahtuvus laua puhul genereeritakse juhuslikult(2-6)
     *
     */
    public void looTerrass(int terrassiLaudu) {

        boolean ligipääseatav;
        for (int i = 0; i < terrassiLaudu; i++) {
            if (((double) i + 1.0) % 3.0 == 1) // maja poolsed lauad on terassil ligipääsetavad
                ligipääseatav = true;
            else
                ligipääseatav = false;

            this.laudService.save(new Laud(0, null, false, false, ligipääseatav, getRandomNumber(2, 7), "terass"));
        }
    }

    /**
     * Genereerime lauad, laudadel erinevad omadused.
     * Terrass- terass on 3xn. Aknakohaga laudu pole; privaatsust terassil pole; ligipääsetavad lauad on peasaali küljega kohakuti; inimeste mahtuvus genereeritakse juhuslikult
     * PrivaatneRuum- privaatne ruum on 4x1. Pooled lauad on aknakohaga; kõik lauad on privaatsed; kõik lauad on hästi ligipääsetavad; inimeste mahtuvus genereeritakse juhuslikult
     */

    /**
     * Genereerime privaatsete ruumide lauad, struktuuriga 4x1.
     * Pooled lauad on aknakohaga,
     * Kõik lauad on privaatsed,
     * Kõik lauad on hästi ligipääsetavad,
     * Inimeste mahtuvus laua puhul genereeritakse juhuslikult(2-10)
     */
    public void looPrivaatsedLauad(int privaatseidLaudu) {

        boolean aknaKoht;
        for (int i = 0; i < privaatseidLaudu; i++) { // igal teisel privaatsel laual on aken
            if (i % 2 == 0)
                aknaKoht = true;
            else
                aknaKoht = false;

            this.laudService.save(new Laud(0, null, true, aknaKoht, true, getRandomNumber(2, 11), "privaatneRuum"));
        }
    }
}
