package Kodu1;

import java.util.stream.IntStream;

public class Test {
    /**
     * Meetod, mis kontrollib kas etteantud järjend on sorteeritud.
     * @param järjend - etteantud arvujärjend
     * @return - tagastav tõeväärtuse selle põhjal kas järjend on sorteeritud või mitte.
     */
    boolean kasSorteeritud(int[] järjend){
        if (järjend.length == 0) return true;

        for (int i = 0; i < järjend.length-1; i++) {
            if (järjend[i] > järjend[i+1]) return false;
        }

        return true;
    }

    /**
     * Meetod, mis kontrollib kas etteantud järjend on sorteeritud ning kasutab selleks kahte indeksit.
     * @param järjend - etteantud arvujärjend
     * @return - tagastav tõeväärtuse selle põhjal kas järjend on sorteeritud või mitte.
     */
    boolean kasSorteeritudKaksIndeksit(int[] järjend){
        if (järjend.length == 0) return true;

        int i = 0; // Algusindeks
        int j = järjend.length-1; // Lõpuindeks
        while (i != j) { // Hakkame mõlemaltpoolt indeksitega tulema.
            if (järjend[i] > järjend[i+1] || järjend[j] < järjend[j-1]) return false;

            i++;
            j--;
        }

        return true;
    }

    /**
     * Alternatiiv meetodile kasSorteeritud, mis kasutab tsükli asemel vooge.
     * @param järjend - etteantud arvujärjend
     * @return - tagastav tõeväärtuse selle põhjal kas järjend on sorteeritud või mitte.
     */
    public boolean kasSorteeritudVoog(int[] järjend) {
        if (järjend.length == 0) return true;
        boolean kasSorteeritud = !(IntStream
                .range(0, järjend.length-1)
                .anyMatch(i -> järjend[i] > järjend[i+1]));

        return kasSorteeritud;
    }
}
