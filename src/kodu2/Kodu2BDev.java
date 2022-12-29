package kodu2;

import kodu1.Abi;

import javax.management.DescriptorAccess;
import java.math.BigDecimal;
import java.util.*;

public class Kodu2BDev {
    Abi abi = new Abi();
    int loendur = 0;
    int objekte = 0;

    public static int tükeldused(double[] a, double p) {

        /* Eeltöö */
        Arrays.sort(a);

        // Teisendame järjendi BigDecimal elementideks, kuna arvutame komakohtadega.
        BigDecimal[] b = new BigDecimal[a.length];
        for (int i = 0; i < b.length; i++) {
            BigDecimal element = BigDecimal.valueOf(a[i]);
            b[i] = element;
        }

        BigDecimal vähim = b[0];
        BigDecimal jääk = BigDecimal.valueOf(p);

        // Andmestruktuur millesse salvestame juba läbitud hargnevused.
        List<Map<BigDecimal, Integer>> mälu = new ArrayList<>();

        // Igal järjendi elemendile vastab üks tase, vastavalt jäägile.
        for (int i = 0; i < a.length; i++) {
            // Igale võtmele (jäägile) vastab lahendite arv selles hargevuses.
            Map<BigDecimal, Integer> map = new HashMap<>();
            mälu.add(map);
        }

        /* Põhitöö */
        int[] vastus = tükeldused(b, jääk, vähim, 0, mälu);

        return vastus[0];
    }

    public static int[] tükeldusedLong(BigDecimal[] a, BigDecimal jääk, BigDecimal vähim, int i,
                                   List<Map<BigDecimal, Integer>> mälu) {
        // Baasjuht 1
        if (i == a.length || jääk.compareTo(BigDecimal.ZERO) < 0) return new int[]{0, -1};

        // Baasjuht 2
        if (jääk.compareTo(vähim) < 0) return new int[]{1, 1};

        // Kontrollime mälus kas vaadeldav lahend on juba välja arvutatud.
        if (mälu.get(i).containsKey(jääk)) {
            int lahend = mälu.get(i).get(jääk);
            return new int[]{lahend, 1};
        }

        int kokku = 0;
        BigDecimal praegune = a[i];

        // Võtame
        if (praegune.compareTo(jääk) <= 0) {
            int[] võtan = tükeldusedLong(a, jääk.subtract(praegune), vähim, i, mälu);
            kokku += võtan[0];

            // Kui antud lahend viis tupikusse, siis tagastame kohe väärtuse
            if (võtan[1] == -1) {
                // Salvestame lahendi mällu
                if (!mälu.get(i).containsKey(jääk)) mälu.get(i).put(jääk, kokku);

                return new int[]{kokku, 1};
            }

            // Ei võta
            int[] eiVõta = tükeldusedLong(a, jääk, vähim, i + 1, mälu);
            kokku += eiVõta[0];
        }

        // Salvestame lahendi mällu
        if (!mälu.get(i).containsKey(jääk)) {
            mälu.get(i).put(jääk, kokku);
        }

        return new int[]{kokku, 1};
    }

