package paisktabel;

public class Praks8 {
    public static void main(String[] args) {
        KimpPaiskTabel paiskTabel = new KimpPaiskTabel(10);
        paiskTabel.lisa(new Isik(2, "Märt", 80, 30));
        paiskTabel.lisa(new Isik(3, "Märt1", 80, 30));
        paiskTabel.lisa(new Isik(4, "Märt2", 80, 30));
        paiskTabel.lisa(new Isik(5, "Märt3", 80, 30));

        System.out.println();
        paiskTabel.eemalda(3);
        paiskTabel.eemalda(5);
        System.out.println();
    }

}
