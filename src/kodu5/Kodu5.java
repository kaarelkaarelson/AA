/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2022/2023 sügissemester
 *
 * Kodutöö. Ülesanne nr 5
 * Teema: AVL Puud
 *
 * Autor: Kaarel-Richard Kaarelson
 *
 *****************************************************************************/
package kodu5;

import ee.ut.kiho.graaf.Kuvar;
import kodu5.Tipp;

import java.util.Stack;


class Kodu5 {

    /**
     * Kirje lisamise meetod AVL puu jaoks, mis delegeerib ülesande rekursiivsele meetodile.
     *
     * @param juur etteantud AVL puu tipp.
     * @param info etteantud võti.
     * @return tagastab AVL puu tipu pärast lisamist.
     */
    public static Tipp lisaKirje(Tipp juur, String info) {
        if (juur == null) return new Tipp(info);

        int uusVõti = Integer.parseInt(info);
        Stack<Tipp> magasin = new Stack<>();
        magasin.add(juur);

        while (true) {
            Tipp t = magasin.peek();
            int võti = Integer.parseInt(t.info);

            if (uusVõti < võti) {
                if (t.v == null) {
                    Tipp uus = new Tipp(info, null, null);
                    uus.x = 1; // lisame uuele tipule kõrguse;

                    t.v = uus;
                    break;
                } else {
                    magasin.push(t.v);
                }
            } else {
                if (t.p == null) {
                    Tipp uus = new Tipp(info, null, null);
                    uus.x = 1;

                    t.p = uus;
                    break;
                } else {
                    magasin.push(t.p);
                }
            }

        }

        Tipp ülem = null;
        Tipp vaheta = null;

        while (!magasin.isEmpty()) {
            ülem = magasin.pop();

            if (vaheta != null) {
                int võti = Integer.parseInt(vaheta.info);
                int ülemaVõti = Integer.parseInt(ülem.info);

                if (võti < ülemaVõti) {
                    ülem.v = vaheta;
                } else {
                    ülem.p = vaheta;
                }

                vaheta = null;
            }

            if (!(kasTasakaalustatudPuu(ülem))) {
                ülem = tasakaalustaPuu(ülem);
                vaheta = ülem;
            } else ülem.x = leiaKõrgus(ülem); // Kui puu on tasakaalus, siis määrame uue kõrguse.
        }

        return ülem;
    }

    /**
     * AVL puu pöörde meetod, mis keerab puu etteantud alamtipust paremale ning seejärel vanemtipust vasakule.
     *
     * @param juur Puu tipp, mille peal soovitakse pööret sooritada.
     * @return Tagastab pööratud puu.
     */
    public static Tipp paremVasakPööre(Tipp juur) {
        Tipp parem = juur.p;
        Tipp pööratudParem = paremPööre(parem);
        juur.p = pööratudParem;

        Tipp vasakpööratudJuur = vasakPööre(juur);

        return vasakpööratudJuur;
    }

    /**
     * AVL puu pöörde meetod, mis pöörab etteantud puu vasakule.
     *
     * @param juur Puu tipp, mille peal soovitakse pööret sooritada.
     * @return Tagastab pööratud puu.
     */
    public static Tipp vasakPööre(Tipp juur) { // OK

        Tipp parem = juur.p;
        Tipp paremV = parem.v;

        parem.v = juur;
        juur.p = paremV;

        // Fikseerime uued kõrgused vahetatud tippudele.
        juur.x = Math.max(kõrgus(juur.v), kõrgus(juur.p)) + 1;
        parem.x = Math.max(kõrgus(parem.v), kõrgus(parem.p)) + 1;

        return parem;
    }

    /**
     * AVL puu pöörde meetod, mis keerab puu etteantud alamtipust vasakule ning seejärel vanemtipust paremale.
     *
     * @param juur Puu tipp, mille peal soovitakse pööret sooritada.
     * @return Tagastab pööratud puu.
     */
    public static Tipp vasakparemPööre(Tipp juur) {
        Tipp vasak = juur.v;
        Tipp pööratudVasak = vasakPööre(vasak);
        juur.v = pööratudVasak;

        Tipp parempööratudJuur = paremPööre(juur);

        return parempööratudJuur;
    }

    /**
     * AVL puu pöörde meetod, mis pöörab etteantud puu paremale.
     *
     * @param juur Puu tipp, mille peal soovitakse pööret sooritada.
     * @return Tagastab pööratud puu.
     */
    public static Tipp paremPööre(Tipp juur) { // OK

        Tipp vasak = juur.v;
        Tipp vasakP = vasak.p;

        vasak.p = juur;
        juur.v = vasakP;

        // Fikseerime uued kõrgused vahetatud tippudele.
        juur.x = Math.max(kõrgus(juur.v), kõrgus(juur.p)) + 1;
        vasak.x = Math.max(kõrgus(vasak.v), kõrgus(vasak.p)) + 1;

        return vasak;
    }

