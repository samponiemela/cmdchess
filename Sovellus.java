import java.util.*;
import Shakki.*;
import PeliEntiteetit.*;


public class Sovellus {
    /**
     * Luo pelin ja luo laudan
     * Aloittaa pelin. Peli pyytää pelaajalta komentoja.
     * @param args Komento-rivin komeennot.
     */
    public static void main(String[] args) {
        ShakkiPeli shakkiPeli = new ShakkiPeli();
        boolean peliOhi = false;
        String nykyinenPelaaja = "pelaaja1";
        Scanner scanner = new Scanner(System.in);
        String syote;

        ShakkiSijainti uusiSijainti;
        ShakkiNappula nykyinenNappula;
        Kuningas kuningas;

        while(!peliOhi){
            try {
                System.out.println(shakkiPeli.getShakkiLauta().toString());
                System.out.println(nykyinenPelaaja);
                System.out.println("M - Liikuta nappulaa");
                System.out.println("Q - Quit");
                System.out.println("R - Reset");
                syote = scanner.nextLine();
                if (syote.equalsIgnoreCase("Q") || syote.equalsIgnoreCase("QUIT")) {
                    peliOhi = true;
                    System.out.println("PELI LOPPU");
                    continue;
                } else if (syote.equalsIgnoreCase("R") || syote.equalsIgnoreCase("RESTART")) {
                    shakkiPeli = new ShakkiPeli();
                    System.out.println("PELI RESETOITU");
                    continue;
                } else if (syote.equalsIgnoreCase("M") || syote.equalsIgnoreCase("LIIKU")) {

                    if (nykyinenPelaaja.equals("pelaaja1")) {
                        kuningas = shakkiPeli.getPelaaja1Kuningas();
                    } else {
                        kuningas = shakkiPeli.getPelaaja2Kuningas();
                    }
                    ShakkiNappula vaara = kuningas.tarkista();
                    if (vaara != null) {
                        System.out.println("Kuningas vaarassa nappulasta: (" + vaara.getShakkiSijainti().getRivi() + ", " + vaara.getShakkiSijainti().getSara() + ").");
                    }

                    nykyinenNappula = getNykyinenNappula(shakkiPeli, nykyinenPelaaja);
                    uusiSijainti = getUusiSijainti();

                    if (nykyinenNappula.liikuSijaintiin(uusiSijainti)) {
                        nykyinenPelaaja = (nykyinenPelaaja.equalsIgnoreCase("pelaaja1")) ? "pelaaja2": "pelaaja1";
                    } else {
                        System.out.println("Virheliike");
                    }
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Ei toimi");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("-");
                e.printStackTrace();
            }
        }
    }

    private static ShakkiNappula getNykyinenNappula(ShakkiPeli shakkiPeli, String nykyinenPelaaja) {
        Scanner scanner = new Scanner(System.in);
        String syote;
        ShakkiSijainti nykyinenSijainti;
        ShakkiNappula nykyinenNappula;

        while (true) {
            System.out.println("Liiku: rivi, sara");
            syote = scanner.nextLine();
            nykyinenSijainti = luoShakkiSijainti(syote);
            if (!ShakkiLauta.sijaintiSallituissaRajoissa(nykyinenSijainti)) {
                System.out.println("Sijaintia ei laudalla");
                continue;
            }
            nykyinenNappula = shakkiPeli.getShakkiLauta().getNappulaSijainnista(nykyinenSijainti);
            if (nykyinenNappula == null) {
                System.out.println("Väärä nappula, ei rajojen sisällä");
            } else if (nykyinenNappula.getOmistaja().equalsIgnoreCase(nykyinenPelaaja)) {
                return nykyinenNappula;
            } else {
                System.out.println("Ei sinun nappulasi");
            }
        }
    }

    private static ShakkiSijainti getUusiSijainti() {
        Scanner scanner = new Scanner(System.in);
        String syote;

        ShakkiSijainti uusiSijainti;

        while (true) {
            System.out.println("Liiku: rivi, sara");
            syote = scanner.nextLine();
            uusiSijainti = luoShakkiSijainti(syote);
            if (!ShakkiLauta.sijaintiSallituissaRajoissa(uusiSijainti)) {
                System.out.println("Sijainti rajojen ulkona");
            } else {
                return uusiSijainti;
            }
        }
    }

    private static ShakkiSijainti luoShakkiSijainti(String syote) {
        int rivi = Integer.parseInt(syote.split(",")[0].trim());
        int sara = Integer.parseInt(syote.split(",")[1].trim());
        return new ShakkiSijainti(rivi, sara);
    }
}