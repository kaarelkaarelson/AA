package paisktabel;

public class Ahel{
    private final int väärtus;
    private Ahel järgmine;

    public Ahel(int väärtus) {
        this.väärtus = väärtus;
    }

    public Ahel(int väärtus, Ahel järgmine) {
        this.väärtus = väärtus;
        this.järgmine = järgmine;
    }

    public int getVäärtus() {
        return väärtus;
    }

    public Ahel getJärgmine() {
        return järgmine;
    }

    public void setJärgmine(Ahel järgmine) {
        this.järgmine = järgmine;
    }

    // Tavalise klassi saab genereerida recordi abiga.
//    private record Isik(int ID);
}