    /**
     * Puu kõrguse määramise meetod.
     *
     * @param t Etteantud puu tipp.
     * @return Tagastab puu kõrguse, vastavalt abiväljale.
     */
    public static int kõrgus(Tipp t) {
        return t == null ? 0 : t.x;
    }

    /**
     * AVL puu kirje eemaldamise meetod, mis delegeerib ülesande rekursiivsele funktsioonile.
     *
     * @param juur Ettentud puu juur.
     * @param info Etteantud kirje.
     * @return tagastab eemaldatud kirje.
     */
    public static Tipp eemaldaKirje(Tipp juur, String info) { // Peaks kohe algul kontrollima kas tegu on tipuga, mis tuleb eemaldada.
        Tipp tipp = eemaldaKirjeRek(juur, info);
        juur = tipp;

        return juur;
    }

    /**
     * AVL puu kirje eemaldamise meetod, mis etteantud kirje alusel eemaldab selle puust.
     *
     * @param juur AVL puu tipp.
     * @param info Soovitud kirje võti eemaldamiseks.
     * @return tagastab eemaldatud kirje.
     */
    public static Tipp eemaldaKirjeRek(Tipp juur, String info) {
//        if (juur == null) return null;
//
//        int võti = Integer.parseInt(juur.info);
//        int eemaldatav = Integer.parseInt(info);
//        Tipp tipp = null;
//
//        if (eemaldatav == võti) {
//            Tipp liidetud = liidaAVLpuud(juur.v, juur.p);
//            juur = liidetud;
//
//        } else if (eemaldatav < võti) {
//            tipp = eemaldaKirjeRek(juur.v, info);
//            juur.v = tipp;
//
//        } else {
//            tipp = eemaldaKirjeRek(juur.p, info);
//            juur.p = tipp;
//        }
//
//        // Tasakaalustamine
//        if (!kasTasakaalustatudPuu(juur)) {
//            juur = tasakaalustaPuu(juur);
//        }
//
        return juur;
    }

    /**
     * AVL puu meetod, mis leiab väikseima kirje antud puust ning kustutab selle.
     *
     * @param tipp AVL puu.
     * @return tagastab eemaldatud kirje.
     */
    public static Tipp[] leiaJaEemaldaVäikseim(Tipp tipp) {

        if (tipp.v == null) { // Leidsime väikseima elemendi.

            return new Tipp[]{tipp};
        }

        Tipp[] andmed = leiaJaEemaldaVäikseim(tipp.v);
        Tipp väikseim = andmed[0];

        if (väikseim.x != -1) {
            if (väikseim.p != null) {
                tipp.v = väikseim.p; // Kui väikseimal eksisteeris parem haru, siis ühendame selle.

            } else tipp.v = null; // Vastasel juhul võtame väiksema ära

            väikseim.x = -1;

            tipp.x = leiaKõrgus(tipp);
            return new Tipp[]{väikseim, tipp};
        }

        tipp.x = leiaKõrgus(tipp);
        return andmed;
    }

    /**
     * AVL puu abifunktsioon, mis määrab tippude abiväljade kaudu kas puu on tasakaalustatud.
     *
     * @param t Puu.
     * @return Tagastab tõeväärtuse.
     */
    public static boolean kasTasakaalustatudPuu(Tipp t) {
        if (t == null) return true;

        return Math.abs(kõrgus(t.v) - kõrgus(t.p)) < 2;
    }

