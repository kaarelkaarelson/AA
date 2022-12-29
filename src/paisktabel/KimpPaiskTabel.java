package paisktabel;

import java.util.ArrayList;
import java.util.LinkedList;


public class KimpPaiskTabel {

    private final ArrayList<LinkedList<Isik>> tabel;

    public KimpPaiskTabel(int n ){

        this.tabel = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            this.tabel.add(new LinkedList<>());
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

    public Isik otsi(int id){
        int koht = paiska(id);

        return tabel.get(koht).stream()
                .filter(isik -> isik.getID() == id)
                .findFirst().orElse(null);

    }

    public Isik eemalda (int id ) {
        int koht = paiska(id);

        LinkedList<Isik> kimp = tabel.get(koht);
        return null;
    }
}
