package paisktabel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Praks7 {

    public static int yl1b() {
        Map<Integer, Integer> map = new HashMap<>();

//       for (int i:m) {
//           map.put(i, map.getOrDefault(i, 0) + 1);
//       }
        System.out.println("Hello World");
        String hello = "Hello";
        return 0;
    }

    public static int[] juhuslikMassiiv(int n, int a, int b) {
        //Antud: n - elementide arv, poollõik [a,b)
        //Tagastab: n-elemendilise juhuslike täisarvudega järjendi, elemendid lõigult [a,b)
        int[] x = new int[n];
        for (int i = 0; i < n; i++)
            x[i] = juhuslik(a, b);
        return x;
    }

    public static int juhuslik(int a, int b) {
        //Antud: poollõik [a,b)
        //Tagastab: juhusliku täisarvu sellelt lõigult
        return (int) (Math.round(Math.random() * (b - a) + a));
    }


    public static int suurimAllaSekundiRekursiivne() {
        long start, stopp;
        int i = 0;
        do {
            System.out.println(i);
            int[] juhuslik = juhuslikMassiiv(i, 0, 100);
            start = System.currentTimeMillis();
            //
            stopp = System.currentTimeMillis();
            i += 500000;
        } while (stopp - start < 1000);

        return i - 1;
    }

    public static void yl8_48_2(Ahel ahel) {
        // Peale hashmapi on ka Hashset

        HashSet<Integer> set = new HashSet<>();

        Ahel jooksev = ahel;
        Ahel eelmine = null;
        while (jooksev != null) {
            if (!set.contains(jooksev.getVäärtus())) {
                set.add(jooksev.getVäärtus());
            } else {
                eelmine.setJärgmine(jooksev.getJärgmine());
            }
            eelmine = jooksev;
            jooksev = jooksev.getJärgmine();
        }
    }

    // Keskmine: ruutkeerukus, parim: lineaarne (Siis kui järjendis on samad elemendid).
    public static void yl8_48_1(Ahel ahel) {
        Ahel jooksev = ahel;
        // kontroll != jooksev võrdleb mäluviitasid, st pointereid, seega selline võrdlus ei toimiks.// Üks võimalus oleks kasutada UUID seal klassis.
        for (int i = 0; jooksev != null; i++) {
            // Vaatame kas sellese eespool oli midagi.
            Ahel kontroll = ahel;
            Ahel eelmine = null;

            for (int j = 0; j < i; j++) {
                if (kontroll.getVäärtus() == jooksev.getVäärtus()) {
                    jooksev.setJärgmine(jooksev.getJärgmine());
                }
                kontroll = kontroll.getJärgmine();


            }

            eelmine = jooksev;
            jooksev = jooksev.getJärgmine();
        }
    }

    public static void yl8_49_1(Ahel ahel) {
        int mitu = 0;
        Ahel jooksev = ahel;

        // välimine
        välimine: // Välimise tsükli tähistamiseks
        for (int i = 0; jooksev != null; i++) {
            Ahel kontroll = ahel;
            boolean samaLeitud = false;
            for (int j = 0; kontroll != null; j++) {
                if (i == j && kontroll.getVäärtus() == jooksev.getVäärtus()) {
                    samaLeitud = true;
                    continue;
                }
                kontroll = kontroll.getJärgmine();

            }
            if (!samaLeitud) mitu++;
            jooksev = jooksev.getJärgmine();
        }
    }

    public static void yl8_49_2(Ahel ahel) {
        Map<Integer, Integer> map = new HashMap<>();
        Ahel jooksev = ahel;
        while (jooksev != null) {
            map.put(
                    jooksev.getVäärtus(),
                    map.getOrDefault(jooksev.getVäärtus(), 0)
            );
        }

    }

    public static void main(String[] args) {
        Ahel ahel = new Ahel(1, new Ahel(2, new Ahel(3, new Ahel(2, null))));

        yl8_48_2(ahel);

        System.out.println();

    }
}
