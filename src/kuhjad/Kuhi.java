package kuhjad;

import java.util.ArrayList;
import java.util.List;

public class Kuhi {

	private final List<Integer> kuhi;

	public Kuhi() {
		this.kuhi = new ArrayList<>();
	}

	public Kuhi(List<Integer> list) {
		this.kuhi = new ArrayList<>(list);
	}

	public int vasak(int k) {
		int indeks = 2 * k + 1;
		return indeks >= kuhi.size() ? -1 : indeks;
	}

	public int parem(int k) {
		int indeks = 2 * k + 2;
		return indeks >= kuhi.size() ? -1 : indeks;
	}

	public int ülemus(int k) {
		int indeks = (k - 1) / 2;
		return indeks < 0 ? -1 : indeks;
	}

	private void vaheta(int i, int j) {
		int ajutine = kuhi.get(i);
		kuhi.set(i, kuhi.get(j));
		kuhi.set(j, ajutine);
	}

	public void mullitaÜles(int k) {
		int ülemus = ülemus(k);
		if (ülemus == -1) return;
		if (kuhi.get(k) > kuhi.get(ülemus)) {
			vaheta(k, ülemus);
			mullitaÜles(ülemus);
		}
	}

	public void mullitaAlla(int k) {
		int vasak = vasak(k);
		int parem = parem(k);
		if (vasak == -1) return;
		if (parem != -1 && kuhi.get(parem) > kuhi.get(vasak)) {
			if (kuhi.get(k) < kuhi.get(parem)) {
				vaheta(k, parem);
				mullitaAlla(parem);
			}
		} else if (kuhi.get(k) < kuhi.get(vasak)) {
			vaheta(k, vasak);
			mullitaAlla(vasak);
		}
	}

	public void lisa(int väärtus) {
		kuhi.add(väärtus);
		mullitaÜles(kuhi.size() - 1);
	}

	public int eemaldaJuur() {
		int väärtus = kuhi.get(0);
		kuhi.set(0, kuhi.get(kuhi.size() - 1));
		kuhi.remove(kuhi.size() - 1);
		mullitaAlla(0);
		return väärtus;
	}

	public boolean kasTühi(){
		return this.kuhi.isEmpty();
	}

	public void kuhjasta() {
		kuhjasta(0);
	}

	private void kuhjasta(int k) {
		if (k == -1) return;
		kuhjasta(vasak(k));
		kuhjasta(parem(k));
		mullitaAlla(k);
	}

	public void kuhjastaC() {
		for (int i = kuhi.size() - 1; i >= 0; i--) mullitaAlla(i);
	}

	public void kuhjastaB() {
		for (int i = 0; i < kuhi.size(); i++) mullitaÜles(i);
	}
}
