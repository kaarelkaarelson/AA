package kodu5;

import ee.ut.kiho.graaf.Kuvar;


class Kodu5 {

    public static Tipp lisaKirje(Tipp juur, String info) {

        int harudeVektor = 0b0011; // kahe bitine bitivektor harude salvestamiseks: 1 -> haru ei eksisteerib, 0 - haru eksisteerib

        TippInfo tippInfo = lisaKirjeRek(juur, info, harudeVektor);
        juur = tippInfo.tipp;

        return juur;
    }

    public static TippInfo lisaKirjeRek(Tipp juur, String info, int harudeVektor) {

        int võti = Integer.parseInt(juur.info);
        int uusVõti = Integer.parseInt(info);
        TippInfo tippInfo = null; // alama haru jaoks üles tulles;

        /* LISAMINE */
        if (uusVõti < võti) {
            if (juur.v == null) {
                Tipp uus = new Tipp(info, null, null);
                uus.x = 1; // lisame uuele tipule kõrguse;

                juur.v = uus;

            } else {
                tippInfo = lisaKirjeRek(juur.v, info, harudeVektor);
                tippInfo.harudeVektor = tippInfo.harudeVektor;
                juur.v = tippInfo.tipp;
            }

        } else { // Suurem/võrdne
            if (juur.p == null) {
                Tipp uus = new Tipp(info, null, null);
                uus.x = 1; // lisame uuele tipule kõrguse;

                juur.p = uus;

            } else {
                tippInfo = lisaKirjeRek(juur.p, info, harudeVektor);
                tippInfo.harudeVektor = -tippInfo.harudeVektor; // Parem haru - märgiga.
                juur.p = tippInfo.tipp;

            }
        }

        /* TASAKAALUSTAMINE */
        int kõrgusV = 0;
        int kõrgusP = 0;
        int vektorHarud = 0b00;

        if (juur.p != null) {
            kõrgusP = juur.p.x;
        }

        if (juur.v != null) {
            kõrgusV = juur.v.x;
        }

        if (!(Math.abs(kõrgusV - kõrgusP) <= 1)) {
            System.out.println("Puu ei ole tasakaalus");
            int eelmiseharud = tippInfo.harudeVektor;

            switch (eelmiseharud) {
                // Vasak haru
                case (0b10) -> juur = paremPööre(juur);
                case (0b11) -> {

                    if (juur.v.v.x > juur.v.p.x) juur = paremPööre(juur);
                    else juur = vasakparemPööre(juur); // Esimesel võimalusel pp, teisel vpp
                }
                case (0b01) -> juur = vasakparemPööre(juur);

                // Parem haru
                case (-0b01) -> juur = vasakPööre(juur);
                case (-0b11) -> {

                    if (juur.p.p.x > juur.p.v.x) juur = vasakPööre(juur);
                    else juur = paremVasakPööre(juur);
                }
                case (-0b10) -> juur = paremVasakPööre(juur);
            }
        } else juur.x = Math.max(kõrgusV, kõrgusP) + 1; // Kui puu on tasakaalus, siis määrame uue kõrguse.

        if (juur.p != null) {
            vektorHarud = vektorHarud | 0b01;
        }

        if (juur.v != null) {
            vektorHarud = vektorHarud | 0b10;
        }

        TippInfo tippInfoUus = new TippInfo();
        tippInfoUus.harudeVektor = vektorHarud;
        tippInfoUus.tipp = juur;

        return tippInfoUus;
    }

    public static Tipp paremVasakPööre(Tipp juur) {
        Tipp parem = juur.p;
        Tipp pööratudParem = paremPööre(parem);
        juur.p = pööratudParem;

        Tipp vasakpööratudJuur = vasakPööre(juur);

        return vasakpööratudJuur;
    }

    public static Tipp vasakPööre(Tipp juur) { // OK

        Tipp parem = juur.p;
        Tipp paremV = parem.v;

        parem.v = juur;
        juur.p = paremV;

        // Fikseerime uued kõrgused vahetatud tippudele.
        juur.x = Math.max(juur.v == null ? 0 : kõrgus(juur.v),
                juur.p == null ? 0 : kõrgus(juur.p)) + 1;

        parem.x = Math.max(parem.v == null ? 0 : kõrgus(parem.v),
                parem.p == null ? 0 : kõrgus(parem.p)) + 1;

        return parem;
    }


