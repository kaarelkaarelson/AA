package kodu2;

import kodu1.Abi;

import java.math.BigDecimal;
import java.util.*;

public class Kodu2BDev {
    Abi abi = new Abi();
    int loendur = 0;
    int objekte = 0;

    /**
     * Meetod leiab traadi kõikvõimalike tükelduste arvu, mille puhul ülejaak on väiksem kui vähim traadi lubatud pikkus.
     *
     * @param a - etteantud massiiv, kus on elementide tükelduste pikkused.
     * @param p - etteantud traadi pikkus.
     * @return - tagastab kõikvõimalike tükelduste arvu
     */
    public int tükeldused(double[] a, double p) {
        // Lihtuse mõttes leiame väiksema.
        Arrays.sort(a);
        double vähim = a[0]; // Otsime
//        return 1;
//        System.out.println(vähim);
        return tükeldusedBigDecimal(a, new BigDecimal(Double.toString(p)), new BigDecimal(Double.toString(vähim)), 0);
//        return tüke(a, (int) (p * 100) , (int) (vähim * 100), 0, new ArrayList<>());
    }

    public int tükeldusedBD(double[] a, double p) {
        long start = System.nanoTime();
        Arrays.sort(a);
        double vähim = a[0];
        long stopp = System.nanoTime();

        double aeg = abi.nanoToMilliSeconds(stopp - start);
        System.out.println("Sorteerimisele läks aega: " + aeg);
        loendur +=1;
        int vastus = tükeldusedBigDecimal(a, new BigDecimal(Double.toString(p)), new BigDecimal(Double.toString(vähim)), 0);
        System.out.println("väljakutseid: " + loendur);
        loendur = 0;
        return vastus;
    }

    public int tükeldusedBDOpt(double[] a, double p) {

        Arrays.sort(a);
        double vähim = a[0];
        loendur +=1;
        int vastus = tükeldusedBigDecimalOptimeeritud(a, new BigDecimal(Double.toString(p)), new BigDecimal(Double.toString(vähim)), 0);
        System.out.println("väljakutseid: " + loendur);
        loendur = 0;

        return vastus;
    }

    public int tükeldusedDouble(double[] a, double p) {

        Arrays.sort(a);
        double vähim = a[0];

        return tükeldusedDouble(a, p, vähim, 0, new ArrayList<>());
    }

    /**
     * Leiab õiged.
     * @param a
     * @param p
     * @return
     */
    public int tükeldusedDünaamiline(double[] a, double p) {

        Arrays.sort(a);
        double esimene = a[0];

        BigDecimal jääk = new BigDecimal(Double.toString(p));
        BigDecimal vähim = new BigDecimal(Double.toString(esimene));
        List<Map<BigDecimal, Integer>> mälu = new ArrayList<Map<BigDecimal, Integer>>();
        //[(int) p+1]; // Mis põhjusel ridade arv p+1 on?
//        for (int i = 0; i < a.length; i++) {
//
//        }
//        for (BigDecimal rida : mälu) {
//            Arrays.fill(rida, null);
//        }

        return tükeldusedDünaamilineIter(a, jääk, vähim, 0, mälu);
    }

    public int tükeldusedDünaamilineObj(double[] a, double p) {

        Arrays.sort(a);
        double esimene = a[0];

        BigDecimal jääk = new BigDecimal(Double.toString(p));
        BigDecimal vähim = new BigDecimal(Double.toString(esimene));
        Tase[] mälu = new Tase[a.length];
        for (int i = 0; i < a.length; i++) {
            Tase tase = new Tase(i,new HashMap<>());
            mälu[i] = tase;
        }
        loendur +=1;
        int vastus =  tükeldusedDünaamilineObj(a, jääk, vähim, 0, mälu);
        System.out.println("väljakutseid: " + loendur);
        loendur = 0;

        return vastus;
    }
    public int tükeldusedDünaamilineA(double[] a, double p) {

        Arrays.sort(a);
        double esimene = a[0];

        BigDecimal jääk = new BigDecimal(Double.toString(p));
        BigDecimal vähim = new BigDecimal(Double.toString(esimene));
        Tase[] mälu = new Tase[a.length];
        for (int i = 0; i < a.length; i++) {
            Tase tase = new Tase(i,new HashMap<>());
            mälu[i] = tase;
        }
        loendur +=1;
        int[] vastus =  tükeldusedDünaamilineA(a, jääk, vähim, 0, mälu);
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
            Tase tase = new Tase(i,new HashMap<>());
            mälu[i] = tase;
        }
        loendur +=1;
        int[] vastus =  tükeldusedA(a, jääk, vähim, 0);
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
        loendur +=1;
        int[] vastus =  tükeldusedList(a, jääk, vähim, 0, mälu);
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
        loendur +=1;
        int[] vastus =  tükeldusedListOpt(a, jääk, vähim, 0, mälu,
                new HashMap<String, BigDecimal>());
        System.out.println("väljakutseid: " + loendur);
        System.out.println("objekte: " + objekte);
        loendur = 0;
        objekte = 0;

