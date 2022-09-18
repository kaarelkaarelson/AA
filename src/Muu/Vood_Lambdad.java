package Muu;

import java.util.List;

public class Vood_Lambdad {
    public static void main(String[] args) {


        List.of(10000000, 2000000, 3000000).forEach(sisend -> {
            long start = System.nanoTime();
//            pistemeetod.sorteeri(j);
            long stopp = System.nanoTime();
            System.out.println(stopp - start);

        });
    }
}
