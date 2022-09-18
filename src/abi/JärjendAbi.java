package abi;

public class JärjendAbi {

    public int[] genJärjend(int n) {
        int[] tulemus = new int[n];

        for (int i = 0; i < n; i++) {
            tulemus[i] = (int) (Math.random() * 10);
        }

        return tulemus;
    }


}