        return vastus[0];
    }



    public int tükeldusedDünaamilineObjIter(double[] a, double p) {

        Arrays.sort(a);
        double esimene = a[0];

        BigDecimal jääk = new BigDecimal(Double.toString(p));
        BigDecimal vähim = new BigDecimal(Double.toString(esimene));
        Tase[] mälu = new Tase[a.length];
        for (int i = 0; i < a.length; i++) {
            Tase tase = new Tase(i,new HashMap<>());
            mälu[i] = tase;
        }

        return tükeldusedDünaamilineObjIter(a, jääk, vähim, 0, mälu);
    }

    public int[] tükeldusedListOpt(double[] a, BigDecimal jääk, BigDecimal vähim, int i,
                                          List<Map<Double, Integer>> mälu, Map<String, BigDecimal> bd ){
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
            return new int[] {lahend, 1};
        }

//        System.out.println(a[i] + ", jääk: " + jääk);

        int kokku = 0;
        String element = Double.toString(a[i]);
        BigDecimal praegune; // empty pointer

        if (bd.containsKey(element)) {
            praegune = bd.get(element);
        } else {
            objekte +=1;
            praegune = new BigDecimal(element);
            bd.put(element, praegune);
        }

        // Võtame. Jätame indeksi samaks, sest ehk saab rekursiooni väljakutsel seda uuesti võtta.
        if (praegune.compareTo(jääk) <= 0) {
            loendur +=1;
            int[] võtan = tükeldusedListOpt(a,jääk.subtract(praegune), vähim, i, mälu, bd);
            kokku += võtan[0];

            if (võtan[1] == -1) {
                if (!mälu.get(i).containsKey(jääkDouble)) {
                    mälu.get(i).put(jääkDouble, kokku);
                }
                return new int[]{kokku, 1};
            }

            // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
            loendur +=1;
            int[] eiVõta = tükeldusedListOpt(a, jääk, vähim, i+1, mälu, bd);
            kokku += eiVõta[0];
        }

        if (!mälu.get(i).containsKey(jääkDouble)) {
            mälu.get(i).put(jääkDouble, kokku);
        }

        return new int[]{kokku, 1};
    }

    /**
     * Top-Down lahendus. The previous approach for unbounded knapsack has numerous duplicate calls for
     * the same state ‘N’ and ‘C’, leading to exponential complexity.
     *
     * @param a
     * @param jääk
     * @param vähim
     * @param i
     * @param mälu
     * @return
     */
    public int tükeldusedDünaamiline(double[] a, BigDecimal jääk, BigDecimal vähim, int i,
                                     List<Map<BigDecimal, Integer>> mälu) {
        if (i == a.length || jääk.compareTo(BigDecimal.ZERO) < 0) { // Baasjuht
            return 0;
        }

        if (jääk.compareTo(vähim) < 0) { // meil ei mahu rohkem asju,
            return 1;                          // sest meie jääk on juba minimaalsest elemendist väiksem.
        }

        // Kui antud see on juba välja arvutatud
        if (i < mälu.size() && mälu.get(i).containsKey(jääk)) {
            return mälu.get(i).get(jääk); // tagastab lahendite arvu.
        }

        int kokku = 0;
        BigDecimal praegune = new BigDecimal(Double.toString(a[i]));

        // Võtame. Jätame indeksi samaks, sest ehk saab rekursiooni väljakutsel seda uuesti võtta.
        if (praegune.compareTo(jääk) <= 0) {
            kokku += tükeldusedDünaamiline(a,jääk.subtract(praegune), vähim, i, mälu);
        }

        // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
        kokku += tükeldusedDünaamiline(a, jääk, vähim, i+1, mälu);

        // Kui vastav elemendi kohta pole mälus profiili, siis loome selle
        if (i >= mälu.size()) {
            Map<BigDecimal, Integer> osaülesanne = new HashMap<>();
            osaülesanne.put(jääk, kokku);
            mälu.add(osaülesanne);
        }
        // Kui profiil eksisteerib aga sama juhtumit pole salvestatud, siis lisame selle
        else if (!mälu.get(i).containsKey(jääk)) {
            mälu.get(i).put(jääk, kokku);
        }

        return kokku;
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
            loendur +=1;
            int võtan = tükeldusedDünaamilineObj(a,jääk.subtract(praegune), vähim, i, mälu);
            kokku += võtan;
//            if (võtan == 0) return kokku;
//
//            // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
//            loendur +=1;
//            kokku += tükeldusedDünaamilineObj(a, jääk, vähim, i+1, mälu);
        }
        // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
        loendur +=1;
        kokku += tükeldusedDünaamilineObj(a, jääk, vähim, i+1, mälu);

        // Kui vastav elemendi kohta pole mälus vastavat juhtumit salvestatud, siis salvestame.
        if (!mälu[i].map.containsKey(jääk.toString())) {
            mälu[i].map.put(jääk.toString(), kokku);
        }

        return kokku;
    }

    /** Algne versioon peafailis **/
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
            return new int[] {lahend, 1};
        }

