package kodu2;

import java.util.HashMap;

public class Tase {
    public int tase;
    public HashMap<String, Integer> map; // võti: jääk ja väärtus: lahendid

    public Tase(int tase, HashMap<String, Integer> map) {
        this.tase = tase;
        this.map = map;
    }

    public int getTase() {
        return tase;
    }

    public HashMap<String, Integer> getMap() {
        return map;
    }
}
