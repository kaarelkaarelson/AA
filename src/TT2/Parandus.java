package TT2;

import ee.ut.kiho.graaf.Kuvar;

public class Parandus {
    static boolean kasVasakusRohkemLehtTippe(TippG t) {
        if (t == null) {
            return false;
        }

        if (t.v == null && t.p == null) {
            t.x = 1;
            return false;
        }

        if (kasVasakusRohkemLehtTippe(t.v) | kasVasakusRohkemLehtTippe(t.p)) return true;

        t.x = lehtTippe(t.v) + lehtTippe(t.p);

        return lehtTippe(t.v) > lehtTippe(t.p);
    }

    private static int lehtTippe(TippG t) {
        return t != null ? t.x : 0;
    }

    static int võrdneKorrutisega(TippG t) {
        if (t == null) return 0;

        int kokku = võrdneKorrutisega(t.v) + võrdneKorrutisega(t.p);

        if (Integer.parseInt(t.info) == korruta(t.v, t.p)){
            return kokku + 1;
        }

        return kokku;
    }

    private static int korruta(TippG v, TippG p) {
        return (v != null ? Integer.parseInt(v.info) : 1) * (p != null ? Integer.parseInt(p.info) : 1);
    }


    public static void main(String[] args) {
        TippG t1 = new TippG("a");
        TippG t2 = new TippG("b");
        TippG t3 = new TippG("c");
        TippG t4 = new TippG("d");
        TippG t5 = new TippG("e");

        t1.v = t2;
//        t2.v = t4;
//        t2.p = t5;

//        t1.p = t3;
        TippG t = new TippG("12", new TippG("6" , new TippG("2") , new TippG("2")) , new TippG("2"));

        Kuvar.kuvaPuu(t1);
        System.out.println(kasVasakusRohkemLehtTippe(t1));
        System.out.println(võrdneKorrutisega(t));

    }
}
