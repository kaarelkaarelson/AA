/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2022/2023 sügissemester
 *
 * Kodutöö. Ülesanne nr 4
 * Teema: Paisktabel
 *
 * Autor: Kaarel-Richard Kaarelson
 *
 *****************************************************************************/
package kodu4;
import java.util.Calendar;


public class Kodu4 {

    /**
     * Genereerib isikukoodi lähtudes reeglitest püstitatud <a href=https://et.wikipedia.org/wiki/Isikukood>siin.</a>
     * <br>
     * Numbrite tähendused:
     * <ul style="list-style-type:none">
     *      <li> 1 - sugu ja sünniaasta esimesed kaks numbrit, (1...8) </li>
     *      <li> 2-3 - sünniaasta 3. ja 4. numbrid, (00...99) </li>
     *      <li> 4-5 - sünnikuu, (01...12) </li>
     *      <li> 6-7 - sünnikuupäev (01...31) </li>
     *      <li> 8-10 - järjekorranumber samal päeval sündinute eristamiseks (000...999) </li>
     *      <li> 11 - kontrollnumber (0...9) </li> </ul>
     *
     * @return Eesti isikukoodi reeglitele vastav isikukood
     */
    public static long genereeriIsikukood() {
        java.util.concurrent.ThreadLocalRandom juhus = java.util.concurrent.ThreadLocalRandom.current();
        Calendar kalender = new java.util.GregorianCalendar();
        kalender.setTime(new java.util.Date(juhus.nextLong(-5364669600000L, 7258024800000L)));
        long kood = ((kalender.get(Calendar.YEAR) - 1700) / 100 * 2 - juhus.nextInt(2)) * (long) Math.pow(10, 9) +
                kalender.get(Calendar.YEAR) % 100 * (long) Math.pow(10, 7) +
                (kalender.get(Calendar.MONTH) + 1) * (long) Math.pow(10, 5) +
                kalender.get(Calendar.DAY_OF_MONTH) * (long) Math.pow(10, 3) +
                juhus.nextLong(1000);
        int korrutisteSumma = 0;
        int[] IAstmeKaalud = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        for (int i = 0; i < 10; i++) korrutisteSumma += kood / (long) Math.pow(10, i) % 10 * IAstmeKaalud[9 - i];
        int kontroll = korrutisteSumma % 11;
        if (kontroll == 10) {
            korrutisteSumma = 0;
            int[] IIAstmeKaalud = {3, 4, 5, 6, 7, 8, 9, 1, 2, 3};
            for (int i = 0; i < 10; i++) korrutisteSumma += kood / (long) Math.pow(10, i) % 10 * IIAstmeKaalud[9 - i];
            kontroll = korrutisteSumma % 11;
            kontroll = kontroll < 10 ? kontroll : 0;
        }
        return kood * 10 + kontroll;
    }

    /**
     * Sorteerib isikukoodid sünniaja järgi:
     * <ul style="list-style-type:none">
     *     <li>a) järjestuse aluseks on sünniaeg, vanemad inimesed on eespool;</li>
     *     <li>b) kui sünniajad on võrdsed, määrab järjestuse isikukoodi järjekorranumber (kohad 8-10);</li>
     *     <li>c) kui ka järjekorranumber on võrdne, siis määrab järjestuse esimene number.</li>
     * </ul>
     *
     * @param isikukoodid sorteeritav isikukoodide massiiv
     */
    public static void sort(long[] isikukoodid) {
        Positsioonimeetod.sorteeriIsikukoodid(isikukoodid);
    }

    public static void main(String[] args) {
    }
}

