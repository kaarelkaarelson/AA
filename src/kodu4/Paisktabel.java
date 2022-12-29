package kodu4;

public class Paisktabel {

    long[] paiskTabel;

    public Paisktabel(int pikkus) {
        this.paiskTabel = new long[pikkus];
    }

    public static void paiska(int väärtus, int võtmeteArv, long isikukood, long[] paisktabel) {

        int pikkus = paisktabel.length;
        // Lineaarne kompimine
        for (int võti = väärtus; võti < pikkus; võti += 1) {

            long koht = paisktabel[võti];
            if (koht == 0) {
                paisktabel[võti] = isikukood;
                break;
            }
        }

    }


    public static void paiskaSajand(int väärtus, int võtmeteArv, long isikukood, long[] paisktabel) {

        int pikkus = paisktabel.length;

        for (int võti = väärtus; võti < pikkus; võti += 10) {

            long koht = paisktabel[võti];
            if (koht == 0) {
                paisktabel[võti] = isikukood;
                break;
            }
        }

    }

    public static void otsiJaPaiska(int positisioon, int vanapositsioon, int vanaVõtmeteArv, int uueVõtmeteArv, long[] paisktabel, long[] uusPaisktabel) {
        int pikkus = paisktabel.length;


        for (int j = 0; j < 10; j++) {
            System.out.println("Otsin võtmeid, mis aljgavad " + j);
            for (int võti = j; võti < pikkus; võti += 1) {
                long koht = paisktabel[võti];
                boolean kasMöödas =  kasOlemeMöödas(koht, vanapositsioon, j);
                if (koht == 0 || kasMöödas) break;


                int vanaNumberPositsioonil = leiaNumberPositsioonil(koht, 10, vanapositsioon);
                // kui järgmine pole sama positsiooniga, siis break;

                int numberPositsioonil = leiaNumberPositsioonil(koht, 10, positisioon);
                if (vanaNumberPositsioonil != j) continue;

                if (uueVõtmeteArv == 4) paiska(((numberPositsioonil + 1) >> 1) - 1, uueVõtmeteArv, koht, uusPaisktabel);
                else paiska(numberPositsioonil, uueVõtmeteArv, koht, uusPaisktabel);
            }
        }
    }

    public static void paisktabelJärjendiks(int võtmeteArv, long[] isikukoodid, long[] paisktabel) {

        int pikkus = paisktabel.length;
        int järjendiIndeks = 0;

        for (int j = 0; j < võtmeteArv; j++) {

            for (int võti = j; võti < pikkus; võti += võtmeteArv) {

                long koht = paisktabel[võti];
                if (koht == 0) break;

                isikukoodid[järjendiIndeks] = koht;
                järjendiIndeks++;
            }
        }

    }

    public static boolean kasOlemeMöödas(long koht, int vanapositsioon, long võrreldav) {

        int vanaNumberPositsioonil = leiaNumberPositsioonil(koht, 10, vanapositsioon);

        return vanaNumberPositsioonil > võrreldav;

    }

    public static int leiaNumberPositsioonil(long isikukood, int alus, int positsioon) {
        return (int) ((isikukood / Math.pow(alus, positsioon - 1)) % alus);
    }

}
