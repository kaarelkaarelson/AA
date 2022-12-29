package coding_tasks;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestResult {
    @Test
    public void TestAnagram() {

        var w1 = new String[]{"duel", "speed", "dule", "cars"};
        var q1 = new String[]{"spede", "deul"};

        var result = Result.getSearchResults(List.of(w1), List.of(q1));
        System.out.println();
    }

    @Test
    public void TestATM() {

        var amount1 = Arrays.asList(2, 5, 1);
        var k1 = 2;

        var result = ATMArray.getFinalOrder(k1, amount1);
    System.out.println();
    }
}
