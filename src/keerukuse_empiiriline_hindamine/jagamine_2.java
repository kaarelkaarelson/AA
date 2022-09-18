package keerukuse_empiiriline_hindamine;

import java.math.BigInteger;

public class jagamine_2 {
    public static boolean jaga_2(int n){
        //System.out.println(n);
        if(n % 2 == 0) return jaga_2(n/2);
        return n == 1;
    }
    public static void main(String[] args) {
        /*BigInteger bigI = new BigInteger("");*/
        int arv1 = 1000000000;
        int arv2 = 2000000000;

        long start1 = System.nanoTime();
        boolean alg1 = jaga_2(arv1);
        long end1 = System.nanoTime();
        long tööaeg = end1 - start1;
        System.out.println(tööaeg);

        long start2 = System.nanoTime();
        boolean alg2 = jaga_2(arv2);
        long end2 = System.nanoTime();
        long tööaeg2 = end2 - start2;
        System.out.println(tööaeg2);

        double vahe = (double) tööaeg2/tööaeg;
        // System.out.println(vahe);

        System.out.println("Sisendis suurus erines " + arv2/arv1 + " korda, ja ajaline vahe oli " + vahe + " suurem");

    }
}
