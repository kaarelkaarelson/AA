package kodu1;
import java.util.Arrays;

public class Test {

    static Abi abi = new Abi();
    static JärjendPoleSorteeritudErind järjendPoleSorteeritudErind =
            new JärjendPoleSorteeritudErind("Järjend pole sorteeritud!");

    /**
     * Meetod mõõdab sorteerimisalgrotimi tööaega andmemahu kasvamisel konstandi võrra, st kui andmemahu kasv järgib
     * aritmeetilise jada valemit. Antud juhul: andmemaht = massiivi pikkus ja etteantav massiiv on sorteerimata.
     *.
     * @param minAndmemaht - Minimaalne andmemaht, millega sorteerimisalgoritm käivitatakse.
     * @param maxAndmemaht - Maksimaalne andmemaht, mida test ei ületa.
     * @param samm - suurus, mille alusel andmemaht konstandi võrra kasvab igal sammul.
     * @param algoritm - Etteantud algoritm. Vaikimisi (kui null) käivitatakse Java.util.Arrays.sort() meetod.
     * @return - Tagastab algoritmi tööajad millisekundites kahe komakoha täpsusega, mis on salvestatud ujukoma massiivi
     */
    double[] testAritmeetilineJada(int minAndmemaht, int maxAndmemaht, int samm, SorteerimiseAlgoritm
            algoritm) {

        int mituAjavõttu = (maxAndmemaht-minAndmemaht) / samm +1;
        double[] ajad = new double[mituAjavõttu]; // Määrame massiivi aegade salvestamiseks.
        int i = 0; // Massiivi indeks

        for (int andmemaht = minAndmemaht; andmemaht <= maxAndmemaht; andmemaht += samm) {
            try {
                int[] j = abi.genJuhuJärjend(andmemaht, 10, 100);

                long start = System.nanoTime();
                if (algoritm != null) algoritm.sorteeri(j);
                else Arrays.sort(j);
                long stopp = System.nanoTime();

                if (algoritm == null) abi.pööraJärjendVastupidi(j); // EI PÖÖRA VASTUPIDISEKS
                if (!kasSorteeritudMittekasvavalt(j)) {
                    throw järjendPoleSorteeritudErind;
                }

//                System.out.println(abi.nanoToMilliSeconds(stopp - start) + " ms");
                double aeg = abi.nanoToMilliSeconds(stopp-start);
                ajad[i] = aeg;
                i++;

            } catch (JärjendPoleSorteeritudErind e) {
                System.out.println(e);
            }
        }

        return ajad;
    }

    /**
     * Meetod mõõdab sorteerimisalgrotimi tööaega andmemahu pidevalt kordistamisel konstandiga, st kui andmemahu kasv järgib
     * geomeetrilise jada valemit. Antud juhul: andmemaht = massiivi pikkus ja etteantav massiiv on sorteerimata.
     *
     * @param minAndmemaht - Andmemaht, kus mõõtmist alustatakse.
     * @param kordaja - Konstant, millega andmemahtu igal sammul korrutatakse.
     * @param algoritm - SorteerimiseAlgoritmi objekt, millel on meetod sorteeri(). Vaikimisi (kui null) käivitatakse
     *                 java.util.Arrays.sort meetod.
     * @return - tagastab ujukomaarvu massiivi, kus ajad millisekundites kahe komakoha täpsusega.
     */
    public double[] testGeomeetrilineJada(int minAndmemaht, double kordaja, SorteerimiseAlgoritm algoritm) {

        double[] ajad = new double[6]; // Kitsenduse eesmärgil teeme ainult 6 ajavõttu, sest korrutis kasvab kiirelt.
        int andmemaht = minAndmemaht;
        int i = 0;

        while (i < 6) {
            try {
                int[] j = abi.genJuhuJärjend(andmemaht, 10, 100);

                long start = System.nanoTime();
                if (algoritm != null) algoritm.sorteeri(j);
                else Arrays.sort(j);
                long stopp = System.nanoTime();

                if (algoritm == null) abi.pööraJärjendVastupidi(j);
                if (!kasSorteeritudMittekasvavalt(j)) {
                    throw järjendPoleSorteeritudErind;
                }

//                System.out.println(abi.nanoToMilliSeconds(stopp - start) + " ms");
                ajad[i] = abi.nanoToMilliSeconds(stopp-start);
                andmemaht *= kordaja;
                i++;

            } catch (JärjendPoleSorteeritudErind e) {
                System.out.println(e);
            }
        }

        return ajad;
    }

