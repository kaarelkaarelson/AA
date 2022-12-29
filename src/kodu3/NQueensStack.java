package kodu3;

import abi.ÜhikudAbi;

import java.util.Stack;

/**
 * N-Queens Solver
 *
 * @author Julien Schmidt (student4801) <mail at julienschmidt.com>
 * @version 08.12.2012
 */
public class NQueensStack {


    public static void main(String[] args) {
        int n = 2;

        long start, end;
        int solutions;

        // START MEASURING
        start = System.currentTimeMillis();
        solutions = solveNQueens(n);
        end = System.currentTimeMillis();
        // END MEASURING

        // Print result
        System.out.println("" + n + "-Queens: Found " + solutions +
                " Solutions in " + (end - start) + "ms!");
        //System.out.println("" + n + "-Queens: Found " + solutions +
        //  " Solutions in " + (end-start) + "ms! ["+reCount+" executions]");
    }

    /**
     * Calculates number of possible solutions for n queens on n*n sized board
     *
     * @param n queens count
     * @return solutions count
     */
    public static int solveNQueens(int n) {
        int sol = 0;
        int med = n >> 1;       // n/2
        int check = 1 << n;
        int nMask = (1 << n) - 1; // n-long bitmask filled with 1s
        int col;

        // For every distinct solution exists another symmetric solution
        // mirrored around the Y-axis (middle).
        // So it is still possible to get all solutions by calculating only
        // combinations with the first queen placed on the left half.
        for (int y = 0; y < med; y++) {
            col = 1 << y; // set bit on index y

            // Count solutions twice (solution + mirrored solution).
            // col >>> 1 : shift col values 1 index to right and discards
            //      remainder (unsigned right shift).
            // col << 1  : shift col values 1 index to left.
            sol += (findPos(nMask, col, col >>> 1, col << 1) << 1);
        }

        // If n is odd, the middle column must be treaded separately, because
        // no mirrored solution exists
        if (1 == ((n) & 1)) {   // (n) & 1 <=> n%2, 1 if n is odd
            col = 1 << med;     // set bit on index med

            // col >>> 1 : shift col values 1 index to right and discards
            //      remainder (unsigendes right shift).
            // col << 1  : shift col values 1 index to left.
            sol += findPos(nMask, col, col >>> 1, col << 1);
        }

        return sol;
    }

    /**
     * Recursive method to find all possible places for the next queen
     *
     * @param nMask bitmask filled with 1s and n-length
     * @param bmRow bitmask for rows with existing queens
     * @param bmDL  bitmask for diagonals from (top) left with existing queens
     * @param bmDR  bitmask for diagonals from (top) right with existing queens
     * @return number of solutions found
     */
    private static int findPos(int nMask, int bmRow, int bmDL, int bmDR) {
        //reCount++;
        Stack<Integer> stack = new Stack<>();
        int sol = 0;
        int y, newBmRow;

        // bitmask with 1s on index of y-positions where placing a queen is
        // possible
        int check1 = (bmRow | bmDL | bmDR);
//        ÜhikudAbi.printIntegerToBinary(check1);
        int check2 = ~(bmRow | bmDL | bmDR);
//        ÜhikudAbi.printIntegerToBinary(check2);

        System.out.println("xMask: ");
        ÜhikudAbi.printIntegerToBinary(bmRow);

        int yMask = nMask & ~(bmRow | bmDL | bmDR);
        System.out.println("yMask: ");
        ÜhikudAbi.printIntegerToBinary(yMask);
        // Branch all possible y-positions
        while (yMask > 0) {



            int check3 = -yMask;
//            ÜhikudAbi.printIntegerToBinary(check3);
            y = -yMask & yMask;   // Y-position
           stack.push(y);
            System.out.println("yPosition");
            ÜhikudAbi.printIntegerToBinary(y);
            newBmRow = bmRow | y;        // new row-bitmask
            System.out.println("newXMask: ");
            ÜhikudAbi.printIntegerToBinary(newBmRow);

            // Remove this Y-position from the yMask for the next run
            yMask ^= y;

            // Check if there are more queens to be placed
            if (newBmRow < nMask) {
                // More queens must be placed, make a recursive call for the
                // next queen
                sol += findPos(
                        nMask,
                        newBmRow,
                        (bmDL | y) >>> 1,
                        (bmDR | y) << 1
                );
//                continue;

            } else {
                // No more queens, increment solutions count
                sol++;
            }
            stack.pop();
            System.out.println("---------------------------");
        }
        return sol;
    }
}
