/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2022/2023 sügissemester
 *
 * Kodutöö. Ülesanne nr 5
 * Teema: AVL Puud
 *
 * Mõningane inspiratsioon: https://www.geeksforgeeks.org/deletion-in-an-avl-tree/
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
     * Kirje lisamise meetod AVL puu jaoks.
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

        juur.x = Math.max(kõrgus(juur.v), kõrgus(juur.p)) + 1;
        vasakpööratudJuur.x = Math.max(kõrgus(vasakpööratudJuur.v), kõrgus(vasakpööratudJuur.p)) + 1;

        return vasakpööratudJuur;
    }

    /**
     * AVL puu pöörde meetod, mis pöörab etteantud puu vasakule.
     *
     * @param juur Puu tipp, mille peal soovitakse pööret sooritada.
     * @return Tagastab pööratud puu.
     */
    public static Tipp vasakPööre(Tipp juur) {

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

        juur.x = Math.max(kõrgus(juur.v), kõrgus(juur.p)) + 1;
        parempööratudJuur.x = Math.max(kõrgus(parempööratudJuur.v), kõrgus(parempööratudJuur.p)) + 1;

        return parempööratudJuur;
    }

    /**
     * AVL puu pöörde meetod, mis pöörab etteantud puu paremale.
     *
     * @param juur Puu tipp, mille peal soovitakse pööret sooritada.
     * @return Tagastab pööratud puu.
     */
    public static Tipp paremPööre(Tipp juur) {

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
    public static Tipp eemaldaKirje(Tipp juur, String info) {
        if (juur == null) return null;

        int eemaldaVõti = Integer.parseInt(info);
        Stack<Tipp> magasin = new Stack<>();
        magasin.add(juur);

        Tipp eemaldatud = null;
        Tipp liidetud = null;

        while (true) {
            Tipp t = magasin.peek();

            if (t == null) {
                magasin.pop();
                break;
            }

            int võti = Integer.parseInt(t.info);

            if (eemaldaVõti == võti) {
                eemaldatud = magasin.pop();

                liidetud = liidaAVLpuud(t.v, t.p);

                // Eemaldame kirje ka magasinist
                break;

            } else if (eemaldaVõti < võti) {
                magasin.push(t.v);
            } else {
                magasin.push(t.p);
            }
        }

        if (eemaldatud == null) return juur;

        Tipp ülem = null;

        while (!magasin.isEmpty()) {
            ülem = magasin.pop();

            // Lisame eemaldatud kirje asemele tema liidetud alluvate kirje;
            if (eemaldatud != null) {
                int võti = Integer.parseInt(eemaldatud.info);
                int ülemaVõti = Integer.parseInt(ülem.info);

                if (võti < ülemaVõti) {
                    ülem.v = liidetud;
                } else {
                    ülem.p = liidetud;
                }

                eemaldatud = null;
                liidetud = null;
            }

            if (liidetud != null) {
                int võti = Integer.parseInt(liidetud.info);
                int ülemaVõti = Integer.parseInt(ülem.info);

                if (võti < ülemaVõti) {
                    ülem.v = liidetud;
                } else {
                    ülem.p = liidetud;
                }

                liidetud = null;
            }

            if (!(kasTasakaalustatudPuu(ülem))) {
                ülem = tasakaalustaPuu(ülem);
                liidetud = ülem;
                continue;
            } else ülem.x = leiaKõrgus(ülem); // Kui puu on tasakaalus, siis määrame uue kõrguse.

        }

        // Kui eemaldasime juurtipu, siis tagastame liidetud puu.
        return ülem == null ? liidetud : ülem;
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

        t.x = leiaKõrgus(t);

        return t;
    }

    /**
     * AVL puu meetod, mis liidab mingi kindla tipu kaks alamat, st AVL puud kokku.
     *
     * @param v Vasakpoolne alam
     * @param p Parempoolne alam
     * @return Tagastab kokku liidetud puud.
     */
    public static Tipp liidaAVLpuud(Tipp v, Tipp p) {
        if (v == null && p == null) return null;
        if (v == null) return p;
        if (p == null) return v;

        // Otsime paremast harust väikseima elemendi

        Tipp väikseim = null;

        Stack<Tipp> magasin = new Stack<>();
        magasin.push(p);

        while (true){
            Tipp t = magasin.peek();

            if (t.v == null) {
                väikseim = t;
                magasin.pop();

                break;
            }

            magasin.push(t.v);
        }

        Tipp uusP = null;

        // Eemaldame väiksema puust
        if (!magasin.isEmpty()) {
            Tipp t = magasin.pop();
            t.v = null;

            t.x = kõrgus(t.p) + 1;

            if (!kasTasakaalustatudPuu(p)){
                uusP = tasakaalustaPuu(p);
            } else {
                uusP = p;
            }
        }

        väikseim.v = v;
        väikseim.p = uusP;


        if (!(kasTasakaalustatudPuu(väikseim))) {
            väikseim = tasakaalustaPuu(väikseim);
        }

        väikseim.x = Math.max(kõrgus(väikseim.v), kõrgus(väikseim.p));

        return väikseim;
    }

    /**
     * AVL puu meetod, mis leiab tipu kõrguse vastavalt tema alamate abiväljadele.
     *
     * @param juur AVL puu.
     * @return Tagastab AVL puu kõrguse.
     */
    public static int leiaKõrgus(Tipp juur) {
        return Math.max( kõrgus(juur.v),
                 kõrgus(juur.p)) + 1;
    }

    public static void main(String[] args) {

        // Eemalda V - F
        int[] j = new int[]{7, 6, 2, 12, 3, 15, 5, 4, 3, 13, 5, 11, 13, 8, 11, 5};
        int[] k = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1};

        Tipp t = null;

        for (int i = 0; i < j.length; i++) {
            if (k[i] == 1) {
                t = lisaKirje(t, Integer.toString(j[i]));
            } else {
                t = eemaldaKirje(t, Integer.toString(j[i]));
            }
        }
        Kuvar.kuvaPuu(t);
    }
}//klass Kodu5