//        System.out.println(a[i] + ", jääk: " + jääk);

        int kokku = 0;
        BigDecimal praegune = new BigDecimal(Double.toString(a[i]));
        objekte +=1;

        // Võtame. Jätame indeksi samaks, sest ehk saab rekursiooni väljakutsel seda uuesti võtta.
        if (praegune.compareTo(jääk) <= 0) {
            loendur +=1;
            int[] võtan = tükeldusedList(a,jääk.subtract(praegune), vähim, i, mälu);
            kokku += võtan[0];

            if (võtan[1] == -1) {
                if (!mälu.get(i).containsKey(jääkDouble)) {
                    mälu.get(i).put(jääkDouble, kokku);
                }
                return new int[]{kokku, 1};
            }

            // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
            loendur +=1;
            int[] eiVõta = tükeldusedList(a, jääk, vähim, i+1, mälu);
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
            if (i == a.length ) System.out.println(mälu[1].map.size());
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
            return new int[] {mälu[i].map.get(jääk.toString()), 1};
        }

//        System.out.println(a[i] + ", jääk: " + jääk);

        int kokku = 0;
        BigDecimal praegune = new BigDecimal(Double.toString(a[i]));

        // Võtame. Jätame indeksi samaks, sest ehk saab rekursiooni väljakutsel seda uuesti võtta.
        if (praegune.compareTo(jääk) <= 0) {
            // Võtame praeguse elemendi.
            loendur +=1;
            int[] võtan = tükeldusedDünaamilineA(a,jääk.subtract(praegune), vähim, i, mälu);
            kokku += võtan[0];

            if (võtan[1] == -1) {
                salvestaMällu(mälu, i, jääk, kokku);
                return new int[]{kokku, 1};
            }

            // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
            loendur +=1;
            int[] eiVõta = tükeldusedDünaamilineA(a, jääk, vähim, i+1, mälu);
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
            loendur +=1;
            int[] võtan = tükeldusedA(a,jääk.subtract(praegune), vähim, i);
            kokku += võtan[0];

            if (võtan[1] == -1) {
                return new int[]{kokku, 1};
            }

            // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
            loendur +=1;
            int[] eiVõta = tükeldusedA(a, jääk, vähim, i+1);
            kokku += eiVõta[0];
        }

        return new int[]{kokku, 1};
    }

    public void salvestaMällu(Tase[] mälu, int i, BigDecimal jääk, int lahendeid){
        if (!mälu[i].map.containsKey(jääk.toString())) {
            mälu[i].map.put(jääk.toString(), lahendeid);
        }
    }

    public int tükeldusedDünaamilineIter(double[] a, BigDecimal jääk, BigDecimal vähim, int i,
                                                List<Map<BigDecimal, Integer>> mälu) {
        if (i == a.length || jääk.compareTo(BigDecimal.ZERO) < 0) { // Baasjuht
            return 0;
        }

        System.out.println(jääk.toString());

        if (jääk.compareTo(vähim) < 0) { // meil ei mahu rohkem asju,
            return 1;                          // sest meie jääk on juba minimaalsest elemendist väiksem.
        }

        int kokku = 0;

        for (int j = i; j < a.length; j++) {
            BigDecimal praegune = new BigDecimal(Double.toString(a[i]));
            BigDecimal vahe = jääk.subtract(praegune);

            // Kui antud see on juba välja arvutatud
            if (j < mälu.size() && mälu.get(j).containsKey(vahe)) {
                kokku += mälu.get(i).get(vahe); // tagastab lahendite arvu.
                continue;
            }

            else if (vahe.compareTo(BigDecimal.ZERO) < 0) {
                 continue;
            }
            // Võtame
            else if (vahe.compareTo(BigDecimal.ZERO) >= 0) {
                kokku += tükeldusedDünaamilineIter(a,jääk.subtract(praegune), vähim, j, mälu);
            }

            // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
            kokku += tükeldusedDünaamilineIter(a, jääk, vähim, j+1, mälu);
            /*else break; // Tingimuslausesse ei lähe, sest väärtused on järjestatud.*/

            // Kui vastav elemendi kohta pole mälus profiili, siis loome selle
            if (j >= mälu.size()) {
                Map<BigDecimal, Integer> osaülesanne = new HashMap<>();
                osaülesanne.put(vahe, kokku);
                mälu.add(osaülesanne);
            }
            // Kui profiil eksisteerib aga sama juhtumit pole salvestatud, siis lisame selle
            else if (!mälu.get(j).containsKey(vahe)) {
                mälu.get(j).put(vahe, kokku);
            }
        }


        return kokku;
    }

    public int tükeldusedDünaamilineObjIter(double[] a, BigDecimal jääk, BigDecimal vähim, int i,
                                         Tase[] mälu) {
        if (i == a.length || jääk.compareTo(BigDecimal.ZERO) < 0) { // Baasjuht
            return 0;
        }

//        System.out.println(jääk.toString());

        if (jääk.compareTo(vähim) < 0) { // meil ei mahu rohkem asju,
            return 1;                          // sest meie jääk on juba minimaalsest elemendist väiksem.
        }

        // Kui antud see on juba välja arvutatud
        if (mälu[i].map.containsKey(jääk.toString())) {
            return mälu[i].map.get(jääk.toString()); // tagastab lahendite arvu.
        }

        int kokku = 0;

        for (int j = i; j < a.length; j++) {
            BigDecimal praegune = new BigDecimal(Double.toString(a[j]));
            BigDecimal vahe = jääk.subtract(praegune);

            // Võtame
            if (vahe.compareTo(BigDecimal.ZERO) >= 0) {
                kokku += tükeldusedDünaamilineObjIter(a,jääk.subtract(praegune), vähim, j, mälu);
            }

            // Ei võta. Lähme kohe edasi järgmise elemendi juurde.
            kokku += tükeldusedDünaamilineObjIter(a, jääk, vähim, j+1, mälu);

//            // Kui vastav elemendi kohta pole mälus vastavat juhtumit salvestatud, siis salvestame.
//            if (!mälu[j].map.containsKey(jääk.toString())) {
//                mälu[j].map.put(jääk.toString(), kokku);
//            }
        }

        // Kui vastav elemendi kohta pole mälus vastavat juhtumit salvestatud, siis salvestame.
        if (!mälu[i].map.containsKey(jääk.toString())) {
            mälu[i].map.put(jääk.toString(), kokku);
        }


        return kokku;
    }


