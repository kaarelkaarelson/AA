package kodu4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://stackoverflow.com/questions/9253716/get-a-specific-digit-of-a-number-from-an-int-in-java1 "
 **/
public class SorteeriIsikukoodid {

    public static void sorteeriIsikukoodid(long[] isikukoodid) {
        long pikkus = isikukoodid.length;
        HashMap<Integer, List<Long>> paisktabelEelmine = positsioonimeetod(isikukoodid, pikkus, 2);

//        System.out.println();
        for (int i = 3; i < 11; i++) {

            paisktabelEelmine = positsioonimeetodPaisktabel(i, paisktabelEelmine);
//            System.out.println("-----------------------------");
        }
        System.out.println();

        paisktabelEelmine = positsioonimeetodSajand(11, paisktabelEelmine);


        int k = 0;
        long eelmine = 0L;
        for (int i = 1; i <= 4; i++) {
            List<Long> kimp = paisktabelEelmine.get(i);
            int kimbupikkus = kimp.size();

            for (int j = 0; j < kimbupikkus; j++) {
                long isikukood = kimp.get(j);

                isikukoodid[k] = isikukood;
                k++;
            }
        }

//        for (int i = 0; i < isikukoodid.length; i++) {
//            System.out.print(isikukoodid[i] + " ");
//        }
//        System.out.println();
    }


    private static HashMap<Integer, List<Long>> positsioonimeetodSajand(int positsioon, HashMap<Integer, List<Long>> paisktabelEelmine) {

        HashMap<Integer, List<Long>> paisktabel = new HashMap<>();

        for (int i = 1; i <= 4; i++) {
            List<Long> kimp = new ArrayList<>();
            paisktabel.put(i, kimp);
        }

        boolean kasEelminevõrdne = false;
        int võrdseteAlgusIndeks = -1;

        // Käime läbi eelnevad kõik kimbud
        // Erijuht: Isikukoodid on samal sajandil ja täpselt sama sünnikuupäeva ning sünnimaja järjekorraga.
        for (int i = 0; i < 10; i++) {
            List<Long> kimp = paisktabelEelmine.get(i);
            int kimbupikkus = kimp.size();

            for (int j = 0; j < kimbupikkus; j++) {
                long isikukood = kimp.get(j);
                int numberPositsioonil = leiaNumberPositsioonil(isikukood, 10, positsioon);
                int võti = (numberPositsioonil + 1) >> 1;

//                long isikukoodJärgmine = kimp.get(j + 1)? ;
                int järgmiseVõti = j + 1 < kimbupikkus ?
                        (leiaNumberPositsioonil(kimp.get(j + 1), 10, positsioon) + 1) >> 1
                        :
                        -1;

                // Vaatleme kas eksisteerib korduvaid kimpe.
                if (järgmiseVõti != -1 && kasVõrdsed(võti, järgmiseVõti)) {


                    long lõik = leiaLõikVahemikus(isikukood, 10, 2, 10);
                    long lõikJärgmine = leiaLõikVahemikus(kimp.get(j + 1), 10, 2, 10);

                    // Võrdleme isikukoode ilma ilma esimese ja viimase numbrita.
                    if (kasVõrdsed(lõik, lõikJärgmine)) {
                        if (!kasEelminevõrdne) võrdseteAlgusIndeks = j;

                        kasEelminevõrdne = true;
                        continue;
                    }

                }

                if (kasEelminevõrdne) {
                    // Aeg sorteerida

                    List<Long> sorteeritud = sorteeriJaLisa(kimp, paisktabel.get(võti), võrdseteAlgusIndeks, j, positsioon);
                    List<Long> kimpPositsioonil = paisktabel.get(võti);
                    int pikkusSorteeritud = sorteeritud.size();

                    for (int k = 0; k < pikkusSorteeritud; k++) {
                       kimpPositsioonil.add(sorteeritud.get(k));
                    }

                    kasEelminevõrdne = false;
                    continue;
                }
                // Salvestame praeguse isikukoodi attribuudid järgmiseks ringiks.

                List<Long> kimpPositsioonil = paisktabel.get(võti);
                kimpPositsioonil.add(isikukood);
            }
        }

//        System.out.println();
        return paisktabel;
    }

