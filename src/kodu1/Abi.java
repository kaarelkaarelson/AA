package kodu1;
import java.util.*;

public class Abi {

    /**
     * Genereerib juhuslikutest täisarvudest koosneva järjendi, kus elemendid kuuluvad poollõiku [min,max).
     * @param pikkus - juhusliku järjendi pikkus.
     * @return - tagastab juhusliku täisarvu järjendi.
     */
    int[] genJuhuJärjend(int pikkus, int min, int max) {
        int[] tulemus = new int[pikkus];

        for (int i = 0; i < pikkus; i++) {
            tulemus[i] = min + (int)(Math.random() * ((max - min) + 1));  /*(Math.random() * 10)*/;
        }

        return tulemus;
    }

    /**
     * Meetod loob täisarvudest koosneva sorteeritud järjendi, kus elemendid kuuluvad poollõiku [min, max).
     * @param pikkus - sorteeritud järjendi pikkus.
     * @return - tagastab sorteeritud täisarvu järjendi.
     */
    int[] genMittekahanevJärjend(int pikkus, int min, int max) {
        List<Integer> tulemus = new ArrayList<>();

        for (int i = 0; i < pikkus; i++) {
            tulemus.add(min + (int)(Math.random() * ((max - min) + 1)));  /*(Math.random() * 10)*/
        }
        Collections.sort(tulemus);

        return tulemus.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Meetod loob juhusliku järjendi, mis on sorteeritud mittekasvavalt ning kus elemendid kuuluvad
     * poollõiku [min, max).
     * @param pikkus - soovitava järjendi pikkus.
     * @param min - vähim elemendi väärtus järjendis.
     * @param max - suurim elemendi väärtus järjendis.
     * @return - tagastab mittekasvava järjendi.
     */
    int[] genMittekasvavJärjend(int pikkus, int min, int max) {
        List<Integer> tulemus = new ArrayList<>();

        for (int i = 0; i < pikkus; i++) {
            tulemus.add(min + (int)(Math.random() * ((max - min) + 1)));  /*(Math.random() * 10)*/
        }
        Collections.sort(tulemus);
        Collections.reverse(tulemus);

        return tulemus.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Meetod pöörab arvujärjendi järjekorra vastupidiseks.
     * @param järjend - etteantud arvujärjend.
     */
    public static void pööraJärjendVastupidi(int[] järjend) {
        int vasak = 0;
        int parem = järjend.length - 1;

        while( vasak < parem ) { // Teostame
            // vahetame kohad
            int temp = järjend[vasak];
            järjend[vasak] = järjend[parem];
            järjend[parem] = temp;

            vasak++;
            parem--;
        }
    }

    /**
     * Meetod arvutab kahe arvu jagatise.
     * @param jagatav - etteantud arv, mida jagatakse.
     * @param jagaja - arv milleja jagatakse.
     * @return - tagastab jagatise.
     */
    double arvutaJagatis(double jagatav, double jagaja) {
        if (jagaja == 0) return 0;

        double jagatis = (double) jagatav / jagaja;
        return (double) Math.round(jagatis * 100) / 100; // Ümardame arvu kahe komakohani
    }

    /**
     * Meetod arvutab järjendi aritmeetilise keskmise.
     * @param järjend - etteantud ujukomajärjend.
     * @return - tagastab ujukoma arvu.
     */
    double aritmeetilineKeskmine(double[] järjend) {
        double summa = 0;

        for (int i = 0; i < järjend.length; i++) {
            summa += järjend[i];
        }

        double aritmeetilineKesk = summa / järjend.length;
        return (double) Math.round(aritmeetilineKesk * 100) / 100;
    }

    /**
     * Meetod arvutab järjendi mediaani, ehk keskmise elemendi väärtuse.
     * @param järjend - etteantud ujukomajärjend.
     * @return - tagastab mediaani.
     */
    double arvutaMediaan(double[] järjend) {
        Arrays.sort(järjend); // Sorteerime väärtused

        int keskmineElement = järjend.length / 2;
        double mediaan = järjend.length % 2 == 0 ? // Kontrollime, kas keskel asub kaks elementi.
                (järjend[keskmineElement] + järjend[keskmineElement + 1]) / 2 : järjend[keskmineElement];

        return (double) Math.round(mediaan * 100) / 100;
    }

    /**
     * Teisendab nanosekundid millisekunditeks, koos komakohaga.
     *
     * @param nanoTime - etteantud aeg nanosekundites.
     * @return - tagastab aja millisekundites kahe komakoha täpsusega.
     */
    public double nanoToMilliSeconds(long nanoTime) {
        return (double) Math.round((nanoTime / 1_000_000.0) * 100) / 100;

    }
}