    public static long doubleToLong(double arv, int nullkohti) {
        long kordaja = 10L;

        for (int i = 1; i <= nullkohti; i++) {
            kordaja *= 10L;
        }
        return (long) arv * kordaja;

//        return (long) (arv * (10L * (long) nullkohti);
//        return 10L;

    }

    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);

        System.out.println(doubleToLong(1.9999934892348, 10));

    }

























    /**
     * Meetod leiab traadi kõikvõimalike tükelduste arvu, mille puhul ülejaak on väiksem kui vähim traadi lubatud pikkus.
     *
     * @param a - etteantud massiiv, kus on elementide tükelduste pikkused.
     * @param p - etteantud traadi pikkus.
     * @return - tagastab kõikvõimalike tükelduste arvu
     */
    public int tükeldusedBD(double[] a, double p) {
        long start = System.nanoTime();
        Arrays.sort(a);
        double vähim = a[0];
        long stopp = System.nanoTime();

        double aeg = abi.nanoToMilliSeconds(stopp - start);
        System.out.println("Sorteerimisele läks aega: " + aeg);
        loendur += 1;
        int vastus = tükeldusedBigDecimal(a, new BigDecimal(Double.toString(p)), new BigDecimal(Double.toString(vähim)), 0);
        System.out.println("väljakutseid: " + loendur);
        loendur = 0;
        return vastus;
    }

    public int tükeldusedBDOpt(double[] a, double p) {

        Arrays.sort(a);
        double vähim = a[0];
        loendur += 1;
        int vastus = tükeldusedBigDecimalOptimeeritud(a, new BigDecimal(Double.toString(p)), new BigDecimal(Double.toString(vähim)), 0);
        System.out.println("väljakutseid: " + loendur);
        loendur = 0;

        return vastus;
    }

    public int tükeldusedDünaamilineObj(double[] a, double p) {

        Arrays.sort(a);
        double esimene = a[0];

        BigDecimal jääk = new BigDecimal(Double.toString(p));
        BigDecimal vähim = new BigDecimal(Double.toString(esimene));
        Tase[] mälu = new Tase[a.length];
        for (int i = 0; i < a.length; i++) {
            Tase tase = new Tase(i, new HashMap<>());
            mälu[i] = tase;
        }
        loendur += 1;
        int vastus = tükeldusedDünaamilineObj(a, jääk, vähim, 0, mälu);
        System.out.println("väljakutseid: " + loendur);
        loendur = 0;

        return vastus;
    }

    public int tükeldusedDünaamilineA(double[] a, double p) {

        long start = System.nanoTime();
        Arrays.sort(a);
        long stopp = System.nanoTime();

        double aeg = abi.nanoToMilliSeconds(stopp - start);
        System.out.println("Sorteerimisele läks aega: " + aeg);

        double esimene = a[0];

        BigDecimal jääk = new BigDecimal(Double.toString(p));
        BigDecimal vähim = new BigDecimal(Double.toString(esimene));
        Tase[] mälu = new Tase[a.length];
        for (int i = 0; i < a.length; i++) {
            Tase tase = new Tase(i, new HashMap<>());
            mälu[i] = tase;
        }
        loendur += 1;
        int[] vastus = tükeldusedDünaamilineA(a, jääk, vähim, 0, mälu);
        System.out.println("väljakutseid: " + loendur);
        loendur = 0;

        return vastus[0];
    }

    public int tükeldusedA(double[] a, double p) {

        Arrays.sort(a);
        double esimene = a[0];

        BigDecimal jääk = new BigDecimal(Double.toString(p));
        BigDecimal vähim = new BigDecimal(Double.toString(esimene));
        Tase[] mälu = new Tase[a.length];
        for (int i = 0; i < a.length; i++) {
            Tase tase = new Tase(i, new HashMap<>());
            mälu[i] = tase;
        }
        loendur += 1;
        int[] vastus = tükeldusedA(a, jääk, vähim, 0);
        System.out.println("väljakutseid: " + loendur);
        loendur = 0;

        return vastus[0];
    }

    public int tükeldusedMassiiv(double[] a, double p) {

        Arrays.sort(a);
        double esimene = a[0];

        BigDecimal jääk = new BigDecimal(Double.toString(p));
        BigDecimal vähim = new BigDecimal(Double.toString(esimene));
//        int[][] mälu = new int[a.length][Integer.MAX_VALUE/2];
        int pikkus = Integer.MAX_VALUE / 100;
        int[][] mälu = new int[a.length][pikkus];

        for (int i = 0; i < a.length; i++) {

            System.out.println(mälu[i].length);
            System.out.println(mälu[i][1]);
        }
//        loendur +=1;
//        int[] vastus =  tükeldusedMassiiv(a, jääk, vähim, 0, mälu);
//        System.out.println("väljakutseid: " + loendur);
//        loendur = 0;
//
//        return vastus[0];
        return 0;
    }

    public int tükeldusedList(double[] a, double p) {

        Arrays.sort(a);
        double esimene = a[0];
        if (p < esimene || p <= 0 || esimene < 0) return 0;

        BigDecimal jääk = new BigDecimal(Double.toString(p));
        BigDecimal vähim = new BigDecimal(Double.toString(esimene));
//        int[][] mälu = new int[a.length][Integer.MAX_VALUE/2];

        List<Map<Double, Integer>> mälu = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            Map<Double, Integer> map = new HashMap<>();
            mälu.add(map);
        }
        loendur += 1;
        int[] vastus = tükeldusedList(a, jääk, vähim, 0, mälu);
        System.out.println("väljakutseid: " + loendur);
        System.out.println("objekte: " + objekte);
        loendur = 0;
        objekte = 0;

        return vastus[0];
    }

    public int tükeldusedListOpt(double[] a, double p) {

        Arrays.sort(a);
        double esimene = a[0];
        if (p < esimene || p <= 0 || esimene < 0) return 0;

        BigDecimal jääk = new BigDecimal(Double.toString(p));
        BigDecimal vähim = new BigDecimal(Double.toString(esimene));
//        int[][] mälu = new int[a.length][Integer.MAX_VALUE/2];

        List<Map<Double, Integer>> mälu = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            Map<Double, Integer> map = new HashMap<>();
            mälu.add(map);
        }
        loendur += 1;
        int[] vastus = tükeldusedListOpt(a, jääk, vähim, 0, mälu,
                new HashMap<String, BigDecimal>());
        System.out.println("väljakutseid: " + loendur);
        System.out.println("objekte: " + objekte);
        loendur = 0;
        objekte = 0;

        return vastus[0];
    }

    public int tükeldusedListBD(double[] a, double p) {

        /* Eeltöö */
        // sorteerime järjendi
        Arrays.sort(a);
//        if (p < a[0] || p <= 0 || a[0] < 0) return 0;

        // Loome uue Big decimal järjendi
        BigDecimal[] b = new BigDecimal[a.length];
        for (int i = 0; i < b.length; i++) {
            BigDecimal element = BigDecimal.valueOf(a[i]);
            b[i] = element;
        }

        BigDecimal esimene = b[0];
        BigDecimal jääk = BigDecimal.valueOf(p);

        List<Map<BigDecimal, Integer>> mälu = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            Map<BigDecimal, Integer> map = new HashMap<>();
            mälu.add(map);
        }

        Map<Double, BigDecimal> bd = new HashMap<>();

        loendur += 1;
        int[] vastus = tükeldusedListBD(b, jääk, esimene, 0, mälu,
                new HashMap<String, BigDecimal>());

        System.out.println("väljakutseid: " + loendur);
        System.out.println("objekte: " + objekte);
        loendur = 0;
        objekte = 0;

        return vastus[0];
    }

    public int tükeldusedListBD2(double[] a, double p) {

        /* Eeltöö */
        long start = System.nanoTime();
        Arrays.sort(a);

        BigDecimal[] b = new BigDecimal[a.length];
        for (int i = 0; i < b.length; i++) {
            BigDecimal element = BigDecimal.valueOf(a[i]);
            b[i] = element;
        }

        BigDecimal esimene = b[0];
        BigDecimal jääk = BigDecimal.valueOf(p);

        List<Map<BigDecimal, Integer>> mälu = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            Map<BigDecimal, Integer> map = new HashMap<>();
            mälu.add(map);
        }

        Map<Double, BigDecimal> bd = new HashMap<>();

        double vähim = a[0];
        long stopp = System.nanoTime();

        double aeg = abi.nanoToMilliSeconds(stopp - start);
        System.out.println("eeltöödele läks aega: " + aeg);
