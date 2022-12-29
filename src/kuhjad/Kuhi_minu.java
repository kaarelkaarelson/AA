package kuhjad;

import java.util.ArrayList;
import java.util.List;

public class Kuhi_minu {
    private final List<Integer> kuhi = new ArrayList<>();

    public Kuhi_minu() {

    }

    /*
        public Kuhi( List<Integer> kuhi ){
            this.kuhi = kuhi;
        }
    */
    public int vasak(int k) {
        int indeks = 2 * k + 1;
        return indeks >= kuhi.size() ? -1 : indeks;
    }

    public int parem(int k) {
        int indeks = 2 * k + 2;
        return indeks >= kuhi.size() ? -1 : indeks;
    }

    public int ülemus(int k) {
        return (k - 1) / 2;
    }

    private void vaheta(int i, int j) {
        int ajutine = kuhi.get(i);
        kuhi.set(i, kuhi.get(j));
        kuhi.set(j, ajutine);

    }

    public void mullitaÜles(int k) {
        int ülemus = ülemus(k);
        if (ülemus == -1) return;
        if (kuhi.get(ülemus) < kuhi.get(k)) {
            vaheta(ülemus, k);
        }
    }

    public void mullitaAlla(int k) {
        int vasak = vasak(k);
        int parem = parem(k);

        if (vasak == -1) return; // Kui vasakut alluvat pole, siis pole ka paremat.
        if (parem != -1 && kuhi.get(parem) > kuhi.get(vasak)) {
            if (kuhi.get(k) < kuhi.get(parem)) {
                vaheta(vasak, k);
                mullitaAlla(vasak);
            }
        } else if (kuhi.get(vasak) > kuhi.get(k)) {
            vaheta(vasak, k);
            mullitaAlla(vasak);
        }
    }

    public void lisa(int väärtus) {
        kuhi.add(väärtus);
        mullitaÜles(kuhi.size() - 1);
    }

    public int eemaldaJuur() {
        int eemaldatav = kuhi.get(0);
        kuhi.set(0, kuhi.get(kuhi.size() - 1));
        kuhi.remove(kuhi.size() - 1);
        mullitaAlla(0);
        return eemaldatav;
    }

    // Juku ülesanded - need mis loengus on;

    public void kuhjasta() {

        kuhjasta(0);
    }

    public void kuhjasta(int k) {
        if (k == -1) return;
        kuhjasta(vasak(k));
        kuhjasta(parem(k));
        int vasak = vasak(k);
        int parem = parem(k);
    }

    public void kuhjastaC() {

        for (int i = kuhi.size() - 1; i >= 0; i--) {
            mullitaAlla(i);
        }
    }

    public void kuhjastaB() {

        for (int i = 0; i < kuhi.size(); i--) {
            mullitaAlla(i);
        }
    }

    public static void main(String[] args) {

    }
}