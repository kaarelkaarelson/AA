/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2022/2023 sügissemester
 *
 * Kodutöö. Ülesanne nr 6
 * Teema: Huffmani algoritm.
 *
 * Autor: Kaarel-Richard Kaarelson
 *
 *****************************************************************************/

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Kodu6a {
    /**
     * Kodeerib etteantud sõne vastavalt Huffmani kodeeringule.
     * @param s etteantud sõne.
     * @return tagastab kodeeritud sisu sõnena.
     */
    public static String kodeeri(String s) {

        // Arvutame sümbolite suhtelise sageduse
        HashMap<Character, Integer> sagedused = leiaSümboliteSagedus(s);

        // Leiame Huffmani puu
        PriorityQueue<HuffmaniTipp> q = new PriorityQueue<>((t1, t2) -> t1.x - t2.x);

        for (Character v6ti : sagedused.keySet()) {
            int v22rtus = sagedused.get(v6ti);
            HuffmaniTipp t = new HuffmaniTipp(v6ti, v22rtus);
            q.add(t);
        }

        HuffmaniTipp juur = null;
        if (q.size() == 1) { // Erijuht kui ainult üks tipp.
            HuffmaniTipp x = q.peek();

            HuffmaniTipp t = new HuffmaniTipp(' ', x.x);

            t.v = x;
            juur = t;
        }

        while (q.size() > 1) {
            HuffmaniTipp x = q.peek();
            q.poll();

            HuffmaniTipp y = q.peek();
            q.poll();

            HuffmaniTipp t = new HuffmaniTipp(' ', x.x + y.x);

            t.v = x;

            t.p = y;

            juur = t;

            q.add(t);
        }

        HashMap<Character, String> prefikskoodid = new HashMap<>();

        // Koosta prefikskoodide tabeli
        leiaPrefikskoodid(juur, "", prefikskoodid);

        StringBuilder kodeering = new StringBuilder();

        // Lihtsuse mõttes eraldame faili kirjutamiseks võtmed ja väätused komaga.
        StringBuilder v6tmedSb = new StringBuilder();
        for (Character v6ti : prefikskoodid.keySet()) {
            if (v6ti == ',') {

                v6tmedSb.append("koma" + ',');
                continue;
            }
            v6tmedSb.append(v6ti);
            v6tmedSb.append(',');
        }

        String v6tmed = v6tmedSb.toString();
        String v22rtused = prefikskoodid.values().stream().map(Object::toString).collect(Collectors.joining(","));

        kodeering.append(v6tmed + "/n");
        kodeering.append(v22rtused + "/n");

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ',') {

                String prefikskood = prefikskoodid.get(',');
                kodeering.append(prefikskood + ',');
                continue;
            }

            char t2ht = s.charAt(i);
            String prefikskood = prefikskoodid.get(t2ht);
            kodeering.append(prefikskood + ',');
        }

        return kodeering.toString();
    }

    /**
     * Leiab sõne kõigi sümbolite sageduse.
     *
     * @param s etteantud sõne.
     * @return tagastab sagedustabeli.
     */
    private static HashMap<Character, Integer> leiaSümboliteSagedus(String s) {
        HashMap<Character, Integer> sagedused = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char symbol = s.charAt(i);
            if (sagedused.containsKey(symbol)) {
                sagedused.put(
                        symbol,
                        sagedused.get(symbol) + 1
                );
            } else {
                sagedused.put(symbol, 1);
            }
        }

        return sagedused;
    }

    /**
     * Leiab iga sümboli prefikskoodi ja väärtustab selle etteantud tabelisse.
     *
     * @param tipp  etteantud tipp.
     * @param tee   vastava tipu prefikskood.
     * @param tabel etteantud prefikskoodide tabel.
     */
    public static void leiaPrefikskoodid(HuffmaniTipp tipp, String tee, HashMap<Character, String> tabel) {

        if (tipp == null) return;

        if (tipp.v
                == null
                && tipp.p
                == null
        ) {

            tabel.put(tipp.info.charAt(0), tee);

            return;
        }

        leiaPrefikskoodid(tipp.v, tee + "0", tabel);
        leiaPrefikskoodid(tipp.p, tee + "1", tabel);
    }

    /**
     * Kasutamata jäänud prefikskoodi leidmise meetod, mis tagastab koodi bitidena.
     *
     * @param tipp  etteantud tipp.
     * @param kood  vastava tipud bitijada.
     * @param tabel etteantud prefikskoodide tabel.
     */
    public static void leiaKoodid(HuffmaniTipp tipp, int kood, HashMap<Character, Integer> tabel) {

        if (tipp == null) return;

        if (tipp.v
                == null
                && tipp.p
                == null
        ) {

            tabel.put(tipp.info.charAt(0), kood);

            return;
        }

        leiaKoodid(tipp.v, kood << 1, tabel);
        leiaKoodid(tipp.p, (kood << 1) | 1, tabel);
    }

    /**
     * Dekodeerib etteantud failisisu.
     *
     * @param s etteantud failisisu.
     * @return tagastab dekodeeritud (inimloetava failisisu).
     */
    public static String dekodeeri(String s) {
        String[] andmed = s.split("/n");

        String[] v6tmed = andmed[0].split(",");
        String[] v22rtused = andmed[1].split(",");
        String[] koodid = andmed[2].split(",");

        HashMap<String, Character> symbolid = new HashMap<>();
        for (int i = 0; i < v6tmed.length; i++) {
            char v6ti = '.';
            if (v6tmed[i].equals("koma")) {
                v6ti = ',';
            } else {
                v6ti = v6tmed[i].charAt(0);
            }

            System.out.println(v6tmed[i]);
            String v22rtus = v22rtused[i];

            symbolid.put(v22rtus, v6ti);
        }

        StringBuilder dekodeering = new StringBuilder();
        for (int i = 0; i < koodid.length; i++) {

            String kood = koodid[i];
            char symbol = '.';
            symbol = symbolid.get(kood);
            System.out.println();
            dekodeering.append(symbol);
            System.out.println();
        }

        return dekodeering.toString();
    }

    public static void main(String[] args) {
        /* Näide meetodite rakendamisest
           a) kodeeritud sõne faili kirjutamisest
           b) failist sõne dekodeerimisest
         */

        String s = "Lause on lahe";
        String lihtne = "a";
        String s6ge = "a;a@e(✿◠‿◠)e *_:;>Ze.;aa";

        // Failist lugemine
        String failinimi = "Kõrboja_sisu_puhastekst.txt";
        String sisu = loeFailist(failinimi);

        String kodeering = kodeeri(sisu);

        // Faili kirjutamine
        kirjutaFaili(kodeering, "fail.txt");
        String sisu2 = loeFailist("fail.txt");

        // Dekodeerimine ja väljastamine
        String dekodeering = dekodeeri(sisu2);
        System.out.println(dekodeering);
    }

    /**
     * Loeb failist sisu ja tagastab selle sõne kujul.
     * @param failinimi etteantud failinimi kooos laiendiga.
     * @return tagastab faili sisu.
     */
    private static String loeFailist(String failinimi) {
        StringBuilder sisu = new StringBuilder("");

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(failinimi), "windows-1252"
                )
        )) {

            String rida;
            for (int i = 0; (rida = br.readLine()) != null; i++) {
                sisu.append(rida);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sisu.toString();
    }

    /**
     * Kirjutab faili etteantud sõne.
      * @param sisu etteantud sisu sõnena.
     * @param failinimi etteantud failinimi koos laiendiga.
     */
    private static void kirjutaFaili(String sisu, String failinimi) {
        try (PrintStream out = new PrintStream(new FileOutputStream(failinimi))) {
            out.print(sisu);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}