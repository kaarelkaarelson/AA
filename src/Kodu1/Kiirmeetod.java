package Kodu1;

public class Kiirmeetod implements SorteerimiseAlgoritm {
    /**
     * Ülekirjutatud liidese meetod
     * @param järjend - etteantud arvujärjend.
     */
    @Override
    public void sorteeri(int[] järjend) {
//        kiirmeetod(järjend, 0, järjend.length-1);
        kiirmeetod(järjend, 0, järjend.length-1);
    }

    /**
     * Kiirmeetodi algoritm, mis sorteerib järjendi.
     * @param järjend - etteantud järjend.
     * @param algus - järjendi algusindeks.
     * @param lõpp - järjendi lõpuindeks.
     */
    private void kiirmeetod(int[] järjend, int algus, int lõpp)
    {
        if (algus < lõpp)
        {

            // pi on partitsiooni indeks,
            int pi = partitsioon(järjend, algus, lõpp);

            // Sorteerime alamjärjendeid eraldi enne ja pärast partitsiooni
            kiirmeetod(järjend, algus, pi - 1);
            kiirmeetod(järjend, pi + 1, lõpp);
        }
    }

    /**
     * Kiirmeetodi abimeetod, mis töötleb etteantud osajärjendit.
     * @param järjend - etteantud osajärjend.
     * @param algus - algusindeks.
     * @param lõpp - lõpuindeks.
     * @return - tagastab indeksi väärtuse, mille alusel tehakse töödeldakse kiirmeetodis osajärjendeid.
     */
    private int partitsioon(int[] järjend, int algus, int lõpp)
    {

        int kontrollitav = järjend[lõpp]; // kontrollitav argument

        int i = (algus - 1); // Määrame hüpoteetilise väiksema elemendi

        for(int j = algus; j <= lõpp - 1; j++)
        {

            if (järjend[j] < kontrollitav) // Kui leiame elemendi, mis on väiksem kui kontrollitav, siis vahetame.
            {
                i++; // Lisaks määrame indeksi, millest edasi vaatame.
                vaheta(järjend, i, j);
            }
        }
        vaheta(järjend, i + 1, lõpp);
        return (i + 1);
    }

    /**
     * Meetodi partitsiooni abimeetod, mis vahetab etteantud järjendis kahe elemendi positsiooni.
     * @param järjend - etteantud järjend, kus elemente vahetatakse.
     * @param i - esimese elemendi indeks
     * @param j - teise elemendi indeks.
     */
    private void vaheta(int[] järjend, int i, int j)
    {
        int temp = järjend[i];
        järjend[i] = järjend[j];
        järjend[j] = temp;
    }

    /*3 SUUNALINE KIIRMEETOD*/

    public void sort(int[] A) {
        if (A == null || A.length <= 1) return;
        quicksort(A, 0, A.length - 1);
    }

    private void quicksort(int[] A, int start, int end) {
        if (start >= end)   return;

        // choose the middle element as the pivot
        int mid = start + (end - start) / 2;
        int pivot = A[mid];

        // move pivot to the front
        swap(A, start, mid);

        // lt: index to store next element < pivot
        // gt: index to store next element > pivot
        int lt = start, gt = end, i = start + 1;
        // 坑1: i <= gt not i <= end
        while (i <= gt) {
            // move elements < pivot to the front
            if (A[i] < pivot)    swap(A, i++, lt++);
                // NOTE: don't move i, as we don't know whether value of the element
                // from the end that got swap to current i and we need to check it
                // 坑2: i does not immediately increment
            else if (A[i] > pivot)    swap(A, i, gt--);
                // keep elements == pivot on the same positition
            else    i++;
        }

        // Invariant: A[start .. lt - 1] < A[lt .. gt] = pivot < A[gt + 1 .. end]
        quicksort(A, start, lt - 1);
        quicksort(A, gt + 1, end);
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