//        if (p < a[0] || p <= 0 || a[0] < 0) return 0;

        loendur += 1;
        int[] vastus = tükeldusedListBD2(b, jääk, esimene, 0, mälu,
                new HashMap<String, BigDecimal>());

        System.out.println("väljakutseid: " + loendur);
        System.out.println("objekte: " + objekte);
        loendur = 0;
        objekte = 0;

        return vastus[0];
    }

    public int tükeldusedReverse(double[] a, double p) {

        /* Eeltöö */
        long start = System.nanoTime();
        Arrays.sort(a);

        BigDecimal[] b = new BigDecimal[a.length];
        for (int i = 0; i < b.length; i++) {
            BigDecimal element = BigDecimal.valueOf(a[i]);
            b[i] = element;
        }

        BigDecimal esimene = b[0];
        BigDecimal jääk = BigDecimal.valueOf(p);

        List<Map<BigDecimal, Integer>> mälu = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            Map<BigDecimal, Integer> map = new HashMap<>();
            mälu.add(map);
        }

        Map<Double, BigDecimal> bd = new HashMap<>();

        double vähim = a[0];
        long stopp = System.nanoTime();

        double aeg = abi.nanoToMilliSeconds(stopp - start);
        System.out.println("eeltöödele läks aega: " + aeg);