//    public int tükeldusedBDSorteerimata(double[] a, double p) {
//        return tükeldusedBigDecimalSorteerimata(a, new BigDecimal(Double.toString(p)), new BigDecimal(Double.toString(vähim)), 0);
//    }

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
                loendur +=1;
                kokku += tükeldusedBigDecimal(a, jääk.subtract(new BigDecimal(Double.toString(a[i]))),
                        vähim, i);
            } else break;
        }

        return kokku;
    }

    /**
     * Naiivne: BigDecimal klassiga koos väljastusega. Optimeeritud.
     */
    public int tükeldusedBigDecimalVäljasta(double[] a, BigDecimal jääk, BigDecimal vähim, int indeks, List<Double> lahend) {
        if (/*jääk.compareTo(BigDecimal.ZERO) >= 0 && */jääk.compareTo(vähim) < 0) { //Kui jääk on vähimast väiksem
            System.out.println(lahend);
            return 1;
        }

        int kokku = 0;
        for (int i = indeks; i < a.length; i++) {
            BigDecimal vaadeldav = new BigDecimal(Double.toString(a[i]));
            BigDecimal vahe = jääk.subtract(vaadeldav);

            List<Double> kombinatsioon = new ArrayList<>(lahend);// Teeme koopia
            kombinatsioon.add(a[i]);// lisame uue elemendi juurde.
            double summa = kombinatsioon.stream().mapToDouble(e -> e.doubleValue()).sum();
            System.out.println("Kontrollin: tükid: " + kombinatsioon + ", summa: " + summa + " , jääk: " + jääk);

            if (vahe.compareTo(BigDecimal.ZERO) >= 0) {
                kokku += tükeldusedBigDecimalVäljasta(a, vahe, vähim, i, kombinatsioon);

            } else break; // Tingimuslausesse ei lähe, sest väärtused on järjestatud.

        }
        return kokku;
    }

    public int tükeldusedDouble(double[] a, double jääk, double vähim, int indeks, List<Double> lahend) {
        if (jääk < vähim) { //Kui jääk on vähimast väiksem
//            System.out.println(lahend);
            return 1;
        }

        int kokku = 0;
        for (int i = indeks; i < a.length; i++) {

            List<Double> kombinatsioon = new ArrayList<>(lahend);// Teeme koopia
            kombinatsioon.add(a[i]);// lisame uue elemendi juurde.
            double summa = kombinatsioon.stream().mapToDouble(e -> e.doubleValue()).sum();
//            System.out.println("Kontrollin: tükid: " + kombinatsioon + ", summa: " + summa + " , jääk: " + jääk);

            if (jääk - a[i] >= 0) {
                kokku += tükeldusedDouble(a, jääk - a[i], vähim, i, kombinatsioon);

            } else break; // Tingimuslausesse ei lähe, sest väärtused on järjestatud.

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
                loendur +=1;
                kokku += tükeldusedBigDecimalOptimeeritud(a, jääk.subtract(vaadeldav), vähim, i);

            } else break; // Tingimuslausesse ei lähe, sest väärtused on järjestatud.

        }
        return kokku;
    }

