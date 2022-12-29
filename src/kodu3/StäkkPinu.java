package kodu3;

import java.util.Stack;

public class StäkkPinu {
    static int samme = 0;
    static int sammeEelmine = 0;

    public static int solveN_Queen(int n) {
        if (n == 0) return 0;

        Stack<Integer> stack = new Stack<Integer>();
        int diagonaale = n + (n - 1);
        boolean[] row = new boolean[n];
        boolean[] dv = new boolean[diagonaale];
        boolean[] dp = new boolean[diagonaale];
        int currentRow = 0;
        int currentCol = 0;
        int counter = 0;

        while (true) {
            boolean isValid = false;
            while (currentRow < n) {

                if (currentCol == 0) {
                    System.out.println("samme: " + (samme - sammeEelmine));
                    sammeEelmine = samme;
                }
                samme++;
                int dvi = currentRow + currentCol;
                int dpi = currentRow - currentCol + n - 1;

                if (!(row[currentRow] || dv[dvi] || dp[dpi])) {
                    stack.push(currentRow);
                    row[currentRow] = true;
                    dv[dvi] = true;
                    dp[dpi] = true;
                    currentRow = 0;
                    currentCol++;
                    isValid = true;
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
                }
            }
            if (stack.size() == n) {
//                StäkkKontroll.displaySolution(stack);
                currentRow = stack.pop();
                currentCol--;

                int dvi = currentRow + currentCol;
                int dpi = currentRow - currentCol + n - 1;
                row[currentRow] = false;
                dv[dvi] = false;
                dp[dpi] = false;

                currentRow++;
                counter++;

            }
        }
        System.out.println("Samme: " + samme);
        return counter;
    }
}