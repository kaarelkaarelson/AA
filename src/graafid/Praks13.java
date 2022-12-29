package graafid;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Praks13 {

	private static final String INFOFAIL = "linnade_kaugused.csv";

	private String[] linnad;
	private double[][] naabrusmaatriks;
	private double[][] kaugused;

	public Praks13(int x) {
		loeInfo();
		eemaldaLiigaPikad(x);
		floydWarshall();
	}

	private void loeInfo() {
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(INFOFAIL),
						StandardCharsets.UTF_8
				)
		)) {
			linnad = br.readLine().split(",");

			naabrusmaatriks = new double[linnad.length][];

			String rida;
			for (int i = 0; (rida = br.readLine()) != null; i++) {
				String[] tükid = rida.split(",", -1);
				tükid[i] = "0";

				naabrusmaatriks[i] = Arrays.stream(tükid).mapToDouble(Double::parseDouble).toArray();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void eemaldaLiigaPikad(int x) {
		for (int i = 0; i < naabrusmaatriks.length; i++) {
			for (int j = 0; j < naabrusmaatriks[0].length; j++) {
				if (naabrusmaatriks[i][j] > x) {
					naabrusmaatriks[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
	}

	private void floydWarshall() {
		// https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm

		kaugused = new double[naabrusmaatriks.length][];
		for (int i = 0; i < naabrusmaatriks.length; i++) {
			kaugused[i] = naabrusmaatriks[i].clone();
		}

		for (int k = 0; k < kaugused.length; k++) {
			for (int i = 0; i < kaugused.length; i++) {
				for (int j = 0; j < kaugused.length; j++) {
					if (kaugused[i][j] > kaugused[i][k] + kaugused[k][j]) {
						kaugused[i][j] = kaugused[i][k] + kaugused[k][j];
					}
				}
			}
		}
	}

	private int linnaIndeks(String linn) {
		for (int i = 0; i < linnad.length; i++) {
			if (linnad[i].equals(linn)) return i;
		}
		throw new IllegalArgumentException("Sellist linna ei ole");
	}

	public double kaugus(String linn1, String linn2) {
		return kaugused[linnaIndeks(linn1)][linnaIndeks(linn2)];
	}

	public static void main(String[] args) {
		Praks13 praks13 = new Praks13(20);
		System.out.println(praks13.kaugus("Tallinn", "Tartu"));
	}
}
