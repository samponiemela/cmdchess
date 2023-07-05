package PeliEntiteetit;

import Shakki.ShakkiPeli;
import Shakki.ShakkiSijainti;
import Shakki.ShakkiLauta;
import java.util.ArrayList;

/**
 * Yleinen luokka shakkipelin nappuloille
 */

public abstract class ShakkiNappula implements ShakkiNappulaRajapinta{

    protected ShakkiPeli shakkiPeli;
    protected String omistaja;
    protected ShakkiSijainti shakkiSijainti;
    protected char id;
    protected ArrayList<ShakkiSijainti> uhkaavatSijainnit;

    protected abstract void paivitaUhkaavatSijainnit();

    public ShakkiNappula(String omistaja, ShakkiSijainti alkuSijainti, ShakkiPeli peli) {
        this.omistaja = omistaja;
        shakkiSijainti = null;
        shakkiPeli = peli;
        uhkaavatSijainnit = new ArrayList<>();
        shakkiPeli.getShakkiLauta().asetaNappulaSijaintiin(this, alkuSijainti);

    }

    /**
     * Tarkistaa näköyhteyden tehdessä siirtoa
     * @param alku Sijainnin lähtöpiste
     * @param loppu Sijainnin loppupiste
     * @return Onko siirto laillinen vai ei
     */