    /**
     * AVL puu meetod, mis tasakaalustab etteantud puu. Kui puu ei vaja tasakaalustamist, siis tagastatakse.
     *
     * @param t Puu, mis on vaja tasakaalustada AVL puu reeglite järgi.
     * @return Tagastab tasakaalustatud puu.
     */
    public static Tipp tasakaalustaPuu(Tipp t) {

        int pööratavaHarud = 0; // Fikseerime, millist pööret tuleb teostada.

        if (kõrgus(t.v) > kõrgus(t.p)) {
            pööratavaHarud = (t.v.p != null && t.v.v != null) ? 0b11 :
                    (t.v.p != null) ? 0b01 :
                            0b10;
        } else {
            pööratavaHarud = -((t.p.p != null && t.p.v != null) ? 0b11 :
                    (t.p.p != null) ? 0b01 :
                            0b10);
        }

        /* SWITCH'i IMPLEMENTATSIOON */

        if (pööratavaHarud == 0b10) t = paremPööre(t);
        else if (pööratavaHarud == 0b11) {
            if (t.v.v.x > t.v.p.x) t = paremPööre(t);
            else t = vasakparemPööre(t); // Esimesel võimalusel pp, teisel vpp
        } else if (pööratavaHarud == 0b01) t = vasakparemPööre(t);

        else if (pööratavaHarud == -0b01) t = vasakPööre(t);
        else if (pööratavaHarud == -0b11) {
            if (t.p.p.x > t.p.v.x) t = vasakPööre(t);
            else t = paremVasakPööre(t);
        } else if (pööratavaHarud == -0b10) t = paremVasakPööre(t);

        // Automaatkontroll ei pidavalt switch'i kannatama.
//        switch (pööratavaHarud) {
//            // Vasak haru
//            case (0b10) -> juur = paremPööre(juur);
//            case (0b11) -> {
//
//                if (juur.v.v.x > juur.v.p.x) juur = paremPööre(juur);
//                else juur = vasakparemPööre(juur); // Esimesel võimalusel pp, teisel vpp
//            }
//            case (0b01) -> juur = vasakparemPööre(juur);
//
//            // Parem haru
//            case (-0b01) -> juur = vasakPööre(juur);
//            case (-0b11) -> {
//
//                if (juur.p.p.x > juur.p.v.x) juur = vasakPööre(juur);
//                else juur = paremVasakPööre(juur);
//            }
//            case (-0b10) -> juur = paremVasakPööre(juur);
//        }

        return t;
    }

    /**
     * AVL puu meetod, mis liidab mingi kindla tipu kaks alamat, st AVL puud kokku.
     *
     * @param avl1 Vasakpoolne alam
     * @param avl2 Parempoolen alam
     * @return Tagastab kokku liidetud puud.
     */
    public static Tipp liidaAVLpuud(Tipp avl1, Tipp avl2) {
        if (avl1 == null && avl2 == null) return null;
        if (avl1 == null) return avl2;
        if (avl2 == null) return avl1;

        // Kasutame parema haru väiksemat elementi uue juurena.
        Tipp[] andmed = leiaJaEemaldaVäikseim(avl2);
        Tipp väikseim = andmed[0];
        Tipp vanem = andmed.length > 1 ? andmed[1] : null;

        if (vanem != null && !kasTasakaalustatudPuu(vanem)) {
            Tipp tasakaalustatudVanem = tasakaalustaPuu(vanem);
            Tipp vaadeldav = avl2;
            if (vanem.equals(vaadeldav)) {
                väikseim.p = tasakaalustatudVanem;
            } else {

                boolean leitud = false;
                while (!leitud) {
                    if (vanem.equals(vaadeldav.v)) {
                        avl2.v = tasakaalustatudVanem;

                    }
                }
                väikseim.p = avl2;
            }

        } else väikseim.p = avl2;

        väikseim.v = avl1;
        väikseim.x = leiaKõrgus(väikseim);
        System.out.println();
        return väikseim;
    }

    /**
     * AVL puu meetod, mis leiab tipu kõrguse vastavalt tema alamate abiväljadele.
     *
     * @param juur AVL puu.
     * @return Tagastab AVL puu kõrguse.
     */
    public static int leiaKõrgus(Tipp juur) {
        return Math.max(juur.v == null ? 0 : kõrgus(juur.v),
                juur.p == null ? 0 : kõrgus(juur.p)) + 1;
    }

    public static void main(String[] args) {


//        int[] j = new int[]{2, 1, 3, 4, 5};
//        int[] k = new int[]{1, 1, 1, 1, 1};

        // Vasakparempööre
        int[] j = new int[]{1, 3, 2};
        int[] k = new int[]{1, 1, 1};
        Tipp t = null;

        for (int i = 0; i < j.length; i++) {
            if (k[i] == 1) {
                t = lisaKirje(t, Integer.toString(j[i]));
            } else {
                t = eemaldaKirje(t, Integer.toString(j[i]));
            }
        }

        Kuvar.kuvaPuu(t);

//        Tipp tipp1 = new Tipp("5");
//        tipp1 = lisaKirje(tipp1, "6");
//        tipp1 = lisaKirje(tipp1, "3");
//        tipp1 = lisaKirje(tipp1, "4");
//        tipp1 = lisaKirje(tipp1, "2");
//        tipp1 = lisaKirje(tipp1, "1");
//        Kuvar.kuvaPuu(tipp1);
    }


}//klass Kodu5