//        if (p < a[0] || p <= 0 || a[0] < 0) return 0;

        loendur += 1;
        int[] vastus = tükeldusedReverse(b, jääk, esimene, 0, mälu,
                new HashMap<String, BigDecimal>());

        System.out.println("väljakutseid: " + loendur);
        System.out.println("objekte: " + objekte);
        loendur = 0;
        objekte = 0;

        return vastus[0];
    }


    public int[] tükeldusedListBD2(BigDecimal[] a, BigDecimal jääk, BigDecimal vähim, int i,
                                   List<Map<BigDecimal, Integer>> mälu, Map<String, BigDecimal> bd) {
        if (i == a.length || jääk.compareTo(BigDecimal.ZERO) < 0) { // Baasjuht
//            System.out.println("Elemendid otsas i: " + i + " või jääk: " + jääk + " negatiivne. Tagastan");
            if (!mälu.get(i).containsKey(jääk)) {
                mälu.get(i).put(jääk, 0);
            }
            return new int[]{0, -1};
        }

        if (jääk.compareTo(vähim) < 0) {
//            System.out.println(a[i] + ", jääk " + jääk + " | Jääk: " + jääk + " < " + " vähim: " + vähim + " tagastan");
            if (!mälu.get(i).containsKey(jääk)) {
                mälu.get(i).put(jääk, 1);
            }
            return new int[]{1, 1};
        }

        int kokku = 0;
        //Kui välja arvutatud, liigume järgmise elemendi juurde.
        if (mälu.get(i).containsKey(jääk)) {

            int lahend = mälu.get(i).get(jääk);
            if (lahend > 0)
//                System.out.println(a[i] + ", jääk " + jääk + " | lahend leitud mälust. lahendeid " + lahend + " tagastan");
            kokku += lahend;
            return new int[]{kokku, 1};
        }

        int[] meeles = new int[a.length - i];

        // PEAME JÄÄKI PIDEVALT UUENDAMA TSÜKLIS
        for (int j = i; j < a.length; j++) {
            BigDecimal praegune = a[j]; // pointer

//             //Kui välja arvutatud, liigume järgmise elemendi juurde.
//            if (mälu.get(j).containsKey(jääk)) {
//
//                int lahend = mälu.get(j).get(jääk);
//                if (lahend > 0) System.out.println(a[j] + ", jääk " + jääk + " | lahend leitud mälust. lahendeid " + lahend + " tagastan");
//                kokku += lahend;
//                break; // enne continue
//            }

            // Võtame
            if (praegune.compareTo(jääk) <= 0) {
                BigDecimal uusJääk = jääk.subtract(praegune);
                loendur += 1;
                int[] võtan = tükeldusedListBD2(a, uusJääk, vähim, j, mälu, bd);
                int lahend = võtan[0];
                meeles[i] = kokku;

                kokku += lahend;

//                if (võtan[1] == -1) { // Kui lähme võtmisel piirist üle - isenesest pole vaja.
//                    break;
//                }
            } else break;
        }

        // iga massiivi elemendi lahendite hulk selles tsüklis.
//        for (int k = meeles.length-1; k > 0 ; k--) {
//            BigDecimal alates = a[k];
//            int lahendeid = kokku - (meeles[k]);
//
//            if (!mälu.get(k).containsKey(jääk)) {
//                mälu.get(i).put(alates, kokku);
//            }
//        }

        if (!mälu.get(i).containsKey(jääk)) {
            mälu.get(i).put(jääk, kokku);
        }

        return new int[]{kokku, 1};
    }

    public int[] tükeldusedReverse(BigDecimal[] a, BigDecimal jääk, BigDecimal vähim, int i,
                                   List<Map<BigDecimal, Integer>> mälu, Map<String, BigDecimal> bd) {

        if (i == a.length || jääk.compareTo(BigDecimal.ZERO) < 0) { // Baasjuht
//            System.out.println("Elemendid otsas i: " + i + " või jääk: " + jääk + " negatiivne. Tagastan");
            if (!mälu.get(i).containsKey(jääk)) {
                mälu.get(i).put(jääk, 0);
            }
            return new int[]{0, -1};
        }

        if (jääk.compareTo(vähim) < 0) {
//            System.out.println(a[i] + ", jääk " + jääk + " | Jääk: " + jääk + " < " + " vähim: " + vähim + " tagastan");
            return new int[]{1, 1};
        }

        int kokku = 0;
//        //Kui välja arvutatud, liigume järgmise elemendi juurde.
//        if (mälu.get(i).containsKey(jääk)) {
//
//            int lahend = mälu.get(i).get(jääk);
////            if (lahend > 0) System.out.println(a[i] + ", jääk " + jääk + " | lahend leitud mälust. lahendeid " + lahend + " tagastan");
//            kokku += lahend;
//            return new int[]{kokku, 1};
//        }

        int[] meeles = new int[a.length - i];
        int m = 0; // järjendi loendur
        int lõpp = meeles.length-1;

        // PEAME JÄÄKI PIDEVALT UUENDAMA TSÜKLIS
        for (int j = i; j < a.length; j++, m++) {
            BigDecimal praegune = a[j]; // pointer

//             //Kui välja arvutatud, liigume järgmise elemendi juurde.
            if (mälu.get(j).containsKey(jääk)) {

                int lahend = mälu.get(j).get(jääk);
                if (lahend > 0)
//                    System.out.println(a[j] + ", jääk " + jääk + " | lahend leitud mälust. lahendeid " + lahend + " tagastan");
                meeles[m] = kokku;
                kokku += lahend;
                break; // enne continue
            }

            // Võtame
            if (praegune.compareTo(jääk) <= 0) {
                BigDecimal uusJääk = jääk.subtract(praegune);
                loendur += 1;
                int[] võtan = tükeldusedReverse(a, uusJääk, vähim, j, mälu, bd);
                int lahend = võtan[0];
                meeles[m] = kokku;

                kokku += lahend;

            } else {
                lõpp = a.length-j;
                break;
            }
        }

        // iga massiivi elemendi lahendite hulk sellel tasandil alates elemendist endast.
        for (int k = 0; k < lõpp; k++) {
//            if (k >= meeles.length) System.out.println("Indeks on väljas" + " " + meeles.length + " " + + lõpp);
            int lahendeid = kokku - meeles[k]; // meeles[k] on pointerid

            if (!mälu.get(k).containsKey(jääk)) {
                mälu.get(k).put(jääk, lahendeid);
            }
        }

        return new int[]{kokku, 1};
    }
//        System.out.println(a[i] + ", jääk: " + jääk);


