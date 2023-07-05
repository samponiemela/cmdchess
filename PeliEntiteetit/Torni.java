package PeliEntiteetit;

import Shakki.ShakkiSijainti;
import Shakki.ShakkiPeli;

public class Torni extends ShakkiNappula {

    /**
     * Luo uuden Torni-nappulan
     * @param omistaja Omistaja string.
     * @param alkuSijainti Sijainti johon Torni sijoitetaan.
     * @param peli Peli, johon Torni kuuluu.
     */
    public Torni(String omistaja, ShakkiSijainti alkuSijainti, ShakkiPeli peli) {
        super(omistaja, alkuSijainti, peli);
        if (omistaja.equalsIgnoreCase("pelaaja1")) {
            id = 'R';
        } else if (omistaja.equalsIgnoreCase("pelaaja2")) {
            id = 'r';
        }
    }

    /** Tarkistaa onko Tornin siirto laillinen
     * @return Laillinen siirto tai ei.
     */
    @Override
    public boolean liikuSijaintiin(ShakkiSijainti sijainti) {
        if ((shakkiSijainti.getRivi() == sijainti.getRivi()) != (shakkiSijainti.getSara() == sijainti.getSara())) {

            return tarkistaNakoyhteys(shakkiSijainti, sijainti) && super.liikuSijaintiin(sijainti);
        }
        return false;
    }

    /**
     * Updates the threatening locations.
     */
    @Override
    protected void paivitaUhkaavatSijainnit() {
        uhkaavatSijainnit.clear();

        super.paivitaPysty(1);
        super.paivitaPysty(-1);
        super.paivitaVaaka(1);
        super.paivitaVaaka(-1);
    }
}