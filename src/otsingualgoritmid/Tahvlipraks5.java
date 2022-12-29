package otsingualgoritmid;

import abi.JärjendAbi;

import java.util.Arrays;

public class Tahvlipraks5 {
    public static void main(String[] args) {
        JärjendAbi abi = new JärjendAbi();

        int [] kahanev = abi.genMittekasvavJärjend(10, 1, 10);
        int[] kasvav = abi.genMittekahanevJärjend(10, 1, 10);

        int[] kokku= new int[kahanev.length + kasvav.length];
        System.arraycopy(kahanev, 0, kokku, 0, kahanev.length);
        System.arraycopy(kasvav, 0, kokku, kahanev.length, kasvav.length);

        System.out.println(Arrays.toString(kokku));

        int[][] maatriks = new int[][]{
//                {1,1,1,1,2,2,3,3,4,5,6,7,88, 91,92,93,94,95,96,97},
//                {4,5,8,10,12,15,16,20,24,25,27,30,32,35,40,45,50,55,60,64},
//                {2,4,7,9,11,14,15,18,21,24,26,29,31,34,37,40,42,45,47,50},
                {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31,33,35,37,39}
        };

    }


    static int sadulaMeetod(int[][] maatriks){

        return 0;
    }
    static int y611(int[] a){
        int vanus = (int) (Math.random() * ((10000 - 0) + 1));

        return y611(0, vanus, -1, -1);
    }

    private static int y611(int pakkumine, int vanus, int aluminePiir, int üleminePiir) {
        // Esmalt pakume eksponentsiaalselt ja leiame ülemise piiri
        if (pakkumine > vanus) üleminePiir=pakkumine;

//        yl613(pakkumine*2, vanus, aluminePiir, üleminePiir)
        if (pakkumine < vanus) {

        }
        return 0;
    }

    static int yl613(int[] a) {

        return 0;
    }
}
