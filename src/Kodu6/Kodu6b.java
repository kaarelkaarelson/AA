import kuhjad.Kuhi;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Kodu6b {

    public static int[][] kuhjad(int n){

        int [] kuhi = new int[n];
        int id = 0;

        Kuhi kuhi1 = new Kuhi();

        for (int i = n; i > 0 ; i--) {
            kuhi1.lisa(i);

        }

        while (!kuhi1.kasTühi()){

            System.out.println(kuhi1.eemaldaJuur());
        }


        return null;
    }

    public static void main(String[] args) {
        int n = 2;

        int [][] tulemus = kuhjad(10);

        System.out.println(Arrays.toString(tulemus));
    }
}