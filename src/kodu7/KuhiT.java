package kodu7;

//public class KuhiT<T extends TippT> {
//
//    private final List<T> kuhi;
//
//    public KuhiT() {
//        this.kuhi = new ArrayList<T>();
//    }
//
//    public KuhiT(List<T> list) {
//        this.kuhi = new ArrayList<T>(list);
//    }
//
//    public int vasak(int k) {
//        int indeks = 2 * k + 1;
//        return indeks >= kuhi.size() ? -1 : indeks;
//    }
//
//    public int parem(int k) {
//        int indeks = 2 * k + 2;
//        return indeks >= kuhi.size() ? -1 : indeks;
//    }
//
//    public int ülemus(int k) {
//        int indeks = (k - 1) / 2;
//        return indeks < 0 ? -1 : indeks;
//    }
//
//    private void vaheta(int i, int j) {
//        T ajutine = kuhi.get(i);
//        kuhi.set(i, kuhi.get(j));
//        kuhi.set(j, ajutine);
//    }
//
//    public void mullitaÜles(int k) {
//        int ülemus = ülemus(k);
//        if (ülemus == -1) return;
//        if (kuhi.get(k).getVäärtus() < kuhi.get(ülemus).getVäärtus()) { // muudetud
//            System.out.println("Vahetan " + kuhi.get(k) +  " ja " +   kuhi.get(ülemus));
//            vaheta(k, ülemus);
//            mullitaÜles(ülemus);
//        }
//    }
//
//    public void mullitaAlla(int k) {
//        int vasak = vasak(k);
//        int parem = parem(k);
//        if (vasak == -1) return;
//        if (parem != -1 && kuhi.get(parem).getVäärtus() < kuhi.get(vasak).getVäärtus()) {
//            if (kuhi.get(k).getVäärtus() > kuhi.get(parem).getVäärtus()) { // muudetud
//                vaheta(k, parem);
//                mullitaAlla(parem);
//            }
//        } else if (kuhi.get(k).getVäärtus() > kuhi.get(vasak).getVäärtus()) {
//            vaheta(k, vasak);
//            mullitaAlla(vasak);
//        }
//    }
//
//    public void lisa(T väärtus) {
//        kuhi.add(väärtus);
//        mullitaÜles(kuhi.size() - 1);
//    }
//
//    public T eemaldaJuur() {
//        T väärtus = kuhi.get(0);
//        kuhi.set(0, kuhi.get(kuhi.size() - 1));
//        kuhi.remove(kuhi.size() - 1);
//        mullitaAlla(0);
//        return väärtus;
//    }
//
//    public boolean kasTühi() {
//        return this.kuhi.isEmpty();
//    }
//
//    public void kuhjasta() {
//        kuhjasta(0);
//    }
//
//    private void kuhjasta(int k) {
//        if (k == -1) return;
//        kuhjasta(vasak(k));
//        kuhjasta(parem(k));
//        mullitaAlla(k);
//    }
//
//    public void kuhjastaC() {
//        for (int i = kuhi.size() - 1; i >= 0; i--) mullitaAlla(i);
//    }
//
//    public void kuhjastaB() {
//        for (int i = 0; i < kuhi.size(); i++) mullitaÜles(i);
//    }
//}
