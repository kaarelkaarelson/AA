/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2022/2023 sügissemester
 *
 * Kodutöö. Ülesanne nr 1
 * Teema: Sortimismeetodite keerukuse võrdlus
 *
 * Autor: Kaarel-Richard Kaarelson
 *
 * Mõningane eeskuju: vt
 *  1. https://www.geeksforgeeks.org/insertion-sort/
 *  2. https://gist.github.com/HaoyangFan/b9f0c1f56bb35d944c54ae8437c942a4
 *
 *****************************************************************************/
package kodu1;

import java.util.List;
import java.util.stream.IntStream;

public class Peaklass {

    static Kiirmeetod kiirmeetod = new Kiirmeetod();
    static Pistemeetod pistemeetod = new Pistemeetod();

    static Test test = new Test();
    static Abi abi = new Abi();
    static Tabel tabel = new Tabel();

    static JärjendPoleSorteeritudErind järjendPoleSorteeritudErind =
            new JärjendPoleSorteeritudErind("Järjend pole sorteeritud!");

    public static void main(String[] args) {

        // PS! Käivitamisel võib väljund mõni sekund aega võtta.

        /* PISTEMEETOD */
        ylA();

        /* KIIRMEETOD */
        ylB();

        /* JAVA.UTILS.ARRAYS.SORT */
        ylC();

    }

    /***
     * Meetod, kus on ülesande a lahendus. Valitud sorteerimismeetod: Pistemeetod.
     */
    private static void ylA() {
        // Sisendandmete sätted
        int minAndmemaht = 5_000;
        int maxAndmemaht = 100_000;
        int samm = 10_000;

        // Väljundi päis
        System.out.println(String.format("%65s", "PISTEMEETOD"));
        System.out.println(String.format("%73s", "Sisend: " + minAndmemaht + " <= n <= " + maxAndmemaht + "\n"));

        // Andmestruktuurid erinevate juhtude tööaegade salvestamiseks.
        double[] ajadjuhuslik = test.testAritmeetilineJada(minAndmemaht, maxAndmemaht,
                samm, pistemeetod);
        double[] ajadSorteeritud = test.testSorteeritudJärjend(minAndmemaht, maxAndmemaht,
                samm, pistemeetod);
        double[] ajadMittekahanev = test.testMittekahanevJärjend(minAndmemaht, maxAndmemaht,
                samm, pistemeetod);
        // Testime äärejuhte
        if (!test.testÄärejuhud(null)) throw järjendPoleSorteeritudErind;

        // Määrame veerud, mida soovime tabelina välja printida.
        Veerg veerg1 = new Veerg("Juhuslik järjend", ajadjuhuslik);
        Veerg veerg2 = new Veerg("Mittekasvav", ajadSorteeritud);
        Veerg veerg3 = new Veerg("Mittekahanev", ajadMittekahanev);
        // Väljastame tabeli
        tabel.väljastaTabel3Veergu(List.of(veerg1, veerg2, veerg3));

        // Arvutame kui palju suureneb keskmiselt tööaeg, kui sisend suureneb pidevalt 2*n.
        double[] ajadGeomeetriline = test.testGeomeetrilineJada(10_000, 2, pistemeetod);
        double[] jagatised = new double[ajadGeomeetriline.length - 1];
        IntStream.range(0, ajadGeomeetriline.length - 1)
                .forEach(i -> jagatised[i] = abi.arvutaJagatis(
                        ajadGeomeetriline[i + 1], ajadGeomeetriline[i]));
        double mediaanJagatis = abi.arvutaMediaan(jagatised);

        // Tulemuse põhjal otsustame, kas algoritmi tööaja kasv vastab tema keskmisele ajalisele keerukusele.
        System.out.println("Sisendi suurendamisel 2 korda, kasvab tööaeg " + mediaanJagatis + " korda");
    }

