package kodu1;
public class Veerg {
    // Klass on mõeldud andmete hoidmiseks, mida soovitakse hiljem esitada tabelina.
    private String veeruNimi;
    private double[] veeruAndmed;

    public Veerg(String veeruNimi, double[] andmed) {
        this.veeruNimi = veeruNimi;
        this.veeruAndmed = andmed;
    }

    public String getVeeruNimi() {
        return veeruNimi;
    }

    public double[] getVeeruAndmed() {
        return veeruAndmed;
    }
}