//    /**
//     * Naiivne: BigDecimal klassiga koos väljastusega. List las jääb
//     */
//    public static int tükeldusedBigDecimalSorteerimata(double[] a, BigDecimal jääk, BigDecimal vähim, int indeks) {
//        if (/*jääk.compareTo(BigDecimal.ZERO) >= 0 && */jääk.compareTo(vähim) < 0) {
//
//            return 1;
//        }
//
//        int kokku = 0;
//        for (int i = indeks; i < a.length; i++) {
//            BigDecimal vaadeldav = new BigDecimal(Double.toString(a[i]));
//            BigDecimal vahe = jääk.subtract(vaadeldav);
//
//            if (vahe.compareTo(BigDecimal.ZERO) >= 0) {
//                kokku += tükeldusedBigDecimalOptimeeritud(a, jääk.subtract(vaadeldav), vähim, i);
//
//            } else break; // Tingimuslausesse ei lähe, sest väärtused on järjestatud.
//
//        }
//        return kokku;
//    }


    /**
     * Rekursiivne meetod, mis leiab traadijuppide arvu kasutades täisarvudel üleviimist. Eeldame, et traadijupid esitatakse
     * 2 komakohaga. Paneb pooltel juhtudel ühe arvuga mööda.
     * Sellisel juhul on
     * komakohtadega arvutused väga täpsed, kuid efektiivusus kannatab selle arvelt.
     */
    public static int tükeldusedTäisarv(double[] a, int jääk, int vähim, int indeks, List<Double> lahend) {
        if (jääk >= 0 && jääk < vähim) {
            System.out.println(lahend);
            return 1;
        }

        int kokku = 0;
        for (int i = indeks; i < a.length; i++) {
            if ((jääk - (int) (a[i] * 100)) >= 0) {
                List<Double> kombinatsioon = new ArrayList<>(lahend);// Teeme koopia
                kombinatsioon.add(a[i]);// lisame uue elemendi juurde.
                kokku += tükeldusedTäisarv(a, jääk - (int) (a[i] * 100), vähim, i, kombinatsioon);
            }
        }

        return kokku;
    }

    /**
     * Rekursiivne meetod, mis leiab traadijuppide arvu kasutades täisarvudel üleviimist. Eeldame, et traadijupid esitatakse
     * 2 komakohaga
     * Sellisel juhul on
     * komakohtadega arvutused väga täpsed, kuid efektiivusus kannatab selle arvelt.
     */
    public static int tükeldusedTäisarvVäljasta(double[] a, int jääk, int vähim, int indeks, List<Integer> lahend) {
        if (jääk >= 0 && jääk < vähim) {
            System.out.println(lahend);
            return 1;
        }

        int kokku = 0;
        for (int i = indeks; i < a.length; i++) {
            int praegune = (int) (a[i] * 100);
            if (jääk - praegune >= 0) {
                List<Integer> kombinatsioon = new ArrayList<>(lahend);// Teeme koopia
                kombinatsioon.add(praegune/*a[i]*/);// lisame uue elemendi juurde.
                kokku += tükeldusedTäisarvVäljasta(a, jääk - (int) (a[i] * 100), vähim, i, kombinatsioon);
            }
        }

        return kokku;
    }