    /***
     * Meetod, kus on ülesande b lahendus. Valitud sorteerimismeetod: Kiirmeetod.
     */
    private static void ylB() {
        int minAndmemaht = 500_000;
        int maxAndmemaht = 10_000_000;
        int samm = 1_000_000;

        System.out.println(String.format("%65s", "KIIRMEETOD"));
        System.out.println(String.format("%73s", "Sisend: " + minAndmemaht + " <= n <= " + maxAndmemaht + "\n"));

        double[] ajadjuhuslik = test.testAritmeetilineJada(minAndmemaht, maxAndmemaht,
                samm, kiirmeetod);
        double[] ajadSorteeritud = test.testSorteeritudJärjend(minAndmemaht, maxAndmemaht,
                samm, kiirmeetod);
        double[] ajadMittekahanev = test.testMittekahanevJärjend(minAndmemaht, maxAndmemaht,
                samm, kiirmeetod);
        if (!test.testÄärejuhud(kiirmeetod)) throw järjendPoleSorteeritudErind;

        Veerg veerg1 = new Veerg("Juhuslik järjend", ajadjuhuslik);
        Veerg veerg2 = new Veerg("Mittekasvav", ajadSorteeritud);
        Veerg veerg3 = new Veerg("Mittekahanev", ajadMittekahanev);
        tabel.väljastaTabel3Veergu(List.of(veerg1, veerg2, veerg3));

        double[] ajadGeomeetriline = test.testGeomeetrilineJada(500_000, 2, kiirmeetod);
        double[] jagatised = new double[ajadGeomeetriline.length - 1];
        IntStream.range(0, ajadGeomeetriline.length - 1)
                .forEach(i -> jagatised[i] = abi.arvutaJagatis(
                        ajadGeomeetriline[i + 1], ajadGeomeetriline[i]));
        double mediaanJagatis = abi.arvutaMediaan(jagatised);

        System.out.println("Sisendi suurendamisel 2 korda, kasvab tööaeg " + mediaanJagatis + " korda");
    }

    /***
     * Meetod, kus  ülesande c lahenduse. Testitavaks algoritmiks on java.util.Arrays.sort.
     */

    private static void ylC() {
        int minAndmemaht = 500_000;
        int maxAndmemaht = 10_000_000;
        int samm = 1_000_000;

        System.out.println(String.format("%70s", "JAVA.UTIL.ARRAYS.SORT") + "\n");
        System.out.println(String.format("%73s", "Sisend: " + minAndmemaht + " <= n <= " + maxAndmemaht + "\n"));

        double[] ajadjuhuslik = test.testAritmeetilineJada(minAndmemaht, maxAndmemaht,
                samm, null);
        double[] ajadSorteeritud = test.testSorteeritudJärjend(minAndmemaht, maxAndmemaht,
                samm, null);
        double[] ajadMittekahanev = test.testMittekahanevJärjend(minAndmemaht, maxAndmemaht,
                samm, null);
        if (!test.testÄärejuhud(null)) throw järjendPoleSorteeritudErind;
//
        Veerg veerg1 = new Veerg("Juhuslik järjend", ajadjuhuslik);
        Veerg veerg2 = new Veerg("Mittekasvav", ajadSorteeritud);
        Veerg veerg3 = new Veerg("Mittekahanev", ajadMittekahanev);
        tabel.väljastaTabel3Veergu(List.of(veerg1, veerg2, veerg3));


        double[] ajadGeomeetriline = test.testGeomeetrilineJada(500_000, 2, null);
        double[] jagatised = new double[ajadGeomeetriline.length - 1];
        IntStream.range(0, ajadGeomeetriline.length - 1)
                .forEach(i -> jagatised[i] = abi.arvutaJagatis(
                        ajadGeomeetriline[i + 1], ajadGeomeetriline[i]));
        double mediaanJagatis = abi.arvutaMediaan(jagatised);

        System.out.println("Sisendi suurendamisel 2 korda, kasvab tööaeg " + mediaanJagatis + " korda");
    }
}
