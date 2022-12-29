package rekursioon;

import keerukuse_empiiriline_hindamine.Praks2;

import java.util.ArrayList;
import java.util.List;

public class Praks4 {

	// yl4 praktikum 3 juurest

	public static List<Integer> yl4(int[] hinnad) {
		List<Integer> võimalused = yl4(hinnad, 0, 0);
		return võimalused.stream()
				.sorted()
//				.distinct()
				.toList();
	}

	private static List<Integer> yl4(int[] hinnad, int summa, int i) {
		if (i == hinnad.length) {
			return List.of(summa);
		}
		List<Integer> list = new ArrayList<>();
		list.addAll(yl4(hinnad, summa + hinnad[i], i + 1));
		list.addAll(yl4(hinnad, summa, i + 1));
		return list;
	}

	// Praktikum 4 ülesanded

	public static int hanoi(int n) {
		return tõsta(n, 'A', 'B', 'C');
	}

	public static int tõsta(int n, char kust, char kuhu, char ajutine) {
		if (n == 1) {
			return 1;
//			System.out.println("Tõsta ketas tornist " + kust + " torni " + kuhu + ".");
		} else {
			return 1 + // SEE 1 + OLI MUL PRAKTIKUMIS PUUDU
					tõsta(n - 1, kust, ajutine, kuhu) +
					tõsta(1, kust, kuhu, ajutine) +
					tõsta(n - 1, ajutine, kuhu, kust);
		}
	}

	public static void yl2() {
		System.out.println("2^n * 1.5 - 2\n---");
		for (int i = 1; i <= 10; i++) {
			System.out.printf("2^%d * 1.5 - 2 = %d%n", i, hanoi(i));
		}
		// teeta(2^n) keerukus (ülejäänud on konstant)
	}

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

	public static void yl3() {
		for (int i = 1; i <= 100; i++) {
			int n = 1000 * i;
			int võrdlemised = 0;
			for (int j = 0; j < 10; j++) {
				int[] juhuslik = Praks2.juhuslikMassiiv(n, 1000, 10000);
				võrdlemised += quicksort(juhuslik);
			}
			System.out.println(võrdlemised / 10);
		}
		// teeta(nlogn) keskmise juhu keerukus
		// pikkust 10 korda suurendades suureneb võrdluste arv u 15 korda
	}
}