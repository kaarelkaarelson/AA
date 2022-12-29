package magasin;

import com.sun.source.tree.ReturnTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Praks5 {

    public static boolean yl1(String s) {
        s = s.replaceAll("[^a-zA-Z]", "");
        Stack<Character> magasin = new Stack<>();

        int i = 0;
        for (; i < s.length() / 2; i++) {
            magasin.push(s.charAt(i));
        }

        if (s.length() % 2 != 0) i++;

        for (; i < s.length(); i++){
            if (magasin.pop() != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static int  yl2Magasin(int n) {
        Stack<Integer> magasin = new Stack<>();
        magasin.push(n);

        int sum = 0; // Ühtede liidetis
        while (!magasin.isEmpty()) {
            int a = magasin.pop();
            switch (a){
                case 0:
                    break;
                case 1:
                    sum++;
                    break;
                default:
                    magasin.push(a-1);
                    magasin.push(a-2);
            }
        }
        return sum;
    }

    public static int yl2Järjekord(int n) {
        Queue<Integer> järjekord = new LinkedList<>(); // LinkedList on selle Queue

        // implementatsioon
        järjekord.add(n);

        int sum = 0; // Ühtede liidetis
        while (!järjekord.isEmpty()) {
            int a = järjekord.poll();
            switch (a){
                case 0:
                    break;
                case 1:
                    sum++;
                    break;
                default:
                    järjekord.add(a-1);
                    järjekord.add(a-2);
            }
        }
        return sum;
    }

    public static int yl2JärjekordIter(int n){
        if (n == 0) return 0;
        Queue<Integer> järjekord = new LinkedList<>();
        järjekord.add(0);
        järjekord.add(1);

        for (int i=0; i < n-1; i++){
            järjekord.add(järjekord.poll() + järjekord.peek());
        }

        järjekord.poll();
        return järjekord.poll();
    }

    /** Quick sordi halvim juht: massiivis ainult samad elemendid või juba sorteeritud ükskõik kumba pidi.
     * hakkab väljakutseid tegema rekursiivseid elemente hakakb sama palju väljakutseid tegema kui
     *
     * Java toimib magasini peal.
     *
     * Siin teostame magasiniga
    **/

    private static void vaheta(int[] massiiv, int i, int j) {
        int ajutine = massiiv[i];
        massiiv[i] = massiiv[j];
        massiiv[j] = ajutine;
    }

    private static int partition(int[] massiiv, int algus, int lõpp) {
        int x = massiiv[lõpp];
        int kuhu = algus;
        for (int i = algus; i < lõpp; i++) {
            if (massiiv[i] <= x) {
                vaheta(massiiv, kuhu, i);
                kuhu++;
            }
        }

        vaheta(massiiv, kuhu, lõpp);
        return kuhu;
    }

    private static int quicksort(int[] massiiv, int algus, int lõpp) {
        if (algus < lõpp) {
            int pi = partition(massiiv, algus, lõpp);

            return lõpp - algus +
                    quicksort(massiiv, algus, pi - 1) +
                    quicksort(massiiv, pi + 1, lõpp);
        }
        return 0;
    }

    public static int quicksort(int[] massiiv) {
        return quicksort(massiiv, 0, massiiv.length - 1);
    }

    public static void yl3(int[] m) {
        Stack<Lõik> magasin = new Stack<>(); // Hoiame algust ja lõppu
        magasin.push(new Lõik(0, m.length - 1));

        while (!magasin.isEmpty()) {
            Lõik lõik = magasin.pop();

            if (lõik.algus < lõik.lõpp) {
                int pi = partition(m, lõik.algus, lõik.lõpp);
                System.out.println(lõik.algus + " " + lõik.lõpp);

                // Nüüd me ei tee mitte rekursiivseid väljakutseid vaid paneme magasini

                magasin.push(new Lõik(lõik.algus, pi -1));
                magasin.push(new Lõik(pi + 1, lõik.lõpp));
            }
        }


    }

    private record Lõik(int algus, int lõpp){} // Java uus feature

    public static void main(String[] args) {
        System.out.println(yl1("aiassadassaia"));
        for (int i = 0; i < 10; i++) {
            System.out.println(yl2Magasin(i));
            System.out.println(yl2Järjekord(i));
            System.out.println(yl2JärjekordIter(i));
            System.out.println("----------------");
        }

        int[] m = new int[]{1, 5, 7, 3 ,4, 0};

        int[] j = new Random().ints(1000000).sorted().toArray();
        yl3(j);



    }
}
