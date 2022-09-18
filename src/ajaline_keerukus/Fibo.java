package ajaline_keerukus;

import java.math.BigInteger;

public class Fibo {
	public static int rekursiivne(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		return rekursiivne(n - 1) + rekursiivne(n - 2);
	}

	public static int iteratiivne(int n) {
		int a = 0, b = 1;
		for (int i = 0; i < n; i++) {
			int ajutine = a + b;
			a = b;
			b = ajutine;
		}
		return a;
	}

	public static BigInteger iteratiivneBI(int n) {
		BigInteger a = BigInteger.ZERO, b = BigInteger.ONE;
		for (int i = 0; i < n; i++) {
			BigInteger ajutine = a.add(b);
			a = b;
			b = ajutine;
		}
		return a;
	}

}
