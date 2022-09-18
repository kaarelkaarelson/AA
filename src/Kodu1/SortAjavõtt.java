package Kodu1;

import abi.JärjendAbi;

public class SortAjavõtt implements Ajavõtt {
    static JärjendAbi abi = new JärjendAbi();

    @Override
    public void võtaAega(int minAndmemaht, int maxAndmemaht, int samm, SorteerimiseAlgoritm algoritm) {

        for (int i = minAndmemaht; i <= maxAndmemaht; i += samm) {
            try {
                int[] j = abi.genJärjend(i);

                long start = System.currentTimeMillis();
                algoritm.sorteeri(j);
                long stopp = System.currentTimeMillis();

                System.out.println((double)(stopp-start)/1000 + " s " +  (stopp-start) ); // ms

            } catch (JärjendPoleSorteeritudErind e) {
                System.out.println(e);
            }

        }
    }
}
