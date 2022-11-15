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

        Tipp tipp = eemaldaKirjeRek(juur, info);
        juur = tipp;

        return juur;
    }

    public static Tipp eemaldaKirjeRek(Tipp juur, String info) {
        int võti = Integer.parseInt(juur.info);
        int eemaldatav = Integer.parseInt(info);
        Tipp tipp = null;

        if (eemaldatav == võti) {
            Tipp liidetud = liidaAVLpuud(juur.v, juur.p);
            /*tipp*/
            juur = liidetud;
//            return tipp;
            System.out.println();
        } else if (eemaldatav < võti) {
            tipp = eemaldaKirjeRek(juur.v, info);
            juur.v = tipp;

        } else {
            tipp = eemaldaKirjeRek(juur.p, info);
            juur.p = tipp;
        }

        // Tasakaalustamine
        if (!kasTasakaalustatudPuu(juur)) {
            juur = tasakaalustaPuu(juur);
        }

        return juur;
    }

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

    public static boolean kasTasakaalustatudPuu(Tipp juur) {
        if (juur == null) return true;
        int kõrgusV = 0;
        int kõrgusP = 0;

        if (juur.p != null) {
            kõrgusP = juur.p.x;
        }

        if (juur.v != null) {
            kõrgusV = juur.v.x;
        }

        return (Math.abs(kõrgusV - kõrgusP) <= 1);
    }

    public static Tipp tasakaalustaPuu(Tipp juur) {

        int kõrgusV = 0;
        int kõrgusP = 0;

        if (juur.p != null) {
            kõrgusP = juur.p.x;
        }

        if (juur.v != null) {
            kõrgusV = juur.v.x;
        }

        int pööratavaHarud = 0; // Fikseerime, millist pööret tuleb teostada.

        if (kõrgusV > kõrgusP) {
            pööratavaHarud = (juur.p != null && juur.v != null) ? 0b11 :
                    (juur.p != null) ? 0b01 :
                            0b10;
        }

        if (kõrgusV < kõrgusP) {
            pööratavaHarud = -((juur.p != null && juur.v != null) ? 0b11 :
                    (juur.p != null) ? 0b01 :
                            0b10);
        }

        switch (pööratavaHarud) {
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

        return juur;
    }

    public static Tipp leiaAlluvKeskJärjestuses(Tipp juur) {

        return juur.p != null ? juur.p :
                juur.v != null ? juur.v :
                        null;
    }

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

        }

        väikseim.v = avl1;
        väikseim.x = leiaKõrgus(väikseim);
        System.out.println();
        return väikseim;
    }

    public static int leiaKõrgus(Tipp juur) {
        return Math.max(juur.v == null ? 0 : kõrgus(juur.v),
                juur.p == null ? 0 : kõrgus(juur.p)) + 1;
    }

    public static void main(String[] args) {

//        Tipp AVLpuu = new Tipp("3")
//        Tipp tipp = new Tipp("2");
//
//        lisaKirje(tipp, "1");
//        lisaKirje(tipp, "3");
//        lisaKirje(tipp, "4");
//        Tipp tipp = new Tipp("1");
//
//        tipp = lisaKirje(tipp, "2");
//        tipp = lisaKirje(tipp, "3");
//        Kuvar.kuvaPuu(tipp);

//        Tipp tipp1 = new Tipp("5");
//        tipp1 = lisaKirje(tipp1, "6");
//        tipp1 = lisaKirje(tipp1, "3");
//        tipp1 = lisaKirje(tipp1, "4");
//        tipp1 = lisaKirje(tipp1, "2");
//        tipp1 = lisaKirje(tipp1, "1");

        Tipp tipp1 = new Tipp("2");
        tipp1 = lisaKirje(tipp1, "1");
        tipp1 = lisaKirje(tipp1, "3");
        tipp1 = lisaKirje(tipp1, "4");
        tipp1 = eemaldaKirje(tipp1, "1");

//        Tipp tipp1 = new Tipp("3");
//        tipp1 = lisaKirje(tipp1, "1");
//        tipp1 = lisaKirje(tipp1, "6");
//        tipp1 = lisaKirje(tipp1, "2");
//        tipp1 = lisaKirje(tipp1, "5");
//        tipp1 = lisaKirje(tipp1, "7");
//        tipp1 = lisaKirje(tipp1, "4");
//        tipp1 = eemaldaKirje(tipp1, "6");


        Kuvar.kuvaPuu(tipp1);
        System.out.println();
//        AbiAVL.kasTasakaalustatud(tipp1);
    }

}//klass Kodu5

class TippInfo {
    Tipp tipp;
    int harudeVektor;
}
