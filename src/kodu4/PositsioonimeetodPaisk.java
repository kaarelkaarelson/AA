package kodu4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PositsioonimeetodPaisk {

    public static void sorteeriIsikukoodid(long[] isikukoodid) {
        int pikkus = isikukoodid.length;
        //Sorteerime sajandi järgi:

        long[] paisktabel = new long[pikkus * pikkus + 100];
        paisktabel = positsioonimeetod(isikukoodid, paisktabel, 11);

        long[] uusPaisktabel = new long[paisktabel.length];
        Paisktabel.otsiJaPaiska(2, 11, 10, 10, paisktabel, uusPaisktabel);
        paisktabel = uusPaisktabel;
//        System.out.println();
        for (int i = 3; i <= 10; i++) {
            uusPaisktabel = new long[paisktabel.length];
//            for (int j = 0; j < 10; j++) {

            Paisktabel.otsiJaPaiska(i, i-1, 10, 10, paisktabel, uusPaisktabel);
            System.out.println();
            paisktabel = uusPaisktabel;
//            }


        }
        System.out.println();

        long[] paisktabelViimane = new long[paisktabel.length];
        Paisktabel.otsiJaPaiska(11,  10, 10, 4, paisktabel, paisktabelViimane);

        Paisktabel.paisktabelJärjendiks(4, isikukoodid, paisktabelViimane);
        System.out.println();

    }


    private static boolean kasVõrdsed(long esimene, long teine) {
        return (esimene ^ teine) == 0;
    }


    private static long[] positsioonimeetod(long[] isikukoodid, long[] paisktabel, int positsioon) {

        int pikkus = isikukoodid.length;

        // töötleme numbri sel positsioonil.
        for (int i = 0; i < pikkus; i++) {
            long isikukood = isikukoodid[i];

            int numberPositsioonil = leiaNumberPositsioonil(isikukood, 10, positsioon);

            Paisktabel.paiska(numberPositsioonil, 10, isikukood, paisktabel);

//            kimpPositsioonil.add(isikukood);
        }

//        System.out.println();
        return paisktabel;
    }

    public static int leiaNumberPositsioonil(long isikukood, int alus, int positsioon) {
        return (int) ((isikukood / Math.pow(alus, positsioon - 1)) % alus);
    }
}