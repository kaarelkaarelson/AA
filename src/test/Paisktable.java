package test;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Paisktable {
    public static void main(String[] args) {
//       /**/ kimpudeks(999);
        väikseim(24, 0, 14, 0, 0);
    }

    private static void väikseim(int i1, int i2, int i3, int a, int m)
    {
        if (a > 100 || m > 100) return;

        int id1 = a*i1 % 10;
        int id2 = a*i2 % 10;
        int id3 = a*i3 % 10;

        if (id2 != id1 && id2 != id3) {
            System.out.println("a: " + a + ", m: " + m);
            return;
        }

        väikseim(i1, i2, i3, a+1, m);
        väikseim(i1, i2, i3, a, m+1);


    }

    private static void kimpudeks(int k) {
        Hashtable<Integer, List<Integer>> paisktabel = new Hashtable<>();

        for (int j = 0; j < 10; j++) {
            List<Integer> list = new ArrayList<>();
            paisktabel.put(j, list);
        }

        for (int j = 0; j < k; j++) {
//            int id/**/ = j*j*j % 10;

            int id =(12*k) % 10;
            paisktabel.get(id).add(j);

        }
        for (int j = 0; j < paisktabel.size(); j++) {
            System.out.println(paisktabel.get(j).size());

        }
    }
}

