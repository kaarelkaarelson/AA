package Kodu1;

public class Pistemeetod implements SorteerimiseAlgoritm {
    /**
     * Meetod sorteerib ära arvujärjendi kasvavas järjestuses.
     * @param järjend - Etteantud arvujärjend.
     */
    @Override
    public void sorteeri(int[] järjend) {
        pistemeetod(järjend);
    }

    /**
     * Pistemeetodi algoritm, mis sorteerib etteantud järjendi kasvavas järjekorras.
     * @param järjend - Ettantud arvujärjend.
     */
    private void pistemeetod(int[] järjend) {
        int n = järjend.length;
        for (int i = 1; i < n; ++i) {
            int võti = järjend[i];
            int j = i - 1;

            /* Paigutame kõik elemendid ümber järjend[0..i-1] ühe positsiooni võrra edasi, mis on suuremad kui võti */
            while (j >= 0 && järjend[j] > võti) {
                järjend[j + 1] = järjend[j];
                j = j - 1;
            }
            järjend[j + 1] = võti;
        }
    }
}
