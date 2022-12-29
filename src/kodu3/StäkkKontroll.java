
package kodu3;

import java.util.Stack;

public class StäkkKontroll {

    public static void main(String[] args) {
        solveN_Queen(1);
    }

    public static int solveN_Queen(int n) {
        Stack<Integer> stack = new Stack<Integer>();
        int currentPosition = 0;
        int counter = 0;
        while (true) {
            boolean isValid = false;
            while (currentPosition < n) {
                if (isValid(stack, currentPosition, n)) {
                    stack.push(currentPosition);
                    currentPosition = 0;
                    isValid = true;
                } else {
                    currentPosition++;
                }
            }

            if (!isValid) {
                if (stack.isEmpty()) {
//                    System.out.println("End");
                    break;
                } else {
                    currentPosition = stack.pop();
                    currentPosition++;
                }
            }
            if (stack.size() == n) {
                //displaySolution(stack);
                currentPosition = stack.pop();
                currentPosition++;
                counter++;

            }
        }
//        if (counter > 0) {
//            System.out.println("Number of solutions found: " + counter);
//        } else {
//            System.out.println("No solution found");
//        }

        return counter;
    }

    public static boolean isValid(Stack<Integer> stack, int currentPosition, int n) {
        if (stack.isEmpty()) {
            return true;
        }
        int size = stack.size();
        int[][] arr = new int[n][n];
        int[] colStore = new int[stack.size()];
        int positionRow = n - size - 1;
        int positionCol = currentPosition;
        boolean isValid = true;

        for (int i = 1; i <= size; i++) { // row is i, col is stack.pop()
            int col = stack.pop();
            colStore[i - 1] = col;
            if (col == positionCol) {
                isValid = false;
            }
        }
        for (int i = 0, row = n - size; i < size; i++, row++) {
            arr[row][colStore[i]] = 1;
        }
        for (int i = colStore.length - 1; i >= 0; i--) {
            stack.push(colStore[i]);
        }
        if (!isValid) {
            return false;
        } // return false if same column with previous queen
        for (int i = positionCol - 1, j = positionRow + 1; i >= 0 && j < n; i--, j++) {
            if (arr[j][i] == 1)
                return false;
        }
        // check right diagonal
        for (int i = positionCol + 1, j = positionRow + 1; i < n && j < n; i++, j++) {
            if (arr[j][i] == 1)
                return false;
        }
        return true;
    }

    public static void displaySolution(Stack<Integer> stack) {
        int size = stack.size();
        Stack<Integer> temp = new Stack<Integer>();

        for (int i = 0; i < size; i++) {
            int col;
            col = stack.pop();
            temp.push(col);
            for (int j = 0; j < size; j++) {
                if (j == col)
                    System.out.print("Q ");
                else
                    System.out.print("* ");
            }

            System.out.println();
        }
        while (temp.isEmpty() == false) {
            stack.push(temp.pop());
        }
        System.out.println();
    }
}
