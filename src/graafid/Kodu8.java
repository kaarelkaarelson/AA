/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2022/2023 sügissemester
 *
 * Kodutöö. Ülesanne nr 8
 * Teema: Toespuud
 *
 * Autor: Kaarel-Richard Kaarelson
 *
 *****************************************************************************/
package graafid;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Kodu8 {

    /**
     * Leiab minimaalse kaugusega toesepuu Primi algoritmiga.
     *
     * @param nimed Asukohtade nimed
     * @param K     Asukohtade koordinaadid kaherealise tabelina
     * @return Minimaalse toesepuu kaarte loend, kujul [[i1, j2], [i2, j2], ...], kus i ja j on asukohtade indeksid alates 0-ist
     */
    public static int[][] toesPrim(String[] nimed, double[][] K) {
        int[][] kaared = new int[nimed.length - 1][];

        BitSet valitud = new BitSet();
        int[] lähtepunktid = new int[nimed.length];
        double[] kaugused = new double[nimed.length];
        Arrays.fill(kaugused, Double.POSITIVE_INFINITY);
        kaugused[0] = 0;

        int viimaneLisatu = 0;
        for (int i = 0; i < kaared.length; i++) {
            double lühim = Double.POSITIVE_INFINITY;
            int lühimIndeks = -1;


            for (int siht = 0; siht < kaugused.length; siht++) {
                if (!valitud.get(siht) && kaugused[siht] > 0) {
                    double kaugus = kaugus(K, viimaneLisatu, siht);

                    if (kaugus < kaugused[siht]) {
                        kaugused[siht] = kaugus;
                        lähtepunktid[siht] = viimaneLisatu;

                        if (kaugus < lühim) {
                            lühim = kaugus;
                            lühimIndeks = siht;
                        }
                    } else if (kaugused[siht] < kaugus) {

                        if (kaugused[siht] < lühim) {
                            lühim = kaugused[siht];
                            lühimIndeks = siht;
                        }
                    }
                }
            }

            kaared[i] = new int[]{lähtepunktid[lühimIndeks], lühimIndeks};
            kaugused[lühimIndeks] = 0;
            valitud.set(lühimIndeks);
            viimaneLisatu = lühimIndeks;
        }

        double kogukaal = arvutaKogukaal(K, kaared);
        System.out.println("\nToese puu kogukaal: " + kogukaal);

        return kaared;
    }

    private static double arvutaKogukaal(double[][] K, int[][] kaared) {
        double kogukaal = 0;
        BitSet valitud = new BitSet();

        for (int i = 0; i < kaared.length; i++) {
            int lähtekoht = kaared[i][0];
            int sihtkoht = kaared[i][1];

            valitud.set(lähtekoht);

            if (valitud.get(sihtkoht)) {
                throw new RuntimeException("graaf sisaldab tsükleid: sihtkoht " + sihtkoht + " juba võetud" + " , indeks " + i);
            }

            double kaugus = kaugus(K, lähtekoht, sihtkoht);
            kogukaal += kaugus;
        }

        return kogukaal;
    }



    private static double arvutaTeepikkus(double[][] K, int[] punktid) {
        double kogukaal = 0;
        BitSet valitud = new BitSet();

        int eelmine=0;
        for (int i = 0; i < punktid.length; i++) {
            int punkt = punktid[i];

            double kaugus = kaugus(K, eelmine, punkt);
            kogukaal += kaugus;
        }

        return kogukaal;
    }

    /**
     * Leiab minimaalse kaugusega toesepuu Kruskali algoritmiga.
     * Toespuus kaari ühe võrra vähem kui tippe.
     *
     * @param nimed Asukohtade nimed
     * @param K     Asukohtade koordinaadid kaherealise tabelina
     * @return Minimaalse toesepuu kaarte loend, kujul [[i1, j2], [i2, j2], ...], kus i ja j on asukohtade indeksid alates 0-ist
     */
    public static int[][] toesKruskal(String[] nimed, double[][] K) {
        List<Kaugus> kaugused = leiaKaugused(K);

        int[][] kaared = new int[nimed.length - 1][];
        int mitu = 0;


        List<BitSet> sidususkomponendid = new ArrayList<>(); // Efektiivsem alternatiiv. Lisad arvu 3 ja muudab 3-nda biti 1-ks.

        välimine:
        for (Kaugus kaugus : kaugused) {
            int kompA = -1, kompB = -1;

            if (kaugus.kaugus == 0) continue;
            for (int i = 0; i < sidususkomponendid.size(); i++) {
                // TODO - kui on samas, siis continue välimine
                BitSet bs = sidususkomponendid.get(i);

                if (bs.get(kaugus.a) && bs.get(kaugus.b)) {
                    continue välimine;
                }
                if (bs.get(kaugus.a)) {
                    kompA = i;
                }
                if (bs.get(kaugus.b)) {
                    kompB = i;
                }

            }
            kaared[mitu++] = new int[]{kaugus.a, kaugus.b};

            // loo uus komp
            if (kompA == -1 && kompB == -1) {
                BitSet bs = new BitSet();
                bs.set(kaugus.a);
                bs.set(kaugus.b);

                sidususkomponendid.add(bs);

                // lisa a Kompb-sse
            } else if (kompA == -1) {
                BitSet bs = sidususkomponendid.get(kompB);

                bs.set(kaugus.a);



                // lisa a Kompa-sse
            } else if (kompB == -1) {
                BitSet bs = sidususkomponendid.get(kompA);

                bs.set(kaugus.b);

                // ühenda
            } else {
                BitSet bs1 = sidususkomponendid.remove(kompA);
                BitSet bs2 = sidususkomponendid.remove(kompA < kompB ? kompB - 1 : kompB);

                bs1.or(bs2);
                sidususkomponendid.add(bs1);
            }
        }

        return kaared;
    }

    private static List<Kaugus> leiaKaugused(double[][] K) {
        List<Kaugus> kaugused = new ArrayList<>();

        for (int i = 0; i < K[0].length; i++) {
            for (int j = 0; j < K[0].length; j++) {
                kaugused.add(new Kaugus(
                        kaugus(K, i, j),
                        i,
                        j));

            }

        }

        kaugused.sort(Comparator.comparingDouble(Kaugus::kaugus));
        return kaugused;
    }

    private record Kaugus(double kaugus, int a, int b) {
    }

    // Alternatiiv
    private record Kaar(int lähtepunkt, double kaugus) {
    }


    /**
     * Leiab lähendi rändkaupmehe probleemile.
     *
     * @param nimed Asukohtade nimed
     * @param K     Asukohtade koordinaadid kaherealise tabelina
     * @return Rändkaupmehe lähend kui asukohtade läbimise järjestus arvude 0...523 permutatsioonina
     */
    public static int[] rändkaupmees(String[] nimed, double[][] K) {
//        int[][] kaared = new int[nimed.length][];
        int[] kaared = new int[nimed.length];

        BitSet valitud = new BitSet();
        int[] lähtepunktid = new int[nimed.length];
        double[] kaugused = new double[nimed.length];
        Arrays.fill(kaugused, Double.POSITIVE_INFINITY);
        kaugused[0] = 0;

        int viimaneLisatu = 0;
        for (int i = 0; i < kaared.length-1; i++) {
            double lühim = Double.POSITIVE_INFINITY;
            int lühimIndeks = -1;

            for (int siht = 0; siht < kaugused.length; siht++) {
                if (!valitud.get(siht) && kaugused[siht] > 0) {
                    double kaugus = kaugus(K, viimaneLisatu, siht);

                    if (kaugus < kaugused[siht]) {
                        kaugused[siht] = kaugus;
                        lähtepunktid[siht] = viimaneLisatu;

                        if (kaugus < lühim) {
                            lühim = kaugus;
                            lühimIndeks = siht;
                        }
                    } else if (kaugused[siht] < kaugus) {

                        if (kaugused[siht] < lühim) {
                            lühim = kaugused[siht];
                            lühimIndeks = siht;
                        }
                    }
                }
            }

            kaared[i] = lühimIndeks;
            kaugused[lühimIndeks] = 0;
            valitud.set(lühimIndeks);
            viimaneLisatu = lühimIndeks;
        }
        // Naiivne variant - Siirdume tagasi alguspunkti
        kaared[nimed.length-1] = 0;

        double kogukaal = arvutaTeepikkus(K, kaared);
        System.out.println("\nToese puu kogukaal: " + kogukaal);

        return kaared;
    }
    public static int[] getShortestPath(double[][] coordinates) {
        // Number of nodes
        int n = coordinates[0].length;

        // Initialize list of permutations
        int[][] permutations = genPermutations(n);

        // Initialize minimum distance
        int minDistance = Integer.MAX_VALUE;
        int[] minPath = new int[n];

        // Iterate through all permutations
        for (int[] permutation : permutations) {
            // Calculate distance of this permutation
            int distance = 0;
            for (int i = 0; i < n - 3; i++) {
//                distance += getDistance(coordinates[permutation.get(i)], coordinates[permutation.get(i + 1)]);
                try {
                    distance += kaugus(coordinates, permutation[i], permutation[i+1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println();
                }
            }

            // Check if this permutation is the minimum distance so far
            if (distance < minDistance) {
                minDistance = distance;
                for (int i = 0; i < n-1; i++) {
                    minPath[i] = permutation[i];
                }
            }
        }

        return minPath;
    }

    // Generates all permutations of the numbers 0, 1, 2, ..., n - 1
    private static List<List<Integer>> generatePermutations(int n) {
        List<List<Integer>> permutations = new ArrayList<>();
        if (n == 0) {
            permutations.add(new ArrayList<>());
            return permutations;
        }
        for (int i = 0; i < n; i++) {
            List<List<Integer>> subPermutations = generatePermutations(n - 1);
            for (List<Integer> subPermutation : subPermutations) {
                subPermutation.add(i);
                permutations.add(subPermutation);
            }
        }
        return permutations;
    }

    public static int[][] genPermutations(int n) {
        // Edge case: if n is 0 or 1, there are no permutations
        if (n <= 1) {
            return new int[0][0];
        }

        // Initialize the array to store the permutations
        int[][] permutations = new int[1 << (n-1)][n-1];

        // Generate the permutations
        for (int i = 0; i < permutations.length; i++) {
            // Find the next permutation using bitwise operations
            int j = n - 2;
            int num = i + 1;
            while (j > 0) {
                // If the current number is even, divide it by 2
                if ((num & 1) == 0) {
                    permutations[i][j] = num >>> 1;
                    j--;
                }
                // If the current number is odd, subtract 1 and divide by 2
                else {
                    try {
                        permutations[i][j] = (num - 1) >>> 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println();
                    }
                    permutations[i][j-1] = permutations[i][j] + 1;
                    j -= 2;
                }
                num = permutations[i][j];
            }

            // Add the final number to the permutation
            permutations[i][0] = num;
        }

        return permutations;
    }

    public static int factorial(int n) {
        int num = 10;
        int factorial = 1;
        for(int i = 1; i <= num; ++i)
        {
            // factorial = factorial * i;
            factorial *= i;
        }
        return factorial;
    }
    /**
     * Leiab rändkaupmehe lähendi kogu tee pikkuse.
     *
     * @param p Rändkaupmehe lähend, antud asukohtade indeksite permutatsioonina
     * @param K Asukohtade koordinaadid kaherealise tabelina
     * @return Antud rändkaupmehe lähendi kogu tee pikkus
     */
    public static double kaugusRändkaupmees(int[] p, double[][] K) {

        return Double.NaN;
    }

    public static double kaugus(double[][] K, int a, int b) {
        return kaugus(K[0][a], K[1][a], K[0][b], K[1][b]);
    }

    /**
     * Leiab kahe punkti vahelise kauguse, kasutades valemit siit:
     * https://en.wikipedia.org/wiki/Haversine_formula
     *
     * @param lai1 Esimese punkti laiuskraad
     * @param pik1 Esimese punkti pikkuskraad
     * @param lai2 Teise punkti laiuskraad
     * @param pik2 Teise punkti pikkuskraad
     * @return Punktide vaheline kaugus kilomeetrites
     */
    public static double kaugus(double lai1, double pik1, double lai2, double pik2) {
        double dLaius = Math.pow(Math.sin(Math.toRadians(lai2 - lai1) / 2), 2);
        return 2 * 6371.0088 * Math.asin(Math.sqrt(dLaius +
                (1 - dLaius - Math.pow(Math.sin(Math.toRadians(lai1 + lai2) / 2), 2)) *
                        Math.pow(Math.sin(Math.toRadians(pik2 - pik1) / 2), 2)));
    }


    private static FailiInfo loeInfo() {
        String failinimi = "omniva.csv";
        int i = 0;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(failinimi),
                        StandardCharsets.UTF_8
                )
        )) {

            List<String> nimed = new LinkedList<>();
            List<Double> laiuskraadid = new LinkedList<>();
            List<Double> pikkuskraadid = new LinkedList<>();

            String rida;
            while ((rida = br.readLine()) != null) {
//                if (i == 5) break;
                String[] tükid = rida.split(",");
                nimed.add(tükid[0]);

                laiuskraadid.add(Double.parseDouble(tükid[1]));
                pikkuskraadid.add(Double.parseDouble(tükid[2]));
//                i++;
            }

            return new FailiInfo(
                    nimed.toArray(new String[0]),
                    new double[][]{
                            laiuskraadid.stream().mapToDouble(d -> d).toArray(),
                            pikkuskraadid.stream().mapToDouble(d -> d).toArray()
                    });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private record FailiInfo(String[] nimed, double[][] k) {

    }


    public static void main(String[] args) {
        FailiInfo failiInfo = loeInfo();
        String[] nimed = failiInfo.nimed;
        double[][] kaugused = failiInfo.k;

        for (int i = 0; i < nimed.length; i++) {
            System.out.println(nimed[i] + ", " + kaugused[0][i] + ", " + kaugused[0][i]);
        }


//        toesPrim(nimed, kaugused);
//        toesKruskal(nimed, kaugused);
//        rändkaupmees(nimed, kaugused);
        var s = getShortestPath(kaugused);
        System.out.println();
    }
}