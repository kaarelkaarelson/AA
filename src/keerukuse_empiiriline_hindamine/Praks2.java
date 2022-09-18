package keerukuse_empiiriline_hindamine;

import ajaline_keerukus.Fibo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Praks2 {

	public static int juhuslik(int a, int b) {
		//Antud: poollõik [a,b)
		//Tagastab: juhusliku täisarvu sellelt lõigult
		return (int) (Math.round(Math.random() * (b - a) + a));
	}

	public static int[] juhuslikMassiiv(int n, int a, int b) {
		//Antud: n - elementide arv, poollõik [a,b)
		//Tagastab: n-elemendilise juhuslike täisarvudega järjendi, elemendid lõigult [a,b)
		int[] x = new int[n];
		for (int i = 0; i < n; i++)
			x[i] = juhuslik(a, b);
		return x;
	}

	public static int massiiviSummaKopeerimisega(int[] m) {
		if (m.length == 1) return m[0];

		int esimesePikkus = m.length / 2;
		int teisePikkus = m.length - esimesePikkus;
		int[] esimene = new int[esimesePikkus];
		int[] teine = new int[teisePikkus];

		System.arraycopy(m, 0, esimene, 0, esimesePikkus);
		System.arraycopy(m, esimesePikkus, teine, 0, teisePikkus);

		return massiiviSummaKopeerimisega(esimene) +
				massiiviSummaKopeerimisega(teine);
	}

	public static int massiiviSummaIndeksitega(int[] m) {
		return massiiviSummaIndeksitega(m, 0, m.length);
	}

	private static int massiiviSummaIndeksitega(int[] m, int i, int pikkus) {
		if (pikkus == 1) {
			return m[i];
		}

		int esimesePikkus = pikkus / 2;
		int teisePikkus = pikkus - esimesePikkus;

		return massiiviSummaIndeksitega(m, i, esimesePikkus) +
				massiiviSummaIndeksitega(m, i + esimesePikkus, teisePikkus);
	}

	public static int[] lisa(int[] m, int e) {
		int[] uus = new int[m.length + 1];
		for (int i = 0; i < m.length; i++) {
			uus[i] = m[i];
		}
		uus[m.length] = e;
		return uus;
	}

	public static void hanoi(int n) {
		tõsta(n, 'A', 'B', 'C');
	}

	public static void tõsta(int n, char kust, char kuhu, char ajutine) {
		if (n == 1) {
			System.out.println("Tõsta ketas tornist " + kust + " torni " + kuhu + ".");
		} else {
			tõsta(n - 1, kust, ajutine, kuhu);
			tõsta(1, kust, kuhu, ajutine);
			tõsta(n - 1, ajutine, kuhu, kust);
		}
	}

	public static void yl2a() {
		for (int i = 1; i <= 20; i++) {
			int pikkus = 10000000 * i;
			int[] juhuslikMassiiv = juhuslikMassiiv(pikkus, 10, 100);
			System.out.println("----\n" + pikkus);

			long startK = System.currentTimeMillis();
			massiiviSummaKopeerimisega(juhuslikMassiiv);
			long stoppK = System.currentTimeMillis();
			System.out.println("kopeerimisega " + (stoppK - startK));

			long startI = System.currentTimeMillis();
			massiiviSummaIndeksitega(juhuslikMassiiv);
			long stoppI = System.currentTimeMillis();
			System.out.println("indeksitega " + (stoppI - startI));
		}
	}

	public static void yl2c() {
		for (int i = 1; i < 10; i++) {
			int pikkus = 20000000 * i;
			int[] massiiv = new int[pikkus];
			long start = System.currentTimeMillis();
			lisa(massiiv, 500);
			long stopp = System.currentTimeMillis();
			System.out.println(stopp - start);
		}

		for (int i = 1; i < 10; i++) {
			int pikkus = 20000000 * i;
			List<Integer> list = new ArrayList<>(Arrays.stream(new int[pikkus]).boxed().toList());
			long start = System.currentTimeMillis();
			list.add(500);
			long stopp = System.currentTimeMillis();
			System.out.println(stopp - start);
		}

		for (int i = 1; i < 10; i++) {
			int pikkus = 20000000 * i;
			List<Integer> list = new LinkedList<>(Arrays.stream(new int[pikkus]).boxed().toList());
			long start = System.currentTimeMillis();
			list.add(500);
			long stopp = System.currentTimeMillis();
			System.out.println(stopp - start);
		}
	}

	public static void yl2d() {
		List<Long> ajad = new ArrayList<>();
		for (int i = 1; i <= 22; i++) {
			long start = System.currentTimeMillis();
			hanoi(i);
			long stopp = System.currentTimeMillis();
			ajad.add(stopp - start);
		}

		for (Long l : ajad) {
			System.out.println(l);
		}
	}

	public static void yl2e() {
		for (int i = 1; i <= 50; i++) {
			long start = System.currentTimeMillis();
			Fibo.rekursiivne(i);
			long stopp = System.currentTimeMillis();
			System.out.println(stopp - start);
		}
	}
}
