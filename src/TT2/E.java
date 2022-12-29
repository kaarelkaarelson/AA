package TT2;

import ee.ut.kiho.graaf.Kuvar;

public class E {
    static boolean kas3(TippG t) {
        if (t == null) return false;

        if ( kas3(t.v) || kas3(t.p)) return true;

        int kokku = vaheTippe(t.v) + vaheTippe(t.p);
        t.x = kokku + (kasVaheTipp(t) ? 1 : 0);

        return vaheTippe(t.v) >= 3 &&  vaheTippe(t.p) >= 3;
    }

    private static int vaheTippe(TippG t) {
        return t != null ? t.x : 0;
    }

    private static boolean kasVaheTipp(TippG t) {
        return t.v != null || t.p != null;
    }

    static int mitu(TippG t) {
        if (t == null) return 0;

        int kokku = mitu(t.v) + mitu(t.p);

        if (kasAlluvad(t) && korruta(t.v, t.p) % 2 != 2){
            return kokku + 1;
        }

        return kokku;
    }

    private static int korruta(TippG v, TippG p) {
        return (v != null ? v.x : 1) * (p != null ? p.x : 1) ;
    }

    private static boolean kasAlluvad(TippG t) {
        return t.v != null && t.p != null;
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
        TippG t = new TippG("12", new TippG("6" , new TippG("2", new TippG("4", new TippG("2", null, null ), null),null ) , new TippG("2")) , new TippG("2", new TippG("2", new TippG("2", new TippG("2"), null), null), null));

        Kuvar.kuvaPuu(t);
        System.out.println(kas3(t));

    }
}