    private static List<Long> sorteeriJaLisa(List<Long> võrdseteKimp, List<Long> tulemusKimp, int algus, int lõpp, int positsioon) {
        HashMap<Integer, List<Long>> paisktabel = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            List<Long> uusKimp = new ArrayList<>();
            paisktabel.put(i, uusKimp);
        }

        // Sorteerime kõik võrdsed isikukoodid positsioonimeetodiga
        while (algus <= lõpp) {

            long isikukood = võrdseteKimp.get(algus);
            int numberPositsioonil = leiaNumberPositsioonil(isikukood, 10, positsioon);

            List<Long> kimpPositsioonil = paisktabel.get(numberPositsioonil);
            kimpPositsioonil.add(isikukood);

            algus++;
        }


        List<Long> sorteeritudKimp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            List<Long> kimp = paisktabel.get(i);
            int kimbupikkus = kimp.size();

            for (int j = 0; j < kimbupikkus; j++) {
                long isikukood = kimp.get(j);
//                int numberPositsioonil = leiaNumberPositsioonil(isikukood, 10, positsioon);

                sorteeritudKimp.add(isikukood); //k= paisktabel.get(numberPositsioonil);
//                kimpPositsioonil.add(isikukood);
            }
        }


       return sorteeritudKimp;
    }

    private static List<Long> sorteeriVahemik(List<Long> kimp, int võrdseteAlgusIndeks, int j) {


        return null;
    }

    private static boolean kasVõrdsed(long esimene, long teine) {
        return (esimene ^ teine) == 0;
    }

    private static HashMap<Integer, List<Long>> positsioonimeetodPaisktabel(int positsioon,
                                                                            HashMap<Integer, List<Long>> paisktabelEelmine) {

        HashMap<Integer, List<Long>> paisktabel = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            List<Long> kimp = new ArrayList<>();
            paisktabel.put(i, kimp);
        }

        // Käime läbi eelnevad kõik kimbud
        for (int i = 0; i < 10; i++) {
            List<Long> kimp = paisktabelEelmine.get(i);
            int kimbupikkus = kimp.size();

            for (int j = 0; j < kimbupikkus; j++) {
                long isikukood = kimp.get(j);
                int numberPositsioonil = leiaNumberPositsioonil(isikukood, 10, positsioon);

                List<Long> kimpPositsioonil = paisktabel.get(numberPositsioonil);
                kimpPositsioonil.add(isikukood);
            }
        }

//        System.out.println();
        return paisktabel;
    }


    private static HashMap<Integer, List<Long>> positsioonimeetod(long[] isikukoodid, long pikkus, int positsioon) {

        HashMap<Integer, List<Long>> paisktabel = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            List<Long> kimp = new ArrayList<>();
            paisktabel.put(i, kimp);
        }

        // töötleme numbri sel positsioonil.
        for (int i = 0; i < pikkus; i++) {
            long isikukood = isikukoodid[i];

            int numberPositsioonil = leiaNumberPositsioonil(isikukood, 10, positsioon);

//            System.out.println("Isikukood " + isikukoodid[i]);
//            System.out.println("number positsioonil " + numberPositsioonil);

            List<Long> kimpPositsioonil = paisktabel.get(numberPositsioonil);
            kimpPositsioonil.add(isikukood);
        }

//        System.out.println();
        return paisktabel;
    }

    private static int leiaNumberPositsioonil(long isikukood, int alus, int positsioon) {
        return (int) ((isikukood / Math.pow(alus, positsioon - 1)) % alus);
    }

    private static long leiaLõikVahemikus(long isikukood, int alus, int algus, int lõpp) {
        long ilmaAlguseta = (long) (isikukood / Math.pow(alus, algus - 1));
        long ilmaLõputa = (long) (ilmaAlguseta % Math.pow(alus, lõpp - 1));

        return ilmaLõputa;
    }

}
