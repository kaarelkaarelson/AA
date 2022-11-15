package paisktabel;

public class AlgodeAhel {
    private final int väärtus;
    private AlgodeAhel järgmine;

    public AlgodeAhel(int väärtus) {
        this.väärtus = väärtus;
    }

    public AlgodeAhel(int väärtus, AlgodeAhel järgmine) {
        this.väärtus = väärtus;
        this.järgmine = järgmine;
    }

    public int getVäärtus() {
        return väärtus;
    }

    public AlgodeAhel getJärgmine() {
        return järgmine;
    }

    public void setJärgmine(AlgodeAhel järgmine) {
        this.järgmine = järgmine;
    }

    // Tavalise klassi saab genereerida recordi abiga.
//    private record Isik(int ID);
}
