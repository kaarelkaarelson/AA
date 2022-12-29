/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2022/2023 sügissemester
 *
 * Kodutöö. Ülesanne nr 3c
 * Teema: Magasin ja järjekord
 *
 * Autor: Kaarel-Richard Kaarelson
 *
 * Mõningane eeskuju: vt
 *  1. https://lykoh.wordpress.com/2020/04/22/java-n-queen-problem-using-stack-implementation/
 *
 *****************************************************************************/
package kodu3;
import java.util.Stack;

class Kodu3c {

    /**
     * Meetod leiab kõigi lahendite arvu malelaual.
     * @param n etteatnud järjend
     * @return lahendite arv
     */
    public static long lipudMalelaual(int n) {
        if (n == 0) return 1;

        return nLipuProbleem(n);
    }

    /**
     * Meetod leiab lahendite arvu klassikalisele n lippu probleemile. Algoritm kasutab tagurdamist, et vältida
     * lootusetutesse harudesse minemist. Samuti kasutatakse NxN malelaua nn peegelduse omadust, mille kohaselt
     * läbitakse parimal juhul ainult pool malelauast.
     *
     * @param n - etteantud lippude arv.
     * @return tagastab kõikvõimalikud lipu paigutamise arvu NxN malelaual.
     */
    public static int nLipuProbleem(int n) {

        Stack<Integer> magasin = new Stack<Integer>();
        int diagonaale = n + (n - 1);
        int keskpunkt = n % 2 == 0 ? n / 2 : n / 2 + 1;
        boolean[] rida = new boolean[n];
        boolean[] dv = new boolean[diagonaale]; // Vasaku diagonaalide (vasakult ülevalt) kaardistamiseks
        boolean[] dp = new boolean[diagonaale]; // Parema diagonaalide (paremalt ülevalt) kaaristamiseks

        // tsükli muutujad
        int praeguneRida = 0;
        int praeguneVeerg = 0;
        int startRida = 0;
        int lahendeid = 0;
        int dvi; // vasaku diagonaali indeks
        int dpi; // parema diagonaali indeks

        while (true) {

            boolean isValid = false;
            while (praeguneRida < n) {

                if (praeguneVeerg == 0) {
                    startRida = praeguneRida;
                    if (startRida == keskpunkt) break; // Kui jõuame keskpunkt, siis lõpetame töö.
                }

                // Arvutame praeguse positsiooni diagonaalide indeksid.
                dvi = praeguneRida + praeguneVeerg;
                dpi = praeguneRida - praeguneVeerg + n - 1;

                // Kontrollime, kas on võimalik sellele positsioonile lippu lisada.
                if (!(rida[praeguneRida] || dv[dvi] || dp[dpi])) {
                    magasin.push(praeguneRida);
                    rida[praeguneRida] = true;
                    dv[dvi] = true;
                    dp[dpi] = true;

                    praeguneRida = 0;
                    praeguneVeerg++;
                    isValid = true;

                    break;

                } else praeguneRida++;
            }

            if (!isValid) {
                if (magasin.isEmpty()) {
                    break;
                } else {
                    // Tagurdame tagasi, et minna uude harusse.
                    praeguneRida = magasin.pop();
                    praeguneVeerg--;

                    dvi = praeguneRida + praeguneVeerg;
                    dpi = praeguneRida - praeguneVeerg + n - 1;
                    rida[praeguneRida] = false;
                    dv[dvi] = false;
                    dp[dpi] = false;

                    praeguneRida++;

                }
                // Kontrollime kas tegu on lahendiga.
            } else if (magasin.size() == n) {
                praeguneRida = magasin.pop();
                praeguneVeerg--;

                dvi = praeguneRida + praeguneVeerg;
                dpi = praeguneRida - praeguneVeerg + n - 1;
                rida[praeguneRida] = false;
                dv[dvi] = false;
                dp[dpi] = false;

                praeguneRida++;

                lahendeid += startRida + 1 == keskpunkt && n % 2 == 1 ? 1 : 2;
            }
        }

        return lahendeid;
    }

    public static void main(String[] args) {
        long vastus = lipudMalelaual(1);
        System.out.println((vastus));
    }//peameetod

}//Kodu3c
