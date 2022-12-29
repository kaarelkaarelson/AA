/*
package Kodu3;
*/

public class Kodu3cDev {


    public static void main(String[] args) {

        lipudMalelaual(8);
    }//peameetod

    public static long lipudMalelaual(int n) {
        //n - malelaud mõõtmetega n*n
        int board[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            int row[] = new int[n];
            board[i] = row;

        }

        if (!solveNQueen(board, 0)) {
            System.out.print("Solution does not exist");
        }

        printSolution(board, n);
        // rekursiooni lahenduses kasutada ei tohi
        return 0;
    }

    // print the final solution matrix
    static void printSolution(int board[][], int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(" " + board[i][j]
                        + " ");
            System.out.println();
        }
    }

    // function to check whether the position is safe or not
    static boolean isSafe(int board[][], int row, int col) {
        int i, j;
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;


        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (i = row, j = col; j >= 0 && i < board.length; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    // The function that solves the problem using backtracking
    public static boolean solveNQueen(int board[][], int col) {
        if (col >= board.length)
            return true;

        for (int i = 0; i < board.length; i++) {
            //if it is safe to place the queen at position i,col -> place it
            if (isSafe(board, i, col)) {
                board[i][col] = 1;

                if (solveNQueen(board, col + 1))
                    return true;

                //backtrack if the above condition is false
                board[i][col] = 0;
            }
        }
        return false;
    }


}
