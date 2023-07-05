package PeliEntiteetit;

import Shakki.ShakkiSijainti;
import Shakki.ShakkiPeli;

public class Sotilas extends ShakkiNappula {

    private boolean ekaSiirto;
    private int one;

    /**
     *Luo uuden Sotilas-tyypin nappulan
     * @param omistaja Omistaja string.
     * @param alkuSijainti Sijainti johon Sotilas asetetaan.
     * @param peli Peli johon Sotilas kuuluu.
     */
    public Sotilas(String omistaja, ShakkiSijainti alkuSijainti, ShakkiPeli peli) {
        super(omistaja, alkuSijainti, peli);
        if (omistaja.equalsIgnoreCase("pelaaja1")) {
            id = 'P';
            one = 1;
        } else if (omistaja.equalsIgnoreCase("pelaaja2")) {
            id = 'p';
            one = -1;
        }
        ekaSiirto = true;
    }

    /** Tarkastaa onko siirto laillinen.
     * @return onko siirto laillinen vai ei.
     */
    @Override
    public boolean liikuSijaintiin(ShakkiSijainti sijainti) {
        if (sijainti.getSara() == shakkiSijainti.getSara()) {
            if (sijainti.getRivi() - shakkiSijainti.getRivi() == one) {
                if (ekaSiirto) {
                    ekaSiirto = false;
                }
                return !shakkiPeli.getShakkiLauta().onkoNappulaSijainnissa(sijainti.getRivi(), sijainti.getSara()) && super.liikuSijaintiin(sijainti);
            } else if (ekaSiirto && (sijainti.getRivi() - shakkiSijainti.getRivi() == (one * 2))) {
                if (ekaSiirto) {
                    ekaSiirto= false;
                }
                return !shakkiPeli.getShakkiLauta().onkoNappulaSijainnissa(sijainti.getRivi(), sijainti.getSara()) && super.liikuSijaintiin(sijainti);
            }
        } else if (Math.abs(sijainti.getSara() - shakkiSijainti.getSara()) == 1) {
            if (shakkiPeli.getShakkiLauta().onkoNappulaSijainnissa(sijainti.getRivi(), sijainti.getSara()) &&
                    sijainti.getRivi() - shakkiSijainti.getRivi() == one) {

                if (ekaSiirto) {
                    ekaSiirto = false;
                }
                return super.liikuSijaintiin(sijainti);
            }
        }
        return false;
    }
    /**
     * Päivittää uhkaavat sijainnit
     */
    @Override
    protected void paivitaUhkaavatSijainnit() {
        int yksi = 0;
        if (omistaja.equalsIgnoreCase("pelaaja1") &&
                shakkiSijainti.getRivi() <= 6) {
            yksi = 1;
        } else if (omistaja.equalsIgnoreCase("pelaaja2") &&
                shakkiSijainti.getRivi() >= 1) {
            yksi = -1;
        }

        uhkaavatSijainnit.clear();

        if (shakkiSijainti.getSara() >= 1) {
            uhkaavatSijainnit.add(new ShakkiSijainti(shakkiSijainti.getRivi() + yksi, shakkiSijainti.getSara() - 1));
        }
        if (shakkiSijainti.getSara() <= 6) {
            uhkaavatSijainnit.add(new ShakkiSijainti(shakkiSijainti.getRivi() + yksi, shakkiSijainti.getSara() + 1));
        }
    }
}