//        // Võtame. Jätame indeksi samaks, sest ehk saab rekursiooni väljakutsel seda uuesti võtta.
//        if (praegune.compareTo(jääk) <= 0) {
//            loendur +=1;
//            int[] võtan = tükeldusedListBD2(a,jääk.subtract(praegune), vähim, i, mälu, bd);
//            kokku += võtan[0];
//
//            if (võtan[1] == -1) {
//                if (!mälu.get(i).containsKey(jääk)) {
//                    mälu.get(i).put(jääk, kokku);
//                }
//                return new int[]{kokku, 1};
//            }
//
//            // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
//            loendur +=1;
//            int[] eiVõta = tükeldusedListBD2(a, jääk, vähim, i+1, mälu, bd);
//            kokku += eiVõta[0];
//        }
//
//        if (!mälu.get(i).containsKey(jääk)) {
//            mälu.get(i).put(jääk, kokku);
//        }
//
//        return new int[]{kokku, 1};
//    }

    public int[] tükeldusedListBD(BigDecimal[] a, BigDecimal jääk, BigDecimal vähim, int i,
                                  List<Map<BigDecimal, Integer>> mälu, Map<String, BigDecimal> bd) {
        if (i == a.length || jääk.compareTo(BigDecimal.ZERO) < 0) { // Baasjuht
//            System.out.println("Elemendid otsas i: " + i + " või jääk: " + jääk + " negatiivne. Tagastan");
            return new int[]{0, -1};
        }

        if (jääk.compareTo(vähim) < 0) {
//            System.out.println(a[i] + ", jääk " + jääk + " | Jääk: " + jääk + " < " + " vähim: " + vähim + " tagastan");
            return new int[]{1, 1};
        }

        // Kui antud lahendid on juba välja arvutatud.
        if (mälu.get(i).containsKey(jääk)) {
            int lahend = mälu.get(i).get(jääk);
//            if (lahend > 0) System.out.println(a[i] + ", jääk " + jääk + " | lahend leitud mälust. lahendeid " + lahend + " tagastan");
            return new int[]{lahend, 1};
        }

//        System.out.println(a[i] + ", jääk: " + jääk);

        int kokku = 0;
        BigDecimal praegune = a[i]; // pointer

        // Võtame. Jätame indeksi samaks, sest ehk saab rekursiooni väljakutsel seda uuesti võtta.
        if (praegune.compareTo(jääk) <= 0) {
            loendur += 1;
            int[] võtan = tükeldusedListBD(a, jääk.subtract(praegune), vähim, i, mälu, bd);
            kokku += võtan[0];

            if (võtan[1] == -1) {
                if (!mälu.get(i).containsKey(jääk)) {
                    mälu.get(i).put(jääk, kokku);
                }
                return new int[]{kokku, 1};
            }

            // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
            loendur += 1;
            int[] eiVõta = tükeldusedListBD(a, jääk, vähim, i + 1, mälu, bd);
            kokku += eiVõta[0];
        }

        if (!mälu.get(i).containsKey(jääk)) {
            mälu.get(i).put(jääk, kokku);
        }

        return new int[]{kokku, 1};
    }


    public static int[] tükeldused(BigDecimal[] a, BigDecimal jääk, BigDecimal vähim, int i,
                                   List<Map<BigDecimal, Integer>> mälu) {
        // Baasjuht 1
        if (i == a.length || jääk.compareTo(BigDecimal.ZERO) < 0) return new int[]{0, -1};

        // Baasjuht 2
        if (jääk.compareTo(vähim) < 0) return new int[]{1, 1};

        // Kontrollime mälus kas vaadeldav lahend on juba välja arvutatud.
        if (mälu.get(i).containsKey(jääk)) {
            int lahend = mälu.get(i).get(jääk);
            return new int[]{lahend, 1};
        }

        int kokku = 0;
        BigDecimal praegune = a[i];

        // Võtame
        if (praegune.compareTo(jääk) <= 0) {
            int[] võtan = tükeldused(a, jääk.subtract(praegune), vähim, i, mälu);
            kokku += võtan[0];

            // Kui antud lahend viis tupikusse, siis tagastame kohe väärtuse
            if (võtan[1] == -1) {
                // Salvestame lahendi mällu
                if (!mälu.get(i).containsKey(jääk)) mälu.get(i).put(jääk, kokku);

                return new int[]{kokku, 1};
            }

            // Ei võta
            int[] eiVõta = tükeldused(a, jääk, vähim, i + 1, mälu);
            kokku += eiVõta[0];
        }

        // Salvestame lahendi mällu
        if (!mälu.get(i).containsKey(jääk)) {
            mälu.get(i).put(jääk, kokku);
        }

        return new int[]{kokku, 1};
    }

    public int[] tükeldusedListOpt(double[] a, BigDecimal jääk, BigDecimal vähim, int i,
                                   List<Map<Double, Integer>> mälu, Map<String, BigDecimal> bd) {
        if (i == a.length || jääk.compareTo(BigDecimal.ZERO) < 0) { // Baasjuht
//            System.out.println("Elemendid otsas i: " + i + " või jääk: " + jääk + " negatiivne. Tagastan");
            return new int[]{0, -1}; // Teine element näitab tõeväärtust

        }

        if (jääk.compareTo(vähim) < 0) { // Tehniliselt võime siin if lause sees kontrollida järgmise elemendi sobivust.
//            System.out.println(a[i] + ", jääk " + jääk + " | Jääk: " + jääk + " < " + " vähim: " + vähim + " tagastan");
            return new int[]{1, 1};  // sest meie jääk on juba minimaalsest elemendist väiksem.
        }

        double jääkDouble = jääk.doubleValue();
        // Kui antud lahendid on juba välja arvutatud.
        if (mälu.get(i).containsKey(jääkDouble)) {
            int lahend = mälu.get(i).get(jääkDouble);
//            if (lahend > 0) System.out.println(a[i] + ", jääk " + jääk + " | lahend leitud mälust. lahendeid " + lahend + " tagastan");
            return new int[]{lahend, 1};
        }

//        System.out.println(a[i] + ", jääk: " + jääk);

        int kokku = 0;
        String element = Double.toString(a[i]);
        BigDecimal praegune; // empty pointer

        if (bd.containsKey(element)) {
            praegune = bd.get(element);
        } else {
            objekte += 1;
            praegune = new BigDecimal(element);
            bd.put(element, praegune);
        }

        // Võtame. Jätame indeksi samaks, sest ehk saab rekursiooni väljakutsel seda uuesti võtta.
        if (praegune.compareTo(jääk) <= 0) {
            loendur += 1;
            int[] võtan = tükeldusedListOpt(a, jääk.subtract(praegune), vähim, i, mälu, bd);
            kokku += võtan[0];

            if (võtan[1] == -1) {
                if (!mälu.get(i).containsKey(jääkDouble)) {
                    mälu.get(i).put(jääkDouble, kokku);
                }
                return new int[]{kokku, 1};
            }

            // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
            loendur += 1;
            int[] eiVõta = tükeldusedListOpt(a, jääk, vähim, i + 1, mälu, bd);
            kokku += eiVõta[0];
        }

        if (!mälu.get(i).containsKey(jääkDouble)) {
            mälu.get(i).put(jääkDouble, kokku);
        }

        return new int[]{kokku, 1};
    }

    public int tükeldusedDünaamilineObj(double[] a, BigDecimal jääk, BigDecimal vähim, int i, Tase[] mälu) {
        if (i == a.length || jääk.compareTo(BigDecimal.ZERO) < 0) { // Baasjuht
//            System.out.println("Elemendid otsas või jääk: " + jääk + " tagastan");
            return 0;
        }

        if (jääk.compareTo(vähim) < 0) { // meil ei mahu rohkem asju,
//            System.out.println("Väljakutse elemendiga: " + a[i] + " ja jääk: " + jääk + " tagastan");
            return 1;                          // sest meie jääk on juba minimaalsest elemendist väiksem.
        }


        // Kui antud lahendid on juba välja arvutatud.
        if (mälu[i].map.containsKey(jääk.toString())) {
//            System.out.println("Tagastan mälust, element: " + a[i] + ", jääk " + jääk);
            return mälu[i].map.get(jääk.toString()); // tagastab lahendite arvu.
        }

//        System.out.println("Väljakutse elemendiga " + a[i] + ", jääk: " + jääk);

        int kokku = 0;
        BigDecimal praegune = new BigDecimal(Double.toString(a[i]));

        // Võtame. Jätame indeksi samaks, sest ehk saab rekursiooni väljakutsel seda uuesti võtta.
        if (praegune.compareTo(jääk) <= 0) {
            loendur += 1;
            int võtan = tükeldusedDünaamilineObj(a, jääk.subtract(praegune), vähim, i, mälu);
            kokku += võtan;
//            if (võtan == 0) return kokku;
//
//            // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
//            loendur +=1;
//            kokku += tükeldusedDünaamilineObj(a, jääk, vähim, i+1, mälu);
        }
        // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
        loendur += 1;
        kokku += tükeldusedDünaamilineObj(a, jääk, vähim, i + 1, mälu);

        // Kui vastav elemendi kohta pole mälus vastavat juhtumit salvestatud, siis salvestame.
        if (!mälu[i].map.containsKey(jääk.toString())) {
            mälu[i].map.put(jääk.toString(), kokku);
        }

        return kokku;
    }

    /**
     * Algne versioon peafailis
     **/
