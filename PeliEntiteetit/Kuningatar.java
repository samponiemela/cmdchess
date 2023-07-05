package PeliEntiteetit;

import Shakki.ShakkiSijainti;
import Shakki.ShakkiPeli;

public class Kuningatar extends ShakkiNappula {

    /**
     * Luo uuden Kuningatar-nappulan.
     * @param omistaja Omistajan string.
     * @param alkuSijainti Kuningattaren aloitusruutu.
     * @param peli peli johon Kuningatar kuuluu.
     */
    public Kuningatar(String omistaja, ShakkiSijainti alkuSijainti, ShakkiPeli peli) {
        super(omistaja, alkuSijainti, peli);
        if (omistaja.equalsIgnoreCase("player1")) {
            id = 'Q';
        } else if (omistaja.equalsIgnoreCase("player2")) {
            id = 'q';
        }
    }

    /** Tarkistaa onko Kuningattaren siirto laillinen
     * @return Laillinen siirto vai ei.
     */
    @Override
    public boolean liikuSijaintiin(ShakkiSijainti sijainti) {
        return tarkistaNakoyhteys(shakkiSijainti, sijainti) && super.liikuSijaintiin(sijainti);
    }

    /**
     * Päivittää uhkaavat sijainnit
     */
    @Override
    protected void paivitaUhkaavatSijainnit() {
        uhkaavatSijainnit.clear();

        super.paivitaPysty(1);
        super.paivitaPysty(-1);

        super.paivitaVaaka(1);
        super.paivitaVaaka(-1);

        super.paivitaRisti(1, 1);
        super.paivitaRisti(-1, 1);
        super.paivitaRisti(1, -1);
        super.paivitaRisti(-1, -1);
    }
}