    /**
     * Meetod mõõdab sorteerimisalgrotimi tööaega juba sorteeritud (mittekasvava) massiivi peal, mille andmemaht kasvab
     * konstandi võrra, st kui andmemahu kasv järgib aritmeetilise jada valemit. Antud juhul: andmemaht = massiivi pikkus.
     *.
     * @param minAndmemaht - Minimaalne andmemaht, millega sorteerimisalgoritm käivitatakse.
     * @param maxAndmemaht - Maksimaalne andmemaht, mida test ei ületa.
     * @param samm - suurus, mille alusel andmemaht konstandi võrra kasvab igal sammul.
     * @param algoritm - Etteantud algoritm. Vaikimisi (kui null) käivitatakse Java.util.Arrays.sort() meetod.
     * @return - Tagastab algoritmi tööajad millisekundites kahe komakoha täpsusega, mis on salvestatud ujukoma massiivi
     */
    double[] testSorteeritudJärjend(int minAndmemaht, int maxAndmemaht, int samm, SorteerimiseAlgoritm
            algoritm) {

        int mituAjavõttu = (maxAndmemaht-minAndmemaht) / samm +1;
        double[] ajad = new double[mituAjavõttu]; // Määrame massiivi aegade salvestamiseks.
        int i = 0; // Massiivi indeks

        for (int andmemaht = minAndmemaht; andmemaht <= maxAndmemaht; andmemaht += samm) {
            try {
                int[] j = abi.genMittekasvavJärjend(andmemaht, 10, 100);

                long start = System.nanoTime();
                if (algoritm != null) algoritm.sorteeri(j);
                else Arrays.sort(j);
                long stopp = System.nanoTime();

                if (algoritm == null) abi.pööraJärjendVastupidi(j);
                if (!kasSorteeritudMittekasvavalt(j)) {
                    throw järjendPoleSorteeritudErind;
                }

//                System.out.println(abi.nanoToMilliSeconds(stopp - start) + " ms");
                double aeg = abi.nanoToMilliSeconds(stopp-start);
                ajad[i] = aeg;
                i++;

            } catch (JärjendPoleSorteeritudErind e) {
                System.out.println(e);
            }
        }

        return ajad;
    }

    /**
     * Meetod mõõdab sorteerimisalgrotimi tööaega juba sorteeritud massiivi peal, mille andmemaht kasvab konstandi võrra, st kui andmemahu kasv järgib
     * aritmeetilise jada valemit. Antud juhul: andmemaht = massiivi pikkus.
     *.
     * @param minAndmemaht - Minimaalne andmemaht, millega sorteerimisalgoritm käivitatakse.
     * @param maxAndmemaht - Maksimaalne andmemaht, mida test ei ületa.
     * @param samm - suurus, mille alusel andmemaht konstandi võrra kasvab igal sammul.
     * @param algoritm - Etteantud algoritm. Vaikimisi (kui null) käivitatakse Java.util.Arrays.sort() meetod.
     * @return - Tagastab algoritmi tööajad millisekundites kahe komakoha täpsusega, mis on salvestatud ujukoma massiivi
     */
    double[] testMittekahanevJärjend(int minAndmemaht, int maxAndmemaht, int samm, SorteerimiseAlgoritm
            algoritm) {

        int mituAjavõttu = (maxAndmemaht-minAndmemaht) / samm +1;
        double[] ajad = new double[mituAjavõttu]; // Määrame massiivi aegade salvestamiseks.
        int i = 0; // Massiivi indeks

        for (int andmemaht = minAndmemaht; andmemaht <= maxAndmemaht; andmemaht += samm) {
            try {
                int[] j = abi.genMittekahanevJärjend(andmemaht, 10, 100);

                long start = System.nanoTime();
                if (algoritm != null) algoritm.sorteeri(j);
                else Arrays.sort(j);
                long stopp = System.nanoTime();

                if (algoritm == null) abi.pööraJärjendVastupidi(j);
                if (!kasSorteeritudMittekasvavalt(j)) {
                    throw järjendPoleSorteeritudErind;
                }


//                System.out.println(abi.nanoToMilliSeconds(stopp - start) + " ms");
                double aeg = abi.nanoToMilliSeconds(stopp-start);
                ajad[i] = aeg;
                i++;

            } catch (JärjendPoleSorteeritudErind e) {
                System.out.println(e);
            }
        }

        return ajad;
    }

    /**
     * Meetod testib sorteerimisalgrotimi äärejuhte äärejuhtudel, st kui järjendi pikkus on 0, 1 ja 2.
     *
     * @param algoritm - SorteerimiseAlgoritmi objekt, millel on meetod sorteeri(). Vaikimisi (kui null) käivitatakse
     *                 java.util.Arrays.sort meetod.
     * @return - tagastab tõeväärtuse vastavalt sellele kas algoritm läbis testi.
     */
    public boolean testÄärejuhud(SorteerimiseAlgoritm algoritm) {

        for (int i = 0; i < 2; i++){
            try {
                int[] j = abi.genJuhuJärjend(i, 10, 100);

                if (algoritm != null) algoritm.sorteeri(j);
                else Arrays.sort(j);

                if (!kasSorteeritudMittekasvavalt(j)) {
                    throw järjendPoleSorteeritudErind;
                }

                //System.out.println(abi.nanoToMilliSeconds(stopp - start) + " ms");

            } catch (JärjendPoleSorteeritudErind e) {
                System.out.println(e);
                return false;
            }
        }

        return true;
    }

    /**
     * Meetod, mis kontrollib kas etteantud järjend on sorteeritud mittekahanevalt.
     * @param järjend - etteantud arvujärjend
     * @return - tagastav tõeväärtuse selle põhjal kas järjend on sorteeritud või mitte.
     */
    boolean kasSorteeritud(int[] järjend){
        if (järjend.length == 0) return true;

        for (int i = 0; i < järjend.length-1; i++) {
            if (järjend[i] < järjend[i+1]) return false;
        }

        return true;
    }
    /**
     * Meetod, mis kontrollib kas etteantud järjend on sorteeritud mittekasvavalt.
     * @param järjend - etteantud arvujärjend
     * @return - tagastav tõeväärtuse selle põhjal kas järjend on sorteeritud või mitte.
     */
    boolean kasSorteeritudMittekasvavalt(int[] järjend){
        if (järjend.length == 0) return true;

        for (int i = 0; i < järjend.length-1; i++) {
            if (järjend[i] < järjend[i+1]) return false;
        }

        return true;
    }
}
