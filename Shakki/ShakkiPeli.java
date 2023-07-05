package Shakki;

import PeliEntiteetit.*;
/**
 * ShakkiPeli-luokka pitää sisällään shakkiin liittyvät objektit, joihin kuuluvat lauta,
 * nappulat sekä vuorot
 */

public class ShakkiPeli {
    private ShakkiLauta shakkiLauta;
    private Kuningas pelaaja1Kuningas;
    private Kuningas pelaaja2Kuningas;

    /**
     * Luodaan uusi instanssi tarvittaville ominaisuuksille
     */

    public ShakkiPeli(){
        shakkiLauta = new ShakkiLauta();
        luoJoukkue(0, "pelaaja1");
        luoJoukkue(7, "pelaaja2");
    }

    /**
     * Luo kullekin pelaajalle nappulat
     * @param puoli Pelaajan aloituspuoli
     * @param pelaaja Pelaajan merkkijono
     */

    private void luoJoukkue(int puoli, String pelaaja) {
        int yksi = (puoli > 0) ? -1 : 1;
        int saraKasvu = 0;

        //Torni
        ShakkiNappula a1 = new Torni(pelaaja, new ShakkiSijainti(puoli, saraKasvu), this);
        ShakkiNappula a2 = new Torni(pelaaja, new ShakkiSijainti(puoli, 7-saraKasvu), this);
        saraKasvu += 1;

        //Ratsu
        ShakkiNappula b1 = new Ratsu(pelaaja, new ShakkiSijainti(puoli, saraKasvu), this);
        ShakkiNappula b2 = new Ratsu(pelaaja, new ShakkiSijainti(puoli, 7-saraKasvu), this);
        saraKasvu += 1;

        //Lahetti
        ShakkiNappula c1 = new Lahetti(pelaaja, new ShakkiSijainti(puoli, saraKasvu), this);
        ShakkiNappula c2 = new Lahetti(pelaaja, new ShakkiSijainti(puoli, 7-saraKasvu), this);
        saraKasvu += 1;

        //Kuningas ja Kuningatar
        if(pelaaja.equalsIgnoreCase("pelaaja1")) {
            pelaaja1Kuningas = new Kuningas(pelaaja, new ShakkiSijainti(puoli, saraKasvu), this);
        }
        else{
            pelaaja2Kuningas = new Kuningas(pelaaja, new ShakkiSijainti(puoli, saraKasvu), this);
        }

        ShakkiNappula k = new Kuningatar(pelaaja, new ShakkiSijainti(puoli, 7-saraKasvu), this);

        //Sotilas
        for(int i = 0; i < 8; i++) {
            ShakkiNappula s = new Sotilas(pelaaja, new ShakkiSijainti(puoli + yksi, i), this);
        }
    }

    /**
     * Palauttaa ShakkiLaudan
     * @return Shakkipelin Pelilauta-objekti
     */

    public ShakkiLauta getShakkiLauta() {
        return shakkiLauta;
    }

    public Kuningas getPelaaja1Kuningas() {
        return pelaaja1Kuningas;
    }

    public Kuningas getPelaaja2Kuningas() {
        return pelaaja2Kuningas;
    }
}