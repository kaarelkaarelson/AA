package keerukuse_empiiriline_hindamine;

public class Praks2_minu {

    public static int juhuslik(int a, int b) {
        return (int) (Math.round(Math.random() * (b-a) + a));
    }

    public static int massiviSummaKopeerimisega(int[] m) {
        if (m.length == 1) return m[0];

        int esimesePikkus = m.length / 2;
        int teisePikkus = m.length - esimesePikkus;
        int[] esimene = new int[esimesePikkus];
        int[] teine = new int[teisePikkus];

        System.arraycopy(m,0, esimene, 0, esimesePikkus);
        System.arraycopy(m,0, esimene, 0, esimesePikkus);

        return massiviSummaKopeerimisega(esimene) + massiviSummaKopeerimisega(teine);
    }

    public static int massiiviSummaIndeksitega(int[] m){
        return massiiviSummaIndeksitega(m, 0, m.length);
    }

    private static int massiiviSummaIndeksitega(int[] m, int i, int pikkus) {
        if (pikkus == 1) {
            return m[i];
        }

        int esimesePikkus = pikkus / 2;
        int teisePikkus = pikkus - esimesePikkus;
        return massiiviSummaIndeksitega(m, i, esimesePikkus) + massiiviSummaIndeksitega(m, i+esimesePikkus, teisePikkus);
    }

    public static void hanoi(int n) {
        //tõsta(n, "A", "B", "C");

    }

    public static void tõsta(int n, char kust, char kuhu, char ajutine) {
        if (n == 1) {
            System.out.println("Tõsta ketas tromist " + kust );
        }
    }

    public static void main(String[] args) {
        int[] m = {1, 2, 3, 4, 5};

        System.out.println();

        //Keerukuse kontroll
        for (int i = 1; i <= 20; i++) {
            int pikkus = 100_000_000;
            //List<Integer> List = new ArrayList<>(Arrays.stream(new int[pikkus]).bo)
        }


    }
}
