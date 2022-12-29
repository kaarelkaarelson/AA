package kodu4;

import abi.ÜhikudAbi;
import kodu3.StäkkPinu2;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static kodu4.Kodu4.genereeriIsikukood;
import static kodu4.SorteeriIsikukoodid.sorteeriIsikukoodid;

public class TestKodu4 {


    @Test
    public void TestSorteeriIsikukoodid() {
        int n = 10;
        long[] isikukoodid = new long[10];

        for (int i = 0; i < n; i++) {

            long isikukood = genereeriIsikukood();
            isikukoodid[i] = isikukood;
        }

        for (int i = 0; i < isikukoodid.length; i++) {
            System.out.print(isikukoodid[i] + " ");
        }
        System.out.println();

        sorteeriIsikukoodid(isikukoodid);

    }

    @Test
    public void TestRadixSort() {

        int n = 10;
        long[] isikukoodid = new long[10];
//        int[] isikukoodid = new int[2];
//        isikukoodid[0] = 4321;
//        isikukoodid[1] = 1234;
        for (int i = 0; i < n; i++) {

            long isikukood = genereeriIsikukood();
            isikukoodid[i] = isikukood;
        }

        for (int i = 0; i < isikukoodid.length; i++) {
            System.out.print(isikukoodid[i] + " ");
        }
        System.out.println();
//       RadixSort.initializeredixSort(isikukoodid);
        PositsioonimeetodPaisk.sorteeriIsikukoodid(isikukoodid);
        for (int i = 0; i < isikukoodid.length; i++) {
            System.out.print(isikukoodid[i] + " ");
        }
       // sorteeriIsikukoodid(isikukoodid);

    }

    @Test
    public void stäkkAjaTest() throws Exception {
        int N = 10000;
        for (int i = 0; i < N; i++) {

            long[] isikukoodid = new long[N];
            int pikkus = isikukoodid.length;
            for (int j = 0; j < N; j++) {

                long isikukood = genereeriIsikukood();
                isikukoodid[i] = isikukood;
            }

            for (int j = 0; j < isikukoodid.length; j++) {
                System.out.print(isikukoodid[j] + " ");
            }
            System.out.println();

            long[] javaSort = Arrays.copyOf(isikukoodid, pikkus);
            long[] minuSort = Arrays.copyOf(isikukoodid, pikkus);
//        System.out.println("Algne: " );
//        Arrays.stream(samad).forEach(element -> System.out.print(element + " "));
//        System.out.println();

            Arrays.sort(javaSort);
//        System.out.println("java :");
//        Arrays.stream(javaSort).forEach(element -> System.out.print(element + " "));
//        System.out.println();

            sorteeriIsikukoodid(minuSort);
            PositsioonimeetodPaisk.sorteeriIsikukoodid(minuSort);
//        System.out.println("minu: " );
        Arrays.stream(minuSort).forEach(element -> System.out.print(element + " "));
//
            Assert.assertArrayEquals(javaSort, minuSort);
            System.out.println();
//        for (int i = 1; i < 10; i++) System.out.println(i + " " + ((i+1) >> 1));
        }
    }

    @Test
    public void testJuhud() {
        long[] samad = {16706263969L, 73311191532L, 43702012314L, 43702012314L, 33702012313L};
        long[] javaSort = Arrays.copyOf(samad, samad.length);
        long[] juht1 = {10001010013L, 20001010003L, 30001010004L, 40001010016L};
        long[] juht2 = {84811083673L, 11712094513L};
        long[] minuSort = Arrays.copyOf(samad, samad.length);
        System.out.println("Algne: ");
        Arrays.stream(samad).forEach(element -> System.out.print(element + " "));
        System.out.println();

//        Arrays.sort(javaSort);
//        System.out.println("java :");
//        Arrays.stream(javaSort).forEach(element -> System.out.print(element + " "));
//        System.out.println();

//        sorteeriIsikukoodid(minuSort);
        PositsioonimeetodPaisk.sorteeriIsikukoodid(samad);
        System.out.println("minu: ");
        for (int i = 0; i < samad.length ; i++) System.out.println(samad[i]);
//        Arrays.stream(samad);
        Arrays.stream(samad).forEach(element -> System.out.print(element + " tere "));
        System.out.println();
//        Assert.assertArrayEquals(javaSort, minuSort);
//        for (int i = 1; i < 10; i++) System.out.println(i + " " + ((i+1) >> 1));
    }

    @Test
    public void testRandom() {
        while (true) {
            int n = 3;
            long[] isikukoodid = new long[n];
            int pikkus = isikukoodid.length;
            for (int i = 0; i < n; i++) {

                long isikukood = genereeriIsikukood();
                isikukoodid[i] = isikukood;
            }

            for (int i = 0; i < isikukoodid.length; i++) {
                System.out.print(isikukoodid[i] + " ");
            }
            System.out.println();

            long[] javaSort = Arrays.copyOf(isikukoodid, pikkus);
            long[] minuSort = Arrays.copyOf(isikukoodid, pikkus);
//        System.out.println("Algne: " );
//        Arrays.stream(samad).forEach(element -> System.out.print(element + " "));
//        System.out.println();

            Arrays.sort(javaSort);
//        System.out.println("java :");
//        Arrays.stream(javaSort).forEach(element -> System.out.print(element + " "));
//        System.out.println();

        System.out.println("minu: " );
//        Arrays.stream(minuSorPost).forEach(element -> System.out.print(element + " "));

            Assert.assertArrayEquals(javaSort, minuSort);
            System.out.println();
//        for (int i = 1; i < 10; i++) System.out.println(i + " " + ((i+1) >> 1));
        }
    }

}
