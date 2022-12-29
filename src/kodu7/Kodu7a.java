/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2022/2023 sügissemester
 *
 * Kodutöö. Ülesanne nr 7
 * Teema: Graafid I
 *
 * Autor: Kaarel-Richard Kaarelson
 *
 *****************************************************************************/
//package kodu7;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//public class Kodu7a {
//
//    /**
//     * Linnad, milleni jõuab x tankimisega.
//     * @param lähtelinn
//     * @param x
//     * @param k
//     * @param linnad
//     * @param M
//     * @return
//     */
//    public static String[] jõuame(String lähtelinn, int x, int k, String[] linnad, int[][] M) {
//        List<String> tulemus = new ArrayList<>();
//
//        int index = leiaLinnaIndeks(lähtelinn, linnad);
//        PriorityQueue kuhi = new PriorityQueue<TippT>();
//
//        TippT praegune = new TippT(linnad[index], index, 0, null);
//        BitSet läbitud = new BitSet();
//
//        for (int i = 0; i < M[index].length; i++) {
//            if (M[index][i] > x || i == index) continue;
//
//            tulemus.add(linnad[i]);
//            TippT t = new TippT(linnad[i], i, praegune.x + M[index][i], praegune);
//            kuhi.add(t);
//        }
//
////        while ( praegune != null) {
////
////            // Lisame kaugused vaadeldavast linnast järjekorda.
////            for (int i = 0; i < M[index].length; i++) {
////                if (M[index][i] > x || i == index) continue;
////
////                TippT t = new TippT(linnad[i],i, praegune.x + M[index][i], praegune);
////                kuhi.add(t);
////            }
////
////            int lähim = -1;
////
////            TippT t = (TippT) kuhi.remove();
////
////            index = t.i;
////        }
//
//        return tulemus.toArray(new String[0]);
//    }
//
//    public static void main(String[] args) {
//        String INFOFAIL = "linnade_kaugused.csv";
//
//        Paar<String[], int[][]> andmed = naabrusmaatriksiks("linnade_kaugused.tsv", 300);
//        int[][] naabrusmaatriks = andmed.teine;
//        String[] linnad = andmed.esimene;
//
//        for (int i = 0; i < linnad.length; i++) {
//            System.out.println(linnad[i] + " - " + Arrays.toString(naabrusmaatriks[i]));
//        }
//        String[] tulemus = jõuame("Põltsamaa", 64, 2, linnad, naabrusmaatriks);
//
//
//        System.out.println("-------------- Tulemus ---------------");
//        System.out.println(Arrays.toString(tulemus));
//
//
//    }
//
//    static Paar<String[], int[][]> naabrusmaatriksiks(String fail, int x) {
//        List<String[]> andmed;
//        try {
//            andmed = Files.readAllLines(Path.of(fail)).stream()
//                    .map(s -> s.split("\t")).collect(Collectors.toList());
//        } catch (IOException ignored) {
//            return null;
//        }
//
//        String[] linnad = new String[andmed.size()];
//        int[][] M = new int[andmed.size()][andmed.size()];
//
//        for (int i = 0; i < andmed.size(); i++) {
//            linnad[i] = andmed.get(i)[0]; // võtame nime
//            for (int j = 1; j <= andmed.size(); j++) {
//                if (i == j - 1) continue;
//                int kaugus = Integer.parseInt(andmed.get(i)[j]);
////                M[i][j - 1] = kaugus > x ? -1 : kaugus;
//                M[i][j - 1] = kaugus;
//            }
//        }
//        return new Paar<>(linnad, M);
//    }
//
//    private static int leiaLinnaIndeks(String lähtelinn, String[] linnad) {
//        return IntStream.range(0, linnad.length).filter(i -> lähtelinn.equals(linnad[i])).findFirst().orElse(-1);
//    }
//
//}//Kodu7b
//
//class Paar<A, B> {
//    A esimene;
//    B teine;
//
//    public Paar(A esimene, B teine) {
//        this.esimene = esimene;
//        this.teine = teine;
//    }
//}
