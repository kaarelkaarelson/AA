package paisktabel;

public class Isik {
    int ID;
    String nimi;
    int palk;
    int vanus;

    Isik(int ID, String nimi, int kaal, int vanus) {
        this.ID = ID;
        this.nimi = nimi;
        this.palk = palk;
        this.vanus = vanus;
    }

    public int getID() {
        return ID;
    }

    public String getNimi() {
        return nimi;
    }

    public double getPalk() {
        return palk;
    }

    public double getVanus() {
        return vanus;
    }

    public String toString() {
        return "Isik nr." + ID + " " + nimi;
    }
}
