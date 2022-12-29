package kodu5;

import ee.ut.kiho.graaf.Kuvar;
import org.junit.Test;

public class TestAVLPuu {
    public static void main(String[] args) {
        var a = 0b01;
        var b = 0b10;

        var c = 0b11;
        System.out.println(c);
        c = c & a;
        System.out.println((a & b) + " " + (a & c) + " " + c);
    }

    @Test
    public void testVasakPööre() {

//        Tipp tipp = new Tipp("1");
//        tipp = lisaKirje(tipp, "2");
//        tipp = lisaKirje(tipp, "3");
//        Kuvar.kuvaPuu(tipp);

//        Tipp tipp1 = new Tipp("2");
//        tipp1 = lisaKirje(tipp1, "1");
//        tipp1 = lisaKirje(tipp1, "3");
//        tipp1 = lisaKirje(tipp1, "4");
//        tipp1 = lisaKirje(tipp1, "5");

//        Tipp tipp1 = new Tipp("4");
//        tipp1 = lisaKirje(tipp1, "5");
//        tipp1 = lisaKirje(tipp1, "1");
//        tipp1 = lisaKirje(tipp1, "2");
//        tipp1 = lisaKirje(tipp1, "3");

        Tipp tipp1 = new Tipp("2");
//        tipp1 = lisaKirje(tipp1, "1");
//        tipp1 = lisaKirje(tipp1, "4");
//        tipp1 = lisaKirje(tipp1, "3");
//        tipp1 = lisaKirje(tipp1, "5");
//        tipp1 = lisaKirje(tipp1, "6");
        Kuvar.kuvaPuu(tipp1);
        System.out.println();
    }
}
