package Kodu6;

/* Kokkupakitud teksti esitavasse faili peame panema pakitud teksti ja seda uuesti lahtipakkimist võimaldava koodipuu
   (näiteks koodipuu esitus suluesitusena). Soovituslikud sammud:
a) teksti põhjal luua sümbolite sagedustabel;
b) sagedustabeli põhjal luua loetelu (näiteks list) ühetipulistest puudest (igas tipus on
   siis info kujul sümbol + selle esinemiste arv)
c) selliste puude metsast Huffmani koodipuu loomine
d) leida koodipuust igale sümbolile vastav uus kood -> prefikskood
f) koodipuu -> koodipuu suluesitus sõnena
g) algtekst, prefikskoodid, koodipuu suluesitus -> pakitud tekst
h) puu suluesitus -> koodipuu taastamine
i) (taastatud) koodipuu, pakitud tekst -> lahtipakitud tekst
 */


import ee.ut.kiho.graaf.Kuvar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Kodu6a {

    //    static int count = 0;
    public static String kodeeri(String s) {

        // Arvutame sümbolite suhtelise sageduse
        HashMap<Character, Integer> sagedused = leiaSümboliteSagedus(s);

        sagedused.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });

        // Leiame Huffmani puu
        PriorityQueue<HuffmaniTipp> q = new PriorityQueue<>((t1, t2) -> t1.x - t2.x);

        int countKeys = 0;
        for (Character v6ti : sagedused.keySet()) {


//            System.out.println(v6ti);
            int v22rtus = sagedused.get(v6ti);
            HuffmaniTipp t = new HuffmaniTipp(v6ti, v22rtus);
            q.add(t);
            countKeys += 1;
//            System.out.println();
        }
        System.out.println("kokku võtmeid " + countKeys);

        HuffmaniTipp juur = null;
        if (q.size() == 1) {
            HuffmaniTipp x = q.peek();

            HuffmaniTipp t = new HuffmaniTipp(' ', x.x);

            t.v = x;
            juur = t;
        }

        while (q.size() > 1) {

            // first min extract.
            HuffmaniTipp x = q.peek();
            q.poll();

            // second min extract.
            HuffmaniTipp y = q.peek();
            q.poll();

            // new node f which is equal
            HuffmaniTipp t = new HuffmaniTipp(' ', x.x + y.x);

            // to the sum of the frequency of the two nodes

            // first extracted node as left child.
            t.v = x;

            // second extracted node as the right child.
            t.p = y;

            // marking the f node as the root node.
            juur = t;

            // add this node to the priority-queue.
            q.add(t);
        }

        HashMap<Character, String> prefikskoodid = new HashMap<>();
        HashMap<Character, Integer> prefikskoodid2 = new HashMap<>();
        // Koosta prefikskoodide tabel
        leiaKoodid(juur, 0, prefikskoodid2);

        Kuvar.kuvaPuu(juur);

        StringBuilder kodeering = new StringBuilder();

        String v6tmed = prefikskoodid.keySet().stream().map(Object::toString).collect(Collectors.joining(","));

        StringBuilder v6tmed2 = new StringBuilder();
        for (Character kood : prefikskoodid.keySet()) {
            v6tmed2.append(kood + ",");
            System.out.println(kood + ":" + prefikskoodid.get(kood));
        }

        String[] v6tmedEraldaja = v6tmed.split(",");
        String v22rtused = prefikskoodid.values().stream().map(Object::toString).collect(Collectors.joining(","));

        kodeering.append(v6tmed + "/n");
        kodeering.append(v22rtused + "/n");

