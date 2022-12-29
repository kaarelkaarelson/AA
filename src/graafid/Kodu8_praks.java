import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Kodu8_praks {

	/** Leiab minimaalse kaugusega toespuu Primi algoritmiga.
	 *
	 * @param nimed Asukohtade nimed
	 * @param K Asukohtade koordinaadid kaherealise tabelina
	 * @return Minimaalse toespuu kaarte loend, kujul [[i1, j2], [i2, j2], ...], kus i ja j on asukohtade indeksid alates 0-ist
	 */
	public static int[][] toesPrim(String[] nimed, double[][] K){
		return null;
	}

	/** Leiab minimaalse kaugusega toespuu Kruskali algoritmiga.
	 *
	 * @param nimed Asukohtade nimed
	 * @param K Asukohtade koordinaadid kaherealise tabelina
	 * @return Minimaalse toespuu kaarte loend, kujul [[i1, j2], [i2, j2], ...], kus i ja j on asukohtade indeksid alates 0-ist
	 */
	public static int[][] toesKruskal(String[] nimed, double[][] K){
		return null;
	}

	/** Leiab lähendi rändkaupmehe probleemile.
	 *
	 * @param nimed Asukohtade nimed
	 * @param K Asukohtade koordinaadid kaherealise tabelina
	 * @return Rändkaupmehe lähend kui asukohtade läbimise järjestus arvude 0...523 permutatsioonina
	 */
	public static int[] rändkaupmees(String[] nimed, double[][] K){
		return null;
	}

	/**
	 * Abimeetod kahe punkti kauguste leidmiseks nende indeksite abil.
	 * @param K Asukohtade koordinaadid kaherealise tabelina
	 * @param a Esimese tipu indeks
	 * @param b Teise tipu indeks
	 * @return Punktide vaheline kaugus kilomeetrites
	 */
	public static double kaugus(double[][] K, int a, int b) {
		return kaugus(K[0][a], K[1][a], K[0][b], K[1][b]);
	}

	/**
	 * Leiab kahe punkti vahelise kauguse, kasutades valemit siit:
	 * https://en.wikipedia.org/wiki/Haversine_formula
	 *
	 * @param lai1 Esimese punkti laiuskraad
	 * @param pik1 Esimese punkti pikkuskraad
	 * @param lai2 Teise punkti laiuskraad
	 * @param pik2 Teise punkti pikkuskraad
	 * @return Punktide vaheline kaugus kilomeetrites
	 */
	public static double kaugus(double lai1, double pik1, double lai2, double pik2) {
		double dLaius = Math.pow(Math.sin(Math.toRadians(lai2 - lai1) / 2), 2);
		return 2 * 6371.0088 * Math.asin(Math.sqrt(dLaius +
				(1 - dLaius - Math.pow(Math.sin(Math.toRadians(lai1 + lai2) / 2), 2)) *
						Math.pow(Math.sin(Math.toRadians(pik2 - pik1) / 2), 2)));
	}

	/**
	 * Leiab rändkaupmehe lähendi kogu tee pikkuse.
	 *
	 * @param p Rändkaupmehe lähend, antud asukohtade indeksite permutatsioonina
	 * @param K Asukohtade koordinaadid kaherealise tabelina
	 * @return Antud rändkaupmehe lähendi kogu tee pikkus
	 */
	public static double kaugusRändkaupmees(int[] p, double[][] K) {
		return Double.NaN;
	}

	public static void main(String[] args) {
		FailiInfo info = loeInfo();
		String[] nimed = info.nimed;
		double[][] K = info.K;

		System.out.println(Arrays.toString(nimed));
		System.out.println(Arrays.deepToString(K));
	}

	/**
	 * Abimeetodi omniva.csv failist sisendandmete lugemiseks
	 * @return Record postipunktide nimede (nimed) ja asukohtade koordinaatidega (K)
	 */
	private static FailiInfo loeInfo() {
		String failinimi = "omniva.csv";

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(failinimi),
						StandardCharsets.UTF_8
				)
		)) {

			List<String> nimed = new LinkedList<>();
			List<Double> laiuskraadid = new LinkedList<>();
			List<Double> pikkuskraadid = new LinkedList<>();

			String rida;
			while ((rida = br.readLine()) != null) {
				String[] tükid = rida.split(",");
				nimed.add(tükid[0]);
				laiuskraadid.add(Double.parseDouble(tükid[1]));
				pikkuskraadid.add(Double.parseDouble(tükid[2]));
			}

			return new FailiInfo(
					nimed.toArray(new String[0]),
					new double[][]{
							laiuskraadid.stream().mapToDouble(d -> d).toArray(),
							pikkuskraadid.stream().mapToDouble(d -> d).toArray()
					}
			);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public record FailiInfo(String[] nimed, double[][] K) {
	}
}