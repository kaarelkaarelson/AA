package abi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ÜhikudAbi {

    public double ümarda(double arv, int komakohti) {
        double kordaja = Math.pow(10, komakohti);

        return Math.round(arv * kordaja) / kordaja;
    }

    /**
     * Teisendab nanosekundid millisekunditeks, koos komakohaga.
     *
     * @param nanoTime - etteantud aeg nanosekundites.
     * @return - tagastab aja millisekundites kahe komakoha täpsusega.
     */
    public static double nanoToMilliSeconds(long nanoTime) {
        return (double) Math.round((nanoTime / 1_000_000.0) * 100) / 100;

    }

    public double juhuArv(int min, int max, int komakohti) {
        Random random = new Random();
        return ümarda(min + (max - min) * random.nextDouble(), komakohti);
    }

    public static void printIntegerToBinary(int integer) {
        int input = integer;
        String result = Integer.toBinaryString(input);
        String resultWithPadding = String.format("%32s", result).replaceAll(" ", "0");
//        System.out.println(resultWithPadding);
        System.out.println(printBinary(resultWithPadding, 4, " | "));   // 00000000 | 00000000 | 00000000 | 00001010
//        System.out.println(printBinary(resultWithPadding, 4, " "));
    }

    private static String printBinary(String binary, int blockSize, String separator) {

        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < binary.length()) {
            result.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
            index += blockSize;
        }

        return result.stream().collect(Collectors.joining(separator));
    }
}