    public static Tipp vasakparemPööre(Tipp juur) {
        Tipp vasak = juur.v;
        Tipp pööratudVasak = vasakPööre(vasak);
        juur.v = pööratudVasak;

        Tipp parempööratudJuur = paremPööre(juur);

        return parempööratudJuur;
    }

    public static Tipp paremPööre(Tipp juur) { // OK

        Tipp vasak = juur.v;
        Tipp vasakP = vasak.p;

        vasak.p = juur;
        juur.v = vasakP;

        // Fikseerime uued kõrgused vahetatud tippudele.
        juur.x = Math.max(juur.v == null ? 0 : kõrgus(juur.v),
                juur.p == null ? 0 : kõrgus(juur.p)) + 1;

        vasak.x = Math.max(vasak.v == null ? 0 : kõrgus(vasak.v),
                vasak.p == null ? 0 : kõrgus(vasak.p)) + 1;

        return vasak;
    }

    public static int kõrgus(Tipp tipp) {

        return tipp.x;
    }

    public static Tipp eemaldaKirje(Tipp juur, String info) { // Peaks kohe algul kontrollima kas tegu on tipuga, mis tuleb eemaldada.
        int juurX = Integer.parseInt(juur.v.info);
        int eemaldatav = Integer.parseInt(info);

        if (eemaldatav == juurX) {
            return null;
        }
        return null;
    }

    public static Tipp eemaldaKirjeRek(Tipp juur, String info) { // Peaks kohe algul kontrollima kas tegu on tipuga, mis tuleb eemaldada.
        int vasak = Integer.parseInt(juur.v.info);
        int parem = Integer.parseInt(juur.p.info);
        int eemaldatav = Integer.parseInt(info);

        if (eemaldatav <= vasak) {
            if (eemaldatav != vasak) {
                return eemaldaKirje(juur.v, info);
            } else {// peame alumised üles tooma.
                juur.v = null;
                return null;
            }

        }

        if (eemaldatav >= parem) {
            if (eemaldatav != parem) {
                return eemaldaKirjeRek(juur.p, info);
            } else {// peame alumised üles tooma.
                juur.p = null;
                return null;
            }

        }

        return null;
    }

    public static Tipp liidaAVLpuud(Tipp avl1, Tipp avl2) {
        return null;
    }

    public static void main(String[] args) {

//        Tipp AVLpuu = new Tipp("3")
//        Tipp tipp = new Tipp("3");
//
//        lisaKirje(tipp, "2");
//        lisaKirje(tipp, "1");
//        lisaKirje(tipp, "4");
//        Tipp tipp = new Tipp("1");
//
//        tipp = lisaKirje(tipp, "2");
//        tipp = lisaKirje(tipp, "3");
//        Kuvar.kuvaPuu(tipp);

        Tipp tipp1 = new Tipp("5");
        tipp1 = lisaKirje(tipp1, "6");
        tipp1 = lisaKirje(tipp1, "3");
        tipp1 = lisaKirje(tipp1, "4");
        tipp1 = lisaKirje(tipp1, "2");
        tipp1 = lisaKirje(tipp1, "1");

//        Tipp tipp1 = new Tipp("4");
//        tipp1 = lisaKirje(tipp1, "5");
//        tipp1 = lisaKirje(tipp1, "1");
//        tipp1 = lisaKirje(tipp1, "2");
//        tipp1 = lisaKirje(tipp1, "3");

//        Tipp tipp1 = new Tipp("5");
//        tipp1 = lisaKirje(tipp1, "6");
//        tipp1 = lisaKirje(tipp1, "2");
//        tipp1 = lisaKirje(tipp1, "1");
//        tipp1 = lisaKirje(tipp1, "4");
//        tipp1 = lisaKirje(tipp1, "3");


        Kuvar.kuvaPuu(tipp1);
        System.out.println();
//        AbiAVL.kasTasakaalustatud(tipp1);
    }

}//klass Kodu5

class TippInfo {
    Tipp tipp;
    int harudeVektor;
}
