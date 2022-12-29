package paisktabel;

import java.util.ArrayList;
import java.util.LinkedList;

public class PaiskSorteerija {
    private final ArrayList<LinkedList<Isik>> tabel;

//    private final int a;
//    private final int b;
//
//



    public PaiskSorteerija(Isik[] isikud){

        this.tabel = new ArrayList<>();
        for (int i = 0; i < isikud.length; i++) {
            this.tabel.add(new LinkedList<>());
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (Isik isik : isikud ){
            if (isik.getID() < min) min = isik.getID();
            if (isik.getID() > max) max = isik.getID();
        }
    }

    private int paiska (int n) {
        double T = (Math.sqrt(5) - 1) / 2;
        return 1;
//        return (int) (tabel.size() * (id * T - (int) (id * Y)));
    }

    public void lisa(Isik isik){
        int koht = paiska(isik.getID());

        tabel.get(koht).add(isik);
    }

    public static void sorteeri(Isik[] isikud) {
//        PaiskSorteerija sorteerija = new Paisksorteerija(isikud);
//        for (Isik isik: isikud) {
//
//            sorteerija.lisa(isik);
//        }
//        int i = 0;
//
//        for (LinkedList<Isik> isik : sorteerija.tabel) {
//
//
//        }
    }
}
