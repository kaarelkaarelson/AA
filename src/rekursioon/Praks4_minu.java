package rekursioon;

import java.util.ArrayList;
import java.util.List;

public class Praks4 {

    public static List<Integer> yl4(int[] hinnad) {
        List<Integer> võimalused = yl4(hinnad, 0, 0);

        return võimalused.stream().sorted().distinct().toList();
    }

    private static List<Integer> yl4(int[] hinnad, int summa, int i) {
        if ( i == hinnad.length) {
            return  List.of(summa);
        }
        List<Integer> list = new ArrayList<>();
        list.addAll(yl4(hinnad, summa + hinnad[i], i+1));

        return null;
    }

}
