package coding_tasks;

import java.util.*;

public class ATMOptimized { /*
 * Complete the 'getFinalOrder' function below.
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts following parameters:
 *  1. INTEGER k
 *  2. INTEGER_ARRAY amount
 */

    public static List<Integer> getFinalOrder(int k, List<Integer> amount) {
        List<Integer> result = new ArrayList<>();
        int amountLength = amount.size();

        // Saving position indexes to a queue.
        ArrayDeque arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < amountLength; i++) {
            arrayDeque.add(i);
        }

        while (!arrayDeque.isEmpty()) {

            int order = (int) arrayDeque.poll();
            int withdrawCurrent = amount.get(order);

            if (k - withdrawCurrent < 0) { // Check if the person needs to go to the back of the line
                amount.set(order, withdrawCurrent - k);
                arrayDeque.add(order);
                continue;
            }

            amount.set(order, 0); // if the person got their desired withdrawal then save order nr.
            result.add(order + 1);
        }

        return result;
    }

}