//    public int[] tükeldusedList(double[] a, BigDecimal jääk, BigDecimal vähim, int i, List<Map<Double, Integer>> mälu) {
//        if (i == a.length || jääk.compareTo(BigDecimal.ZERO) < 0) { // Baasjuht
////            System.out.println("Elemendid otsas i: " + i + " või jääk: " + jääk + " negatiivne. Tagastan");
//            return new int[]{0, -1}; // Teine element näitab tõeväärtust
//
//        }
//
//        if (jääk.compareTo(vähim) < 0) { // Tehniliselt võime siin if lause sees kontrollida järgmise elemendi sobivust.
////            System.out.println(a[i] + ", jääk " + jääk + " | Jääk: " + jääk + " < " + " vähim: " + vähim + " tagastan");
//            return new int[]{1, 1};  // sest meie jääk on juba minimaalsest elemendist väiksem.
//        }
//
//        double jääkDouble = jääk.doubleValue();
//        // Kui antud lahendid on juba välja arvutatud.
//        if (mälu.get(i).containsKey(jääkDouble)) {
//            int lahend = mälu.get(i).get(jääkDouble);
////            if (lahend > 0) System.out.println(a[i] + ", jääk " + jääk + " | lahend leitud mälust. lahendeid " + lahend + " tagastan");
//            return new int[] {lahend, 1};
//        }
//
////        System.out.println(a[i] + ", jääk: " + jääk);
//
//        int kokku = 0;
//
//        /*****/
//        for (int j = i; j < a.length; j++) {
//            BigDecimal praegune = new BigDecimal(Double.toString(a[j]));
//
//            if (praegune.compareTo(jääk) <= 0) {
//                // Võtame praeguse elemendi.
//                loendur +=1;
//                int[] võtan = tükeldusedList(a,jääk.subtract(praegune), vähim, j, mälu);
//                kokku += võtan[0];
//
//                if (võtan[1] == -1) {
//                    if (!mälu.get(j).containsKey(jääkDouble)) {
//                        mälu.get(j).put(jääkDouble, kokku);
//                    }
//                    return new int[]{kokku, 1};
//                }
//
//                // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
//                loendur +=1;
//                int[] eiVõta = tükeldusedList(a, jääk, vähim, j+1, mälu);
//                kokku += eiVõta[0];
//            }
//
//            if (!mälu.get(j).containsKey(jääkDouble)) {
//                mälu.get(j).put(jääkDouble, kokku);
//            }
//        }
//        /********/
//
//        return new int[]{kokku, 1};
//    }
    public int[] tükeldusedList(double[] a, BigDecimal jääk, BigDecimal vähim, int i, List<Map<Double, Integer>> mälu) {
        if (i == a.length || jääk.compareTo(BigDecimal.ZERO) < 0) { // Baasjuht
//            System.out.println("Elemendid otsas i: " + i + " või jääk: " + jääk + " negatiivne. Tagastan");
            return new int[]{0, -1}; // Teine element näitab tõeväärtust

        }

        if (jääk.compareTo(vähim) < 0) { // Tehniliselt võime siin if lause sees kontrollida järgmise elemendi sobivust.
//            System.out.println(a[i] + ", jääk " + jääk + " | Jääk: " + jääk + " < " + " vähim: " + vähim + " tagastan");
            return new int[]{1, 1};  // sest meie jääk on juba minimaalsest elemendist väiksem.
        }

        double jääkDouble = jääk.doubleValue();
        // Kui antud lahendid on juba välja arvutatud.
        if (mälu.get(i).containsKey(jääkDouble)) {
            int lahend = mälu.get(i).get(jääkDouble);
//            if (lahend > 0) System.out.println(a[i] + ", jääk " + jääk + " | lahend leitud mälust. lahendeid " + lahend + " tagastan");
            return new int[]{lahend, 1};
        }

//        System.out.println(a[i] + ", jääk: " + jääk);

        int kokku = 0;
        BigDecimal praegune = new BigDecimal(Double.toString(a[i]));
        objekte += 1;

        // Võtame. Jätame indeksi samaks, sest ehk saab rekursiooni väljakutsel seda uuesti võtta.
        if (praegune.compareTo(jääk) <= 0) {
            loendur += 1;
            int[] võtan = tükeldusedList(a, jääk.subtract(praegune), vähim, i, mälu);
            kokku += võtan[0];

            if (võtan[1] == -1) {
                if (!mälu.get(i).containsKey(jääkDouble)) {
                    mälu.get(i).put(jääkDouble, kokku);
                }
                return new int[]{kokku, 1};
            }

            // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
            loendur += 1;
            int[] eiVõta = tükeldusedList(a, jääk, vähim, i + 1, mälu);
            kokku += eiVõta[0];
        }

        if (!mälu.get(i).containsKey(jääkDouble)) {
            mälu.get(i).put(jääkDouble, kokku);
        }

        return new int[]{kokku, 1};
    }

    public int[] tükeldusedDünaamilineA(double[] a, BigDecimal jääk, BigDecimal vähim, int i, Tase[] mälu) {
//        try {
//            if (i > 7000) throw new Exception(String.valueOf(mälu[1].map.size()));
//        } catch (Exception e) {
//            System.out.println(e);;
//        } ;

        if (i == a.length || jääk.compareTo(BigDecimal.ZERO) < 0) { // Baasjuht
            if (i == a.length) System.out.println(mälu[1].map.size());
//            System.out.println("Elemendid otsas i: " + i + " või jääk: " + jääk + " negatiivne. Tagastan");
            return new int[]{0, -1}; // Teine element näitab tõeväärtust

        }

        if (jääk.compareTo(vähim) < 0) { // Tehniliselt võime siin if lause sees kontrollida järgmise elemendi sobivust.
//            System.out.println(a[i] + ", jääk " + jääk + " | Jääk: " + jääk + " < " + " vähim: " + vähim + " tagastan");
            return new int[]{1, 1};  // sest meie jääk on juba minimaalsest elemendist väiksem.
        }


        // Kui antud lahendid on juba välja arvutatud.
        if (mälu[i].map.containsKey(jääk.toString())) {
            int lahend = mälu[i].map.get(jääk.toString());
//            if (lahend > 0) System.out.println(a[i] + ", jääk " + jääk + " | lahend leitud mälust. lahendeid " + lahend + " tagastan");
            return new int[]{mälu[i].map.get(jääk.toString()), 1};
        }

//        System.out.println(a[i] + ", jääk: " + jääk);

        int kokku = 0;
        BigDecimal praegune = new BigDecimal(Double.toString(a[i]));

        // Võtame. Jätame indeksi samaks, sest ehk saab rekursiooni väljakutsel seda uuesti võtta.
        if (praegune.compareTo(jääk) <= 0) {
            // Võtame praeguse elemendi.
            loendur += 1;
            int[] võtan = tükeldusedDünaamilineA(a, jääk.subtract(praegune), vähim, i, mälu);
            kokku += võtan[0];

            if (võtan[1] == -1) {
                salvestaMällu(mälu, i, jääk, kokku);
                return new int[]{kokku, 1};
            }

            // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
            loendur += 1;
            int[] eiVõta = tükeldusedDünaamilineA(a, jääk, vähim, i + 1, mälu);
            kokku += eiVõta[0];
        }

        salvestaMällu(mälu, i, jääk, kokku);

        return new int[]{kokku, 1};
    }

    // Ilma meeldejätmiseta
    public int[] tükeldusedA(double[] a, BigDecimal jääk, BigDecimal vähim, int i) {
//        try {
//            if (i > 7000) throw new Exception(String.valueOf(mälu[1].map.size()));
//        } catch (Exception e) {
//            System.out.println(e);;
//        } ;

        if (i == a.length || jääk.compareTo(BigDecimal.ZERO) < 0) { // Baasjuht
//            System.out.println("Elemendid otsas i: " + i + " või jääk: " + jääk + " negatiivne. Tagastan");
            return new int[]{0, -1}; // Teine element näitab tõeväärtust

        }

        if (jääk.compareTo(vähim) < 0) { // Tehniliselt võime siin if lause sees kontrollida järgmise elemendi sobivust.
//            System.out.println(a[i] + ", jääk " + jääk + " | Jääk: " + jääk + " < " + " vähim: " + vähim + " tagastan");
            return new int[]{1, 1};  // sest meie jääk on juba minimaalsest elemendist väiksem.
        }

//        System.out.println(a[i] + ", jääk: " + jääk);

        int kokku = 0;
        BigDecimal praegune = new BigDecimal(Double.toString(a[i]));

        // Võtame. Jätame indeksi samaks, sest ehk saab rekursiooni väljakutsel seda uuesti võtta.
        if (praegune.compareTo(jääk) <= 0) {
            // Võtame praeguse elemendi.
            loendur += 1;
            int[] võtan = tükeldusedA(a, jääk.subtract(praegune), vähim, i);
            kokku += võtan[0];

            if (võtan[1] == -1) {
                return new int[]{kokku, 1};
            }

            // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
            loendur += 1;
            int[] eiVõta = tükeldusedA(a, jääk, vähim, i + 1);
            kokku += eiVõta[0];
        }

        return new int[]{kokku, 1};
    }

    public void salvestaMällu(Tase[] mälu, int i, BigDecimal jääk, int lahendeid) {
        if (!mälu[i].map.containsKey(jääk.toString())) {
            mälu[i].map.put(jääk.toString(), lahendeid);
        }
    }

    /**
     * Rekursiivne meetod, mis leiab traadijuppide arvu kasutades BigDecimal klassi. Sellisel juhul on
     * komakohtadega arvutused väga täpsed, kuid efektiivusus kannatab selle arvelt.
     */
    public int tükeldusedBigDecimal(double[] a, BigDecimal jääk, BigDecimal vähim, int indeks) {
        if (jääk.compareTo(BigDecimal.ZERO) >= 0 && jääk.compareTo(vähim) < 0) {
            return 1;
        }

        int kokku = 0;

        for (int i = indeks; i < a.length; i++) {
            if (jääk.subtract(new BigDecimal(Double.toString(a[i]))).compareTo(new BigDecimal("0")) >= 0) {
                loendur += 1;
                kokku += tükeldusedBigDecimal(a, jääk.subtract(new BigDecimal(Double.toString(a[i]))),
                        vähim, i);
            } else break;
        }

        return kokku;
    }

    /**
     * Naiivne: BigDecimal klassiga koos väljastusega. List las jääb
     */
    public int tükeldusedBigDecimalOptimeeritud(double[] a, BigDecimal jääk, BigDecimal vähim, int indeks) {
        if (/*jääk.compareTo(BigDecimal.ZERO) >= 0 && */jääk.compareTo(vähim) < 0) {
            return 1;
        }

        int kokku = 0;
        for (int i = indeks; i < a.length; i++) {
            BigDecimal vaadeldav = new BigDecimal(Double.toString(a[i]));
            BigDecimal vahe = jääk.subtract(vaadeldav);

            if (vahe.compareTo(BigDecimal.ZERO) >= 0) {
                loendur += 1;
                kokku += tükeldusedBigDecimalOptimeeritud(a, jääk.subtract(vaadeldav), vähim, i);

            } else break; // Tingimuslausesse ei lähe, sest väärtused on järjestatud.

        }
        return kokku;
    }

}//Kodu2B


