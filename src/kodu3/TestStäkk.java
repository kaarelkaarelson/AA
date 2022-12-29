package kodu3;

import kodu2.Kodu2BTest;
import org.junit.Assert;
import org.junit.Test;
import abi.ÜhikudAbi;

import java.util.Arrays;

public class TestStäkk {


    @Test
    public void stäkkTest() {
        int i = 6 ;
//        System.out.println("Testin " + i + " lippu");
//            var kontroll = StäkkKontroll.solveN_Queen(i);
//        var efektiivne = StäkkPinu.solveN_Queen(i);
//        StäkkPinu2.solveN_Queen(i);
//        var lahend = StäkkLõimed.nLippu(i);
//        System.out.println(lahend);
//            Assert.assertEquals(lahend, 10);
    }


    @Test
    public void juhudTest() {
        for (int i = 4; i < 10; i++) {

//        System.out.println("Testin " + i + " lippu");
//            var kontroll = StäkkKontroll.solveN_Queen(i);
//        var efektiivne = StäkkPinu.solveN_Queen(i);
//        StäkkPinu2.solveN_Queen(i);
//        var lahend = StäkkLõimed.nLippu(i);
//        System.out.println(lahend);
        }
//            Assert.assertEquals(efektiivne, kontroll);
    }

    @Test
    public void stäkkAjaTest() throws Exception {
        /* Big Decimal vs Optimeeritud */ // 2x vahe tööajal, sort võtab suure andmemahu korral < 1.00 ms
        /* Aga Tööaeg kasvab sama funktsiooni kiirusel u 10x kui sisendis suurendada kaks korda */

        for (int i = 0; i < 15; i += 1) {
//            long start = System.nanoTime();
//            int vastus = StäkkKontroll.solveN_Queen(i);
//            long stopp = System.nanoTime();j
//
//            double aeg = ÜhikudAbi.nanoToMilliSeconds(stopp - start);
//            System.out.println("Naiivne -> n: " + i + ", aeg: " + aeg + ", vastus: " + vastus);
//
//            long start2 = System.nanoTime();
//            int vastus2 = StäkkPinu.solveN_Queen(i);
//            long stopp2 = System.nanoTime();
//
//            double aeg2 = ÜhikudAbi.nanoToMilliSeconds(stopp2 - start2);
//            System.out.println("Pinuga -> n: " + i + ", aeg: " + aeg2 + ", vastus: " + vastus2);

            long start3 = System.nanoTime();
            int vastus3 = StäkkPinu2.solveN_Queen(i);
            long stopp3 = System.nanoTime();

            double aeg3 = ÜhikudAbi.nanoToMilliSeconds(stopp3 - start3);
            System.out.println("Pinuga2 -> n: " + i + ", aeg: " + aeg3 + ", vastus: " + vastus3);

            long start4 = System.nanoTime();
//            int vastus4 = StäkkPinu3.solveN_Queen(i);
            long stopp4 = System.nanoTime();

            double aeg4 = ÜhikudAbi.nanoToMilliSeconds(stopp4 - start4);
//            System.out.println("PinugaNoLocal -> n: " + i + ", aeg: " + aeg4 + ", vastus: " + vastus4);

//            long start5 = System.nanoTime();
//            int vastus5 = NQueens.solveNQueens(i);
//            long stopp5 = System.nanoTime();
//
//            double aeg5 = ÜhikudAbi.nanoToMilliSeconds(stopp5 - start5);
//            System.out.println("NQueens -> n: " + i + ", aeg: " + aeg5 + ", vastus: " + vastus5);

//            long start6 = System.nanoTime();
//            int vastus6 = StäkkLõimed.nLippu(i);
//            long stopp6 = System.nanoTime();
//
//            double aeg6 = ÜhikudAbi.nanoToMilliSeconds(stopp6 - start6);
//            System.out.println("Lõimed -> n: " + i + ", aeg: " + aeg6 + ", vastus: " + vastus6);
//
//            System.out.println("-------------------------------------");
        }


    }

    @Test
    public void AjaTestParim() {
        for (int i = 0; i < 20; i++) {

            long start = System.nanoTime();
            int vastus = NQueens.solveNQueens(i);
            long stopp = System.nanoTime();

            double aeg = ÜhikudAbi.nanoToMilliSeconds(stopp - start);
            System.out.println("NQueens -> n: " + i + ", aeg: " + aeg + ", vastus: " + vastus);
        }
    }
    @Test
    public void AjaTestLõimed() {
        for (int i = 0; i < 20; i++) {

            long start = System.nanoTime();
            int vastus = NQueens.solveNQueens(i);
            long stopp = System.nanoTime();

            double aeg = ÜhikudAbi.nanoToMilliSeconds(stopp - start);
            System.out.println("NQueens -> n: " + i + ", aeg: " + aeg + ", vastus: " + vastus);
        }
    }

}
