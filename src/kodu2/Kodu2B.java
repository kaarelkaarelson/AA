/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2022/2023 sügissemester
 *
 * Kodutöö. Ülesanne nr 2b
 * Teema: Rekursioon. Variantide läbivaatamine
 *
 * Autor: Kaarel-Richard Kaarelson
 *
 * Mõningane eeskuju: vt
 *  1. https://astikanand.github.io/techblogs/dynamic-programming-patterns/unbounded-knapsack-pattern
 *
 *****************************************************************************/

import java.math.BigDecimal;
import java.util.*;

public class Kodu2B {
    /**
     * Meetod leiab traadi kõikvõimalike tükelduste arvu, mille puhul ülejaak on väiksem kui vähim traadi lubatud pikkus.
     *
     * @param a etteantud massiiv, kus on traaditükkide pikkused.
     * @param p etteantud lubatud traadi pikkus.
     * @return tagastab kõikvõimalike tükelduste arvu.
     */
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

    /**
     * Rekursiivne meetod, mis käib käib läbi kõik traadipikkuste kombinatsioonid. Meetodi käigus salvestatakse
     * juba läbitud hargnevused andmestruktuuri mälu. Mälust otsides vastavaid teekondi hoiab aega kokku. Kui oleme korra
     * juba olnud kindla elemendi puhul harus, kus jäägid kattuvad, siis järgnevad hargnevused on identsed. See
     * tuleneb sorteeritud järjendi eelisest.
     *
     * järjendi puhul kehtib selline seaduspära.
     * @param a sorteeritud BigDecimal järjend.
     * @param jääk arv, mis näitab palju on kui palju traati on veel järel.
     * @param vähim väikseim element järjendis a.
     * @param i järjendi indeks.
     * @param mälu List kuhu salvestame vastavate elementide võtmed ja väärtused.
     * @return tagastab kahekohalise järjendi. Kohal [0] on lahendite arv ja kohal [1] on väärtus -1 või 1 näitamaks
     * on mõistlik samas hargnevuses edasi minna või mitte.
     */
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

    public static void main(String[] args) {
        System.out.println(tükeldused(new double[]{0.5, 0.4, 0.2}, 1.2));
    }//peameetod


}//Kodu2B

