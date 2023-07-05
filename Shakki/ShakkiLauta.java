package Shakki;

import PeliEntiteetit.ShakkiNappula;

/**
 * Shakkilauta-luokka pitää huolen, että nappulat liikkuvat sekä poistuvat
 * jos ne pelistä poistetaan.
 */
public class ShakkiLauta {
    private ShakkiNappula[][] pelilauta;

    /**
     * Luo pelilaudan.
     */
    public ShakkiLauta() {
        pelilauta = new ShakkiNappula[8][8];
    }

    /**
     * Tarkistaa onko pelinappula jossain tietyssä sijainnissa.
     * @param rivi Sijainnin rivi.
     * @param sara Sijainnin sarake.
     * @return Boolean sen mukaan onko sijainnissa nappula vai ei.
     */
    public boolean onkoNappulaSijainnissa(int rivi, int sara) {
        return pelilauta[rivi][sara] != null;
    }

    /**
     * Sijoittaa nappulan tiettyyn kohtaan. Jos kohdassa on jo nappula,
     * vanhva nappula korvataan nykyisellä.
     * @param nappula Nappula, jota liikutetaan
     * @param sijainti Sijainti, johon nappula siirretään
     */
    public void asetaNappulaSijaintiin(ShakkiNappula nappula, ShakkiSijainti sijainti) {
        if(onkoNappulaSijainnissa(sijainti.getRivi(), sijainti.getSara())) {
            poistaNappulaSijainnista(sijainti);
        }
        if(nappula.getShakkiSijainti() != null) {
            poistaNappulaSijainnista(nappula.getShakkiSijainti());
        }

        pelilauta[sijainti.getRivi()][sijainti.getSara()] = nappula;
        nappula.asetaShakkiSijainti(sijainti);
    }

    /**
     * Poistaa nappulan tietystä sijainnista
     * @param sijainti Sijainti, josta nappula poistetaan
     */
    private void poistaNappulaSijainnista(ShakkiSijainti sijainti) {
        pelilauta[sijainti.getRivi()][sijainti.getSara()] = null;
    }

    /**
     * Tarkistaa, onko sijainti laudalla
     * @param sijainti Sijainti, joka tarkistetaan
     * @return Boolean, onko nappula sallittujen rajojen sisällä
     */
    public static boolean sijaintiSallituissaRajoissa(ShakkiSijainti sijainti) {
        return sijainti.getRivi() >= 0 &&
               sijainti.getRivi() < 8 &&
               sijainti.getSara() >= 0 &&
               sijainti.getSara() < 8;
    }

    /**
     * Ottaa nappulan tietystä laudan sijainnista
     * @param sijainti Sijainti josta nappula otetaan
     * @return Nappula joka on sijainnissa
     */
    public ShakkiNappula getNappulaSijainnista(ShakkiSijainti sijainti) {
        return pelilauta[sijainti.getRivi()][sijainti.getSara()];
    }

    /**
     * Luo pelilaudan jossa P on pelinappula
     */
    @Override
    public String toString() {
        String s = "  0 1 2 3 4 5 6 7\n";
        for(int rivi = 0; rivi < 8; rivi++) {
            s += rivi;
            for(int sara = 0; sara < 8; sara++) {
                if(pelilauta[rivi][sara] != null) {
                    s += " " + pelilauta[rivi][sara].getId();
                }
                else {
                    s += " -";
                }
            }
            s += "\n";
        }
        return s;
    }
}