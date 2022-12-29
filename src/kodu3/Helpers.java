package kodu3;

import java.util.HashMap;
import java.util.Stack;
import java.util.function.DoubleToIntFunction;

public class Helpers {

    public static void displaySolution(Stack<Integer> stack, int n) {
        int size = n;

        Stack<Integer> copy = (Stack<Integer>) stack.clone();
        Stack<Integer> temp = new Stack<Integer>();
        HashMap<Integer, Integer> map = new HashMap<>();
        while (!copy.isEmpty()) {
            int insertRow = copy.size() - 1;
            int insertCol = copy.pop();
            map.put(insertCol, insertRow);
        }

        int insertCol = -1;
        for (
                int row = 0;
                row < size; row++) {
            if (map.containsKey(row)) insertCol = map.get(row);
            for (int column = 0; column < size; column++) {
                if (insertCol == column) {
                    System.out.print("Q ");
                    insertCol = -1;
                } else {
                    System.out.print("* ");
                }
            }
//            if (!temp.isEmpty()){
//
//                col = temp.pop();
//
//                for (int column = 0; column < size; column++) {
//                    if ( col == column)
//                        System.out.print("Q ");
//                    else
//                        System.out.print("* ");
//                }
//            } else {
//
//                for (int j = 0; j < n; j++) {
//
//                    System.out.print("* ");
//                }
//            }


            System.out.println();
        }
        System.out.println();
    }
}
