package Shakki;

/**
 * ShakkiSijainti-luokassa on nappuloiden sijainnit
 */

public class ShakkiSijainti {
    private int rivi;
    private int sara;

    /**
     * Asettaa rivin(rivi) ja sarakkeen(sara)
     * @param rivi aseta Rivi
     * @param sara aseta Sara
     */
    public ShakkiSijainti(int rivi, int sara) {
        this.rivi = rivi;
        this.sara = sara;
    }

    public boolean equals(Object o) {
        if(o != null && o instanceof ShakkiSijainti) {
            ShakkiSijainti s = (ShakkiSijainti) o;
            return(rivi == s.getRivi() &&
                   sara == s.getSara());

        }
        return false;
    }

    public int getRivi() {
        return rivi;
    }

    public int getSara() {
        return sara;
    }

    public void setRivi(int rivi) {
        this.rivi = rivi;
    }

    public void setSara(int sara) {
        this.sara = sara;
    }
}
