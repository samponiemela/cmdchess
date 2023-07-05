package PeliEntiteetit;

import Shakki.ShakkiSijainti;
import Shakki.ShakkiPeli;

public class Lahetti extends ShakkiNappula {

    /** Luo uuden Lähetti-nappulan.
     * @param omistaja Omistajan string.
     * @param alkuSijainti Lähetin aloitusruutu.
     * @param game Peli johon lähetti kuuluu.
     */
    public Lahetti(String omistaja, ShakkiSijainti alkuSijainti, ShakkiPeli peli) {
        super(omistaja, alkuSijainti, peli);
        if (omistaja.equalsIgnoreCase("pelaaja1")) {
            id = 'B';
        } else if (omistaja.equalsIgnoreCase("pelaaja2")) {
            id = 'b';
        }
    }

    /** Tarkistaa onko Lähetin siirto laillinen 
     * @return Onko siirto laillinen vai ei.
     */
    @Override
    public boolean liikuSijaintiin(ShakkiSijainti sijainti) {
        if (Math.abs(shakkiSijainti.getRivi() - sijainti.getRivi()) ==
                Math.abs(shakkiSijainti.getSara() - sijainti.getSara())) {

            return tarkistaNakoyhteys(shakkiSijainti, sijainti) && super.liikuSijaintiin(sijainti);
        }
        return false;
    }

    /**
     * Päivittää uhkaavat sijainnit
     */
    @Override
    protected void paivitaUhkaavatSijainnit() {
        uhkaavatSijainnit.clear();
        super.paivitaRisti(1, 1);
        super.paivitaRisti(-1, 1);
        super.paivitaRisti(1, -1);
        super.paivitaRisti(-1, -1);
    }
}