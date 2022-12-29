package kodu3;

public class Kodu3cIse {


    public static long lipudMalelaual(int n) {
        // Genereerime malelaua.
        int laud[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            int row[] = new int[n];
            laud[i] = row;

        }

        int lahendeid = leiaPaigutused(laud);
        // rekursiooni lahenduses kasutada ei tohi
        return 0;
    }

    private static int leiaPaigutused(int[][] laud) {

        for (int i = 0; i < laud.length; i++) {

        }
        return 0;
    }


    public static void main(String[] args) {
        lipudMalelaual(1);
    }//peameetod
}
