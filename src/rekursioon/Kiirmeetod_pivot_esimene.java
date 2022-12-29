package rekursioon;

public class Kiirmeetod_pivot_esimene{
    int loenda = 0;
    int väljakutseid(int[] järjend) {
        sorteeri(järjend);
        return loenda;
    }

    /**
     * Ülekirjutatud liidese meetod, mis delegeerib arvujärjendi sorteerimise konkreetsele sortimismeetodile.
     * @param järjend - etteantud arvujärjend.
     */
    public void sorteeri(int[] järjend) {
        kiirmeetod(järjend, 0, järjend.length-1);
    }

    /**
     * Kiirmeetodi algoritm, mis sorteerib arvujärjendi mittekasvavalt. Kasutusel on 3-haruline versioon.
     * Algoritm kopeeritud veebilehelt (ja muudetud): https://gist.github.com/HaoyangFan/b9f0c1f56bb35d944c54ae8437c942a4
     *
     * @param järjend - etteantud arvyjärjend.
     * @param algus - järjendi algusindeks.
     * @param lõpp - järjendi lõpuindeks.
     */
    private void kiirmeetod(int[] järjend, int algus, int lõpp) {
        if (algus >= lõpp)   return;

        // Valime esimese elemendi eraldajaks
        int eraldaja = järjend[0];

        // Viime eraldaja järjendi ette ette
        vaheta(järjend, algus, 0);

        // Määrame indeksid elemendi jaoks, mis on eraldajast suurem ja need mis on väiksemad.
        int väiksem = algus, suurem = lõpp, i = algus + 1;

        // Läbime järjendi vahemiku
        while (i <= suurem) {
            // Suuremad elemendid viime eraldaja ette
            if (järjend[i] > eraldaja)    vaheta(järjend, i++, väiksem++);
                // Väiksemad elemendid viime viime hetkesele postisioonile.
            else if (järjend[i] < eraldaja)    vaheta(järjend, i, suurem--);

            else    i++; // Jätame elemendid samasse positsiooni
        }

        // Jaotame alamülesanneteks
        kiirmeetod(järjend, algus, väiksem - 1);
        kiirmeetod(järjend, suurem + 1, lõpp);
        loenda += 2;
    }

    /**
     * Meetodi partitsiooni abimeetod, mis vahetab etteantud järjendis kahe elemendi positsiooni.
     *
     * @param järjend - etteantud järjend, kus elemente vahetatakse.
     * @param i - esimese elemendi indeks
     * @param j - teise elemendi indeks.
     */
    private void vaheta(int[] järjend, int i, int j) {
        int temp = järjend[i];
        järjend[i] = järjend[j];
        järjend[j] = temp;
    }
}
