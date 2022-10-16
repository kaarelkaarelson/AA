package rekursioon;


import java.util.Arrays;

public class Praks3 {
    public static void main(String[] args) {

    yl3(3);
//        bitivektorid(4, 2);
    }

    /**
     *
     * @param n - tulemuse pikkus
     * @param k - mitu ühte
     */
    private static void bitivektorid(int n, int k) {
//        bitivektoridRek(n, k, "");

    }

    /**
     * Ajaline keerukus theta(2**n - 2**(n-k))
     * @param n
     * @param k
     * @param tee
     */
    private static void bitivektoridRek(int n, int k, String tee) {
        if (n < k || k == -1) return;
        if (n == 0 && k == 0){
            System.out.println(tee);
            return;
        }

        bitivektoridRek(n-1, k, tee + "0");
        bitivektoridRek(n-1, k-1, tee + "1");
    }

    public static int[] lisa(int[] m, int e) {
        int[] uus = new int[m.length + 1];
        for (int i = 0; i < m.length; i++) {
            uus[i] = m[i];
        }
        uus[m.length] = e;
        return uus;
    }

    private static void kombinatsioonid(int[] arvud, int k, int[] kombinatsioon, int i ) {
        if (k == 0) {
            System.out.println(Arrays.toString(kombinatsioon));
        }

        if (k > 0) kombinatsioonid(arvud, k-1, lisa(kombinatsioon, arvud[i]), i +1);
        if (k< arvud.length - 1) kombinatsioonid(arvud, k, kombinatsioon, i+1);
    }

    private static void yl2(int[] kaalud){
        int[][] tulemus = yl2(kaalud, new int[0], new int[0], 0, 0);

//        System.out.println(Arrays.toString(tulemus[1]) + "Summa: " + Arrays.stream(Arrays.stream(tulemus[1]).sum()));

    }
    private static int[][] yl2(int[] kaalud, int[] rühm1, int[] rühm2, int tasakaal, int i) {
        if (i == kaalud.length) return new int[][] {
            {tasakaal},        //Tasakaal jääb ühemõõtmeliseks massiiviks
            rühm1,
            rühm2
        };

        int[][] esimene = yl2(kaalud, lisa(rühm1, kaalud[i]), rühm2,
                tasakaal + kaalud[i], i +1);

        if (esimene[0][0] == 0) return esimene;

        int[][] teine = yl2(kaalud, rühm1, lisa(rühm1, kaalud[i]),
                tasakaal + kaalud[i], i +1);
        return esimene[0][0] < teine[0][0] ? esimene : teine;
    }

    // Efektiivsem versioon praksi materjalis - indeksitega.

    // Yl2 - kolmas kõige parem variant, arvutame kaalude keskmine. Kaotame ainult lootusetud harud ühelt poolt ära.
        // Teiselt poolt pole võimalik, kuna me ei saa väga ette vaadata. Muidu
        // Baasjuhuna huvitab meid juht, kui kaal läheb üle poole.
        // Kui ei sobi, siis tagastame sellise mille tasakaalunäitaja on nii kaugel nullpunktist kui võimalik.

    // Komentaar rekursioonis pole mõtet listi lisada, sest seda peetakse globaalseks muutujaks.

    private static void yl3(int n){
        yl3(n, new int[n], 0);
    }

    private static void yl3(int n, int[] lahutus, int i){ // lõpeta kodus.
        if (i == lahutus.length) return;
        if (n <= 0) {
            System.out.println(Arrays.toString(lahutus));
        }
        lahutus[i] = 1;
        yl3(n-1, lahutus, i+1);
        lahutus[i] = 2;
        yl3(n-2, lahutus, i+1);

    }
}

