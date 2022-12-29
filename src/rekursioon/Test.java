package rekursioon;

import kodu1.Kiirmeetod;
import abi.JärjendAbi;
import kodu1.Pistemeetod;

import java.util.Arrays;

public class Test {
    static int ridu = 0;
    static Kiirmeetod_pivot_esimene esimene_lahkmeks = new Kiirmeetod_pivot_esimene();
    static Kiirmeetod keskmine_lahkmeks = new Kiirmeetod();
    static JärjendAbi abi = new JärjendAbi();
    static Pistemeetod pistemeetod = new Pistemeetod();


    public static void sõned(String s, int n) {
        if (n == 0) {
            System.out.println(s);
        } else {

            sõned(s + "0", n - 1);


            sõned(s + "1", n - 1);

        }
    }

    public static void fun(int x, int tase) {
        if (
                tase > 0
        ) {
            System.out.println(x);
            fun(
                    x+1
                    ,
                    tase-1
            );
        }
    }

    public static boolean otsi(int[] a, int i, int j, int x) {
        if (i == j) {

            return false;

        }
        if (a[i] == x) {

            return true;

        }

        return otsi(a, i + 1, j, x);

    }

    public static void ruudud(int n) {
        if (n == 1) {
            System.out.println(1);
        } else {

            System.out.println(n*n);


            ruudud(n-1);

        }
    }



    public static void h(int n) {
        if (n >= 1) {
            System.out.println("Kutsun funktsiooni, argumendiga: " + (n-1));
            h(n-1);
            System.out.println(n);
        }
        System.out.println(n);
    }

//    public static void f(int n) {
//        if (n >= 1) {
//            System.out.println(n);
//            System.out.println("Kutsun funktsiooni, argumendiga: " + (n-1));
//            f(n-1);
//            System.out.println("Kutsun funktsiooni, argumendiga: " + (n-1));
//            f(n-1);
//        }
//    }

    public static String leia(String s) {
        if (s.length() > 1) {
            return leia(s.substring(1));
        }
        return s;
    }

    public static String arvud(int n) {
        if (n == 0) {

            return "";

        } else {

            return arvud(n-1) + n + arvud(n-1);

        }
    }


    public static void f(int n) {
        if (n == 0) {
            System.out.println(1);
            ridu += 1;
        } else {
            f(n-1);
            f(n-1);
        }
    }
    public static void arvud1(int n) {
        if (n == 1) {
            System.out.println(1);
        } else {

            arvud1(n-1);


            System.out.println(n);

        }
    }

    public static void gun(int x, int tase) {
        if (tase > 0) {
            gun(x+1,tase-1);
            System.out.println(x);
        }
    }

    public static int k(int n){
        if (n > 1) {
            int vastus = k(n-1) + 2 * (n-1);
            return vastus;
        }
        return 1;

    }

    public static int s(int p, int q){
        if (p == 0 && q >= 0) return q;
        return s(p-1, q+1);

    }

    public static void f1(int n) {
        if (n >= 1) {
            System.out.println(n);
            System.out.println("Kutsun funktsiooni, argumendiga: " + (n-1));
            f1(n-1);
            System.out.println(n);
        }
    }

    public static void h1(int n) {
        if (n >= 1) {
            System.out.println("Kutsun funktsiooni, argumendiga: " + (n-1));
            h1(n-1);
            System.out.println("Kutsun funktsiooni, argumendiga: " + (n-1));
            h1(n-1);
            System.out.println(n);
        }
    }

    public static int f(int[] a) {
        if (a.length == 1) {
            return a[0];
        } else {
            int x = f(Arrays.copyOfRange(a, 1, a.length));
            return (a[0] < x ? a[0] : x);
        }
    }

    public static int summa5(int n) {
        if (n == 1) {

            return 5;

        } else {

            return 5 * n +  summa5(n-1);

        }
    }


    public static void main(String[] args) {

        String[] j = new String[]{"P", "I", "S", "T", "E", "M", "E", "E", "T", "O", "D"};
        pistemeetod.insertionSort(j);




//        int[] a = {5, 2, 6, 3, 4};
//        int[] samad = abi.genSamadeElementidegaJärjend(100000, 1);
//        int[] pooleldi_sorteeritud = {1, 1, 1, 3, 4};
//        int[] juhuslik = abi.genJuhuJärjend(100000, 1, 1000);
//        int[] sorteeritud = abi.genMittekahanevJärjend(100000, 1, 1000);
//        int[] sorteeritudMittekasvav = abi.genMittekasvavJärjend(100000, 1, 100);
        //Collections.reverse(Arrays.asList(sorteeritud));
        //Arrays.stream(sorteeritud).forEach(e -> System.out.println(e));

//        int[] j = abi.genKonstantseltKasvavJärjendkahanevJärjend(23, 1, 1);
////        System.out.println(keskmine_lahkmeks.väljakutseid(j));
//        System.out.println(Arrays.toString(j));
//        System.out.println(esimene_lahkmeks.väljakutseid(juhuslik));
//        System.out.println(esimene_lahkmeks.väljakutseid(juhuslik)); // 335
//        System.out.println(esimene_lahkmeks.väljakutseid(sorteeritud)); // 200
//        System.out.println(esimene_lahkmeks.väljakutseid(sorteeritudMittekasvav)); // 398
//        System.out.println(esimene_lahkmeks.väljakutseid(samad));

//        System.out.println(summa5(5));
//        System.out.println(f(a));
//        System.out.println("Kutsun funktsiooni, argumendiga: " + (2));
//        h1(2);
//        for (int i = 0; i < 12; i++) {
//            f(i);
//        }
//        f(5);
//        System.out.println("Ridu: " + ridu);
//        gun(6,4);
//        arvud1(5);

//        System.out.println(s(6, 7));
//        System.out.println(k(6));
//        System.out.println(arvud(4));
//        System.out.println(otsi(a, 0, a.length, 0));
//        sõned("", 3);
//        System.out.println(leia("soov"));
//        System.out.println("Kutsun funktsiooni, argumendiga: " + (2));
//        f(2);
//        fun(6, 4);

        //ruudud(3);

//        f(9);
    }
}
