package kodu1;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Pistemeetod implements SorteerimiseAlgoritm {
    int loendur = 0;

    /**
     * Ülekirjutatud liidese meetod, mis delegeerib arvujärjendi sorteerimise konkreetsele sortimismeetodile.
     *
     * @param järjend - etteantud arvujärjend.
     *                //
     */
    @Override
    public void sorteeri(int[] järjend) {
        pistemeetod(järjend);
    }

    /**
     * Pistemeetodi algoritm, mis sorteerib etteantud järjendi kasvavas järjekorras.
     *
     * @param järjend - Ettantud arvujärjend.
     *                <p>
     *                Algoritm kopeeritud ja muudetud veebilehelt: https://www.geeksforgeeks.org/insertion-sort/
     */
    private void pistemeetod(int[] järjend) {
        int n = järjend.length;
        for (int i = 1; i < n; ++i) {
            int võti = järjend[i];
            int j = i - 1;

            /* Paigutame kõik elemendid ümber järjend[0..i-1] ühe positsiooni võrra edasi, mis on suuremad kui võti */
            while (j >= 0 && järjend[j] < võti) {
                järjend[j + 1] = järjend[j];
                j = j - 1;
            }
            järjend[j + 1] = võti;
        }
    }

    /**
     * Ülekirjutatud liidese meetod, mis delegeerib arvujärjendi sorteerimise konkreetsele sortimismeetodile.
     *
     * @param järjend - etteantud arvujärjend.
     *                //
     */

    private <T> void sorteeri(T[] järjend) {
        pistemeetod(järjend);
    }

    /**
     * Pistemeetodi algoritm, mis sorteerib etteantud järjendi kasvavas järjekorras.
     *
     * @param järjend - Ettantud arvujärjend.
     *                <p>
     *                Algoritm kopeeritud ja muudetud veebilehelt: https://www.geeksforgeeks.org/insertion-sort/
     */
    private <T> void pistemeetod(T[] järjend) {

        int n = järjend.length;
        for (int i = 1; i < n; ++i) {
            T võti = järjend[i];
            int j = i - 1;
            System.out.println(Arrays.toString(järjend));
            /* Paigutame kõik elemendid ümber järjend[0..i-1] ühe positsiooni võrra edasi, mis on suuremad kui võti */
            while (j >= 0 && ((Comparable) järjend[j]).compareTo(võti) < 0) {
                järjend[j + 1] = järjend[j];
                j = j - 1;
            }
            järjend[j + 1] = võti;
        }
    }

    public void sorteeri(String[] järjend) {
        pistemeetod(järjend);
    }

    /**
     * Pistemeetodi algoritm, mis sorteerib etteantud järjendi kasvavas järjekorras.
     *
     * @param järjend - Ettantud arvujärjend.
     *                <p>
     *                Algoritm kopeeritud ja muudetud veebilehelt: https://www.geeksforgeeks.org/insertion-sort/
     */
    private void pistemeetod(String[] järjend) {
        int n = järjend.length;
        for (int i = 1; i < n; ++i) {
            String võti = järjend[i];
            int j = i - 1;

            /* Paigutame kõik elemendid ümber järjend[0..i-1] ühe positsiooni võrra edasi, mis on suuremad kui võti */
            while (j >= 0 && järjend[j].compareTo(võti) < 0) {
                järjend[j + 1] = võti;
                j = j - 1;
                loendur++;
                System.out.println(Arrays.toString(järjend) + ", loendur: " + loendur + ", sees");
            }

            järjend[j + 1] = võti;
            loendur++;
            System.out.println(Arrays.toString(järjend) + ", loendur: " + loendur);
        }
    }

    private char[] convert(String[] words) {
        return Arrays.stream(words)
                .collect(Collectors.joining())
                .toCharArray();
    }

    public char[] insertionSort(String[] s) {
        return insertionSort(convert(s));
    }

    private char[] insertionSort(char[] array) {
        int len = array.length;

        for (int i = len - 1; i > 0; i--) {
            if (array[i] < array[i - 1]) {
                swap(array, i, i - 1);
                loendur++;
            }
        }

        for (int i = 2; i < len; i++) {
            char temp = array[i];
            int j = i;
            while (temp < array[j - 1]) {
                array[j] = array[j - 1];
                loendur++;
                j--;
            }
            array[j] = temp;
        }

        return array;
    }

    private static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
