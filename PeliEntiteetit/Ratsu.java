package PeliEntiteetit;

import Shakki.ShakkiSijainti;
import Shakki.ShakkiPeli;
import Shakki.ShakkiLauta;

/**
 * Asettaa Ratsulle 'omistavan pelaajan', sen sijainnin ja pelin johon se kuuluu
 * @param omistaja Omistaja string.
 * @param alkuSijainti Ratsun aloitusruutu.
 * @param peli Peli johon Ratsu kuuluu.
 */


public class Ratsu extends ShakkiNappula {

    public Ratsu(String omistaja, ShakkiSijainti alkuSijainti, ShakkiPeli peli) {
        super(omistaja, alkuSijainti, peli);
        if (omistaja.equalsIgnoreCase("pelaaja1")) {
            id = 'N';
        } else if (omistaja.equalsIgnoreCase("pelaaja2")) {
            id = 'n';
        }
    }

    /**
     * Tarkistaa onko Ratsun siirto laillinen
     * @param location Tarkistaa onko annettu paikki laillinen
     * @return Boolean Onko siirto laillinen vai ei
     */
    @Override
    public boolean liikuSijaintiin(ShakkiSijainti sijainti) {
        if (Math.abs(shakkiSijainti.getRivi() - sijainti.getRivi()) == 2 && Math.abs(shakkiSijainti.getSara() - sijainti.getSara()) == 1) {

            return super.liikuSijaintiin(sijainti);
        } else if (Math.abs(shakkiSijainti.getRivi() - sijainti.getRivi()) == 1 && Math.abs(shakkiSijainti.getSara() - sijainti.getSara())== 2) {

            return super.liikuSijaintiin(sijainti);
        }
        return false;
    }

    /**
     * Päivittää uhkaavat sijainnit.
     */
    @Override
    protected void paivitaUhkaavatSijainnit() {
        int[] riviSiirrot = { -2, -1, 1, 2, -2, -1, 1, 2 };
        int[] saraSiirrot = { 1, 2, 2, 1, -1, -2, -2, -1 };

        uhkaavatSijainnit.clear();
        for (int i = 0; i < 8; i++) {
            ShakkiSijainti sijainti = new ShakkiSijainti(riviSiirrot[i], saraSiirrot[i]);
            if (ShakkiLauta.sijaintiSallituissaRajoissa(sijainti)) {
                ShakkiNappula nappula = shakkiPeli.getShakkiLauta().getNappulaSijainnista(sijainti);

                if (nappula != null &&
                        !nappula.getOmistaja().equals(omistaja)) {

                    uhkaavatSijainnit.add(sijainti);
                }
            }
        }
    }
}