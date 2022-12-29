package kodu4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Positsioonimeetod {

    public static void sorteeriIsikukoodid(long[] isikukoodid) {
        long pikkus = isikukoodid.length;

        HashMap<Integer, List<Long>> paisktabelEelmine = positsioonimeetod(isikukoodid,isikukoodid.length, 11);

        for (int i = 2; i <= 10; i++) {

            paisktabelEelmine = positsioonimeetodPaisktabel(i, paisktabelEelmine);
        }
        System.out.println();

        paisktabelEelmine = positsioonimeetodSajandMap(paisktabelEelmine, 11);

        // Sisestame sorteeridu paisktabeli järjendisse
        int k = 0;
        for (int i = 0; i < 4; i++) {
            List<Long> kimp = paisktabelEelmine.get(i);
            int kimbupikkus = kimp.size();

            for (int j = 0; j < kimbupikkus; j++) {
                long isikukood = kimp.get(j);

                isikukoodid[k] = isikukood;
                k++;
            }
        }
 }

    /**
     * Meetod paiskab isikukoodid tabelisse sajandi järgi.
     * @param eelminePaisktabel
     * @param positsioon
     * @return
     */
    private static HashMap<Integer, List<Long>> positsioonimeetodSajandMap (HashMap<Integer, List<Long>> eelminePaisktabel, int positsioon) {

        HashMap<Integer, List<Long>> paisktabel = new HashMap<>();

        for (int i = 0; i < 4; i++) {
            List<Long> kimp = new ArrayList<>();
            paisktabel.put(i, kimp);
        }

        int pikkus = eelminePaisktabel.size();

        for (int i = 0; i < pikkus; i++) {
            List<Long> kimp = eelminePaisktabel.get(i);
            int kimbuPikkus = kimp.size();

            for (int j = 0; j < kimbuPikkus; j++) {
                long isikukood = kimp.get(j);
                int numberPositsioonil = leiaNumberPositsioonil(isikukood, 10, positsioon);
                int võti = ((numberPositsioonil + 1) >> 1) - 1;

                List<Long> kimpPositsioonil = paisktabel.get(võti);
                kimpPositsioonil.add(isikukood);
            }

        }

        System.out.println();
        return paisktabel;
    }

    private static boolean kasVõrdsed(long esimene, long teine) {
        return (esimene ^ teine) == 0;
    }

    /**
     * Meetod paiskab isikukoodid etteantud positsiooni põhjal paisktabelisse.
     * @param positsioon
     * @param paisktabelEelmine
     * @return tagastab uue paisktabeli.
     */
    private static HashMap<Integer, List<Long>> positsioonimeetodPaisktabel(int positsioon,
                                                                            HashMap<Integer, List<Long>> paisktabelEelmine) {

        HashMap<Integer, List<Long>> paisktabel = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            List<Long> kimp = new ArrayList<>();
            paisktabel.put(i, kimp);
        }
        int pikkus = paisktabelEelmine.size();

        // Käime läbi eelnevad kõik kimbud
        for (int i = 0; i  < pikkus; i++) {
            List<Long> kimp = paisktabelEelmine.get(i);
            int kimbupikkus = kimp.size();

            for (int j = 0; j < kimbupikkus; j++) {
                long isikukood = kimp.get(j);
                int numberPositsioonil = leiaNumberPositsioonil(isikukood, 10, positsioon);

                List<Long> kimpPositsioonil = paisktabel.get(numberPositsioonil);
                kimpPositsioonil.add(isikukood);
            }
        }

        System.out.println();
        return paisktabel;
    }

    /**
     * Positsiooni meetod, mida on vaja esimesel sammul, et isikukoodid saada paisktabelisse.
     * @param isikukoodid
     * @param pikkus
     * @param positsioon
     * @return - tagastab uuepaisktabeli.
     */
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

            List<Long> kimpPositsioonil = paisktabel.get(numberPositsioonil);
            kimpPositsioonil.add(isikukood);
        }

        return paisktabel;
    }

    /**
     * Abimeetod, mis leiab isikukoodi numbri kindlal positioonil. Nt paremalt poolt esimese numbri.
     * @param isikukood
     * @param alus
     * @param positsioon
     * @return tagastab numbri antud positsioonil.
     */
    private static int leiaNumberPositsioonil(long isikukood, int alus, int positsioon) {
        return (int) ((isikukood / Math.pow(alus, positsioon - 1)) % alus);
    }

    private static long leiaLõikVahemikus(long isikukood, int alus, int algus, int lõpp) {
        long ilmaAlguseta = (long) (isikukood / Math.pow(alus, algus - 1));
        long ilmaLõputa = (long) (ilmaAlguseta % Math.pow(alus, lõpp - 1));

        return ilmaLõputa;
    }

}