//        byte [] kodeeritud =
        System.out.println(prefikskoodid.values());

        for (int i = 0; i < s.length(); i++) {
            char t2ht = s.charAt(i);
            String prefikskood = prefikskoodid.get(t2ht);
            kodeering.append(prefikskood + ',');
        }

        return kodeering.toString();
    }

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

    public static void leiaKoodid(HuffmaniTipp root, int kood, HashMap<Character, Integer> tabel) {

        if (root == null) return;

        if (root.v
                == null
                && root.p
                == null
//                && Character.isLetter(root.info.charAt(0))
        ) {

            tabel.put(root.info.charAt(0), kood);
//            System.out.println(root.info + ":" + s);

            return;
        }

        // if we go to left then add "0" to the code.
        // if we go to the right add"1" to the code.

        // recursive calls for left and
        // right sub-tree of the generated tree.
        leiaKoodid(root.v, kood << 1 , tabel);
        leiaKoodid(root.p, (kood << 1) | 1, tabel);
    }

    public static void leiaPrefikskoodid(HuffmaniTipp root, String s, HashMap<Character, String> tabel) {

        if (root == null) return;

        if (root.v
                == null
                && root.p
                == null
//                && Character.isLetter(root.info.charAt(0))
        ) {

            tabel.put(root.info.charAt(0), s);
//            System.out.println(root.info + ":" + s);

            return;
        }

        // if we go to left then add "0" to the code.
        // if we go to the right add"1" to the code.

        // recursive calls for left and
        // right sub-tree of the generated tree.
        leiaPrefikskoodid(root.v, s + "0", tabel);
        leiaPrefikskoodid(root.p, s + "1", tabel);
    }

    public static void printCode(HuffmaniTipp root, String s) {

        if (root == null) return;
//        if (root == null) return;
        // base case; if the left and right are null
        // then its a leaf node and we print
        // the code s generated by traversing the tree.
        if (root.v
                == null
                && root.p
                == null
//                && Character.isLetter(root.info.charAt(0))
        ) {

            // c is the character in the node
            System.out.println(root.info + ":" + s);
//            count += 1;

            return;
        }

        // if we go to left then add "0" to the code.
        // if we go to the right add"1" to the code.

        // recursive calls for left and
        // right sub-tree of the generated tree.
        printCode(root.v, s + "0");
        printCode(root.p, s + "1");
    }

    public static String dekodeeri(String s) {
        String[] andmed = s.split("/n");

        String[] v6tmed = andmed[0].split(",");
        String[] v22rtused = andmed[1].split(",");
        String[] koodid = andmed[2].split(",");

        HashMap<String, Character> symbolid = new HashMap<>();
        for (int i = 0; i < v6tmed.length; i++) {
            char v6ti = '.';
            try {
                v6ti = v6tmed[i].charAt(0);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(v6tmed[i]);
            }
            String v22rtus = v22rtused[i];

            symbolid.put(v22rtus, v6ti);
        }

        StringBuilder dekodeering = new StringBuilder();
        for (int i = 0; i < koodid.length; i++) {

            String kood = koodid[i];
            char symbol = symbolid.get(kood);
            dekodeering.append(symbol);
        }

        return dekodeering.toString();
    }

    public static void main(String[] args) {
        /* Näide meetodite rakendamisest
           a) kodeeritud sõne faili kirjutamisest
           b) failist sõne dekodeerimisest
         */

//        String failinimi = "Kõrboja_sisu_puhastekst.txt";
//        String sisu = loeFailist(failinimi);
//        System.out.println(sisu);
        String s1 = "llllllllllllahe loom oled, sa minu remma: lauluga' ära mine suve otsa ";
        String s = "Lause on lahe";
        String lihtne = "a";
        String s6ge = "a;a@e(✿◠‿◠)e *_:;>Ze.;aa";

        String s6ge2 = "a3a;bb:cc:cc(d!\"#¤%&/()=?d) aa:,c,.;c`@£$€€😎😫${{[]][}]\\§bb";

        String kodeering = kodeeri(s);
        String dekodeering = dekodeeri(kodeering);
        System.out.println(dekodeering);
    }

    private static String loeFailist(String failinimi) {
        StringBuilder sisu = new StringBuilder("");

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(failinimi), "windows-1252"
                )
        )) {

            String rida;
            for (int i = 0; (rida = br.readLine()) != null; i++) {
//                System.out.println(rida);
                sisu.append(rida);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sisu.toString();
    }
}