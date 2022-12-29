package coding_tasks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ATM { /*
 * Complete the 'getFinalOrder' function below.
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts following parameters:
 *  1. INTEGER k
 *  2. INTEGER_ARRAY amount
 */

    public static List<Integer> getFinalOrder(int k, List<Integer> amount) {
        List<Integer> result = new ArrayList<>(); // Optimize to array.

        Queue queue = new LinkedList<Integer>();
        for (int i = 0; i < amount.size(); i++) queue.add(i);

        while (!queue.isEmpty()) {

            int order = (int) queue.poll();
            int withdrawCurrent = amount.get(order);

            if (k - withdrawCurrent < 0) {
                amount.set(order, withdrawCurrent - k);
                queue.add(order);
                continue;
            }

            amount.set(order, 0);
            result.add(order + 1);
        }
        System.out.println();
        return result;
    }

}
