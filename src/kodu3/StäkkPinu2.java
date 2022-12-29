package kodu3;

import java.util.Stack;

/**
 * Algoritm käib läbi pool malelaua veerge ja arvestab, et iga esimes poole lahend peegeldub ka teises pooles. See
 * Kehtib kui n on paarisarv, paaritu arvu korral peal läbi käima ka keskmise rea.
 */
public class StäkkPinu2 {
    static int samme = 0;
    static int sammeEelmine = 0;


    public static void main(String[] args) {
        System.out.println((solveN_Queen(5)));
    }

    public static int solveN_Queen(int n) {
        if (n == 0) return 0;

        Stack<Integer> stack = new Stack<Integer>();
        int diagonaale = n + (n - 1);
        int keskpunkt = n % 2 == 0 ? n / 2 : n / 2 + 1;
        boolean[] row = new boolean[n];
        boolean[] dv = new boolean[diagonaale];
        boolean[] dp = new boolean[diagonaale];
        int currentRow = 0;
        int currentCol = 0;
        int startRow = 0;
        int counter = 0;

        while (true) {


            boolean isValid = false;
            while (currentRow < n) {
                if (currentCol == 0) {
                    startRow = currentRow;
//                    System.out.println("samme: " + (samme - sammeEelmine));
                    sammeEelmine = samme;
                    if (startRow == keskpunkt) break;
                }
                int dvi = currentRow + currentCol;
                int dpi = currentRow - currentCol + n - 1;
                samme += 1;

                if (!(row[currentRow] || dv[dvi] || dp[dpi])) {
                    stack.push(currentRow);
                    row[currentRow] = true;
                    dv[dvi] = true;
                    dp[dpi] = true;
                    currentRow = 0;
                    currentCol++;
                    isValid = true;
//                    Helpers.displaySolution(stack, n);
                    break;

                } else {

                    currentRow++;
                }
            }

            if (!isValid) {
                if (stack.isEmpty()) {
//                    System.out.println("End");
                    break;
                } else {
                    // We're going back to the last column, lets go to previous state
                    currentRow = stack.pop();
                    currentCol--;
                    int dvi = currentRow + currentCol;
                    int dpi = currentRow - currentCol + n - 1;
                    row[currentRow] = false;
                    dv[dvi] = false;
                    dp[dpi] = false;

                    currentRow++;

//                    if (currentCol == 0) { startRow = currentRow; }
                }
            }
            if (stack.size() == n) {
//                Helpers.displaySolution(stack, n);
                currentRow = stack.pop();
                currentCol--;

                int dvi = currentRow + currentCol;
                int dpi = currentRow - currentCol + n - 1;
                row[currentRow] = false;
                dv[dvi] = false;
                dp[dpi] = false;

                currentRow++;

//                if (currentCol == 0) { startRow = currentRow; }
                counter += startRow + 1 == keskpunkt && n % 2 == 1 ? 1 : 2;  //!(n - keskpunkt == keskpunkt) ? 1 : 2;

            }

        }

//        System.out.println("Samme : " + samme);
        return counter;
    }
}
