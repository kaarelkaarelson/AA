import TT2.TippG;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

class Tipp {
    //graafi tippu esitav klass
    String info = null; //tipu info
    int x = 0; //abiväli
    int kaal = 0; //tipu kaal (kui peaks vaja olema)

    //graafi tippude esitus on tippude ahel
//    Tipp2 jrg; //tippude loetelus viit järgmisele tipule
    Kaar kaared = null; //sellest tipust väljuvate kaarte loetelu

    public Tipp(String info) {
        this.info = info;
    }

}//Tipp

class Kaar {
    //graafi ühte kaart esitav klass

    int kaal = 0; //kaare kaal
    TippG alg = null; //kaare lähtetipp
    TippG lõpp = null; //kaare suubumistipp
    Kaar jrg = null; //kaarte loetelus viit järgmisele kaarele

    public Kaar(TippG alg, TippG lõpp, int kaal) {
        this.alg = alg;
        this.lõpp = lõpp;
        this.kaal = kaal;
    }//konstruktor

}//Kaar

class Paar<A, B> {
    A esimene;
    B teine;

    public Paar(A esimene, B teine) {
        this.esimene = esimene;
        this.teine = teine;
    }
}


public class Graafid {

    static Paar<String[], int[][]> naabrusmaatriksiks(String fail, int x) {
        List<String[]> andmed;
        try {
            andmed = Files.readAllLines(Path.of(fail)).stream()
                    .map(s -> s.split("\t")).collect(Collectors.toList());
        } catch (IOException ignored) {
            return null;
        }


        String[] linnad = new String[andmed.size()];
        int[][] M = new int[andmed.size()][andmed.size()];

        for (int i = 0; i < andmed.size(); i++) {
            linnad[i] = andmed.get(i)[0]; // võtame nime
            for (int j = 1; j <= andmed.size(); j++) {
                if (i == j - 1) continue;
                int kaugus = Integer.parseInt(andmed.get(i)[j]);
                M[i][j - 1] = kaugus > x ? -1 : kaugus;
            }
        }

        return new Paar<>(linnad, M);
    }

    static int[][] ühesuunasta(int[][] M) { int[][] ühesuunaline = new int[M.length][M.length];
        for (int i = 0; i < M.length; i++) System.arraycopy(M[i], 0, ühesuunaline[i], 0, M.length); // 😎

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < i; j++) {
                if (ühesuunaline[i][j] == -1) continue;
                if (Math.random() < 0.5) {
                    ühesuunaline[i][j] = -1;
                } else {
                    ühesuunaline[j][i] = -1;
                }
            }
        }
        return ühesuunaline;
    }

    static List<String> kõigePikemTee(String[] linnad, int[][] M, int algusIndeks) {

        Stack<Integer> teekond = new Stack<>();
        Stack<Integer> naabriIndeks = new Stack<>();
        BitSet töötlusel = new BitSet();

        List<String> pikim = List.of();

        teekond.add(algusIndeks);
        naabriIndeks.add(0);
        while (!naabriIndeks.isEmpty()) {
            int tipp = teekond.peek();
            töötlusel.set(tipp);

            int naabriOtsinguAlgus = naabriIndeks.pop();
            int järgmine = -1;
            for (; naabriOtsinguAlgus < linnad.length; naabriOtsinguAlgus++) {

                if (M[tipp][naabriOtsinguAlgus] <= 0 && !töötlusel.get(naabriOtsinguAlgus))
                    järgmine = naabriOtsinguAlgus;
            }
            if (järgmine == -1) {
                if (teekond.size() > pikim.size()) {
                    pikim = teekond.stream().map(i -> linnad[i]).collect(Collectors.toList());
                }
                töötlusel.clear(tipp);
                teekond.pop();
                continue;
            }
            naabriIndeks.add(naabriOtsinguAlgus + 1);
            teekond.add(järgmine);
            naabriIndeks.add(0);
        }

        return pikim;
    }

    public static int linnaIndeks(String linn, String[] linnad) {
        for (int i = 0; i < linnad.length; i++) {
            if (Objects.equals(linn, linnad[i])) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Paar<String[], int[][]> andmed = naabrusmaatriksiks("linnade_kaugused.tsv", 300);

        String[] linnad = andmed.esimene;
        int[][] M = andmed.teine;
        int[][] ühesuunaline = ühesuunasta(M);
        int x = 100;
        int k = 10;
        String lahtelinn = "Tartu";

        for (int i = 0; i < linnad.length; i++) {
            System.out.println(linnad[i] + " - " + Arrays.toString(M[i]));
        }
    }
}
