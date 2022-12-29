package KT;

import ee.ut.kiho.graaf.Kuvar;

public class Tasemetöö2näidis {
    /**
     * Tagastatav tõeväärtus väljendab, kas puus leidub tipp, mille vasakus harus
     * leidub rohkem tippe kui paremas harus. Täispunktide teenimiseks hoidu igas tipus kõigi
     * alamtippude uuesti üle loendamisest.
     *
     * @param tipp
     */
    public static boolean yl1(Tipp tipp) {
        if (tipp == null) {
            return false;
        }

        boolean vasak = yl1(tipp.v);
        boolean parem = yl1(tipp.p);

        if (vasak || parem) return true;

        // tipp.x = Math.max(kõrgus(tipp.v),kõrgus(tipp.p)) + 1;
        tipp.x = Math.max(tippe(tipp.v), tippe(tipp.p)) + 1; // Võime võtta ükskõik kumma kõrguse.

        return Math.abs(tippe(tipp.v) - tippe(tipp.p)) > 0;
    }

    public static boolean yl1Praks(Tipp tipp) {
        if (tipp == null) {
            return false;
        }

        if(yl1Praks(tipp.v) || yl1Praks(tipp.p)) return true;

        tipp.x = 1 + tippe(tipp.v) + tippe(tipp.p); // Võime võtta ükskõik kumma kõrguse.

        return tippe(tipp.v) - tippe(tipp.p) > 0;
    }

    /**
     * Kirjutada funktsioon või meetod, mis võtab argumendiks täisarvuliste kirjetega kahendpuu
     * juurtipu ja tagastab täisarvu. Tagastatav täisarv väljendab, mitu sellist tippu etteantud puus
     * leidub, mis vastab järgmistele tingimustele:
     * ● Tipul on 2 vahetut alluvat. Teisisõnu pole kumbki haru tühi.
     * ● Mõlemad vahetud alluvad on lehttipud.
     * ● Vasem vahetu alluv sisaldab parempoolsest väiksema arvuga kirjet
     *
     * @param tipp
     */
    public static int yl2(Tipp tipp) {
        if (tipp == null) {
            return 0;
        }

        if (kasLehtTipp(tipp.v) && kasLehtTipp(tipp.p) && tipp.v.info.compareTo(tipp.p.info) < 0) {
            int vasak = Integer.parseInt(String.valueOf(tipp.v.info));
            int parem = Integer.parseInt(String.valueOf(tipp.p.info));
            if (vasak < parem) return 1;
//            if (tipp.v.x < tipp.p.x) return 1;
        }

        int kokku = yl2(tipp.v) + yl2(tipp.p);

        return kokku;
    }
    // Teha Javas.
    // getValue võib kasutada. ja ei pea parsima
    public static int yl2Praks(Tipp tipp) {
        if (tipp == null) {
            return 0;
        }

        if (tipp.v != null && tipp.p != null && kasLehtTipp(tipp.v) && kasLehtTipp(tipp.p)) {
            int vasak = Integer.parseInt(String.valueOf(tipp.v.info));
            int parem = Integer.parseInt(String.valueOf(tipp.p.info));
            if (vasak < parem) return 1;
//            if (tipp.v.x < tipp.p.x) return 1;
        }

        int kokku = yl2Praks(tipp.v) + yl2Praks(tipp.p);

        return kokku;
    }
    private static boolean kasLehtTipp(Tipp tipp) {
        if (tipp == null) return false;
        return tipp.v == null && tipp.p == null;
    }

    public static int tippe(Tipp tipp) {
        if (tipp == null) return 0;

        return tipp.x;
    }

    public static void main(String[] args) {

        Tipp tipp = new Tipp("3", null, null);
        Tipp tipp1 = new Tipp("2", null, null);
        Tipp tipp2 = new Tipp("5", null, null);
        Tipp tipp3 = new Tipp("1", null, null);
        tipp.v = tipp1;
        tipp1.v = tipp3;
        tipp1.p = new Tipp("2");
        tipp.p = tipp2;

        tipp2.v = new Tipp("4" );
        tipp2.p = new Tipp("6");

        boolean kasLeidub = yl1Praks(tipp);
        System.out.println(kasLeidub);
        int mitu = yl2(tipp);
        System.out.println(mitu);
        Kuvar.kuvaPuu(tipp);
    }
}
