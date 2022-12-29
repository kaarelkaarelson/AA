package kodu7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Kodu7b {
//
//    public static void kaugused(String lähtelinn, int x, String[] linnad, int[][] M) {
//        int index = leiaLinnaIndeks(lähtelinn, linnad);
//
//        HashMap<String, Integer> kaugused = new HashMap<>();
//        Set<String> läbitud = new HashSet<>();
//
//        for (int i = 0; i < linnad.length; i++) {
//            if (i == index) {
//                kaugused.put(linnad[i], 0);
//                continue;
//            }
//            ;
//            kaugused.put(linnad[i], Integer.MAX_VALUE);
//        }
//
//        PriorityQueue kuhi = new PriorityQueue<TippT>();
//        TippT praegune = new TippT(linnad[index], index, 0, null);
//        while ( praegune != null) {
//
//            // Lisame kaugused vaadeldavast linnast järjekorda.
//            for (int i = 0; i < M[index].length; i++) {
//                if (M[index][i] > x || i == index) continue;
//
//                TippT t = new TippT(linnad[i],i, praegune.x + M[index][i], praegune);
//                kuhi.add(t);
//            }
//
//            int lähim = -1;
////            while (!kuhi.isEmpty()) {
//
//            TippT t = (TippT) kuhi.remove();
//            if (läbitud.x < kaugused.get(t.info)) {
//                kaugused.replace(t.info, t.x);
//            }
//            läbitud.add(t.info);
//
////                int uusKaugus = t.x == Integer.MAX_VALUE ? M[index][t.x] : t.x + M[index][t.x];
////            }
//            index = t.i;
//        }
//    }
//
//    private static int leiaLinnaIndeks(String lähtelinn, String[] linnad) {
//        return IntStream.range(0, linnad.length).filter(i -> lähtelinn.equals(linnad[i])).findFirst().orElse(-1);
//    }
//
//
//    public static void main(String[] args) {
//        String INFOFAIL = "linnade_kaugused.csv";
//
//        KuhiMin kuhi = new KuhiMin();
//        kuhi.lisa(5);
//        kuhi.lisa(4);
//        kuhi.lisa(4);
//        kuhi.lisa(3);
//        kuhi.lisa(3);
//        kuhi.lisa(7);
//        kuhi.lisa(3);
//        kuhi.lisa(7);
//
//        while (!kuhi.kasTühi()) {
//            System.out.println(kuhi.eemaldaJuur());
//        }
//
//        Paar<String[], int[][]> andmed = naabrusmaatriksiks("linnade_kaugused.tsv", 300);
//        int[][] naabrusmaatriks = andmed.teine;
//        ;
//        String[] linnad = andmed.esimene;
//
//        String lahtelinn = "Tartu";
////
//        kaugused(linnad[3], 200, linnad, naabrusmaatriks);
//
//        for (int i = 0; i < linnad.length; i++) {
//            System.out.println(linnad[i] + " - " + Arrays.toString(naabrusmaatriks[i]));
//        }
//
////       Dijkstra.findShortestDistance("Anstla", 100, linnad,naabrusmaatriks  );
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
//        int[][] M = new int[andmed.size() - 50][andmed.size() - 50];
//        String[] linnad = new String[andmed.size() - 50];
//
//        int valim = 50;
//
////        String[] linnad = new String[andmed.size() - valim];
////        int[][] M = new int[andmed.size() - valim][andmed.size() - valim];
////        for (int i = valim, index = 0; i < andmed.size(); i++, index ++) {
////            linnad[index] = andmed.get(i)[0]; // võtame nime
//////            for (int j = valim; j <= andmed.size();kj j++) {
////            for (int j = 1; j <= andmed.size(); j++) {
////                if (i - 50 == j - 1) continue;
////                int kaugus = Integer.parseInt(andmed.get(i)[j]);
////                M[index][j - 1] = kaugus > x ? -1 : kaugus;
////            }
////        }
//
//        for (int i = 0; i < andmed.size() - 50; i++) {
//            linnad[i] = andmed.get(i)[0]; // võtame nime
//            for (int j = 1; j <= andmed.size() - 50; j++) {
//                if (i == j - 1) continue;
//                int kaugus = Integer.parseInt(andmed.get(i)[j]);
//                M[i][j - 1] = kaugus > x ? -1 : kaugus;
//            }
//        }
//
//        return new Paar<>(linnad, M);
//    }
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
}
