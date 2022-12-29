package kodu3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.*;

public class StäkkLõimed {

    public int nLippu(int n) {

        if (n == 0) return 0;


        Stack<Integer> stack = new Stack<Integer>();
        int diagonaale = n + (n - 1);
        int keskpunkt = n % 2 == 0 ? n / 2 : n / 2 + 1;
        boolean[] row = new boolean[n];
        boolean[] dv = new boolean[diagonaale];
        boolean[] dp = new boolean[diagonaale];
        int lahendeid = 0;
        List<Thread> lõimed = new ArrayList<>();

        List<Callable<ReaLõim>> callableList = new ArrayList<Callable<ReaLõim>>();
        FutureTask[] tasks = new FutureTask[keskpunkt];
        int peegeldavaid = n / 2;
        for (int i = 0; i < peegeldavaid; i++) {
            Stack<Integer> magasin = new Stack<>();
            Callable reaLõim = new ReaLõim(n, i, row, dv, dp, magasin);
            tasks[i] = new FutureTask(reaLõim);

            Thread lõim = new Thread(tasks[i]);
            lõim.start();
//            callableList.add(reaLõim);
        }

        if (n % 2 == 1) {
            Stack<Integer> magasin = new Stack<>();
            Callable reaLõim = new ReaLõim(n, keskpunkt - 1, row, dv, dp, magasin);
            tasks[keskpunkt - 1] = new FutureTask(reaLõim);

            Thread lõim = new Thread(tasks[keskpunkt - 1]);
            lõim.start();
        }

        for (int i = 0; i < tasks.length; i++) {
            FutureTask task = tasks[i];

//            System.out.println(task.isDone());
            while (!task.isDone()) ;

            try {
                System.out.println(task.get());

                lahendeid += i + 1 == tasks.length && n % 2 == 1 ? ((int) task.get()) / 2 : (int) task.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
//        for (int i = 0; i < keskpunkt; i++) {
//            try {
//                taskt[i].
//                System.out.println(tasks[i].get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }

//        ExecutorService service = Executors.newFixedThreadPool(keskpunkt);
//        try {
//            List<Future<ReaLõim>> futureObjects = service.invokeAll(callableList);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

///
// j           ReaLõim reaLõim = new ReaLõim(n, i, row, dv, dp);
//            reaLõim.start();
//            lõimed.add(reaLõim);
//        }
//
//        for (int i = 0; i < lõimed.size(); i++) {
//            try {
//                lõimed.get(i).join();
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        for (int i = 0; i < lõimed.size(); i++) {
////            lahendeid += lõimed.get(i).
//            ReaLõim reaLõim = (ReaLõim) lõimed.get(i);
//
//
//
//        }

//        if (n % 2 == 1) {
//            ReaLõim reaLõim = new ReaLõim(n, keskpunkt, row, dv, dp);
//            reaLõim.call();
//            try {
//                reaLõim.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            int vastus = reaLõim.getLahendeid();
//            lahendeid += vastus / 2;
//        }

//        System.out.println("Samme : " +  samme);

        return lahendeid;
    }

    class ReaLõim implements Callable { //extends Thread {

        static int samme = 0;
        static int sammeEelmine = 0;
        private int n;
        private int startRow;
        private boolean[] row;
        private boolean[] dv;
        private boolean[] dp;
        private final Stack<Integer> magasin;
//    private int lahendeid;


        @Override
        public Object call() throws Exception {
//            System.out.println("Lõim " + startRow + " läks tööle");
            int lahendeid = leiaLahendid();
//        System.out.println("Lõim " + startRow + " lõpetas töö");
            return lahendeid;
        }
//    @Override
//    public void run() {
//        System.out.println("Lõim " + startRow + " läks tööle");
//        this.lahendeid = leiaLahendid();
//        System.out.println("Lõim " + startRow + " lõpetas töö");
//    }

        public ReaLõim(int n, int startRow, boolean[] row, boolean[] dv, boolean[] dp, Stack<Integer> magasin) {
            this.n = n;
            this.startRow = startRow;
            this.row = row;
            this.dv = dv;

            this.dp = dp;
            this.magasin = magasin;
        }

//    public int getLahendeid() {
//        return lahendeid;
//    }

        int leiaLahendid() {

//        System.out.println("Alustan tööd ");
            //        for (int i = 0; i < dp.length; i++) {
            //            System.out.println(dp[i]);
//            System.out.println(dv[i]);
//
//        }
            int currentRow = startRow;
            int currentCol = 0;
            int dvi;
            int dpi;
            int lahendeid = 0;

            do {
                boolean isValid = false;

                while (currentRow < n) {
                    if (currentCol == 0) {
//                    System.out.println("samme: " + (samme - sammeEelmine));
                        sammeEelmine = samme;
                        startRow = currentRow;
                        if (currentRow > startRow) break;
                    }

                    dvi = currentRow + currentCol;
                    dpi = currentRow - currentCol + n - 1;
                    samme += 1;

                    if (!(row[currentRow] || dv[dvi] || dp[dpi])) {
                        lisaMagasini(magasin, currentRow, dvi, dpi);


                        currentRow = 0;
                        currentCol++;
                        isValid = true;
                        if (currentCol == n) break;
//                    Helpers.displaySolution(magasin, n);
                        continue;

                    } else {

                        currentRow++;
                    }
                    if (!isValid) {
                        if (magasin.isEmpty()) {
                            break;
                        }
                        // We're going back to the last column, lets go to previous state
                        try {
                            currentRow = magasin.pop();
                        } catch (Exception e) {
                            System.out.println("startRow: " + startRow + " col:" + currentCol + " row: " + currentRow);
                        }
                        currentCol--;
                        dvi = currentRow + currentCol;
                        dpi = currentRow - currentCol + n - 1;

                        row[currentRow] = false;
                        dv[dvi] = false;
                        dp[dpi] = false;

                        currentRow++;

                    } else if (magasin.size() == n) {
//                Helpers.displaySolution(magasin, n);
                        currentRow = magasin.pop();
                        currentCol--;

                        dvi = currentRow + currentCol;
                        dpi = currentRow - currentCol + n - 1;
                        row[currentRow] = false;
                        dv[dvi] = false;
                        dp[dpi] = false;

                        currentRow++;
                        lahendeid += 2;
                    }

//            lahendeid += startRow + 1 == keskpunkt && n % 2 == 1 ? 1 : 2;

                }

            } while (!magasin.isEmpty());

            System.out.println("lõpetasin töö edukalt");

            return lahendeid;
        }

        private void lisaMagasini(Stack<Integer> magasin, int currentRow, int dvi, int dpi) {
            magasin.push(currentRow);
            row[currentRow] = true;
            dv[dvi] = true;
            dp[dpi] = true;
        }

    }
}
