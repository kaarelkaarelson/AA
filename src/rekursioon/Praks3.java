package praks3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Praks3 {

	public static void yl1(int n, int k, String vektor) {
		if (n == vektor.length()) {
			if (k == vektor.chars().filter(c -> c == '1').count()) {
				System.out.println(vektor);
			}
			return;
		}
		yl1(n, k, vektor + "0");
		yl1(n, k, vektor + "1");
	}

	public static void yl1Parem(int n, int k, String vektor, int ühtesid) {
		if (n == vektor.length()) {
			if (k == ühtesid) {
				System.out.println(vektor);
			}
			return;
		}
		yl1Parem(n, k, vektor + "0", ühtesid);
		if (ühtesid < k) yl1Parem(n, k, vektor + "1", ühtesid + 1);
	}

	public static void yl1Parim(int n, int k, String vektor) {
		if (n == vektor.length()) {
			System.out.println(vektor);
			return;
		}
		if (k < n - vektor.length()) yl1Parim(n, k, vektor + "0");
		if (k > 0) yl1Parim(n, k - 1, vektor + "1");
	}

	public static int[] lisa(int[] m, int e) {
		int[] uus = new int[m.length + 1];
		System.arraycopy(m, 0, uus, 0, m.length);
		uus[m.length] = e;
		return uus;
	}

	public static void yl2(int[] kaalud) {
		int[][] tulemus = yl2(kaalud, new int[0], new int[0], 0, 0);
		System.out.println(Arrays.toString(tulemus[1]) + " Summa: " + Arrays.stream(tulemus[1]).sum());
		System.out.println(Arrays.toString(tulemus[2]) + " Summa: " + Arrays.stream(tulemus[2]).sum());
	}

	private static int[][] yl2(int[] kaalud, int[] rühm1, int[] rühm2, int tasakaal, int i) {
		if (i == kaalud.length) {
			return new int[][]{
					{Math.abs(tasakaal)},
					rühm1,
					rühm2
			};
		}
		int[][] esimene = yl2(kaalud, lisa(rühm1, kaalud[i]), rühm2, tasakaal + kaalud[i], i + 1);
		if (esimene[0][0] == 0) return esimene;
		int[][] teine = yl2(kaalud, rühm1, lisa(rühm2, kaalud[i]), tasakaal - kaalud[i], i + 1);
		return esimene[0][0] < teine[0][0] ? esimene : teine;
	}

	public static void yl2Parem(int[] kaalud) {
		int[][] tulemus = yl2Parem(kaalud, new int[0], 0, 0);

		List<Integer> rühm1 = new ArrayList<>();
		List<Integer> rühm2 = new ArrayList<>();

		int[] rühm1Indeksid = tulemus[1];
		int indeks = 0;
		for (int i = 0; i < kaalud.length; i++) {
			if (indeks < rühm1Indeksid.length && i == rühm1Indeksid[indeks]) {
				rühm1.add(kaalud[i]);
				indeks++;
			} else {
				rühm2.add(kaalud[i]);
			}
		}

		System.out.println(rühm1 + " Summa: " + rühm1.stream().mapToInt(i -> i).sum());
		System.out.println(rühm2 + " Summa: " + rühm2.stream().mapToInt(i -> i).sum());
	}

	private static int[][] yl2Parem(int[] kaalud, int[] rühm1Indeksid, int tasakaal, int i) {
		if (i == kaalud.length) {
			return new int[][]{
					{Math.abs(tasakaal)},
					rühm1Indeksid
			};
		}
		int[][] esimene = yl2Parem(kaalud, lisa(rühm1Indeksid, i), tasakaal + kaalud[i], i + 1);
		if (esimene[0][0] == 0) return esimene;
		int[][] teine = yl2Parem(kaalud, rühm1Indeksid, tasakaal - kaalud[i], i + 1);
		return esimene[0][0] < teine[0][0] ? esimene : teine;
	}

	public static void yl2Parim(int[] kaalud) {
		int pool = Arrays.stream(kaalud).sum() / 2;
		int[][] tulemus = yl2Parim(kaalud, pool, new int[0], 0, 0);

		List<Integer> rühm1 = new ArrayList<>();
		List<Integer> rühm2 = new ArrayList<>();

		int[] rühm1Indeksid = tulemus[1];
		int indeks = 0;
		for (int i = 0; i < kaalud.length; i++) {
			if (indeks < rühm1Indeksid.length && i == rühm1Indeksid[indeks]) {
				rühm1.add(kaalud[i]);
				indeks++;
			} else {
				rühm2.add(kaalud[i]);
			}
		}

		System.out.println(rühm1 + " Summa: " + rühm1.stream().mapToInt(i -> i).sum());
		System.out.println(rühm2 + " Summa: " + rühm2.stream().mapToInt(i -> i).sum());
	}

	private static int[][] yl2Parim(int[] kaalud, int pool, int[] rühm1Indeksid, int rühm1Kaal, int i) {
		if (rühm1Kaal >= pool) {
			return new int[][]{
					{rühm1Kaal - pool},
					rühm1Indeksid
			};
		}
		if (i == kaalud.length) {
			return new int[][]{
					{Integer.MAX_VALUE},
					new int[0]
			};
		}
		int[][] esimene = yl2Parim(kaalud, pool, lisa(rühm1Indeksid, i), rühm1Kaal + kaalud[i], i + 1);
		if (esimene[0][0] == 0) return esimene;
		int[][] teine = yl2Parim(kaalud, pool, rühm1Indeksid, rühm1Kaal, i + 1);
		return esimene[0][0] < teine[0][0] ? esimene : teine;
	}

	public static int yl3(int n, String lahutus) {
		if (n < 0) return 0;
		if (n == 0) {
			System.out.println(lahutus.substring(1));
			return 1;
		}
		return (lahutus.endsWith("1+2") ? 0 : yl3(n - 1, lahutus + "+1")) +
				(lahutus.endsWith("2+1") ? 0 : yl3(n - 2, lahutus + "+2"));
	}

	public static void yl5(int[] arvud, int k, int[] kombinatsioon, int i) {
		if (i == arvud.length) {
			System.out.println(Arrays.toString(kombinatsioon));
			return;
		}
		if (k > 0) yl5(arvud, k - 1, lisa(kombinatsioon, arvud[i]), i + 1);
		if (k < arvud.length - i) yl5(arvud, k, kombinatsioon, i + 1);
	}

	public static void yl5Parem(int[] arvud, int k, int[] kombinatsioon, int i) {
		if (k == 0) {
			System.out.println(Arrays.toString(kombinatsioon));
			return;
		}
		if (k > 0) yl5Parem(arvud, k - 1, lisa(kombinatsioon, arvud[i]), i + 1);
		if (k < arvud.length - i) yl5Parem(arvud, k, kombinatsioon, i + 1);
	}
}
