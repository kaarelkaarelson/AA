package graafid;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

// Mida väiksem distants lubatud, seda pikem teekond tuleb.
public class Praks13_14 {
    private static final String INFOFAIL = "linnade_kaugused.csv";

    private String[] linnad;
    private int[][] naabrusmaatriks;
    private int[][] kaugused;

    public Praks13_14(int x) {
        loeInfo();
        eemaldaLiigaPikad(x);
        floydWarshall();

    }

    private void loeInfo() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(INFOFAIL), StandardCharsets.UTF_8
                )
        )) {
            linnad = br.readLine().split(", ", -1);

            naabrusmaatriks = new int[linnad.length][];
            String rida;
            for (int i = 0; (rida = br.readLine()) != null; i++) {
                String[] tykid = rida.split(",");

                naabrusmaatriks[i] = Arrays.stream(rida.split(",")).mapToInt(Integer::parseInt).toArray();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void eemaldaLiigaPikad(int x) {

        for (int i = 0; i < naabrusmaatriks.length; i++) {
            for (int j = 0; j < naabrusmaatriks[i].length; j++) {
                if (naabrusmaatriks[i][j] > x) {
                    naabrusmaatriks[i][j] = -1;
                }

            }

        }
    }

    private void floydWarshall() {
        // Teem koopia naabrusmaatriksist.
        kaugused = new int[naabrusmaatriks.length][];

        for (int i = 0; i < linnad.length; i++) {
            kaugused[i] = naabrusmaatriks[i].clone();
        }

        for (int k = 0; k < kaugused.length; k++) {

            for (int i = 0; i < kaugused.length; i++) {
                if (kaugused[i][k] == -1) continue;
                for (int j = 0; j < kaugused.length; j++) {
                    if (kaugused[k][j] == -1) continue;

                    if (kaugused[i][j] == -1 || kaugused[i][j] > kaugused[i][k] + kaugused[k][j]) {
                        kaugused[i][j] = kaugused[i][k] + kaugused[k][j];
                    }

                }

            }
        }


    }

    private int linnaIndeks(String linn) {
        for (int i = 0; i < linnad.length; i++) {
            if (linnad[i].equals(linn)) return i;

        }
        throw new IllegalArgumentException("Sellist linna ei ole.");
    }

    public int kaugus(String linn1, String linn2) {
        return kaugused[linnaIndeks(linn1)][linnaIndeks(linn2)];
    }

    public static void main(String[] args) {
        new Praks13_14(100);
    }

}
