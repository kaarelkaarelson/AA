package rekursioon;

import abi.JärjendAbi;

import java.util.Arrays;


public class Tahvlipraktikum3 {

    public static int summarek(int[] a, int alg, int lõpp) {

        if (a.length == 0) return 0;

        if (alg == lõpp) { // Lõpp on alati välja arvatud
            System.out.println(a[alg]);
            return a[alg];
        }

        int keskkoht = arvutaJärjendiKeskkoht(a.length, alg, lõpp);

        return summarek(a, alg, keskkoht - 1) + summarek(a, keskkoht, lõpp);
    }

    /**
     * Arvutab keskkoha etteantud indeksite vahel
     *
     * @param length
     * @param alg
     * @param lõpp
     * @return
     */
    private static int arvutaJärjendiKeskkoht(int length, int alg, int lõpp) {

        int pikkus = (lõpp - alg) + 1;
        int poolPikkust = pikkus / 2;
        int keskkoht = alg + poolPikkust;

        return keskkoht;
    }

    public static void bitivektori(int n, String tulemus) {
        if (n == 0) {
            System.out.println(tulemus);
            return;
        }

        bitivektori(n - 1, tulemus + "0");
        tulemus = tulemus.substring(0, tulemus.length() - 1);
        bitivektori(n - 1, tulemus + "1");

    }

    private static void alamhulgad(int[] j) {
        if (j.length == 0) return;
        alamhulgad(j, 0, "");

    }

    private static void alamhulgad(int[] j, int i, String tee) {
        if (i == j.length) {
            if (!tee.equals("")) System.out.println(tee);
        }

        /*else{

            for (int k = i; k < j.length; k++) {
                alamhulgad(j, i + 1, tee);
                alamhulgad(j, i + 1, tee + j[k]);
            }
        }*/

        else {
            alamhulgad(j, i + 1, tee);
            alamhulgad(j, i + 1, tee + j[i]);
        }
    }


    public static void main(String[] args) {
        int[] j = new JärjendAbi().genJärjend(5);
        Arrays.stream(j).forEach(System.out::print);

//        Arrays.stream(j).forEach(number -> {
//            System.out.println(number);
//        });

        //bitivektori(10, "");
//        int[] järjend = new int[]{1, 2};
//        alamhulgad(j);
  /*      int[] j = JärjendAbi.genJärjend(100);
        Arrays.stream(j).forEach(System.out::println);

        System.out.println("\n");

        System.out.println("\n" + summarek(j, 0, j.length-1)); // Lahutame pikkusest ühe maha, et saada lõpu i.
*/

    }

}