//        System.out.println(Arrays.asList(summa).toString());


//    private static int tükeldused(double[] a, double p, double vähim, List<Double> summa) {
//        double jääk = p;
//        if (jääk <= 0) return 0;
//
//        List<Integer> rühm1 = new ArrayList<>();
//        /*int i = 0;
//        while (jääk > 0 && i < a.length) {
//            if (jääk >= a[i] *//*&& jääk - a[i] < vähim*//*) {
//                summa.add(a[i]);
//                jääk -= a[i] + tükeldused(a, p-a[i], vähim, summa);
//                i++;
//            }
//        }*/
//        System.out.println(rühm1 + " Summa: " + rühm1.stream().mapToInt(i -> i).sum());
////        System.out.println(Arrays.asList(summa).toString());
//
//        return 0;
//
//    }

    /**
     * Naiivne lahendus - otsime kõik kombinatsioonid, mille summa ei ületa p-d, ning prindime välja need mille
     * jääk on väiksem kui järjendi vähem element.
     *
     * @param a
     * @param p
     * @param jääk
     * @param vähim
     * @return
     */
    public void tükeldused(double[] a, double p, double jääk, double vähim, int indeks, List<Double> lahend, int j) {


        for (int i = indeks; i < a.length; i++) {
            if (jääk - a[i] >= 0) {
                List<Double> kombinatsioon = new ArrayList<>(lahend);// Teeme koopia
                kombinatsioon.add(a[i]);// lisame selle elemendi juurde.
                tükeldused(a, p, jääk - a[i], vähim, i, kombinatsioon, 0);
            }
        }
        if (jääk < vähim) {
            System.out.println(lahend);
        }

    }

    /**
     * Leiab väikseima elemendi iteratiivselt.
     *
     * @param järjend
     * @return
     */
    public static double leiaVäikseimElementIteratiivne(double[] järjend) {

        double väikseim = järjend[0]; // Eeldame, et esimene on kõige väiksem element.

        for (int i = 0, j = järjend.length - 1; i <= j; i++, j--) {
            if (järjend[i] < väikseim) väikseim = järjend[i];
            if (järjend[j] < väikseim) väikseim = järjend[j];
        }
        return väikseim;
    }

    /**
     * Leiab järjendi väikseima elemendi rakendades süsteemisisest sorteerimismeetodit.
     *
     * @param järjend
     * @return
     */
    public static double leiaVäikseimElementSorteerides(double[] järjend) {
        Arrays.sort(järjend);
        return järjend[0];
    }


    public void main(String[] args) {
//        System.out.println(tükeldused(new double[]{0.5, 0.4, 0.2/**/}, 1.2));
        tükeldusedMassiiv(new double[]{0.5, 0.4, 0.2/**/}, 1.2);
    }//peameetod


}//Kodu2B


