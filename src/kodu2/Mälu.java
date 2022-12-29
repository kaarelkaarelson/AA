package kodu2;

import java.util.HashMap;

public class Mälu {
    int PIKKUS = Integer.MAX_VALUE / 10;

    public int i;
    public String[] võtmed;
    public int[] lahendid;

    public Mälu(int i, String[] võtmed, int[] lahendid) {
        this.i = i;
        this.võtmed = võtmed;
        this.lahendid = lahendid;
    }

    public String[] getVõtmed() {
        return võtmed;
    }

    public int[] getLahendid() {
        return lahendid;
    }
}
