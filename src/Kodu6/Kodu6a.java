/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2022/2023 sügissemester
 *
 * Kodutöö. Ülesanne nr 6
 * Teema: Huffmani algoritm.
 *
 * Mõningane inspiratsioon:
 * https://www.programiz.com/dsa/huffman-coding
 *
 * Autor: Kaarel-Richard Kaarelson
 *
 *****************************************************************************/
package Kodu6;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Kodu6a {
    @FunctionalInterface
    interface GenericInterface<T> {
        T func(T t);
    }

    static class Paar<A, B> {
        A võti;
        B väärtus;

        Paar v;
        Paar p;

        Paar(A võti, B väärtus) {
            this.võti = võti;
            this.väärtus = väärtus;
        }
    }

    /**
     * Kodeerib etteantud sõne vastavalt Huffmani kodeeringule.
     *
     * @param s etteantud sõne.
     * @return tagastab kodeeritud sisu sõnena.
     */
    public static String kodeeri(String s) {

        // Arvutame sümbolite suhtelise sageduse
        // Ja konstrueerime eelistusjärjekorra alusel Huffmani kodeeringu puu
        HashMap<Character, Integer> sagedused = leiaSümboliteSagedus(s);

        PriorityQueue<Tipp> järjekord = new PriorityQueue<>((t1, t2) -> t1.x - t2.x);

        for (Character v6ti : sagedused.keySet()) {
            int väärtus = sagedused.get(v6ti);
            Tipp t = new Tipp(v6ti, väärtus);

            järjekord.add(t);
        }

        Tipp juur = null;
        if (järjekord.size() == 1) { // Erijuht kui ainult üks tipp.
            Tipp x = järjekord.peek();

            Tipp t = new Tipp(' ', x.x);

            t.v = x;
            juur = t;
        }

        while (järjekord.size() > 1) {
            Tipp x = järjekord.peek();
            järjekord.poll();

            Tipp y = järjekord.peek();
            järjekord.poll();

            Tipp t = new Tipp(' ', x.x + y.x);

            t.v = x;

            t.p = y;

            juur = t;

            järjekord.add(t);
        }

        Map<Character, String> prefikskoodid = leiaPrefikskoodid(juur);
        List<Map.Entry<Character, String>> järjestatudPrefikskoodid = new ArrayList<>(prefikskoodid.entrySet());

        järjestatudPrefikskoodid.sort(Map.Entry.comparingByValue()); // Sorteerime paisktabeli väärtuste alusel.

        StringBuilder kodeering = new StringBuilder();
        StringBuilder võtmedSb = new StringBuilder();

        for (int i = 0; i < järjestatudPrefikskoodid.size(); i++) {
            Map.Entry<Character, String> kirje = järjestatudPrefikskoodid.get(i);
            String paar;

            if (kirje.getKey() == '\n') {
                paar = "/n" + ' ' + kirje.getValue();
            } else {
                paar = kirje.getKey() + " " + kirje.getValue();
            }

            võtmedSb.append(paar + '\n');
        }

        String võtmed = võtmedSb.toString();

        String kood = s.chars()
                .mapToObj(täht -> (char) täht)
                .map(täht -> prefikskoodid.get(täht))
                .map(String::valueOf).
                collect(Collectors.joining());

//        byte võtmedBait = (byte)(int)Integer.valueOf(väärtused, 2);
//        byte koodBait =  (byte)(int)Integer.valueOf(kood, 2);

//        byte võtmedBait2 = Byte.parseByte(väärtused, 2);
//        byte koodBait2 =  Byte.parseByte(kood, 2);

        // Olgu sümbol "■" sektsiooni lõpumärk.
        // Kasutame päise ja koodi lõpu märkimiseks seda sümbolit.

        GenericInterface<Double> pi = (number) -> number + 3.14;

        kodeering.append(võtmed);
        kodeering.append("■" + "\n"); // Määrame päise lõpu märgi
        kodeering.append(kood + "■");

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
     * Leiab sõne sümbolite sagedustabeli.
     *
     * @param tipp etteantud tipp.
     * @return sümbolite sagedustabel.
     */
    public static Map<Character, String> leiaPrefikskoodid(Tipp tipp) {
        if (tipp == null) return null;

        Map<Character, String> prefikskoodid = new HashMap<>();
        leiaPrefikskoodid(tipp, "", prefikskoodid);

        return prefikskoodid;
    }

    /**
     * Leiab iga sümboli prefikskoodi ja väärtustab selle etteantud tabelisse.
     *
     * @param tipp  etteantud tipp.
     * @param tee   vastava tipu prefikskood.
     * @param tabel etteantud prefikskoodide tabel.
     */
    public static void leiaPrefikskoodid(Tipp tipp, String tee, Map<Character, String> tabel) {

        if (tipp == null) return;

        if (tipp.v == null && tipp.p == null) {
            tabel.put(tipp.info.charAt(0), tee);

            return;
        }

        leiaPrefikskoodid(tipp.v, tee + "0", tabel);
        leiaPrefikskoodid(tipp.p, tee + "1", tabel);
    }

    /**
     * - Magasini versioon
     * Leiab iga sümboli prefikskoodi ja väärtustab selle etteantud tabelisse.
     *
     * @param juur etteantud tipp.
     */
    public static HashMap<Character, String> leiaPrefikskoodidMagasin(Tipp juur) {
        if (juur == null) return null;

        HashMap<Character, String> sagedused = new HashMap<>();
        Stack<Tipp> magasin = new Stack();
        StringBuilder tee = new StringBuilder();

        magasin.push(juur);

        while (!magasin.isEmpty()) {
            Tipp t = magasin.pop();

            if (t.v == null && t.p == null) {
                sagedused.put(t.info.charAt(0), tee.toString());

                continue;
            }

            if (t.p != null) magasin.push(t.p);
            if (t.v != null) magasin.push(t.v);
        }

        return sagedused;
    }

    /**
     * Kasutamata jäänud prefikskoodi leidmise meetod, mis tagastab koodi bitidena.
     *
     * @param tipp  etteantud tipp.
     * @param kood  vastava tipud bitijada.
     * @param tabel etteantud prefikskoodide tabel.
     */
    public static void leiaKoodid(Tipp tipp, int kood, HashMap<Character, Integer> tabel) {

        if (tipp == null) return;

        if (tipp.v == null && tipp.p == null ) {

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
        String[] andmed = s.split("\n");

        // Töötleme andmed. Eraldame faili päisest sümbolite prefikskoodid
        // ja bitidena kodeeritud sisu.

        String kood = "";
        ArrayList<Paar> prefikskoodid = new ArrayList<>();

        for (int i = 0; i < andmed.length; i++) {
            if (andmed[i].equals("■")) {
                System.out.println("lõpp");
                kood = andmed[i + 1];
                break;
            }

            String[] tükid = andmed[i].split(" ");

            if (tükid.length == 3) {
                Paar<String, String> paar = new Paar(" ", tükid[2]);

                prefikskoodid.add(paar);
            } else {
                Paar<String, String> paar;

                if (tükid[0].equals("/n")) {
                    paar = new Paar("\n", tükid[1]);
                } else {
                    paar = new Paar(tükid[0], tükid[1]);
                }

                prefikskoodid.add(paar);
            }
            System.out.println(andmed[i] + " " + andmed[i].length());
        }

        Paar<String, String> juur = ehitaPuu(prefikskoodid);
        String tekst = koodSümboliteks(kood, juur);

        return tekst;
    }


    /***
     * Tõlgib Huffmani kodeeringu eeskirja järgi kokkupakitud teksti lahti, kasutades
     * algset Huffmani puud.
     * @param kood Huffmani kodeering sõnena
     * @param juur Huffmani puu juur, kus vasak haru omab väärtust '0' ja parem haru '1'.
     * @return tagastab dekodeeritud teksi.
     */
    private static String koodSümboliteks(String kood, Paar<String, String> juur) {

        StringBuilder tulemus = new StringBuilder();

        Paar<String, String> kirje = juur;

        for (int i = 0; i < kood.length(); i++) {
            if (kirje.v == null && kirje.p == null) {

                String sümbol = kirje.võti;
                tulemus.append(sümbol);
                kirje = juur;
            }

            char suund = kood.charAt(i);

            if (suund == '0') {
                kirje = kirje.v;

            } else if (suund == '1') {
                kirje = kirje.p;
            }
        }

        return tulemus.toString();
    }


    /**
     * Ehitab algse Huffmani puu kasutades prefikskoodide tabelit.
     * @param prefikskoodid etteantud prefikskoodide tabel
     * @return tagastab Huffmani puu juure.
     */
    private static Paar ehitaPuu(ArrayList<Paar> prefikskoodid) {
        if (prefikskoodid.size() == 0) { // Erijuht kui ainult üks tipp.
            return null;
        }

        Paar<String, String> juur = new Paar(" ", " ");

        for (Paar<String, String> paar : prefikskoodid) {
            String tee = paar.väärtus;
            int i = 0;

            Stack<Paar> magasin = new Stack<>();
            magasin.push(juur);
            while (i < tee.length()) {
                Paar p = magasin.pop();

                if (tee.charAt(i) == '0') {
                    if (p.v == null) {
                        if (i == tee.length() - 1) {
                            p.v = paar;
                        } else {
                            p.v = new Paar(" ", " ");
                            magasin.push(p.v);
                        }
                    } else {
                        magasin.push(p.v);
                    }
                } else if (tee.charAt(i) == '1') {
                    if (p.p == null) {
                        if (i == tee.length() - 1) {
                            p.p = paar;
                        } else {
                            p.p = new Paar(" ", " ");
                            magasin.push(p.p);
                        }
                    } else {
                        magasin.push(p.p);
                    }
                }

                i++;
            }
        }

        return juur;
    }

    public static void main(String[] args) {
        /* Näide meetodite rakendamisest
           a) kodeeritud sõne faili kirjutamisest
           b) failist sõne dekodeerimisest
         */

//        // Failist lugemine
//        String failinimi = "Kõrboja_sisu_puhastekst.txt";
//        String sisu = loeFailist(failinimi);

        String lihtne = "a";
        String sõge= "a;a@e(✿◠‿◠)e *_:;>Ze.;aa";
        String näide = "aa44a,b,,ba   \u0009     \n   aac,c^½^žbb";

        // Kodeerimine
        String kodeering = kodeeri(näide);
        kirjutaFaili(kodeering, "fail.txt");

        // Dekodeerimine
        String kodeeritudSisu = loeFailist("fail.txt");
        String dekodeeritudSisu = dekodeeri(kodeeritudSisu);
        System.out.println(dekodeeritudSisu);
    }

    /**
     * Loeb failist sisu ja tagastab selle sõne kujul.
     *
     * @param failinimi etteantud failinimi kooos laiendiga.
     * @return tagastab faili sisu.
     */
    private static String loeFailist(String failinimi) {
        StringBuilder sisu = new StringBuilder("");

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(failinimi), "utf-8" // "windows-1252"
                )
        )) {

            String rida;
            for (int i = 0; (rida = br.readLine()) != null; i++) {
                sisu.append(rida + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sisu.toString();
    }

    /**
     * Kirjutab faili etteantud sõne.
     *
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