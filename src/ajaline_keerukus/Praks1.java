package ajaline_keerukus;

public class Praks1 {

	// 1a
	public static int suurimAllaSekundiRekursiivne() {
		long start, stopp;
		int i = 0;
		do {
			start = System.currentTimeMillis();
			Fibo.rekursiivne(i);
			stopp = System.currentTimeMillis();
			i++;
		} while (stopp - start < 1000);

		return i - 1;
	}

	// 1c
	public static long suurimAllaSekundiIteratiivne() {
		long start, stopp;
		int i = 0;
		int aste = 5;

		do {
			do {
				i += Math.pow(10, aste);
				start = System.currentTimeMillis();
				Fibo.iteratiivneBI(i);
				stopp = System.currentTimeMillis();
			} while (stopp - start < 1000);
			i -= Math.pow(10, aste);
			aste--;
		} while (aste > -1);

		return i;
	}

	// 2a
	public static int suurimAllaSekundiBittGen() {
		long start, stopp;
		int i = 0;
		do {
			start = System.currentTimeMillis();
			bittGen(i);
			stopp = System.currentTimeMillis();
			i++;
		} while (stopp - start < 1000);

		return i - 1;
	}

	public static int bittGen(int n) {
		return bittGen(n, "");
	}

	public static int bittGen(int n, String vektor) {
		if (n == vektor.length()) return 1;
		return bittGen(n, vektor + "0") + bittGen(n, vektor + "1");
	}

	public String DnaMolGen(int n, String genoomiLõik){
		if (genoomiLõik.length() == n) System.out.println(genoomiLõik);

		else {
			String[] nukleotiidid = new String[]{"A", "T", "C", "G"};
			for (int i = 0; i < nukleotiidid.length; i++){
				DnaMolGen(n, genoomiLõik + nukleotiidid[i]);

			}
		}
		return "";
	}
}
