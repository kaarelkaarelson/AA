package abi;

public class MaatriksAbi {
    private static int[][] genMaatriks(int n) {
        int[][] tulemus = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tulemus[i][j] = (int) (Math.random() * 10);
            }

        }
        return tulemus;
    }
}
