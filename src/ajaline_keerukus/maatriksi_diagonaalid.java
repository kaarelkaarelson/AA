
package ajaline_keerukus;

public class maatriksi_diagonaalid {
    /**
     * @param M - maatriks
     * @param n - maatriksi pikkus nxn
     * @return - tagastab peadiagonaalide ja kõrvalidagonaali summa
     */
    public static int diagonaalide_summa(int[][] M, int n) {
        int summa = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || i + j == n - 1) {
                    //System.out.println("Vaatlen: " + M[i][j]);
                    summa += M[i][j];
                }

            }

        }
        return summa;
    }

    public static int diagonaalide_summa2(int[][] M, int n) {
        int summa = 0;
        return summa;
    }

    private static int[][] gen_maatriks(int n) {
        int[][] tulemus = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tulemus[i][j] = (int) (Math.random() * 10);
            }

        }
        return tulemus;
    }

    public static void main(String[] args) {
        System.out.println("Tere");
        int[][] M1 = gen_maatriks(10000);
        int[][] M2 = gen_maatriks(20000);



        /*for (int[] ints : M) {
            for (int e : ints) {
                System.out.print(e + " ");

            }
            System.out.println();
        }*/

        long start1 = System.nanoTime();
        int alg1 = diagonaalide_summa(M1, M1.length);
        long end1 = System.nanoTime();
        long tööaeg = end1 - start1;
        System.out.println(tööaeg);

        long start2 = System.nanoTime();
        int alg2 = diagonaalide_summa(M2, M2.length);
        long end2 = System.nanoTime();
        long tööaeg2 = end2 - start2;
        System.out.println(tööaeg2);

        double vahe = (double) tööaeg2/tööaeg;
        System.out.println(vahe);

        System.out.println("Sisendis suurus erines " + M2.length/M1.length + " korda, ja ajaline vahe oli " + vahe + " suurem");
    }
}
