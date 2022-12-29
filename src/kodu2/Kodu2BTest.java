
package kodu2;

import abi.JärjendAbi;
import abi.ÜhikudAbi;
import kodu1.Abi;
import org.junit.Test;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class Kodu2BTest {
    ÜhikudAbi ühikudAbi = new ÜhikudAbi();
    JärjendAbi abi = new JärjendAbi();
    Kodu2BDev kodu2BDev = new Kodu2BDev();

    class Sisend {
        public Sisend(double[] järjend, double pikkus, int vastus) {
            this.järjend = järjend;
            this.pikkus = pikkus;
            this.vastus = vastus;
        }

        double[] järjend;
        double pikkus;
        int vastus;

    }

    @Test
    public void tükeldusedTest() {

        var lihtsad1 = new Sisend(new double[]{1.0}, 0.5, 1);
        var lihtsad2 = new Sisend(new double[]{1.0, 1.1}, 1.5, 2);
        var lihtsad3 = new Sisend(new double[]{1.0, 2.0}, 4.0, 3);
        var lihtsad4 = new Sisend(new double[]{1.0, 2.0, 3.0, 4.0}, 5.0, 6);
        var juhuslikud = new Sisend(new double[]{3.826, 9.036, 8.337, 2.602, 9.622, 3.975, 5.735, 2.582},
                3.8217455579536788, 2);
        var korduvad = new Sisend(new double[]{5.0, 5.0, 5.0, 5.0, 2.0, 32.0, 2.0}, 17.0, 6);
        var korduvad1 = new Sisend(new double[]{2.0, 5.0, 5.0}, 7.0, 6);

        var juht = new Sisend(new double[]{1.08, 1.08, 1.1, 9.81}, 3.27, 6);
        var juht2 = new Sisend(new double[]{1.07, 2.14, 2.82}, 8.7, 6);
        var juht3 = new Sisend(new double[]{2.32, 2.32, 2.44}, 8.51, 6);
        var juht4 = new Sisend(new double[]{2.11, 2.11, 2.2}, 6.8, 6);

//        System.out.println(kodu2BDev.tükeldusedDünaamilineA(lihtsad4.järjend, lihtsad4.pikkus));
//        System.out.println(kodu2BDev.tükeldusedD(lihtsad4.järjend, lihtsad4.pikkus));
//        System.out.println(kodu2BDev.tükeldusedReverse(lihtsad1.järjend, lihtsad1.pikkus));
//        System.out.println(kodu2BDev.tükeldusedReverse(lihtsad2.järjend, lihtsad2.pikkus));
//        System.out.println(kodu2BDev.tükeldusedReverse(lihtsad3.järjend, lihtsad3.pikkus));
//        System.out.println(kodu2BDev.tükeldusedReverse(lihtsad4.järjend, lihtsad4.pikkus));
//        System.out.println(kodu2BDev.tükeldusedReverse(juhuslikud.järjend, juhuslikud.pikkus));
//        System.out.println(kodu2BDev.tükeldusedReverse(korduvad.järjend, korduvad.pikkus));
        System.out.println(kodu2BDev.tükeldusedReverse(juht4.järjend, juht4.pikkus));
//        System.out.println("--------------------------------");
//        System.out.println(kodu2BDev.tükeldusedListBD(juht4.järjend, juht4.pikkus));
//        System.out.println(kodu2BDev.tükeldusedListBD(lihtsad2.järjend, lihtsad2.pikkus));
//        System.out.println(kodu2BDev.tükeldusedListBD(lihtsad3.järjend, lihtsad3.pikkus));
//        System.out.println(kodu2BDev.tükeldusedListBD(lihtsad4.järjend, lihtsad4.pikkus));
//        System.out.println(kodu2BDev.tükeldusedListBD(juhuslikud.järjend, juhuslikud.pikkus));
//        System.out.println(kodu2BDev.tükeldusedListBD(korduvad.järjend, korduvad.pikkus));
//        System.out.println(kodu2BDev.tükeldusedListBD(juhuJärjend, aritmeetilineKesk));
//        System.out.println("------------------");
//        System.out.println(kodu2BDev.tükeldusedDünaamilineA(juht4.järjend, juht4.pikkus));

//        try {
//            int i = 0;
//            while (true) {
//                var juhuJärjend = abi.genJuhuJärjendDouble(3, 2, 5, 2);
////                var aritmeetilineKesk = ühikudAbi.ümarda(Arrays.stream(juhuJärjend).sum() /
////                        juhuJärjend.length, 2);
//                var aritmeetilineKesk = ühikudAbi.juhuArv(3, 7, 2);
////            System.out.println("Vaatlen" + Arrays.toString(juhuJärjend));
//
//                int vastus1 = kodu2BDev.tükeldusedListBD2(juhuJärjend, aritmeetilineKesk);
//                int vastus2 = kodu2BDev.tükeldusedListBD(juhuJärjend, aritmeetilineKesk);
//                int vastus3 = kodu2BDev.tükeldusedDünaamilineA(juhuJärjend, aritmeetilineKesk);
//
//                if (vastus1 != vastus2 || vastus1 != vastus3) {
//                    System.out.println("Leidsin järjendi mille korral ei klapi" + Arrays.toString(juhuJärjend));
//                    System.out.println("pikkus" + aritmeetilineKesk);
//                    throw new Exception("Leidsin järjendi mille korral ei klapi");
//                }
//                i++;
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//
//        }
//        System.out.println(kodu2BDev.tükeldusedListOpt(lihtsad4.järjend, lihtsad4.pikkus));

//        ar juhuJärjend = abi.genJuhuJärjendDouble(10, 1, 10, 2);v
//        var aritmeetilineKesk = ühikudAbi.ümarda( Arrays.stream(juhuJärjend).sum() /
//                juhuJärjend.length, 2);
//
//        System.out.println(Arrays.toString(juhuJärjend));
//        System.out.println(aritmeetilineKesk);
//        var jääkBD = new BigDecimal(Double.toString(lihtsad3.pikkus));
//        var vähimBD =  new BigDecimal(Double.toString(1.0));
//        int vastus = kodu2BDev.tükeldusedBigDecimalVäljasta(lihtsad3.järjend, jääkBD, vähimBD, 0, new ArrayList<>());
//        int vastus = kodu2BDev.tükeldusedBigDecimalOptimeeritud(lihtsad3.järjend, jääkBD, vähimBD, 0);
//        int vastus = kodu2BDev.tükeldusedDouble(lihtsad3.järjend, 4.0, 1.0, 0, new ArrayList<>());
//        int vastus = kodu2BDev.tükeldusedDünaamilineObj(lihtsad1.järjend, lihtsad1.pikkus);
//        int vastus = kodu2BDev.tükeldusedDünaamilineObj(lihtsad2.järjend, lihtsad2.pikkus);
//        int vastus = kodu2BDev.tükeldusedDünaamilineObj(lihtsad3.järjend, lihtsad3.pikkus);
        }

        @Test
        public void tükeldusedAjaTest () throws Exception {
            /* Big Decimal vs Optimeeritud */ // 2x vahe tööajal, sort võtab suure andmemahu korral < 1.00 ms
            /* Aga Tööaeg kasvab sama funktsiooni kiirusel u 10x kui sisendis suurendada kaks korda */

            for (int i = 20; i < 6000; i *= 2) {
                var juhuJärjend = abi.genJuhuJärjendDouble(i, 1, 10, 2);
                var aritmeetilineKesk = ühikudAbi.ümarda(Arrays.stream(juhuJärjend).sum() /
                        juhuJärjend.length, 2);
//            long start = System.nanoTime();
//            int vastus = kodu2BDev.tükeldusedBD(juhuJärjend, aritmeetilineKesk);
//            long stopp = System.nanoTime();
//
//            double aeg = abi.nanoToMilliSeconds(stopp-start);
//            System.out.println("n: " + i + ", aeg: " + aeg + ", vastus: " + vastus);

//            long start2 = System.nanoTime();
//            int vastus2 = kodu2BDev.tükeldusedBDOpt(juhuJärjend, aritmeetilineKesk);
//            long stopp2 = System.nanoTime();
//
//            double aeg2 = abi.nanoToMilliSeconds(stopp2-start2);
//
//            System.out.println("n: " + i + ", aeg: " + aeg2 + ", vastus: " + vastus2 );
//
//            /* Kõige parema tööajaga hetkel */
//            long start3 = System.nanoTime();
//            int vastus3= kodu2BDev.tükeldusedDünaamilineObj(juhuJärjend, aritmeetilineKesk);
//            long stopp3 = System.nanoTime();
//
//            double aeg3 = abi.nanoToMilliSeconds(stopp3-start3);
//            System.out.println("n: " + i + ", aeg: " + aeg3 + ", vastus: " + vastus3);

//                long start4 = System.nanoTime();
//                int vastus4 = kodu2BDev.tükeldusedDünaamilineA(juhuJärjend, aritmeetilineKesk);
//                long stopp4 = System.nanoTime();
//
//                double aeg4 = abi.nanoToMilliSeconds(stopp4 - start4);
//                System.out.println("n: " + i + ", aeg: " + aeg4 + ", vastus: " + vastus4);
//
//                long start5 = System.nanoTime();
//                int vastus5 = kodu2BDev.tükeldusedList(juhuJärjend, aritmeetilineKesk);
//                long stopp5 = System.nanoTime();
//
//                double aeg5 = abi.nanoToMilliSeconds(stopp5 - start5);
//                System.out.println("n: " + i + ", aeg: " + aeg5 + ", vastus: " + vastus5);
////
//                long start6 = System.nanoTime();
//                int vastus6 = kodu2BDev.tükeldusedListOpt(juhuJärjend, aritmeetilineKesk);
//                long stopp6 = System.nanoTime();
//
//                double aeg6 = abi.nanoToMilliSeconds(stopp6 - start6);
//                System.out.println("n: " + i + ", aeg: " + aeg6 + ", vastus: " + vastus6);

                long start7 = System.nanoTime();
                int vastus7 = kodu2BDev.tükeldusedListBD(juhuJärjend, aritmeetilineKesk);
                long stopp7 = System.nanoTime();

                double aeg7 = abi.nanoToMilliSeconds(stopp7 - start7);
                System.out.println("n: " + i + ", aeg: " + aeg7 + ", vastus: " + vastus7);
//
//                long start8 = System.nanoTime();
//                int vastus8 = kodu2BDev.tükeldusedListBD2(juhuJärjend, aritmeetilineKesk);
//                long stopp8 = System.nanoTime();
//
//                double aeg8 = abi.nanoToMilliSeconds(stopp8 - start8);
//                System.out.println("n: " + i + ", aeg: " + aeg8 + ", vastus: " + vastus8);
//
//                System.out.println("---------------------------------");
//            }
            }
        }

        @Test
        public void tükeldusedAMäluTest(){
            /* Big Decimal vs Optimeeritud */ // 2x vahe tööajal, sort võtab suure andmemahu korral < 1.00 ms
            /* Aga Tööaeg kasvab sama funktsiooni kiirusel u 10x kui sisendis suurendada kaks korda */

            for (int i = 5000; i < 20_000; i += 1000) {
                var juhuJärjend = abi.genJuhuJärjendDouble(i, 1, 10, 2);
                var aritmeetilineKesk = ühikudAbi.ümarda(Arrays.stream(juhuJärjend).sum() /
                        juhuJärjend.length, 2);

                long start = System.nanoTime();
                int vastus = kodu2BDev.tükeldusedListBD2(juhuJärjend, aritmeetilineKesk);
                long stopp = System.nanoTime();

                double aeg = abi.nanoToMilliSeconds(stopp - start);
                // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
                long heapFreeSize = Runtime.getRuntime().freeMemory();
                System.out.println("n: " + i + ", aeg: " + aeg + ", vastus: " + vastus + ", vaba heap: " + heapFreeSize);
                System.out.println("---------------------------------");
            }


        }

        public static void main (String[]args){

        }
    }
