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

package Kodu1;

import abi.JärjendAbi;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


public class Peaklass {

    static Kiirmeetod kiirmeetod = new Kiirmeetod();
    static Pistemeetod pistemeetod = new Pistemeetod();
    static Test test = new Test();
    static JärjendAbi abi = new JärjendAbi();
    static JärjendPoleSorteeritudErind järjendPoleSorteeritudErind =
            new JärjendPoleSorteeritudErind("Järjend pole sorteeritud!");

    public static void main(String[] args) {

        /* PISTEMEETOD */
        ylA();

        /* KIIRMEETOD */
        ylB();

        /* JAVA.UTILS.ARRAYS.SORT */
        ylC();

    }

    /** PISTEMEETOD **/

    private static void ylA() {

        testAritmeetilineJada(50_000, 500_000, 50_000, pistemeetod);
        double[] ajad = testGeomeetrilineJada(1_000, 2,  pistemeetod);

        double[] jagatised = new double[ajad.length-1]; //double[ajad.length-1]; // Ühe võrra vähem võrdlust saame teha
        IntStream.range(0, ajad.length-1)
                .forEach(i -> jagatised[i] = arvutaJagatis(ajad[i+1], ajad[i]));

        System.out.println(Arrays.toString(jagatised));
//        double[] test = new double[]{0.0, 0.0, 0.0, 0.89, 3.94, 3.92, 3.67, 4.35, 4.33};
        double keskmineJagatis = aritmeetilineKeskmine(/*test*/jagatised);
        double mediaanJagatis = arvutaMediaan(/*test*/jagatised);
        System.out.println("Keskmine jagatis:" + keskmineJagatis + ", Mediaan: " + mediaanJagatis);

        System.out.println(Arrays.toString(/*test*/jagatised));

    }

    /** KIIRMEETOD **/

    private static void ylB() {

        testAritmeetilineJada(50_000, 500_000, 50_000, kiirmeetod);

        double[] ajad = testGeomeetrilineJada(1_000, 2,  pistemeetod);

        double[] jagatised = new double[ajad.length-1];
        IntStream.range(0, ajad.length-1)
                .forEach(i -> jagatised[i] = arvutaJagatis(ajad[i+1], ajad[i]));

        System.out.println(Arrays.toString(jagatised));

        double keskmineJagatis = aritmeetilineKeskmine(jagatised);
        double mediaanJagatis = arvutaMediaan(jagatised);
        System.out.println("Keskmine jagatis:" + keskmineJagatis + ", Mediaan: " + mediaanJagatis);

        System.out.println(Arrays.toString(jagatised));



    }

    /** JAVA.UTILS.ARRAY.SORT **/

    private static void ylC() {
        testAritmeetilineJada(50_000, 500_000, 50_000, null);
        double[] ajad = testGeomeetrilineJada(1_000, 2,  null);

        double[] jagatised = new double[ajad.length-1];
        IntStream.range(0, ajad.length-1)
                .forEach(i -> jagatised[i] = arvutaJagatis(ajad[i+1], ajad[i]));

        System.out.println(Arrays.toString(jagatised));

        double keskmineJagatis = aritmeetilineKeskmine(jagatised);
        double mediaanJagatis = arvutaMediaan(jagatised);
        System.out.println("Keskmine jagatis:" + keskmineJagatis + ", Mediaan: " + mediaanJagatis);

        System.out.println(Arrays.toString(jagatised));


    }

    /**
     *
     * @param minAndmemaht
     * @param maxAndmemaht
     * @param samm
     * @param algoritm
     */
    private static void testAritmeetilineJada(int minAndmemaht, int maxAndmemaht, int samm, SorteerimiseAlgoritm
            algoritm) {

        for (int i = minAndmemaht; i <= maxAndmemaht; i += samm) {
            try {
                int[] j = abi.genJärjend(i);

                long start = System.currentTimeMillis();
                if (algoritm != null) algoritm.sorteeri(j);
                else Arrays.sort(j);
                long stopp = System.currentTimeMillis();

                if (!test.kasSorteeritud(j)) {
                    throw järjendPoleSorteeritudErind;
                }

                System.out.println(/*(double)(stopp-start)/1000 + " s " +  */(stopp - start)); // s

            } catch (JärjendPoleSorteeritudErind e) {
                System.out.println(e);
            }

        }
    }

    /**
     * Arvutab ühe algoritmi kasvamiskordaja valimi põhjal, kus n=10.
     * Kui andmemahtu suurendatakse 2x, siis leiab mitu korda suureneb tööaeg vastavalt sellele.
     *
     * @param minAndmemaht
     * @param algoritm - SorteerimiseAlgoritmi objekt, millel on meetod sorteeri(). Vaikimisi (kui null) käivitatakse
     *                 java.util.Arrays.sort meetod.
     * @return - tagastab ujukomaarvu massiivi, kus ajad millisekundites.
     */
    private static double[] testGeomeetrilineJada(int minAndmemaht, double kordaja, SorteerimiseAlgoritm algoritm) {

        double[] ajad = new double[10]; // Kitsenduse eesmärgil teeme ainult 10 ajavõttu.
        int andmemaht = minAndmemaht;
        int i = 0;

        while (i < 10) {
            try {
                int[] j = abi.genJärjend(andmemaht);

                long start = System.nanoTime();
                if (algoritm != null) algoritm.sorteeri(j);
                else Arrays.sort(j);
                long stopp = System.nanoTime();

                if (!test.kasSorteeritud(j)) {
                    throw järjendPoleSorteeritudErind;
                }

                ajad[i] = nanoToMilliSeconds(stopp-start);
                System.out.println(nanoToMilliSeconds(stopp - start) + " ms");

                andmemaht *= kordaja;
                i++;

            } catch (JärjendPoleSorteeritudErind e) {
                System.out.println(e);
            }
        }

        return ajad;
    }

    private static double arvutaJagatis(double jagatav, double jagaja) {
        if (jagaja == 0) return 0;

        double jagatis = (double) jagatav / jagaja;
        return (double) Math.round(jagatis * 100) / 100; // Ümardame arvu kahe komakohani
    }

    private static double aritmeetilineKeskmine(double[] järjend) {
        double summa = 0;

        for (int i = 0; i < järjend.length; i++) {
            summa += järjend[i];
        }

        double aritmeetilineKesk = summa / järjend.length;
        return (double) Math.round(aritmeetilineKesk * 100) / 100;
    }

    private static double arvutaMediaan(double[] järjend) {
        Arrays.sort(järjend); // Sorteerime väärtused

        int keskmineElement = järjend.length / 2;
        double mediaan = järjend.length % 2 == 0 ? // Kontrollime, kas keskel asub kaks elementi.
                (järjend[keskmineElement] + järjend[keskmineElement+1]) / 2 : järjend[keskmineElement];

        return (double) Math.round(mediaan * 100) / 100;
    }

    /**
     * Teisendab nanosekundid millisekunditeks, koos komakohaga
     * @param nanoTime
     * @return
     */
    private static double nanoToMilliSeconds(long nanoTime){
        return (double) Math.round((nanoTime/1_000_000.0) * 100) / 100;

    }

}