    protected boolean tarkistaNakoyhteys(ShakkiSijainti alku, ShakkiSijainti loppu){
        //Pystyrivi
        if(alku.getSara() == loppu.getSara()) {
            int one = (alku.getRivi() - loppu.getRivi() < 0) ? 1: -1;
            for (int rivi = alku.getRivi() + one; rivi < loppu.getRivi(); rivi += one) {
                if (shakkiPeli.getShakkiLauta().onkoNappulaSijainnissa(rivi, alku.getSara())) {
                    return false;
                }
            }
            return true;
        }

        //Vaakarivi
        if(alku.getRivi() == loppu.getRivi()) {
            int one = (alku.getSara() - loppu.getSara() < 0) ? 1: -1;
            for(int sara = alku.getSara() + one; sara < loppu.getSara(); sara += one) {
                if(shakkiPeli.getShakkiLauta().onkoNappulaSijainnissa(alku.getSara(), sara)) {
                    return false;
                }
            }
            return true;
        }

        //Diagonaali

        if(alku.getSara() - loppu.getSara() == alku.getRivi() - loppu.getRivi()) {
            int one = (alku.getRivi() - loppu.getRivi() < 0) ? 1: -1;
            for (int i = one; Math.abs(i) < Math.abs(alku.getRivi() - loppu.getRivi()); i += one) {
                if (shakkiPeli.getShakkiLauta().onkoNappulaSijainnissa(alku.getRivi() + i, alku.getSara() + i)) {
                    return false;
                }
            }
            return true;
        } else if ( alku.getSara() - loppu.getSara() * -1 == alku.getRivi() - loppu.getSara()) {
            int one = (alku.getRivi() - loppu.getRivi() < 0) ? 1: -1;
            int negOne = one * -1;
            for(int i = one; Math.abs(i) < Math.abs(alku.getRivi() - loppu.getRivi()); i += one) {
                if(shakkiPeli.getShakkiLauta().onkoNappulaSijainnissa(alku.getRivi() + i, alku.getSara() + (i * negOne))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Päivittää uhkaavat sijainnit pystyriveillä
     * @param one Sijainti joka tarkistetaan
     */

    protected void paivitaPysty(int one) {
        ShakkiSijainti sijainti = new ShakkiSijainti(shakkiSijainti.getRivi() + one, shakkiSijainti.getSara());
        int i = one;
        while(ShakkiLauta.sijaintiSallituissaRajoissa(sijainti)) {
            ShakkiNappula nappula = shakkiPeli.getShakkiLauta().getNappulaSijainnista(sijainti);
            if(nappula != null) {
                if(!nappula.getOmistaja().equalsIgnoreCase(omistaja)) {
                    uhkaavatSijainnit.add(sijainti);
                    return;
                } else if(!shakkiSijainti.equals(sijainti)) {
                    uhkaavatSijainnit.add(new ShakkiSijainti(sijainti.getRivi() - one, sijainti.getSara()));
                    return;
                }
            } else {
                sijainti = new ShakkiSijainti(sijainti.getRivi() + one, sijainti.getSara());
            }
        }
    }

    /**
     * Päivittää uhkaavat sijainnit vaakariveillä
     * @param one Sijainti joka tarkistetaan
     */

    protected void paivitaVaaka(int one)  {
        ShakkiSijainti sijainti = new ShakkiSijainti(shakkiSijainti.getRivi(), shakkiSijainti.getSara() + one);
        while (ShakkiLauta.sijaintiSallituissaRajoissa(sijainti)) {
            ShakkiNappula nappula = shakkiPeli.getShakkiLauta().getNappulaSijainnista(sijainti);
            if (nappula != null) {
                if (!nappula.getOmistaja().equalsIgnoreCase(omistaja)) {
                    uhkaavatSijainnit.add(sijainti);
                    return;
                } else if (!shakkiSijainti.equals(sijainti)) {
                    uhkaavatSijainnit.add(new ShakkiSijainti(sijainti.getRivi(), sijainti.getSara() - one));
                    return;
                }
            } else {
                sijainti = new ShakkiSijainti(sijainti.getRivi(), sijainti.getSara() + one);
            }
        }
    }

    /**
     * Päivittää uhkaavat sijainnit diagonaalissa
     * @param riviOne Tarkistettava rivi
     * @param saraOne Tarkistettava sarake
     */

    protected void paivitaRisti(int riviOne, int saraOne) {
        ShakkiSijainti sijainti = new ShakkiSijainti(shakkiSijainti.getRivi() + riviOne, shakkiSijainti.getSara() + saraOne);
        while (ShakkiLauta.sijaintiSallituissaRajoissa(sijainti)) {
            ShakkiNappula nappula = shakkiPeli.getShakkiLauta().getNappulaSijainnista(sijainti);
            if(nappula != null) {
                if(!nappula.getOmistaja().equalsIgnoreCase(omistaja)) {
                    uhkaavatSijainnit.add(sijainti);
                    return;
                } else if (!shakkiSijainti.equals(sijainti)) {
                    uhkaavatSijainnit.add(new ShakkiSijainti(sijainti.getRivi() - riviOne, sijainti.getSara() - saraOne));
                    return;
                }
            } else {
                sijainti = new ShakkiSijainti(sijainti.getRivi() + riviOne, sijainti.getSara() + saraOne);
            }
        }
    }

    /**
     * Asettaa ShakkiNappulan sijainnin
     * @param uusiSijainti Nappulan uusi sijainti
     */
    public boolean liikuSijaintiin(ShakkiSijainti uusiSijainti) {
        ShakkiLauta lauta = shakkiPeli.getShakkiLauta();
        ShakkiNappula vanhaNappula = lauta.getNappulaSijainnista(uusiSijainti);

        if (vanhaNappula == null || vanhaNappula.getOmistaja() != omistaja) {
            lauta.asetaNappulaSijaintiin(this,uusiSijainti);
            return true;
        }
        return false;
    }

    /**
     * Palauttaa nappulan sijainnnin
     * @return ShakkiNappulan ShakkiSijainnin
     */
    public ShakkiSijainti getShakkiSijainti() {
        return shakkiSijainti;
    }

    /**
     * Asettaa nappulan sijainnin
     * @param sijainti Uusi sijainti
     */
    public void asetaShakkiSijainti(ShakkiSijainti sijainti) {
        shakkiSijainti = sijainti;
    }

    /**
     * Saa omistajan Stringin
     * @return Omistaja string
     */
    public String getOmistaja() {
        return omistaja;
    }

    /**
     * Saa nappulan id:n
     * @return Id:n char
     */
    public char getId() {
        return id;
    }

    /**
     * Saa uhkaavaSijainti
     * @return ArrayList ShakkiSijaintien
     */
    public ArrayList<ShakkiSijainti> getUhkaavaSijainti() {
        return uhkaavatSijainnit;
    }
}