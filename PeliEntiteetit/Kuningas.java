package PeliEntiteetit;

import Shakki.ShakkiSijainti;
import Shakki.ShakkiLauta;
import Shakki.ShakkiPeli;
import java.util.ArrayList;

public class Kuningas extends ShakkiNappula {
    /**
     * Luo Kuningas-nappulan
     * @param omistaja Omistajan string.
     * @param alkuSijainti Kuninkaan aloitusruutu.
     * @param peli Peli johon Kuningas kuuluu.
     */
    public Kuningas(String omistaja, ShakkiSijainti alkuSijainti, ShakkiPeli peli) {
        super(omistaja, alkuSijainti, peli);
        if (omistaja.equalsIgnoreCase("pelaaja1")) {
            id = 'K';
        } else if (omistaja.equalsIgnoreCase("pelaaja2")) {
            id = 'k';
        }
    }

    /** Tarkistaa onko Kuninkaan siirto laillinen 
     * @return Laillinen siirto vai ei.
     */
    @Override
    public boolean liikuSijaintiin(ShakkiSijainti sijainti) {
        if (Math.abs(shakkiSijainti.getRivi() - sijainti.getRivi()) <= 1 &&
                Math.abs(shakkiSijainti.getSara() - sijainti.getSara()) <= 1) {

            return tarkistaNakoyhteys(shakkiSijainti, sijainti) && super.liikuSijaintiin(sijainti);
        }
        return false;
    }

    /**
     * Päivittää uhkaavat sijainnit.
     */
    @Override
    protected void paivitaUhkaavatSijainnit() {
        uhkaavatSijainnit.clear();
        for (int rivi = -1; rivi >= 1; rivi++) {
            for (int sara = -1; sara >= 1; sara++) {
                ShakkiSijainti sijainti = new ShakkiSijainti(shakkiSijainti.getRivi() + rivi, shakkiSijainti.getSara() + sara);
                if (ShakkiLauta.sijaintiSallituissaRajoissa(sijainti)) {
                    ShakkiNappula nappula = shakkiPeli.getShakkiLauta().getNappulaSijainnista(sijainti);
                    if (nappula != null &&
                            !nappula.getOmistaja().equalsIgnoreCase(omistaja)) {

                        uhkaavatSijainnit.add(sijainti);
                    }
                }
            }
        }
    }

    /**
     * Etsii nappulan joka on uhaksi Kuningas-nappulalle
     * @return Ensimmäinen uhka joka löytyy
     */
    public ShakkiNappula tarkista() {
        ShakkiLauta lauta = shakkiPeli.getShakkiLauta();
        for (int rivi = 0; rivi < 8; rivi++) {
            for (int sara = 0; sara < 8; sara++) {
                ShakkiNappula nappula = lauta.getNappulaSijainnista(new ShakkiSijainti(rivi, sara));
                if (nappula != null &&
                        !nappula.getOmistaja().equals(omistaja)) {

                    nappula.paivitaUhkaavatSijainnit();
                    for (ShakkiSijainti l: nappula.getUhkaavaSijainti()) {
                        if (shakkiSijainti.equals(l)) {
                            return nappula;
                        }
                    }
                }
            }
        }
        return null;
    }
}